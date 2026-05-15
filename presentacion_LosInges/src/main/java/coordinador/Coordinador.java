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
    public void abrirVentanaGestionHorariosMenu(JFrame forma){
        forma.dispose();
        gestionDeHorariosMenu = new Presentacion_gestionDeHorariosMenu();
        gestionDeHorariosMenu.setVisible(true);
        
    }
    
    /**
     * Regresa a la pantalla de GestionHorariosMenu de la ventana de GestionHorarios
     * 
     * @param ventana 
     */
    public void regresarAGestionHorariosMenuDeGestionHorarios(Presentacion_gestionDeHorarios gestionHorarios){
        gestionHorarios.dispose();
    }
    
    /**
     * Abre la ventana Turno desde la ventana de GestionHorariosMenu
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaTurnoDeMenu(DTOEmpleado idEmpleado){
        gestionDeTurnos = new Presentacion_gestionDeTurnos(idEmpleado.getId());
        gestionDeHorariosMenu.setVisible(false);
        gestionDeTurnos.setVisible(true);
        
    }
    
    /**
     * Abre la ventana Turno desde la ventana de GestionHorario
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaTurnoDeGestionHorario(DTOEmpleado idEmpleado){
        gestionDeTurnos = new Presentacion_gestionDeTurnos(idEmpleado.getId());
        gestionDeHorarios.setVisible(false);
        gestionDeTurnos.setVisible(true);
        
    }
    
    /**
     * Regresa de la ventana turno a la ventana de GestionHorario
     * 
     */
    public void regresarDeVentanaTurnoAGestionHorario(DTOEmpleado idEmpleado){
        gestionDeTurnos.dispose();
        gestionDeTurnos = null;
        
        if (gestionDeHorarios == null) {
            gestionDeHorarios = new Presentacion_gestionDeHorarios(idEmpleado.getId());
        }
        
        gestionDeHorarios.setVisible(false);
    
    }
    
    /**
     * Abre la pantalla de GestionHorarios desde la pantalla de GestionHorariosMenu
     * 
     * @param idEmpleado 
     */
    public void abrirVentanaGestionHorariosDeMenu(String idEmpleado){
        gestionDeHorarios = new Presentacion_gestionDeHorarios(idEmpleado);
        gestionDeHorarios.setVisible(true);
        
    }
    
}
