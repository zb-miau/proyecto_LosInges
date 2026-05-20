/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionAsistencias;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTORegistroMarca;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.HorarioEmpleadoBO;
import objetosNegocio.NegocioException;
import objetosNegocio.RegistroMarcaBO;

/**
 * Clase de control encargada de orquestar el flujo de trabajo del subsistema de
 * asistencias. Actúa como un controlador de casos de uso que coordina las
 * interacciones entre los distintos objetos de negocio (BO) para realizar
 * operaciones complejas como el registro validado de marcas y la generación de
 * reportes.
 *
 * @author josma
 */
public class ControlGestionAsistencias {

    private RegistroMarcaBO registroMarcaBO;
    private HorarioEmpleadoBO horarioEmpleadoBO;
    private EmpleadoBO empleadoBO;

    /**
     * Constructor que inicializa las instancias de los objetos de negocio
     * necesarios mediante sus respectivos métodos Singleton.
     */
    public ControlGestionAsistencias() {
        this.registroMarcaBO = RegistroMarcaBO.getInstance();
        this.horarioEmpleadoBO = HorarioEmpleadoBO.getInstance();
        this.empleadoBO = EmpleadoBO.getInstance();
    }

    /**
     * Registra una nueva marca de entrada o actualiza una existente con la hora
     * de salida. El flujo de validación incluye: 1. Verificar la existencia del
     * horario del empleado. 2. Validar que el día actual sea un día laborable
     * para el empleado. 3. Si no hay marca previa: validar que la hora esté
     * dentro del rango permitido e insertar entrada. 4. Si hay marca previa:
     * validar que no exista ya una salida y registrar la salida actual.
     *
     * @param registroDTO DTO con la información básica del empleado para
     * procesar la marca.
     * @return El DTORegistroMarca resultante tras la operación de persistencia.
     * @throws NegocioException Si el empleado no tiene horario, si no es día
     * laborable, si está fuera de rango horario o si ya cuenta con una salida
     * registrada.
     */
    protected  synchronized DTORegistroMarca agregarMarca(DTORegistroMarca registroDTO) throws NegocioException {
        LocalDate fechaHoy = LocalDate.now();
        LocalTime tiempoHoy = LocalTime.now();

        //1. Validar si al empleado le toca laborar ese dia  
        //obtener el horario
        DTOHorarioEmpleado horarioHoy = empleadoBO.obtener(registroDTO.getEmpleadoDTO()).getHorarioActual();
        //Obtener el empleado
        DTOEmpleado empleado = empleadoBO.obtener(registroDTO.getEmpleadoDTO());
        if (horarioHoy == null) {
            throw new NegocioException("El empleado no tiene un horario asignado.");
        }
        //Validar si al empleado le toca trabajar hoy
        Set<DayOfWeek> diasTrabajo = horarioHoy.getTurno().getDiasTrabajo();
        //Corroborar que si se encuentre ese día en el arreglo
        if (!diasTrabajo.contains(fechaHoy.getDayOfWeek())) {
            throw new NegocioException("Hoy no es un día laborable para el empleado.");
        }

        //2. Validar si existe una marca previa
        System.out.println("DEBUG: Buscando marca para empleado: " + empleado.getId() + " en fecha: " + fechaHoy);
        DTORegistroMarca marcaExistente = registroMarcaBO.obtenerPorEmpleadoYFecha(empleado.getId(), fechaHoy);
        //Si no hay una marca previa
        if (marcaExistente == null) {
            //Validar que este intentando registrar dentro del rango del horario
            if (tiempoHoy.isBefore(horarioHoy.getTurno().getHoraInicio())
                    || tiempoHoy.isAfter(horarioHoy.getTurno().getHoraFin())) {
                //Si la hora en que esta intentando registrar es antes del inicio  despues del fin, NO puede registrar
                throw new NegocioException("No puedes es posible registrar entrada fuera de la hora de trabajo");
            }
            //seteamos todos los valores 
            DTORegistroMarca dtoNuevoRegistro = new DTORegistroMarca();
            dtoNuevoRegistro.setEmpleadoDTO(empleado);
            dtoNuevoRegistro.setHorarioEmpledoDTO(horarioHoy);
            dtoNuevoRegistro.setEntrada(tiempoHoy);
            dtoNuevoRegistro.setFecha(fechaHoy);
            return registroMarcaBO.crear(dtoNuevoRegistro);
        } else {//SI YA TIENE UNA ENTRADA, MARCA LA SALIDA
            if (marcaExistente.getSalida() != null) {
                
                throw new NegocioException("ERROR DEPURACIÓN: La salida ya existe y es: " + marcaExistente.getSalida() + 
                               " para el registro ID: " + marcaExistente.getIdRegistroMarca());
            } else {
                marcaExistente.setSalida(tiempoHoy);
                return registroMarcaBO.modificar(marcaExistente);
            }

        }
    }

    /**
     * Genera una lista de asistencias filtrada por un rango de fechas para un
     * empleado específico.
     *
     * @param idEmpleado Identificador único del empleado.
     * @param fechaInicio Límite inferior del rango de fechas.
     * @param fechaFin Límite superior del rango de fechas.
     * @return Lista de DTORegistroMarca encontrados en el periodo.
     * @throws NegocioException Si la fecha de fin es anterior a la fecha de
     * inicio.
     */
    protected List<DTORegistroMarca> reporteAsistencia(String idEmpleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        //Validación de fechas coherente
        if (fechaFin.isBefore(fechaInicio) || fechaInicio.isAfter(fechaFin)) {
            throw new NegocioException("Los formatos de las fechas no son válidos");
        }
        return registroMarcaBO.obtenerLista(idEmpleado, fechaInicio, fechaFin);
    }

    /**
     * Calcula el conteo total de asistencias válidas (con entrada y salida) de
     * una lista proporcionada.
     *
     * @param listaMarcas Lista de registros a contabilizar.
     * @return Cantidad de asistencias completas.
     * @throws NegocioException Si ocurre un error en la lógica de cálculo.
     */
    protected int ObtenerConteo(List<DTORegistroMarca> listaMarcas) throws NegocioException {
        return registroMarcaBO.calcularAsistencias(listaMarcas);
    }

    /**
     * Recupera una marca específica basada en el empleado y una fecha
     * determinada.
     *
     * @param idEmpleado Identificador del empleado.
     * @param fecha Fecha de consulta.
     * @return El DTO de la marca o null si no existe registro.
     * @throws NegocioException Si ocurre un error en la consulta de negocio.
     */
    protected DTORegistroMarca obtenerMarca(String idEmpleado, LocalDate fecha) throws NegocioException {
        return registroMarcaBO.obtenerPorEmpleadoYFecha(idEmpleado, fecha);
    }

}
