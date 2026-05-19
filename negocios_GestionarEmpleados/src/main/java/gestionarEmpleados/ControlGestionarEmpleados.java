/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

import asignarHorario.FacadeAsignarHorario;
import asignarHorario.IAsignarHorario;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
import dto.DTOTurno;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class ControlGestionarEmpleados {

    private EmpleadoBO empleadoBO;

    private IGestionIncidencias gestionIncidencias;

    private IAsignarHorario asignarHorario;

    public ControlGestionarEmpleados() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.gestionIncidencias = new FacadeGestionIncidencias();
        this.asignarHorario = new FacadeAsignarHorario();
    }

    public DTOEmpleado registrarEmpleado(DTOEmpleado empleado) {

        return empleadoBO.crear(empleado);

    }

    public List<DTOEmpleado> obtenerEmpleados() {

        return empleadoBO.obtenerLista();

    }

    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia) {

        try {
            return gestionIncidencias.crearIncidencia(incidencia);
        } catch (NegocioException ex) {

            System.out.println(ex.getMessage());
            ex.printStackTrace();

        }
        return incidencia;

    }

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) {

        try {
            return gestionIncidencias.validarIncidencia(incidencia);
        } catch (NegocioException ex) {

            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return incidencia;

    }

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) {

        try {
            return gestionIncidencias.rechazarIncidencia(incidencia);
        } catch (NegocioException ex) {

            System.out.println(ex.getMessage());
            ex.printStackTrace();

        }

        return incidencia;

    }

    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado) {

        return asignarHorario.obtenerHorarioEmpleado(empleado);

    }

    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException {

        asignarHorario.actualizarHorarioEmpleado(turno, empleado, fecha_inicio, fecha_fin);

    }

    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {

        return asignarHorario.listaHistorial(empleado, fechaInicio, fechaFin);

    }

    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado) {

        return empleadoBO.obtener(empleado);

    }

}
