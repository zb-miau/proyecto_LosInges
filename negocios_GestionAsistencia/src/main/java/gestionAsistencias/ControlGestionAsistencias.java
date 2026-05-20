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
 * Clase Control encargada de del flujo de trabajo del subistema, se encarga de
 * controlar los flujos de trabajo y las pantallas relacionadas con la
 * administración y gestión de asistencias.
 *
 * @author josma
 */
public class ControlGestionAsistencias {

    private RegistroMarcaBO registroMarcaBO;
    private HorarioEmpleadoBO horarioEmpleadoBO;
    private EmpleadoBO empleadoBO;

    public ControlGestionAsistencias() {
        this.registroMarcaBO = RegistroMarcaBO.getInstance();
        this.horarioEmpleadoBO = HorarioEmpleadoBO.getInstance();
        this.empleadoBO = EmpleadoBO.getInstance();
    }
    /**
     * Método que sirve para poder registrar o actualizar una marca del empleado
     * Si la marca no existe le genera una entrada, si la marca ya existe actualiza
     * la instancia y le agrega la salida.
     * @param registroDTO 
     * @return
     * @throws NegocioException 
     */
    protected DTORegistroMarca agregarMarca(DTORegistroMarca registroDTO) throws NegocioException {
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
            return registroMarcaBO.crear(registroDTO);
        } else {//SI YA TIENE UNA ENTRADA, MARCA LA SALIDA
            if (marcaExistente.getSalida() != null) {
                throw new NegocioException("Ya hay una salida registrada");
            }
            marcaExistente.setSalida(tiempoHoy);
            return registroMarcaBO.modificar(marcaExistente);

        }
    }
    /**
     * Método para poder generar un reporte con todas las asistencias de un empleado en un rango de 
     * fechas definido
     * @param idEmpleado id del empleado con el que se va abuscar sus registros
     * @param fechaInicio rango de fecha mayor  o igual 
     * @param fechaFin rango de fecha menor o igual
     * @return regresa una lista DTO con los registros
     * @throws NegocioException 
     */
    protected List<DTORegistroMarca> reporteAsistencia(String idEmpleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException{
        //Validación de fechas coherente
        if (fechaFin.isBefore(fechaInicio) || fechaInicio.isAfter(fechaFin)) {
            throw new NegocioException("Los formatos de las fechas no son válidos");
        }
        return registroMarcaBO.obtenerLista(idEmpleado, fechaInicio, fechaFin);
    }
    /**
     * Cuenta la cantidad de asistencias del empleado
     * @param listaMarcas lista de todas las asistencias del empleado en un rango de fechas
     * @return un valor entero con la cantidad de asistencias
     * @throws NegocioException 
     */
    protected int ObtenerConteo(List<DTORegistroMarca> listaMarcas) throws NegocioException{
        return registroMarcaBO.calcularAsistencias(listaMarcas);
    }
    protected DTORegistroMarca obtenerMarca(String idEmpleado, LocalDate fecha)throws NegocioException{
        return registroMarcaBO.obtenerPorEmpleadoYFecha(idEmpleado, fecha);
    }
    
}
