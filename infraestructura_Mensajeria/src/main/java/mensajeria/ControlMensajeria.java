/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajeria;

import dto.DTOIncidencia;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Servicio encargado de la gestión y envío de notificaciones por correo
 * electrónico dentro del sistema.
 *
 * Utiliza la API de jakarta.mail para conectarse a un servidor SMTP externo
 * (por defecto Gmail) y alertar a los involucrados o supervisores sobre los
 * eventos y cambios de estado en las incidencias.
 *
 * @author jesus
 */
public class ControlMensajeria {

    /**
     * Envía una notificación por correo electrónico al supervisor informando
     * que se ha registrado una nueva incidencia para un empleado específico.
     *
     * El método inicializa la sesión SMTP con TLS estructurando dinámicamente
     * el asunto y el cuerpo del mensaje a partir de los datos contenidos en el
     * DTO de la incidencia.
     *
     *
     * @param incidencia Objeto DTOIncidencia que contiene la información
     * detallada de la incidencia y del empleado afectado. No debe ser nulo y
     * debe contar con la relación de un DTOEmpleado válida.
     * @throws NullPointerException si incidencia o los datos del empleado son
     * nulos al estructurar el mensaje.
     */
    public void enviarSupervisor(DTOIncidencia incidencia) {

        // 1. Configuración del servidor SMTP (Ejemplo con Gmail)
        String servidorSmtp = "smtp.gmail.com";
        String puerto = "587"; // Puerto estándar para TLS

        // Datos de autenticación
        final String usuario = "remboy121@gmail.com";
        final String contrasenia = "deyt nnci vcnc ycmq";

        // Correo del destinatario
        String destinatario = "jesus.flores262713@potros.itson.edu.mx";

        // 2. Establecer las propiedades del sistema
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", servidorSmtp);
        propiedades.put("mail.smtp.port", puerto);

        // 3. Crear la sesión con autenticación
        Session sesion = Session.getInstance(propiedades, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contrasenia);
            }
        });

        try {
            // 4. Crear el mensaje de correo
            Message mensaje = new MimeMessage(sesion);

            // Remitente
            mensaje.setFrom(new InternetAddress(usuario));

            // Destinatario
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Asunto y cuerpo del mensaje
            mensaje.setSubject("Se le ha registrado una incidencia a " + incidencia.getEmpleado().getNombre() + " " + incidencia.getEmpleado().getApellidoPaterno() + " " + incidencia.getEmpleado().getApellidoMaterno());
            mensaje.setText("Se le ha registrado una incidencia a " + incidencia.getEmpleado().getNombre() + " " + incidencia.getEmpleado().getApellidoPaterno() + " " + incidencia.getEmpleado().getApellidoMaterno() + "\n"
                    + "ID incidencia: " + incidencia.getIdIncidencia() + "\n"
                    + "ID empleado: " + incidencia.getEmpleado().getId() + "\n"
                    + "Descripción\n"
                    + incidencia.getDescripcion());

            // 5. Enviar el correo
            Transport.send(mensaje);

        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
