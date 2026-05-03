/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import java.util.List;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO implements IAccesoDatos{
    private static final String COLECCION_HORARIO_EMPLEADO = "horario_empleados";
    @Override
    public Object crear(Object entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<HorarioEmpleado> coleccionRestaurante = bd.getCollection(COLECCION_HORARIO_EMPLEADO, HorarioEmpleado.class);
            
             InsertOneResult resultado = coleccionRestaurante.insertOne(entidad);
            
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
