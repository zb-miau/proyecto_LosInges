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
import dto.DTOHorarioEmpleado;
import java.util.List;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO implements IAccesoDatos<DTOHorarioEmpleado>{
    private static final String COLECCION_HORARIO_EMPLEADO = "horario_empleados";

    @Override
    public DTOHorarioEmpleado crear(DTOHorarioEmpleado entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOHorarioEmpleado> coleccionRestaurante = bd.getCollection(COLECCION_HORARIO_EMPLEADO, DTOHorarioEmpleado.class);
            
             InsertOneResult resultado = coleccionRestaurante.insertOne(entidad);
            
            return entidad;
         }
    }

    @Override
    public DTOHorarioEmpleado eliminar(DTOHorarioEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOHorarioEmpleado modificar(DTOHorarioEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DTOHorarioEmpleado obtener(DTOHorarioEmpleado entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DTOHorarioEmpleado> obtenerLista() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
