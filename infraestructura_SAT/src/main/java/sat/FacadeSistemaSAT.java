/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sat;

/**
 *
 * @author RAMSES
 */
public class FacadeSistemaSAT implements ISistemaSAT{
    
    private ControlSistemaSAT control;

    /**
     * Constructor de la fachada para el sistema externo del SAT
     * 
     * @param control con el cual se llama a los metodos del control 
     * que interactuan de manera directa con el servidor del SAT
     */
    public FacadeSistemaSAT() {
        this.control = new ControlSistemaSAT();
    }
    
    public boolean validacionSistemaSATConRFC(String rfc){
        
        return control.validacionSistemaSATConRFC(rfc);
        
    }
    
}
