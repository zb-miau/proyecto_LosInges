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
import java.time.LocalDate;
import java.util.ArrayList;
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
    public final IGestionarEmpleados gestionarEmpleados = new FachadaGestionarEmpleados();
    public final IGestionarTurnos gestionarTurnos = new FachadaGestionarTurnos();

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
    public static final int LISTA_DE_EMPLEADOS = 2;
    public static final int MENU_PRINCIPAL = 3;
    public static final int REGISTRO_DE_INCIDENCIAS = 4;
    public static final int VALIDACION_DE_INCIDENCIAS = 5;
    public static final int VALIDACION_INCIDECIAS_TABLA = 6;
    public static final int MENU_GERENTE = 7;
    public static final int CONTRATACION_EMPLEADO = 8;
    public static final int REGISTRAR_ASISTENCIA = 9;
    public static final int REPORTE_ASISTENCIA = 10;

    private int ventanaSiguiente = -1;
    private DTOEmpleado empleado;

    public void setVentanaSiguiente(int ventanaSiguiente) {
        this.ventanaSiguiente = ventanaSiguiente;
    }
    public void setEmpleado(DTOEmpleado empleado){
        this.empleado = empleado; 
    }

    public int getVentanaSiguiente() {
        return ventanaSiguiente;
    }

    public void cambioDeVentana(int ventana) {

        switch (ventana) {
            case 1 -> {
                if (gestionDeTurnos == null) {
                    gestionDeTurnos = new Presentacion_gestionDeTurnos(null, this);

                }
                gestionDeTurnos.setVisible(true);
                gestionDeTurnos.setLocationRelativeTo(null);
            }

            case 2 -> {
                if (listaDeEmpleados == null) {
                    listaDeEmpleados = new Presentacion_listaDeEmpleados(this);
                }

                listaDeEmpleados.setVisible(true);
                listaDeEmpleados.setLocationRelativeTo(null);
            }
            case 3 -> {
                if (menuPrincipal == null) {
                    menuPrincipal = new Presentacion_menuPrincipal(this);
                }

                menuPrincipal.setVisible(true);
                menuPrincipal.setLocationRelativeTo(null);
            }
            case 6 -> {
                if (validacionIncidenciasTabla == null) {
                    validacionIncidenciasTabla = new Presentacion_validacionIncidenciasTabla(this);
                }

                validacionIncidenciasTabla.setVisible(true);
                validacionIncidenciasTabla.setLocationRelativeTo(null);
            }
            case 7 -> {
                if (menuGerente == null) {
                    menuGerente = new Presentacion_menuGerente(this);
                }

                menuGerente.setVisible(true);
                menuGerente.setLocationRelativeTo(null);
            }
            case 8 -> {
                if (contratacionEmpleado == null) {
                    contratacionEmpleado = new Presentacion_contratacionEmpleados(this);
                    
                }

                contratacionEmpleado.setVisible(true);
                contratacionEmpleado.setLocationRelativeTo(null);
            }
            case 9 ->{
                if (registrarAsistencia == null) {
                    registrarAsistencia = new Presentacion_registrarAsistencia(this);
                    
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
                    registrarAsistencia = new Presentacion_registrarAsistencia(this);
                    
                }
                
                registrarAsistencia.cargarDatos(empleado);
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

    public DTOIncidencia cambioDeVentana(int ventana, DTOIncidencia incidencia) {

        switch (ventana) {
            case 5 -> {
                validacionDeIncidencias = new Presentacion_validacionDeIncidencias(validacionIncidenciasTabla, incidencia, this);
                validacionDeIncidencias.setVisible(true);
                validacionDeIncidencias.setLocationRelativeTo(null);
                return validacionDeIncidencias.getIncidencia();
            }

            default ->
                throw new AssertionError();
        }
    }
    
    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException{
            return gestionarEmpleados.registrarEmpleado(empleado);

    }
    
    public List<DTOTurno> recuperarListaTurno(){
        try {
            return gestionarTurnos.recuperarTurno();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo recuperar la lista de turnos.");
            List<DTOTurno> lista = new ArrayList<>();
            return lista;
        }
    }
    
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado){
        return gestionarEmpleados.recuperarEmpleado(empleado);
    }
    
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        try {
            return gestionarEmpleados.listaHistorial(empleado, fechaInicio, fechaFin);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo recuperar la lista de horarios.");
            List<DTOHorarioEmpleado> lista = new ArrayList<>();
            return lista;
        }
    }
    
    public void actualizarHorarioDelEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        try {
            gestionarEmpleados.actualizarHorarioEmpleado(turno, empleado, fechaInicio, fechaFin);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo actualizar el horario.");
        }
    }
    
    public DTOTurno agregarTurno(DTOTurno turno){
        try {
            return gestionarTurnos.agregarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo agregar el turno.");
            return null; 
        }
    }
    public DTOTurno eliminarTurno(DTOTurno turno){
        try {
            return gestionarTurnos.eliminarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo eliminar el turno.");
            return null;
        }
    }
    
    public DTOTurno modificarTurno(DTOTurno turno){
        try {
            return gestionarTurnos.modificarTurno(turno);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo modificar el turno.");
            return null;
        }
    }

    
    public List<DTOEmpleado> obtenerEmpleados(){
        return gestionarEmpleados.obtenerEmpleados();
    }
    public DTORegistroMarca obtenerMarca(DTOEmpleado empleado, LocalDate fecha){
        return gestionarEmpleados.obtenerMarca(empleado, fecha);
    }
    
    public DTORegistroMarca crearMarca(DTORegistroMarca marca) throws NegocioException{
            return gestionarEmpleados.crearMarca(marca);
    }
    
    public DTOIncidencia crearIncidencia(DTOIncidencia incidencia){
        return gestionarEmpleados.registrarIncidencia(incidencia);
    }
    public void enviarSupervisor(DTOIncidencia incidencia){
        gestionarEmpleados.enviarSupervisor(incidencia);
    }
    
    public List<DTORegistroMarca> obtenerListaMarca(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin){
        return gestionarEmpleados.obtenerLista(empleado, fechaInicio, fechaFin);
    }
    
    public int conteoAsistencia(List<DTORegistroMarca> lista){
        return gestionarEmpleados.obtenerConteoAsistencia(lista);
    }
}
