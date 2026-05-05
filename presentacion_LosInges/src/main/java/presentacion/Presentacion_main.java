/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package presentacion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.DTOEmpleado;
import itson.accesodatos.ManejadorConexiones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaira
 */
public class Presentacion_main {

    public static void main(String[] args) {
        
        // 1. Obtener la conexión mediante tu Manejador
//        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
//            MongoDatabase db = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
//            
//            // 2. Obtener la colección usando la clase DTOEmpleado para el Codec
//            MongoCollection<DTOEmpleado> coleccion = db.getCollection("empleados", DTOEmpleado.class);
//
//            // 3. Crear la lista de empleados
//            List<DTOEmpleado> listaNuevosEmpleados = new ArrayList<>();
//
//            listaNuevosEmpleados.add(new DTOEmpleado("Ramses", "Contreras", LocalDate.of(1995, 5, 10), "Calle 1", "Centro", 101, 85000, "CURP1", "RFC1", "NSS1"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Josmara", "Quintana", LocalDate.of(1998, 9, 26), "Av. Tecnologico", "Norte", 502, 85010, "CURP2", "RFC2", "NSS2"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Hector", "Flores", LocalDate.of(2000, 1, 15), "Calle Morelos", "Sur", 20, 85050, "CURP3", "RFC3", "NSS3"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Zaira", "Barajaz", LocalDate.of(1998, 8, 24), "Calle Quintana Roo", "Oeste", 88, 85100, "CURP4", "RFC4", "NSS4"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Jesus", "Martinez", LocalDate.of(1990, 12, 5), "Callejón 5", "Ejidal", 5, 85200, "CURP5", "RFC5", "NSS5"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Ana", "García", LocalDate.of(1993, 3, 20), "Blvd. Kino", "Pitic", 300, 83000, "CURP6", "RFC6", "NSS6"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Luis", "Pérez", LocalDate.of(1985, 7, 14), "Calle Sonora", "Centro", 45, 85000, "CURP7", "RFC7", "NSS7"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Maria", "Lopez", LocalDate.of(1997, 11, 30), "Calle 10", "Nainari", 15, 85110, "CURP8", "RFC8", "NSS8"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Carlos", "Sánchez", LocalDate.of(1992, 6, 18), "Av. 200", "Valle", 1024, 85120, "CURP9", "RFC9", "NSS9"));
//            listaNuevosEmpleados.add(new DTOEmpleado("Sofia", "Ramírez", LocalDate.of(1999, 2, 28), "Calle Mayo", "Casa Blanca", 77, 85130, "CURP10", "RFC10", "NSS10"));
//
//            // 4. Inserción Masiva
//            System.out.println("Insertando " + listaNuevosEmpleados.size() + " empleados...");
//            coleccion.insertMany(listaNuevosEmpleados);
//            
//            System.out.println("¡Inserción completada con éxito!");
//            System.out.println("Ya puedes revisar la colección 'empleados' en Studio3T.");
//            
//        } catch (Exception e) {
//            System.err.println("Error al insertar: " + e.getMessage());
//            e.printStackTrace();
//        }
        
          Presentacion_gestionDeHorariosMenu main = new Presentacion_gestionDeHorariosMenu();
          // Presentacion_gestionDeTurnos main = new Presentacion_gestionDeTurnos(3L);
    }
}
