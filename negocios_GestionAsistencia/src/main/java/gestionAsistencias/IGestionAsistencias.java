/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionAsistencias;
import dto.DTORegistroMarca;
import java.time.LocalDate;
import java.util.List;
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
     * Registra y persiste una nueva instancia de RegistroMarca.
     * @param marcaDTO DTO de la marca que se quiere agregar.
     * @return regresa un DTO de la marca registrada.
     * @throws NegocioException 
     */
    public DTORegistroMarca crearMarca(DTORegistroMarca marcaDTO) throws NegocioException;
    /**
     * Modifica una marca ya registrada. Permite actualizar la hora de salida del empleado
     * @param marcaDTO la marca para actualizar
     * @return regresa un DTO de la marca ya actualizada
     * @throws NegocioException 
     */
    public DTORegistroMarca modificarMarca(DTORegistroMarca marcaDTO) throws NegocioException;
    /**
     * Obtiene un registro en concreto. Método auxiliar para poder modificar una marca
     * @param idEmpleado atributo del empleado asociado a la marca 
     * @param fecha atributo del día en que la marca se creo
     * @return regresa un DTORegistroMarca para poder modificarlo
     * @throws NegocioException 
     */
    public DTORegistroMarca obtenerPorEmpleadoYFecha(String idEmpleado, LocalDate fecha)throws NegocioException;
    /**
     * Método para poder traer todas los registros de asistencia de un empleado
     * @param idEmpleado atributo para identificar al empleado
     * @param inicio periodo de comienzo de la filtración de reigstros
     * @param fin periodo de finalización de la filtración de reigstros
     * @return
     * @throws NegocioException 
     */
    public List<DTORegistroMarca> obtenerListaMarca(String idEmpleado, LocalDate inicio, LocalDate fin) throws NegocioException;
    
}
