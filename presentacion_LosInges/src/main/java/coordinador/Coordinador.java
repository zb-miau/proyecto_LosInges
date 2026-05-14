/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinador;

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
        
        if (gestionDeHorariosMenu == null) {
            gestionDeHorariosMenu = new Presentacion_gestionDeHorariosMenu();
        }

        gestionDeHorariosMenu.setVisible(true);
        
    }
    
    /**
     * Regresa a la pantalla de GestionHorariosMenu de la ventana de GestionHorarios
     * 
     * @param ventana 
     */
    public void regresarAGestionHorariosMenuDeGestionHorarios(){
        
        gestionDeHorarios.dispose();
        gestionDeHorarios = null;
        
        if (gestionDeHorariosMenu == null) {
            gestionDeHorariosMenu = new Presentacion_gestionDeHorariosMenu();
        }
        
        gestionDeHorariosMenu.setVisible(true);
        
    }
    
    /**
     * Abre la ventana Turno desde la ventana de GestionHorariosMenu
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaTurnoDeMenu(String idEmpleado){
        
        if (gestionDeTurnos == null) {
            gestionDeTurnos = new Presentacion_gestionDeTurnos(idEmpleado);
        }
        
        gestionDeHorariosMenu.setVisible(false);
        gestionDeTurnos.setVisible(true);
        
    }
    
    /**
     * Abre la ventana Turno desde la ventana de GestionHorario
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaTurnoDeGestionHorario(String idEmpleado){
        
        if (gestionDeTurnos == null) {
            gestionDeTurnos = new Presentacion_gestionDeTurnos(idEmpleado);
        }
        
        gestionDeHorarios.setVisible(false);
        gestionDeTurnos.setVisible(true);
        
    }
    
    /**
     * Regresa de la ventana turno a la ventana de GestionHorario
     * 
     */
    public void regresarDeVentanaTurnoAGestionHorario(String idEmpleado){
        
        gestionDeTurnos.dispose();
        gestionDeTurnos = null;
        
        if (gestionDeHorarios == null) {
            gestionDeHorarios = new Presentacion_gestionDeHorarios(idEmpleado);
        }
        
        gestionDeHorarios.setVisible(false);
    
    }
    
    /**
     * Abre la pantalla de GestionHorarios desde la pantalla de GestionHorariosMenu
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaGestionHorariosDeMenu(String idEmpleado){
        
        gestionDeHorariosMenu.setVisible(false);
        
        gestionDeHorarios = new Presentacion_gestionDeHorarios(idEmpleado);
        gestionDeHorarios.setVisible(true);
        
    }
    
}
