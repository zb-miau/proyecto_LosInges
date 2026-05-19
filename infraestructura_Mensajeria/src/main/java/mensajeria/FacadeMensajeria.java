/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajeria;

import dto.DTOIncidencia;

/**
 * Implementación del patrón de diseño **Facade (Fachada)** para el subsistema
 * de mensajería.
 *
 * Esta clase proporciona un punto de acceso simplificado y unificado hacia las
 * operaciones de envío de notificaciones gestionadas por
 * {@link ControlMensajeria}. Al implementar la interfaz {@link IMensajeria},
 * desacopla a los clientes externos de la complejidad interna y las
 * dependencias de configuración del protocolo de correo (SMTP, Jakarta Mail,
 * etc.).
 *
 *
 * @author jesus
 */
public class FacadeMensajeria implements IMensajeria {

    /**
     * Referencia al controlador que ejecuta la lógica real de envío y
     * configuración del servicio de mensajería.
     */
    private ControlMensajeria control;

    /**
     * Construye una nueva instancia de la fachada inyectando el controlador de
     * mensajería requerido.
     *
     * @param control El controlador de lógica de negocio ControlMensajeria. No
     * debe ser nulo.
     */
    public FacadeMensajeria(ControlMensajeria control) {
        this.control = control;
    }

    /**
     * Envía una notificación por correo electrónico (Gmail) al supervisor a
     * través del subsistema.
     *
     * Delegue el procesamiento y la estructuración del correo directamente al
     * método encapsulado enviarSupervisor del controlador.
     *
     *
     * @param incidencia Objeto DTOIncidencia que contiene los datos del reporte
     * y del empleado asociado que se enviarán en la notificación.
     */
    @Override
    public void enviarGmailSupervisor(DTOIncidencia incidencia) {

        control.enviarSupervisor(incidencia);

    }

}
