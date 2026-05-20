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
 * Esta interfaz sirve como la abstracción principal (API) para que cualquier
 * cliente externo o capa de presentación interactúe con la lógica de negocio de
 * asistencia, garantizando el desacoplamiento mediante el uso exclusivo de
 * DTORegistroMarca.
 *
 * @author josma
 */
public interface IGestionAsistencias {

    /**
     * Registra una marca de entrada o salida para un empleado y la persiste en el sistema.
     * Este método procesa la lógica de negocio necesaria para determinar si corresponde
     * a una nueva entrada o al cierre de una jornada (salida).
     * @param marcaDTO Objeto de transferencia de datos con la información de la marca a procesar.
     * @return El DTORegistroMarca con los datos confirmados y el ID asignado.
     * @throws NegocioException Si los datos son inválidos, el empleado no tiene horario 
     * o se intenta registrar fuera de los rangos permitidos.
     */
    public DTORegistroMarca crearMarca(DTORegistroMarca marcaDTO) throws NegocioException;

    /**
     * Recupera una lista filtrada de todos los registros de asistencia de un empleado 
     * en un rango de fechas específico.
     * @param idEmpleado Identificador único del empleado.
     * @param inicio     Fecha de inicio para el filtrado (inclusive).
     * @param fin        Fecha de fin para el filtrado (inclusive).
     * @return Una {@link List} de DTORegistroMarca que coinciden con los criterios de búsqueda.
     * @throws NegocioException Si el identificador es nulo o si el rango de fechas es incoherente.
     */
    public List<DTORegistroMarca> obtenerListaMarca(String idEmpleado, LocalDate inicio, LocalDate fin) throws NegocioException;

    /**
     * Realiza el conteo total de asistencias válidas presentes en una lista de registros.
     * El cálculo se basa en las reglas de negocio que definen qué constituye una 
     * asistencia completa (típicamente contar con entrada y salida).
     * @param listaMarcas Lista de registros sobre la cual se realizará el conteo.
     * @return Un valor entero que representa la cantidad de asistencias totales.
     * @throws NegocioException Si ocurre un error durante el procesamiento de la lista.
     */
    public int conteoAsistencia(List<DTORegistroMarca> listaMarcas) throws NegocioException;
    /**
     * Busca y recupera el registro de marca de un empleado para un día calendario específico.
     * @param idEmpleado Identificador único del empleado.
     * @param fecha      Fecha exacta del registro a consultar.
     * @return El DTORegistroMarca correspondiente al día solicitado, 
     * o null si no existe registro para esa fecha.
     * @throws NegocioException Si ocurre un error en la capa de acceso a datos.
     */
    public DTORegistroMarca obtenerMarca(String idEmpleado, LocalDate fecha) throws NegocioException;
}
