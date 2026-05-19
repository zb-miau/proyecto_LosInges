/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

import asignarHorario.FacadeAsignarHorario;
import asignarHorario.IAsignarHorario;
import dto.DTOContratacion;
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
import validaciones.Validaciones;

/**
 *
 * @author jesus
 */
public class ControlGestionarEmpleados {

    private EmpleadoBO empleadoBO;

    private IGestionIncidencias gestionIncidencias;

    private IAsignarHorario asignarHorario;
    
    private Validaciones validacion;

    public ControlGestionarEmpleados() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.gestionIncidencias = new FacadeGestionIncidencias();
        this.asignarHorario = new FacadeAsignarHorario();
        this.validacion = new Validaciones();
    }

    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException{
        
        if (empleado.getNombre() == null || !validacion.validarNombre(empleado.getNombre())) {
            throw new NegocioException("El nombre no cumple con lo requerido.");
        }
        
        if (empleado.getApellidoPaterno() == null || !validacion.validarApellidoPaterno(empleado.getApellidoPaterno())) {
            throw new NegocioException("El apellido paterno no cumple con lo requerido.");
        }
        
        if (empleado.getApellidoMaterno() != null || empleado.getApellidoMaterno() != "") {
            if (!validacion.validarApellidoMaterno(empleado.getApellidoMaterno())) {
                throw new NegocioException("El apellido materno no cumple con lo requerido.");
            }
        }
        
        if (empleado.getCalle() == null || !validacion.validarCalle(empleado.getCalle())) {
            throw new NegocioException("El nombre de la calle no cumple con lo requerido.");
        }
        
        if (empleado.getCodigoPostal() == null || !validacion.validarCodigoPostal(empleado.getCodigoPostal())) {
            throw new NegocioException("El codigo postal no cumple con lo requerido.");
        }
        
        if (empleado.getColonia() == null || !validacion.validarColonia(empleado.getColonia())) {
            throw new NegocioException("El nombre de la colonia no cumple con lo requerido.");
        }
        
        if (empleado.getCurp() == null || !validacion.validarCurp(empleado.getCurp())) {
            throw new NegocioException("La CURP no cumple con lo requerido.");
        }
        
        if (empleado.getFechaNacimiento() == null || !validacion.validarMayoríaEdad(empleado.getFechaNacimiento())) {
            throw new NegocioException("La fecha de nacimiento no cumple con lo requerido.");
        }
        
        if (empleado.getNss() == null || !validacion.validarNss(empleado.getNss())) {
            throw new NegocioException("El NSS no cumple con lo requerido.");
        }
        
        if (empleado.getNumeroCasa() == null || !validacion.validarNumeroCasa(empleado.getNumeroCasa())) {
            throw new NegocioException("El numero de casa no cumple con lo requerido.");
        }
        
        if (empleado.getRfc() == null || !validacion.validarRfc(empleado.getRfc())) {
            throw new NegocioException("El RFC no cumple con lo requerido.");
        }
        
            
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
