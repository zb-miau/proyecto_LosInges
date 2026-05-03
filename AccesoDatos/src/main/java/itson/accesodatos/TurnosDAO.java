/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import ObjetosNegocio.Turno;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.List;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoDatos{
    private static final String COLECCION_TURNOS = "turnos";
    
    @Override
    public Object crear(Object entidad) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionRestaurante = bd.getCollection(COLECCION_TURNOS, Turno.class);
            
             InsertOneResult resultado = coleccionRestaurante.insertOne((Turno) entidad);
            
            return entidad;
         }
    }

    @Override
    public Object eliminar(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object modificar(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object obtener(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List obtener() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
