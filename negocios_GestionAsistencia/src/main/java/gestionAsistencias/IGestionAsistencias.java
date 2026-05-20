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
     * Método para poder traer todas los registros de asistencia de un empleado
     * @param idEmpleado atributo para identificar al empleado
     * @param inicio periodo de comienzo de la filtración de reigstros
     * @param fin periodo de finalización de la filtración de reigstros
     * @return regresa una lista DTO con los registros
     * @throws NegocioException
     */
    public List<DTORegistroMarca> obtenerListaMarca(String idEmpleado, LocalDate inicio, LocalDate fin)throws NegocioException;
    /**
     * Método que realiza el conteo de las asistencias totales de un empleado.
     * @param listaMarcas lista donde se realiza el conteo
     * @return regresa un valor entero de la cantidad de asistencias
     * @throws NegocioException
     */
    public int conteoAsistencia(List<DTORegistroMarca> listaMarcas)throws NegocioException;
}
