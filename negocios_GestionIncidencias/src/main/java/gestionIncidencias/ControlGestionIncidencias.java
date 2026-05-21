/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOIncidencia;
import dtos.DTO_CorreoElectronico;
import java.util.List;
import mensajeria.ControlMensajeria;
import mensajeria.FacadeMensajeria;
import mensajeria.IMensajeria;
import objetosNegocio.IncidenciaBO;
import objetosNegocio.NegocioException;

/**
 * Controlador de aplicación encargado de coordinar el flujo de trabajo y los
 * casos de uso relacionados con la gestión de incidencias.
 *
 * Actúa como un intermediario o controlador de fachada (Controller Pattern)
 * entre la capa de presentación (interfaces de usuario) y las capas internas de
 * lógica de negocio (IncidenciaBO) y servicios externos (IMensajeria).
 *
 *
 * @author jesus
 */
public class ControlGestionIncidencias {

    /**
     * Componente de lógica de negocio (Business Object) para la gestión interna
     * de incidencias.
     */
    private IncidenciaBO incidenciaBO;

    /**
     * Abstracción del servicio de mensajería para el despacho de notificaciones
     * y alertas.
     */
    private IMensajeria mensajeria;

    /**
     * Construye una nueva instancia del controlador inicializando sus
     * dependencias requeridas.
     *
     * Instancia de forma directa el subsistema de mensajería a través de su
     * fachada FacadeMensajeria y recupera la instancia única del objeto de
     * negocio IncidenciaBO bajo el patrón Singleton.
     *
     */
    public ControlGestionIncidencias() {
        mensajeria = new FacadeMensajeria(new ControlMensajeria());

        this.incidenciaBO = IncidenciaBO.getInstance();
    }

    /**
     * Coordina el caso de uso para el registro e inserción de una nueva
     * incidencia en el sistema.
     *
     * @param dTOIncidencia El objeto de transferencia de datos con la
     * información de la incidencia a crear.
     * @return El DTOIncidencia resultante con los datos persistidos y el
     * identificador asignado.
     * @throws NegocioException Si ocurre una violación a las reglas de
     * validación de negocio durante la creación.
     */
    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        return incidenciaBO.crear(dTOIncidencia);

    }

    /**
     * Recupera el catálogo completo de incidencias registradas que coincidan
     * con un estado específico.
     *
     * @param estado Criterio de filtrado (Ej: "PENDIENTE", "VALIDADA",
     * "RECHAZADA").
     * @return Una lista List que contiene los objetos DTOIncidencia filtrados.
     * @throws NegocioException Si ocurre un error de consistencia o acceso al
     * procesar la solicitud en la capa de negocio.
     */
    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException {

        return incidenciaBO.obtenerLista(estado);

    }

    /**
     * Actualiza el estado de una incidencia específica a Estado.VALIDADA.
     *
     * Modifica el estado del DTO localmente y delega la persistencia del cambio
     * a la capa de negocio.
     *
     *
     * @param dTOIncidencia La incidencia que se desea aprobar o validar.
     * @return El DTOIncidencia modificado y actualizado desde el repositorio.
     * @throws NegocioException Si la incidencia no cumple con las condiciones
     * de negocio para ser validada o no se encuentra.
     */
    public DTOIncidencia validarIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.VALIDADA);

        return incidenciaBO.modificar(dTOIncidencia);

    }

    /**
     * Actualiza el estado de una incidencia específica a
     * {@code Estado.RECHAZADA}.
     *
     * Modifica el estado del DTO localmente y delega la persistencia del cambio
     * a la capa de negocio.
     *
     *
     * @param dTOIncidencia La incidencia que se desea declinar o rechazar.
     * @return El DTOIncidencia modificado y actualizado desde el repositorio.
     * @throws NegocioException Si la incidencia no se encuentra o las reglas de
     * negocio impiden su rechazo.
     */
    public DTOIncidencia rechazarIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.RECHAZADA);

        return incidenciaBO.modificar(dTOIncidencia);

    }

    /**
     * Solicita de manera asíncrona o directa el envío de un correo de alerta al
     * supervisor notificando los detalles del DTO de la incidencia provisto.
     *
     * @param incidencia Objeto DTOIncidencia con los datos de interés a ser
     * enviados por correo.
     */
    public void enviarSupervisor(DTOIncidencia incidencia) {

        DTO_CorreoElectronico correo = new DTO_CorreoElectronico(
                incidencia.getEmpleado().getId(),
                incidencia.getIdIncidencia(),
                incidencia.getEmpleado().getNombre() + " " + incidencia.getEmpleado().getApellidoPaterno() + " " + incidencia.getEmpleado().getApellidoMaterno(),
                incidencia.getTipo().toString(),
                incidencia.getDescripcion());

        mensajeria.enviarGmailSupervisor(correo);

    }

}
