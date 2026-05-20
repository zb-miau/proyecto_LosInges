/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
import dto.DTORegistroMarca;
import dto.DTOTurno;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class FachadaGestionarEmpleados implements IGestionarEmpleados {

    private ControlGestionarEmpleados control;

    /**
     * Constructor por defecto que inicializa la fachada.
     * Crea la instancia del controlador interno encargado de procesar la logica.
     */
    public FachadaGestionarEmpleados() {
        this.control = new ControlGestionarEmpleados();
    }

    /**
     * Delega la peticion de registro y validacion del empleado al controlador del subsistema.
     *
     * @param empleado Objeto DTOContratacion con la informacion del candidato.
     * @return El DTOContratacion con los datos del empleado confirmado.
     * @throws NegocioException Si el registro no cumple con los criterios de aceptacion.
     */
    @Override
    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException{
        
        return control.registrarEmpleado(empleado);

    }

    /**
     * Solicita al controlador interno la lista completa de trabajadores registrados.
     *
     * @return Una lista de objetos DTOEmpleado con los registros del sistema.
     */
    @Override
    public List<DTOEmpleado> obtenerEmpleados() {

        return control.obtenerEmpleados();

    }

    @Override
    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia) {

        return control.registrarIncidencia(incidencia);

    }

    @Override
    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) {

        return control.validarIncidencia(incidencia);

    }

    @Override
    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) {

        return control.rechazarIncidencia(incidencia);

    }

    @Override
    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado) {

        return control.obtenerHorarioEmpleado(empleado);

    }

    @Override
    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException {

        control.actualizarHorarioEmpleado(turno, empleado, fecha_inicio, fecha_fin);

    }

    @Override
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {

        return control.listaHistorial(empleado, fechaInicio, fechaFin);

    }

    @Override
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado) {

        return control.recuperarEmpleado(empleado);

    }
    /**
     * Crea una marca para el empleado por medio de una solicitud al controlador
     * @param marca la marca que se va a registrar 
     * @return devuelve un DTO con la marca
     */
    @Override
    public DTORegistroMarca crearMarca(DTORegistroMarca marca) {
        return control.agregarMarca(marca);
    }
    /**
     * Método que genera un reporte de todas las asistencias del empleado en un rango de
     * fechas establecido
     * @param idEmpleado id del empleado al que se le atribuyen las asistencias
     * @param inicio rango de fecha mayor o igual
     * @param fin rango de fecha menor o igual 
     * @return regresa una Lista de DTO de marcas
     */ 
    @Override
    public List<DTORegistroMarca> obtenerLista(String idEmpleado, LocalDate inicio, LocalDate fin) {
        return control.obtenerListaMarcas(idEmpleado, inicio, fin);
    }
    /**
     * Obtiene el total de asistencias completas del empleado
     * @param listaMarcas lista de asistencias filtrada para obtener el conteo
     * @return  regresa un valor entero de la cantidad de asistencias
     */
    @Override
    public int obtenerConteoAsistencia(List<DTORegistroMarca> listaMarcas) {
        return control.conteoAsistencias(listaMarcas);
    }

}
