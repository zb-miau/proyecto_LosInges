/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import itson.entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira y Ramses
 */
public class EmpleadosDAO implements IAccesoDatos<Empleado>, IAccesoMongo{
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final Logger LOGGER = Logger.getLogger(EmpleadosDAO.class.getName());
    
    private static EmpleadosDAO empleadosDAO;

    public static synchronized EmpleadosDAO getInstance() {
        if (empleadosDAO == null) {
            empleadosDAO = new EmpleadosDAO();
        }
        return empleadosDAO;
    }
    
    private EmpleadosDAO(){
        
    }

    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_EMPLEADOS, Empleado.class);
    }
    

    /**
     * Crea un empleado y lo guarda en la base de datos.
     * 
     * @param entidad
     * @return Empleado DTO
     */
    @Override
    public Empleado crear(Empleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
             MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

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
    public Empleado eliminar(Empleado entidad) {
        if (entidad == null || entidad.getId() == null) return null;
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            // IMPORTANTE: Siempre usar new ObjectId para buscar por ID en Mongo
            return coleccionEmpleados.findOneAndDelete(Filters.eq("_id", new ObjectId(entidad.getId())));
        }
    }

    /**
     * Modifica a un empleado
     * 
     * @param entidad
     * @return 
     */
    @Override
    public Empleado modificar(Empleado entidad) {
        if (entidad == null || entidad.getId() == null) return null;

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            Bson filtro = Filters.eq("_id", new ObjectId(entidad.getId()));
            List<Bson> actualizaciones = new ArrayList<>();

            // Agrupamos todas las actualizaciones en una sola lista
            if (entidad.getNombre() != null) actualizaciones.add(Updates.set("nombre", entidad.getNombre()));
            if (entidad.getApellidoPaterno() != null) actualizaciones.add(Updates.set("apellido_paterno", entidad.getApellidoPaterno()));
            if (entidad.getFechaNacimiento() != null) actualizaciones.add(Updates.set("fecha_nacimiento", entidad.getFechaNacimiento()));
            if (entidad.getDireccion().getCalle() != null) actualizaciones.add(Updates.set("calle", entidad.getDireccion().getCalle()));
            if (entidad.getDireccion().getColonia() != null) actualizaciones.add(Updates.set("colonia", entidad.getDireccion().getColonia()));
            if (entidad.getDireccion().getNumeroCasa() != null) actualizaciones.add(Updates.set("numero_casa", entidad.getDireccion().getNumeroCasa()));
            if (entidad.getDireccion().getCodigoPostal() != null) actualizaciones.add(Updates.set("codigo_postal", entidad.getDireccion().getCodigoPostal()));
            if (entidad.getCurp() != null) actualizaciones.add(Updates.set("curp", entidad.getCurp()));
            if (entidad.getRfc() != null) actualizaciones.add(Updates.set("rfc", entidad.getRfc()));
            if (entidad.getNss() != null) actualizaciones.add(Updates.set("nss", entidad.getNss()));
            if (entidad.getHistorial() != null) actualizaciones.add(Updates.set("historial", entidad.getHistorial()));

            if (!actualizaciones.isEmpty()) {
                coleccionEmpleados.updateOne(filtro, Updates.combine(actualizaciones));
            }

            return coleccionEmpleados.find(filtro).first();
        }
    }

    @Override
    public Empleado obtener(Empleado entidad) {

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            // Quitamos posibles espacios o caracteres extraños del ID
            String idLimpio = entidad.getId().trim();
            Document filtro = new Document("_id", new ObjectId(idLimpio));

            return coleccionEmpleados.find(filtro).first();
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: El ID no tiene formato hexadecimal válido: " + entidad.getId());
            return null;
        }
    }

    @Override
    public List<Empleado> obtenerLista() {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
             MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            coleccionEmpleados.find().into(listaEmpleados);

            return listaEmpleados;
        }
    }


   
}
