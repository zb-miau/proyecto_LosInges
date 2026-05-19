/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
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

}
