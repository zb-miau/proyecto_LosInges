/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import dto.DTOEmpleado;
import java.util.List;

/**
 *
 * @author Zaira
 */
public class EmpleadosDAO implements IAccesoDatos<DTOEmpleado>{
    private static final String COLECCION_EMPLEADOS = "empleados";

    @Override
    public DTOEmpleado crear(DTOEmpleado entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionRestaurante = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);
            
             InsertOneResult resultado = coleccionRestaurante.insertOne(entidad);
            
            return entidad;
         }
    }

    @Override
    public DTOEmpleado eliminar(DTOEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOEmpleado modificar(DTOEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOEmpleado obtener(DTOEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DTOEmpleado> obtenerLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  
    
}
