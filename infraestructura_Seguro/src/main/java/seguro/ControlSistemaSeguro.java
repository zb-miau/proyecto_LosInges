/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguro;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author RAMSES
 */
public class ControlSistemaSeguro {
    
    /**
     * Cliente HTTP reutilizable para realizar las peticiones hacia los servicios web.
     */
    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Ruta base de la API del servidor Flask que simula los sistemas gubernamentales.
     */
    private static final String BASE_URL = "http://localhost:5000/api";

    /**
     * Realiza una peticion HTTP GET al servidor de Flask para validar si el NSS
     * ingresado se encuentra registrado en el padron del seguro social.
     *
     * @param nss Cadena de texto que contiene el Numero de Seguro Social del empleado.
     * @return true si el NSS es valido y existe; false si no existe o falla la conexion.
     */
    public boolean validacionSistemaSeguroConNSS(String nss) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/validar-imss?nss=" + nss.trim()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Eliminamos espacios y saltos de linea para que el string coincida exactamente
            String cuerpoLimpio = response.body().replace(" ", "").replace("\n", "").replace("\r", "");
            
            // Evaluamos el exito del estado HTTP y la presencia exacta del valor valido
            boolean tieneExito = response.statusCode() == 200 && cuerpoLimpio.contains("\"valido\":true");
            return tieneExito;

        } catch (Exception e) {
            System.err.println("Error al conectar con el servicio del IMSS: " + e.getMessage());
            return false; 
        }
    }
    
}
