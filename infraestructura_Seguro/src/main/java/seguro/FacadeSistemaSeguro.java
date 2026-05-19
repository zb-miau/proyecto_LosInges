/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguro;

/**
 *
 * @author RAMSES
 */
public class FacadeSistemaSeguro implements ISistemaSeguro{
    
    private ControlSistemaSeguro control;

    /**
     * Constructor de la fachada para el sistema externo del Seguro
     * 
     * @param control con el cual se llama a los metodos del control 
     * que interactuan de manera directa con el servidor del Seguro
     */
    public FacadeSistemaSeguro() {
        this.control = new ControlSistemaSeguro();
    }
    
    public boolean validacionSistemaSeguroConNSS(String nss){
        
        return control.validacionSistemaSeguroConNSS(nss);
        
    }
    
}
