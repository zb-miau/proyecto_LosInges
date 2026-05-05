/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import dto.DTOEmpleado;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira y Ramses
 */
public class EmpleadosDAO implements IAccesoDatos<DTOEmpleado>{
    private static final String COLECCION_EMPLEADOS = "empleados";
    
    private static EmpleadosDAO empleadoDAO;
    
    private EmpleadosDAO(){
        
    }
    
    /**
     * Obtener la instancia del EmpleadoDAO
     * 
     * @return EmpleadosDAO
     */
    public static EmpleadosDAO getInstanceEmpleadosDAO(){
        if (empleadoDAO == null) {
            empleadoDAO = new EmpleadosDAO();
        }
        
        return empleadoDAO;
    }

    /**
     * Crea un empleado y lo guarda en la base de datos.
     * 
     * @param entidad
     * @return Empleado DTO
     */
    @Override
    public DTOEmpleado crear(DTOEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionEmpleados = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);

            coleccionEmpleados.insertOne(entidad);
            return entidad;
        }
    }

    /**
     * Elimina un empleado de la base de datos
     * 
     * @param entidad
     * @return Empleado DTO 
     */
    @Override
    public DTOEmpleado eliminar(DTOEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionEmpleados = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);

            Document filtro = new Document("_id", entidad.getId());

            return coleccionEmpleados.findOneAndDelete(filtro);
        }
    }

    /**
     * Modifica a un empleado
     * 
     * @param entidad
     * @return 
     */
    @Override
    public DTOEmpleado modificar(DTOEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionEmpleados = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);

            Document filtro = new Document("_id", entidad.getId());

            // Actualización de campos individuales
            if (entidad.getNombre() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("nombre", entidad.getNombre()));
            }
            if (entidad.getApellidos() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("apellidos", entidad.getApellidos()));
            }
            if (entidad.getFechaNacimiento() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("fechaNacimiento", entidad.getFechaNacimiento()));
            }
            if (entidad.getCalle() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("calle", entidad.getCalle()));
            }
            if (entidad.getColonia() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("colonia", entidad.getColonia()));
            }
            if (entidad.getNumeroCasa() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("numeroCasa", entidad.getNumeroCasa()));
            }
            if (entidad.getCodigoPostal() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("codigoPostal", entidad.getCodigoPostal()));
            }
            if (entidad.getCurp() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("curp", entidad.getCurp()));
            }
            if (entidad.getRfc() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("rfc", entidad.getRfc()));
            }
            if (entidad.getNss() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("nss", entidad.getNss()));
            }
            if (entidad.getHistorial() != null) {
                coleccionEmpleados.updateOne(filtro, Updates.set("historial", entidad.getHistorial()));
            }

            return coleccionEmpleados.find(filtro).first();
        }
    }

    @Override
    public DTOEmpleado obtener(DTOEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionEmpleados = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);

            Document filtro = new Document("_id", new ObjectId(entidad.getId()));

            return coleccionEmpleados.find(filtro).first();
        }
    }

    @Override
    public List<DTOEmpleado> obtenerLista() {
        List<DTOEmpleado> listaEmpleados = new ArrayList<>();

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOEmpleado> coleccionEmpleados = bd.getCollection(COLECCION_EMPLEADOS, DTOEmpleado.class);

            coleccionEmpleados.find().into(listaEmpleados);

            return listaEmpleados;
        }
    }
   
}
