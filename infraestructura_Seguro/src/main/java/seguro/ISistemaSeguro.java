/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package seguro;

/**
 * Interfaz que define el contrato para los servicios de validacion ante el seguro social.
 * Proporciona las operaciones necesarias para comprobar la vigencia de prestaciones medicas.
 *
 * @author RAMSES
 */
public interface ISistemaSeguro {
    
    /**
     * Valida si el NSS proporcionado se encuentra registrado de forma activa 
     * mediante una consulta HTTP GET externa al servicio del seguro social.
     *
     * @param nss Cadena de texto con el Numero de Seguro Social del empleado.
     * @return true si el NSS existe y es valido en la plataforma; 
     * false si no existe o si ocurre un fallo en la conexion con el servidor.
     */
    public boolean validacionSistemaSeguroConNSS(String nss);
    
}
