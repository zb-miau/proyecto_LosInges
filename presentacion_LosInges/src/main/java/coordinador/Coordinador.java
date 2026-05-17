/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
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

    /**
     * Abre la vantana de GestionHorariosMenu
     *
     */
    public void abrirVentanaGestionHorariosMenu() {

        // Si ya existe la ventana, la traemos al frente, si no, la creamos
        if (listaDeEmpleados == null) {
            listaDeEmpleados = new Presentacion_listaDeEmpleados();
            listaDeEmpleados.setCoordinador(this);
        }

        listaDeEmpleados.setVisible(true);
        listaDeEmpleados.setLocationRelativeTo(null);

    }

    /**
     * Regresa a la pantalla de GestionHorariosMenu de la ventana de
     * GestionHorarios
     *
     * @param ventana
     */
    public void regresarAGestionHorariosMenuDeGestionHorarios() {
        if (gestionDeHorarios != null) {
            gestionDeHorarios.dispose();
        }
        abrirVentanaGestionHorariosMenu();
    }

    /**
     * Abre la ventana Turno desde la ventana de GestionHorariosMenu
     *
     * @param idEmpleado
     */
    public void abrirVentanaTurnoDeMenu(DTOEmpleado idEmpleado) {
        // Creamos la pantalla pasándole el empleado que requiere su constructor
        Presentacion_gestionDeTurnos ventanaTurnos = new Presentacion_gestionDeTurnos(idEmpleado);

        ventanaTurnos.setCoordinador(this);

        ventanaTurnos.setVisible(true);
        ventanaTurnos.setLocationRelativeTo(null);

    }

    /**
     * Abre la ventana Turno desde la ventana de GestionHorario
     *
     * @param idEmpleado
     */
    public void abrirVentanaTurnoDeGestionHorario(DTOEmpleado idEmpleado) {
        // Cerramos la de horarios si está abierta para evitar duplicidad
        if (gestionDeHorarios != null) {
            gestionDeHorarios.dispose();
        }

        gestionDeTurnos = new Presentacion_gestionDeTurnos(idEmpleado);
        gestionDeTurnos.setCoordinador(this);
        gestionDeTurnos.setVisible(true);
        gestionDeTurnos.setLocationRelativeTo(null);
    }

    /**
     * Regresa de la ventana turno a la ventana de GestionHorario
     *
     */
    public void regresarDeVentanaTurnoAGestionHorario(DTOEmpleado idEmpleado) {

        if (gestionDeTurnos != null) {
            gestionDeTurnos.dispose();
        }
        abrirVentanaTurnoDeGestionHorario(idEmpleado);

    }

    /**
     * Abre la pantalla de GestionHorarios desde la pantalla de
     * GestionHorariosMenu
     *
     * @param idEmpleado
     */
//    public void abrirVentanaGestionHorariosDeMenu(DTOEmpleado idEmpleado) {
//        Presentacion_gestionDeHorarios ventanaHorarios = new Presentacion_gestionDeHorarios(idEmpleado);
//        ventanaHorarios.setCoordinador(this);
//        ventanaHorarios.setVisible(true);
//        ventanaHorarios.setLocationRelativeTo(null);
//
//    }
    public void cambioDeVentana(int ventana) {

        switch (ventana) {
            case 2 -> {
                if (listaDeEmpleados == null) {
                    listaDeEmpleados = new Presentacion_listaDeEmpleados();
                    listaDeEmpleados.setCoordinador(this);
                }

                listaDeEmpleados.setVisible(true);
                listaDeEmpleados.setLocationRelativeTo(null);
            }
            case 3 -> {
                if (menuPrincipal == null) {
                    menuPrincipal = new Presentacion_menuPrincipal();
                    menuPrincipal.setCoordinador(this);
                }

                menuPrincipal.setVisible(true);
                menuPrincipal.setLocationRelativeTo(null);
            }
            case 6 -> {
                if (validacionIncidenciasTabla == null) {
                    validacionIncidenciasTabla = new Presentacion_validacionIncidenciasTabla();
                    validacionIncidenciasTabla.setCoordinador(this);
                }

                validacionIncidenciasTabla.setVisible(true);
                validacionIncidenciasTabla.setLocationRelativeTo(null);
            }
            case 7 -> {
                if (menuGerente == null) {
                    menuGerente = new Presentacion_menuGerente();
                    menuGerente.setCoordinador(this);
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
                    gestionDeHorarios = new Presentacion_gestionDeHorarios();
                    gestionDeHorarios.setCoordinador(this);
                }

                gestionDeHorarios.cargardatos(empleado);
                gestionDeHorarios.setVisible(true);
                gestionDeHorarios.setLocationRelativeTo(null);
            }
            case 1 -> {
                if (gestionDeTurnos == null) {
                    gestionDeTurnos = new Presentacion_gestionDeTurnos(empleado);
                    gestionDeTurnos.setCoordinador(this);
                }

                gestionDeTurnos.setVisible(true);
                gestionDeTurnos.setLocationRelativeTo(null);
            }
            case 4 -> {
                if (registroDeIncidencias == null) {
                    registroDeIncidencias = new Presentacion_registroDeIncidencias();
                    registroDeIncidencias.setCoordinador(this);
                }

                registroDeIncidencias.cargarTexto(empleado);
                registroDeIncidencias.setVisible(true);
                registroDeIncidencias.setLocationRelativeTo(null);
            }
            default ->
                throw new AssertionError();
        }

    }

    public void cambioDeVentana(int ventana, DTOIncidencia incidencia) {

//        switch (ventana) {
//            case 5 -> {
//                if (validacionDeIncidencias == null) {
//                    validacionDeIncidencias = new Presentacion_validacionDeIncidencias(incidencia);
//                    validacionDeIncidencias.setCoordinador(this);
//                }
//
//                validacionDeIncidencias.setVisible(true);
//                validacionDeIncidencias.setLocationRelativeTo(null);
//            }
//
//            default ->
//                throw new AssertionError();
//        }
    }

}
