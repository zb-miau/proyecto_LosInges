/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import asignarHorario.FacadeAsignarHorario;
import asignarHorario.IAsignarHorario;
import dto.DTOEmpleado;
import dto.DTOIncidencia;
import gestionAsistencias.IGestionAsistencias;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import gestionarEmpleados.FachadaGestionarEmpleados;
import gestionarEmpleados.IGestionarEmpleados;
import gestionarTurnos.FachadaGestionarTurnos;
import gestionarTurnos.IGestionarTurnos;
import javax.swing.JDialog;
import javax.swing.JFrame;
import presentacion.Presentacion_gestionDeHorarios;
import presentacion.Presentacion_listaDeEmpleados;
import presentacion.Presentacion_gestionDeTurnos;
import presentacion.Presentacion_menuGerente;
import presentacion.Presentacion_menuPrincipal;
import presentacion.Presentacion_registroDeIncidencias;
import presentacion.Presentacion_validacionDeIncidencias;
import presentacion.Presentacion_validacionIncidenciasTabla;

/**
 *
 * @author RAMSES
 */
public class Coordinador {

    public final IAsignarHorario asignarHorario = new FacadeAsignarHorario();
    public final IGestionIncidencias gestionIncidencias = new FacadeGestionIncidencias();
//    private final IGestionAsistencias gestionAsistencias;
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

    public static final int GESTION_DE_HORARIOS = 0;
    public static final int GESTION_DE_TURNOS = 1;
    public static final int LISTA_DE_EMPLEADOS = 2;
    public static final int MENU_PRINCIPAL = 3;
    public static final int REGISTRO_DE_INCIDENCIAS = 4;
    public static final int VALIDACION_DE_INCIDENCIAS = 5;
    public static final int VALIDACION_INCIDECIAS_TABLA = 6;
    public static final int MENU_GERENTE = 7;

    private int ventanaSiguiente = -1;

    public void setVentanaSiguiente(int ventanaSiguiente) {
        this.ventanaSiguiente = ventanaSiguiente;
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
            default ->
                throw new AssertionError();
        }

    }

    public DTOIncidencia cambioDeVentana(int ventana, DTOIncidencia incidencia) {

        switch (ventana) {
            case 5 -> {
                if (validacionDeIncidencias == null) {
                    validacionDeIncidencias = new Presentacion_validacionDeIncidencias(validacionIncidenciasTabla, incidencia, this);

                }

                validacionDeIncidencias.setVisible(true);
                validacionDeIncidencias.setLocationRelativeTo(null);
                return validacionDeIncidencias.getIncidencia();
            }

            default ->
                throw new AssertionError();
        }
    }

}
