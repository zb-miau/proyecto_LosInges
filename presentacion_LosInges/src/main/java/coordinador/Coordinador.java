/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import asignarHorario.FacadeAsignarHorario;
import asignarHorario.IAsignarHorario;
import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
import dto.DTORegistroMarca;
import dto.DTOTurno;
import gestionAsistencias.FacadeGestionAsistencias;
import gestionAsistencias.IGestionAsistencias;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import gestionarEmpleados.FachadaGestionarEmpleados;
import gestionarEmpleados.IGestionarEmpleados;
import gestionarTurnos.FachadaGestionarTurnos;
import gestionarTurnos.IGestionarTurnos;
import java.awt.Frame;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetosNegocio.NegocioException;
import presentacion.Presentacion_contratacionEmpleados;
import presentacion.Presentacion_gestionDeHorarios;
import presentacion.Presentacion_listaDeEmpleados;
import presentacion.Presentacion_gestionDeTurnos;
import presentacion.Presentacion_menuGerente;
import presentacion.Presentacion_menuPrincipal;
import presentacion.Presentacion_registrarAsistencia;
import presentacion.Presentacion_registroDeIncidencias;
import presentacion.Presentacion_reporteAsistencia;
import presentacion.Presentacion_validacionDeIncidencias;
import presentacion.Presentacion_validacionIncidenciasTabla;

/**
 *
 * @author RAMSES
 */
public class Coordinador {

    public final IGestionarEmpleados gestionarEmpleadosF = new FachadaGestionarEmpleados();
    public final IGestionarTurnos gestionTurnosF = new FachadaGestionarTurnos();
    private DTOEmpleado empleadoDTO;
    private DTOIncidencia incidenciaDTO;

    private Presentacion_gestionDeHorarios gestionDeHorarios;
    private Presentacion_gestionDeTurnos gestionDeTurnos;
    private Presentacion_listaDeEmpleados listaDeEmpleados;
    private Presentacion_menuPrincipal menuPrincipal;
    private Presentacion_registroDeIncidencias registroDeIncidencias;
    private Presentacion_validacionDeIncidencias validacionDeIncidencias;
    private Presentacion_validacionIncidenciasTabla validacionIncidenciasTabla;
    private Presentacion_menuGerente menuGerente;
    private Presentacion_contratacionEmpleados contratacionEmpleado;
    private Presentacion_registrarAsistencia registrarAsistencia;
    private Presentacion_reporteAsistencia reporteAsistencia;
    
    public static final int GESTION_DE_HORARIOS = 0;
    public static final int GESTION_DE_TURNOS = 1;
    public static final int REGISTRO_DE_INCIDENCIAS = 4;
    public static final int REGISTRAR_ASISTENCIA = 9;
    public static final int REPORTE_ASISTENCIA = 10;
    
    public void cambioDeVentana(int ventana) {

        switch (ventana) {
            case 1 -> {
                if (gestionDeTurnos == null) {
                    gestionDeTurnos = new Presentacion_gestionDeTurnos(null, this);

                }
                gestionDeTurnos.setVisible(true);
                gestionDeTurnos.setLocationRelativeTo(null);
            }

            
            case 9 ->{
                if (registrarAsistencia == null) {
                    registrarAsistencia = new Presentacion_registrarAsistencia(null, this);
                    
                }
                registrarAsistencia.setVisible(true);
                registrarAsistencia.setLocationRelativeTo(null);
            }
            
            default ->
                throw new AssertionError();
        }

    }
    
    public void cambioDeVentana(int ventana, DTOEmpleado empleado) {

        switch (ventana) {
            case 0 -> {
                if (gestionDeHorarios == null) {
                    gestionDeHorarios = new Presentacion_gestionDeHorarios(this);
                }

                gestionDeHorarios.cargardatos(empleado);
                gestionDeHorarios.setVisible(true);
                gestionDeHorarios.setLocationRelativeTo(null);
            }
            case 1 -> {
                if (gestionDeTurnos == null) {
                    gestionDeTurnos = new Presentacion_gestionDeTurnos(empleado, this);
                }

                gestionDeTurnos.setVisible(true);
                gestionDeTurnos.setLocationRelativeTo(null);
            }
            case 4 -> {
                if (registroDeIncidencias == null) {
                    registroDeIncidencias = new Presentacion_registroDeIncidencias(this);
                }

                registroDeIncidencias.cargarTexto(empleado);
                registroDeIncidencias.setVisible(true);
                registroDeIncidencias.setLocationRelativeTo(null);
            }
            case 9 ->{
                if (registrarAsistencia == null) {
                    registrarAsistencia = new Presentacion_registrarAsistencia(empleado, this);
                    
                }
                registrarAsistencia.setVisible(true);
                registrarAsistencia.setLocationRelativeTo(null);
            }
            case 10 ->{
                if (reporteAsistencia == null) {
                    reporteAsistencia = new Presentacion_reporteAsistencia(this,empleado);
                    
                }
                reporteAsistencia.setVisible(true);
                reporteAsistencia.setLocationRelativeTo(null);
            }
            default ->
                throw new AssertionError();
        }

    }

    
    private int ventanaSiguiente = -1;

    public void setVentanaSiguiente(int ventanaSiguiente) {
        this.ventanaSiguiente = ventanaSiguiente;
    }

    public int getVentanaSiguiente() {
        return ventanaSiguiente;
    }
    
    
    public void setEmpleado(DTOEmpleado empleado){
        this.empleadoDTO = empleado;
    }
    
    
    public void abrirPresentacionRoles() {
        if (menuPrincipal == null) {
            menuPrincipal = new Presentacion_menuPrincipal(this);
        }
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }

    public void abrirPresentacionListaEmpleados() {
        if (listaDeEmpleados == null) {
            listaDeEmpleados = new Presentacion_listaDeEmpleados(this);
        }
        listaDeEmpleados.setVisible(true);
        listaDeEmpleados.setLocationRelativeTo(null);
    }

    public void abrirContratacionEmpleados() {
        if (contratacionEmpleado == null) {
            contratacionEmpleado = new Presentacion_contratacionEmpleados(this);
        }
        contratacionEmpleado.setVisible(true);
        contratacionEmpleado.setLocationRelativeTo(null);
    }

    public void abrirVentanaGestionHorarios() {
        if (gestionDeHorarios == null) {
            gestionDeHorarios = new Presentacion_gestionDeHorarios(this);
        }
        gestionDeHorarios.setVisible(true);
        gestionDeHorarios.setLocationRelativeTo(null);
    }

    public void abrirGestionTurnos() {
        if (gestionDeTurnos == null) {
            gestionDeTurnos = new Presentacion_gestionDeTurnos(empleadoDTO, this);
        }
        gestionDeTurnos.setVisible(true);
        gestionDeTurnos.setLocationRelativeTo(null);
    }

    public void abrirMenuGerente() {
        if (menuGerente == null) {
            menuGerente = new Presentacion_menuGerente(this);
        }
        menuGerente.setVisible(true);
        menuGerente.setLocationRelativeTo(null);
    }

    public void abrirRegistroAsistencia() {
        if (registrarAsistencia == null) {
            registrarAsistencia = new Presentacion_registrarAsistencia(empleadoDTO, this);
        }
        registrarAsistencia.setVisible(true);
        registrarAsistencia.setLocationRelativeTo(null);
    }

    public void abrirReporteAsistencia() {
        if (reporteAsistencia == null) {
            reporteAsistencia = new Presentacion_reporteAsistencia(this, empleadoDTO);
        }
        reporteAsistencia.setVisible(true);
        reporteAsistencia.setLocationRelativeTo(null);
    }

    public void abrirRegistroIncidencia() {
        if (registroDeIncidencias == null) {
            registroDeIncidencias = new Presentacion_registroDeIncidencias(this);
        }
        registroDeIncidencias.setVisible(true);
        registroDeIncidencias.setLocationRelativeTo(null);
    }
    
    public void setIncidencia(DTOIncidencia incidencia){
        this.incidenciaDTO = incidencia;
    }

    public DTOIncidencia abrirValidacionIncidencia() {
        if (validacionDeIncidencias == null) {
            validacionDeIncidencias = new Presentacion_validacionDeIncidencias(null, incidenciaDTO, this);
        }
        validacionDeIncidencias.setVisible(true);
        validacionDeIncidencias.setLocationRelativeTo(null);
        return validacionDeIncidencias.getIncidencia();
    }

    public void abrirValidacionIncidenciaTabla() {
        if (validacionIncidenciasTabla == null) {
            validacionIncidenciasTabla = new Presentacion_validacionIncidenciasTabla(this);
        }
        validacionIncidenciasTabla.setLocationRelativeTo(null);
        validacionIncidenciasTabla.setVisible(true);
    }

    
    public void registrarEmpleado(DTOContratacion empleado) {
        try {
            this.gestionarEmpleadosF.registrarEmpleado(empleado);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar registrar empleado: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public List<DTOTurno> recuperarListaTurno(){
        try {
            return this.gestionTurnosF.recuperarTurno();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar recuperar el turno: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
    }
    
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado){
        this.gestionarEmpleadosF.recuperarEmpleado(empleado);
        return empleado;
    }
    
    public List<DTOHorarioEmpleado> listaDeHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        try {
            return this.gestionarEmpleadosF.listaHistorial(empleado, fechaInicio, fechaFin);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar recuperar el la lista: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void actualizarHorarioDelEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        try {
            this.gestionarEmpleadosF.actualizarHorarioEmpleado(turno, empleado, fechaInicio, fechaFin);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar el horario del empleado: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void agregarTurno(DTOTurno turno){
        try {
            this.gestionTurnosF.agregarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar agregar el turno: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public List<DTOTurno> recuperarTurno(){
        try {
            return this.gestionTurnosF.recuperarTurno();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar agregar el turno: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    public void eliminarTurno(DTOTurno turno){
        try {
            this.gestionTurnosF.eliminarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar el turno: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void modificarTurno(DTOTurno turno){
        try {
            this.gestionTurnosF.modificarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar modificar el turno: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public List<DTOEmpleado> obtenerEmpleados(){
        
        return this.gestionarEmpleadosF.obtenerEmpleados();
        
    }
    
    public DTORegistroMarca obtenerMarca(DTOEmpleado empleado, LocalDate fecha){
        return this.gestionarEmpleadosF.obtenerMarca(empleadoDTO, fecha);
    }
    
    public DTORegistroMarca crearMarca(DTORegistroMarca marca){
        return this.gestionarEmpleadosF.crearMarca(marca);
    }
    
    public DTOIncidencia crearIncidencia(DTOIncidencia incidencia){
        return this.gestionarEmpleadosF.registrarIncidencia(incidencia);
    }
    
    public void enviarSupervisor(DTOIncidencia incidencia){
        this.gestionarEmpleadosF.enviarSupervisor(incidencia);
    }
    
    public int conteoAsistencia(List<DTORegistroMarca> lista){
        return this.gestionarEmpleadosF.obtenerConteoAsistencia(lista);
    }
    
    public List<DTORegistroMarca> obtenerListaMarca(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        return this.gestionarEmpleadosF.obtenerLista(empleado, fechaInicio, fechaFin);
    }
    

}
