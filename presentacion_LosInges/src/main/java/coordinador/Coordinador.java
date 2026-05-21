/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import asignarHorario.FacadeAsignarHorario;
import asignarHorario.IAsignarHorario;
import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOIncidencia;
import gestionAsistencias.FacadeGestionAsistencias;
import gestionAsistencias.IGestionAsistencias;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import gestionarEmpleados.FachadaGestionarEmpleados;
import gestionarEmpleados.IGestionarEmpleados;
import gestionarTurnos.FachadaGestionarTurnos;
import gestionarTurnos.IGestionarTurnos;
import java.awt.Frame;
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

    public void abrirPresentacionRoles() {
        if (menuPrincipal == null) {
            menuPrincipal = new Presentacion_menuPrincipal(this);
        }
        menuPrincipal.setVisible(true);
    }

    public void abrirPresentacionListaEmpleados() {
        if (listaDeEmpleados == null) {
            listaDeEmpleados = new Presentacion_listaDeEmpleados(this);
        }
        listaDeEmpleados.setVisible(true);
    }

    public void abrirContratacionEmpleados() {
        if (contratacionEmpleado == null) {
            contratacionEmpleado = new Presentacion_contratacionEmpleados(this);
        }
        contratacionEmpleado.setVisible(true);
    }

    public void abrirVentanaGestionHorarios() {
        if (gestionDeHorarios == null) {
            gestionDeHorarios = new Presentacion_gestionDeHorarios(this);
        }
        gestionDeHorarios.setVisible(true);
    }

    public void abrirGestionTurnos() {
        if (gestionDeTurnos == null) {
            gestionDeTurnos = new Presentacion_gestionDeTurnos(empleadoDTO, this);
        }
        gestionDeTurnos.setVisible(true);
    }

    public void abrirMenuGerente() {
        if (menuGerente == null) {
            menuGerente = new Presentacion_menuGerente(this);
        }
        menuGerente.setVisible(true);
    }

    public void abrirRegistroAsistencia() {
        if (registrarAsistencia == null) {
            registrarAsistencia = new Presentacion_registrarAsistencia(empleadoDTO, this);
        }
        registrarAsistencia.setVisible(true);
    }

    public void abrirReporteAsistencia() {
        if (reporteAsistencia == null) {
            reporteAsistencia = new Presentacion_reporteAsistencia(this, empleadoDTO);
        }
        reporteAsistencia.setVisible(true);
    }

    public void abrirRegistroIndidencia() {
        if (registroDeIncidencias == null) {
            registroDeIncidencias = new Presentacion_registroDeIncidencias(this);
        }
        registroDeIncidencias.setVisible(true);
    }

    public void abrirValidacionIndidencia(Frame padre) {
        if (validacionDeIncidencias == null) {
            validacionDeIncidencias = new Presentacion_validacionDeIncidencias(padre, incidenciaDTO, this);
        }
    }

    public void abrirValidacionIncidenciaTabla() {
        if (validacionIncidenciasTabla == null) {
            validacionIncidenciasTabla = new Presentacion_validacionIncidenciasTabla(this);
        }
        validacionIncidenciasTabla.setVisible(true);
    }

    
    public void registrarEmpleado(DTOContratacion empleado) {
        try {
            this.gestionarEmpleadosF.registrarEmpleado(empleado);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar registrar empleado: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

}
