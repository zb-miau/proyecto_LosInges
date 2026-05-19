/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionIncidencias;

import dto.DTOIncidencia;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 * Contrato formal que define los servicios públicos disponibles para el
 * subsistema de gestión y control de incidencias.
 *
 * Esta interfaz sirve como la abstracción principal (API) para que cualquier
 * cliente externo o capa de presentación interactúe con la lógica de negocio de
 * incidencias, garantizando el desacoplamiento mediante el uso exclusivo de
 * DTOIncidencia.
 *
 *
 * @author jesus
 */
public interface IGestionIncidencias {

    /**
     * Registra y persiste una nueva incidencia laboral en el sistema.
     *
     * @param dTOIncidencia Objeto de transferencia de datos con la información
     * necesaria para dar de alta la incidencia.
     * @return El DTOIncidencia completamente estructurado, incluyendo el
     * identificador único asignado por el sistema de almacenamiento.
     * @throws NegocioException Si los datos de la incidencia violan alguna
     * regla de validación o política de la empresa (Ej: empleado inexistente,
     * tipo inválido).
     */
    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException;

    /**
     * Recupera una lista filtrada de todas las incidencias registradas en el
     * sistema que coincidan con el estado operativo provisto.
     *
     * @param estado Criterio de filtrado correspondiente a los estados del
     * flujo (Ej: "PENDIENTE", "VALIDADA").
     * @return Una lista List conteniendo los objetos DTOIncidencia que cumplen
     * con el criterio.
     * @throws NegocioException Si ocurre un fallo en el procesamiento de la
     * consulta o inconsistencia en las capas inferiores.
     */
    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException;

    /**
     * Aprueba o valida una incidencia que se encuentra en revisión, cambiando
     * su estado de forma permanente a un estado de autorización en las capas de
     * negocio y persistencia.
     *
     * @param incidencia Objeto DTOIncidencia que representa la incidencia que
     * se desea validar.
     * @return El DTOIncidencia actualizado reflejando el nuevo estado de
     * aprobación.
     * @throws NegocioException Si la incidencia no se encuentra o su estado
     * actual no permite una transición hacia el estado validado.
     */
    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) throws NegocioException;

    /**
     * Declina o rechaza una incidencia bajo revisión, impidiendo que proceda en
     * el flujo de trabajo y actualizando su estado en el sistema de
     * almacenamiento.
     *
     * @param incidencia Objeto DTOIncidencia que representa la incidencia que
     * se desea rechazar.
     * @return El DTOIncidencia actualizado reflejando el nuevo estado de
     * rechazo.
     * @throws NegocioException Si la incidencia no existe o el ciclo de vida
     * actual del registro bloquea la operación de rechazo.
     */
    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) throws NegocioException;

    /**
     * Despacha de forma asíncrona o directa una notificación (usualmente por
     * correo electrónico) al supervisor asignado para informarle sobre los
     * detalles o cambios de la incidencia adjunta.
     *
     * @param incidencia Objeto DTOIncidencia con los datos informativos del
     * reporte y del empleado que se enviarán en el cuerpo del mensaje.
     */
    public void enviarSupervisor(DTOIncidencia incidencia);

}
