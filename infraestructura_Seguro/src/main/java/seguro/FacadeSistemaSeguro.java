/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguro;

/**
 * Implementacion de la fachada que sirve como punto de acceso unico para el sistema del seguro social.
 * Delega las solicitudes de verificacion de salud y prestaciones hacia los controladores internos del modulo.
 *
 * @author RAMSES
 */
public class FacadeSistemaSeguro implements ISistemaSeguro{
    
    private ControlSistemaSeguro control;

    /**
     * Constructor que inicializa el controlador interno del sistema del seguro social.
     */
    public FacadeSistemaSeguro() {
        this.control = new ControlSistemaSeguro();
    }
    
    /**
     * Delega la validacion del NSS al controlador interno del seguro social.
     *
     * @param nss Cadena de texto con el Numero de Seguro Social a validar.
     * @return true si el NSS es valido en el sistema; false en caso contrario.
     */
    public boolean validacionSistemaSeguroConNSS(String nss){
        
        return control.validacionSistemaSeguroConNSS(nss);
        
    }
    
}
