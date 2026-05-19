/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

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

    public FachadaGestionarEmpleados() {
        this.control = new ControlGestionarEmpleados();
    }

    @Override
    public DTOEmpleado registrarEmpleado(DTOEmpleado empleado) {

        return control.registrarEmpleado(empleado);

    }

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
