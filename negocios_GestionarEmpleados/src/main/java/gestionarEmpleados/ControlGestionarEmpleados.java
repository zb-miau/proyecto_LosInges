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
import dto.DTORegistroMarca;
import dto.DTOTurno;
import dtos.DTO_Contratacion;
import gestionAsistencias.FacadeGestionAsistencias;
import gestionAsistencias.IGestionAsistencias;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.NegocioException;
import sat.FacadeSistemaSAT;
import sat.ISistemaSAT;
import seguro.FacadeSistemaSeguro;
import seguro.ISistemaSeguro;
import validaciones.Validaciones;

/**
 * Clase controladora del subsistema encargada de coordinar los flujos de
 * trabajo y las pantallas relacionadas con la administracion y gestion integral
 * de empleados.
 *
 * @author jesus
 */
public class ControlGestionarEmpleados {

    private EmpleadoBO empleadoBO;

    private IGestionIncidencias gestionIncidencias;

    private IGestionAsistencias gestionAsistencias;

    private IAsignarHorario asignarHorario;

    private Validaciones validacion;

    private ISistemaSAT sat;

    private ISistemaSeguro seguro;

    /**
     * Constructor por defecto que inicializa los componentes necesarios para la
     * gestion. Vincula las fachadas de servicios externos (SAT, Seguro Social,
     * Horarios, Incidencias) y las utilidades de validacion de datos basicos.
     */
    public ControlGestionarEmpleados() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.gestionIncidencias = new FacadeGestionIncidencias();
        this.asignarHorario = new FacadeAsignarHorario();
        this.gestionAsistencias = new FacadeGestionAsistencias();
        this.validacion = new Validaciones();
        this.sat = new FacadeSistemaSAT();
        this.seguro = new FacadeSistemaSeguro();
    }

    /**
     * Ejecuta el flujo completo de contratacion para un nuevo empleado. Realiza
     * validaciones sintacticas de formato, comprueba la vigencia de los datos
     * fiscales ante el SAT y verifica la existencia del numero de seguridad
     * social.
     *
     * @param empleado Objeto DTOContratacion con la informacion del candidato.
     * @return El DTOContratacion con el registro confirmado por la capa de
     * negocio.
     * @throws NegocioException Si algun campo no cumple con el formato
     * requerido o si las validaciones ante los sistemas externos (SAT o Seguro
     * Social) fallan.
     */
    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException {

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

        DTO_Contratacion rfcYNss = new DTO_Contratacion(empleado.getRfc(), empleado.getNss());

        boolean valorSAT = sat.validacionSistemaSATConRFC(rfcYNss);
        if (!valorSAT) {
            throw new NegocioException("El RFC del empleado a contratar no existe.");
        }

        boolean valorSeguro = seguro.validacionSistemaSeguroConNSS(rfcYNss);
        if (!valorSeguro) {
            throw new NegocioException("El NSS del empleado a contratar no existe.");
        }

        return empleadoBO.crear(empleado);

    }

    /**
     * Recupera la nomina de empleados activos mapeados en estructuras de
     * transferencia.
     *
     * @return Una lista de tipo DTOEmpleado con todos los registros del
     * sistema.
     */
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

    public DTORegistroMarca agregarMarca(DTORegistroMarca marcaDTO) {
        try {
            return gestionAsistencias.crearMarca(marcaDTO);
        } catch (NegocioException e) {
            System.out.println("Algo fallo al intentar insertar la marca");
            e.printStackTrace();
            return marcaDTO;
        }

    }

    public List<DTORegistroMarca> obtenerListaMarcas(DTOEmpleado empleado, LocalDate incio, LocalDate fin) {
        try {
            return gestionAsistencias.obtenerListaMarca(empleado, incio, fin);
        } catch (NegocioException e) {
            System.out.println("Ocurrio un error al intentar obtener la lista");
            e.printStackTrace();
            return null;
        }

    }

    public int conteoAsistencias(List<DTORegistroMarca> listaMarcas) {
        try {
            return gestionAsistencias.conteoAsistencia(listaMarcas);
        } catch (NegocioException e) {
            System.out.println("Ocurrio un error al intentar contar las asistencias");
            e.printStackTrace();
            return 0;
        }

    }

    public DTORegistroMarca obtenerMarca(DTOEmpleado empleado, LocalDate fecha) {
        try {
            return gestionAsistencias.obtenerMarca(empleado, fecha);
        } catch (NegocioException e) {
            System.out.println("Algo fallo al intentar obtener la marca");
            e.printStackTrace();
            return null;
        }
    }
}
