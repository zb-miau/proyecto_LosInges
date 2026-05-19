/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sat;

/**
 * Implementacion de la fachada que sirve como punto de acceso unico para el sistema del SAT.
 * Delega las solicitudes de verificacion de credenciales hacia los controladores internos del modulo.
 *
 * @author RAMSES
 */
public class FacadeSistemaSAT implements ISistemaSAT{
    
    private ControlSistemaSAT control;

    /**
     * Constructor que inicializa el controlador interno del sistema del SAT.
     */
    public FacadeSistemaSAT() {
        this.control = new ControlSistemaSAT();
    }
    
    /**
     * Delega la validacion del RFC al controlador interno del SAT.
     *
     * @param rfc Cadena de texto con el RFC a validar en el padron.
     * @return true si el RFC es valido en el sistema; false en caso contrario.
     */
    public boolean validacionSistemaSATConRFC(String rfc){
        
        return control.validacionSistemaSATConRFC(rfc);
        
    }
    
}
