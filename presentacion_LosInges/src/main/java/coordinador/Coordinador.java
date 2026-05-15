/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

import dto.DTOEmpleado;
import javax.swing.JFrame;
import presentacion.Presentacion_gestionDeHorarios;
import presentacion.Presentacion_gestionDeHorariosMenu;
import presentacion.Presentacion_gestionDeTurnos;

/**
 *
 * @author RAMSES
 */
public class Coordinador {
    
    private Presentacion_gestionDeHorariosMenu gestionDeHorariosMenu;
    private Presentacion_gestionDeTurnos gestionDeTurnos;
    private Presentacion_gestionDeHorarios gestionDeHorarios;
    
    /**
     * Abre la vantana de GestionHorariosMenu
     * 
     */
    public void abrirVentanaGestionHorariosMenu(){
        
        // Si ya existe la ventana, la traemos al frente, si no, la creamos
        if (gestionDeHorariosMenu == null) {
            gestionDeHorariosMenu = new Presentacion_gestionDeHorariosMenu();
            gestionDeHorariosMenu.setCoordinador(this);
        }
        
        gestionDeHorariosMenu.setVisible(true);
        gestionDeHorariosMenu.setLocationRelativeTo(null);
        
    }
    
    /**
     * Regresa a la pantalla de GestionHorariosMenu de la ventana de GestionHorarios
     * 
     * @param ventana 
     */
    public void regresarAGestionHorariosMenuDeGestionHorarios(){
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
    public void abrirVentanaTurnoDeMenu(DTOEmpleado idEmpleado){
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
    public void abrirVentanaTurnoDeGestionHorario(DTOEmpleado idEmpleado){
        // Cerramos la de horarios si está abierta para evitar duplicidad
        if (gestionDeHorarios != null) {
            gestionDeHorarios.setVisible(false);
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
    public void regresarDeVentanaTurnoAGestionHorario(DTOEmpleado idEmpleado){
        
        if (gestionDeTurnos != null) {
            gestionDeTurnos.dispose();
        }
        abrirVentanaTurnoDeGestionHorario(idEmpleado);
    
    }
    
    /**
     * Abre la pantalla de GestionHorarios desde la pantalla de GestionHorariosMenu
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaGestionHorariosDeMenu(DTOEmpleado idEmpleado){
        Presentacion_gestionDeHorarios ventanaHorarios = new Presentacion_gestionDeHorarios(idEmpleado);
        ventanaHorarios.setCoordinador(this);
        ventanaHorarios.setVisible(true);
        ventanaHorarios.setLocationRelativeTo(null);
        
    }
    
}
