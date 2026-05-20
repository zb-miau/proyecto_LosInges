/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sat;

import dtos.DTO_Contratacion;

/**
 * Interfaz que define el contrato para los servicios de validacion ante el SAT.
 * Proporciona las operaciones necesarias para comprobar la existencia de identificadores oficiales.
 *
 * @author RAMSES
 */
public interface ISistemaSAT {
    
    /**
     * Manda un rfc a validacion dentro del servidor del SAT
     * al ser un rfc que si esta registrado dentro del
     * servidor del SAT, entonces cuando se identifica 
     * si es que existe este rfc, se devuelve una respuesta.
     * 
     * @param rfc con el cual validamos que el empleado nuevo
     * que esta por ser contratado si esta dado de alta en el SAT.
     * @return verdadero si es que el rfc que se valida esta 
     * dado de alta en el servidor del SAT.
     */
    public boolean validacionSistemaSATConRFC(DTO_Contratacion rfc);
    
}
