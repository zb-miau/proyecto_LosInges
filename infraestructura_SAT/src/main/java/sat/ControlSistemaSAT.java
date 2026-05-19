/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase controladora encargada de gestionar la logica de negocio y el procesamiento
 * de las peticiones de validacion de datos fiscales ante el sistema del SAT.
 *
 * @author RAMSES
 */
public class ControlSistemaSAT {
    
    /**
     * Cliente HTTP reutilizable para realizar las peticiones hacia los servicios web.
     */
    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Ruta base de la API del servidor Flask que simula los sistemas gubernamentales.
     */
    private static final String BASE_URL = "http://localhost:5000/api";

    /**
     * Realiza una peticion HTTP GET al servidor externo para validar si el RFC 
     * ingresado se encuentra registrado de forma legal en el padron del SAT.
     *
     * @param rfc Cadena de texto que contiene el RFC con homoclave del empleado a validar.
     * @return true si el servidor responde con codigo 200 y el JSON contiene la confirmacion 
     * de validez, false en caso contrario o si ocurre una falla de conexion.
     */
    public boolean validacionSistemaSATConRFC(String rfc) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/validar-sat?rfc=" + rfc.trim()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String cuerpoLimpio = response.body().replace(" ", "").replace("\n", "").replace("\r", "");
            
            boolean tieneExito = response.statusCode() == 200 && cuerpoLimpio.contains("\"valido\":true");
            
            return tieneExito;

        } catch (Exception e) {
            System.err.println("Error al conectar con el servicio del SAT: " + e.getMessage());
            return false; 
        }
    }
    
}
