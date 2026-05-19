/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mensajeria;

import dto.DTOIncidencia;

/**
 * Interfaz que define el contrato abstracto para los servicios de mensajería y
 * notificaciones del sistema.
 *
 * Su principal objetivo es proporcionar un mecanismo de inversión de
 * dependencias y desacoplamiento, permitiendo que las capas superiores (como
 * controladores de eventos o lógica de negocio) soliciten el envío de alertas
 * sin necesidad de conocer la implementación técnica subyacente (ej. protocolos
 * SMTP, APIs de terceros o configuraciones de red).
 *
 *
 * @author jesus
 */
public interface IMensajeria {

    /**
     * Despacha una notificación formal por correo electrónico hacia la cuenta
     * del supervisor configurado, informando sobre el registro de una nueva
     * incidencia.
     *
     * Las clases que implementen este método deben encargarse de la extracción
     * de datos del DTO, la composición del mensaje (asunto/cuerpo) y la gestión
     * del transporte seguro del correo.
     *
     *
     * @param incidencia Objeto DTOIncidencia que contiene toda la información
     * estructurada del reporte y el empleado involucrado. No debe ser nulo.
     */
    public void enviarGmailSupervisor(DTOIncidencia incidencia);

}
