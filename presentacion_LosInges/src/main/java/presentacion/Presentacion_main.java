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
import objetosNegocio.EmpleadoBO;

/**
 *
 * @author Zaira
 */
public class Presentacion_main {

    public static void main(String[] args) {
        

        
          Presentacion_gestionDeHorariosMenu main = new Presentacion_gestionDeHorariosMenu();
//           Presentacion_gestionDeTurnos main = new Presentacion_gestionDeTurnos("");
    }
}
