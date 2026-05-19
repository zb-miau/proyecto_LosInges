/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionAsistencias;
import dto.DTORegistroMarca;
import objetosNegocio.NegocioException;
/**
 * Contrato formal que define los servicios públicos disponibles para el
 * subsistema de gestión y control de asistencias.
 *
 * Esta interfaz sirve como la abstracción principal (API) para que cualquier
 * cliente externo o capa de presentación interactúe con la lógica de negocio de
 * assitencia, garantizando el desacoplamiento mediante el uso exclusivo de
 * DTORegistroMarca.
 * @author josma
 */
public interface IGestionAsistencias {
    
    /**
     * 
     * @return
     * @throws NegocioException 
     */
    public DTORegistroMarca crearMarca() throws NegocioException;
}
