/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.EmpleadoMongoAEmpleadoAdapter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entidadesMongo.EmpleadoMongo;
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
public class EmpleadosDAO implements IAccesoEmpleados<Empleado>, IAccesoMongo{
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final String CAMPO_HORARIO_ACTUAL = "horario_actual";
    private static final String CAMPO_ID = "_id";
    
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
        return baseDatos.getCollection(COLECCION_EMPLEADOS, EmpleadoMongo.class);
    }
    


   @Override
   public Empleado crear(Empleado entidad) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           // La colección ahora es de EmpleadoMongo
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           EmpleadoMongo empleadoMongo = EmpleadoMongoAEmpleadoAdapter.toMongo(entidad);
           coleccion.insertOne(empleadoMongo);

           // Actualizamos el ID generado por Mongo en el DTO original y lo regresamos
           entidad.setId(empleadoMongo.getId());
           return entidad;
       }
   }


   @Override
   public Empleado eliminar(Empleado entidad) {
       if (entidad == null || entidad.getId() == null) return null;

       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           EmpleadoMongo eliminado = coleccion.findOneAndDelete(Filters.eq("_id", new ObjectId(entidad.getId())));
           return EmpleadoMongoAEmpleadoAdapter.toDomain(eliminado);
       }
   }


   @Override
   public Empleado modificar(Empleado entidad) {
       if (entidad == null || entidad.getId() == null) return null;

       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           ObjectId id = new ObjectId(entidad.getId());
           EmpleadoMongo mongoData = EmpleadoMongoAEmpleadoAdapter.toMongo(entidad);

           // Usamos replaceOne para actualizar todo el documento basándonos en el objeto mapeado
           coleccion.replaceOne(Filters.eq("_id", id), mongoData);

           return obtener(entidad);
       }
   }

   @Override
   public Empleado obtener(Empleado entidad) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           String idLimpio = entidad.getId().trim();
           EmpleadoMongo resultado = coleccion.find(Filters.eq("_id", new ObjectId(idLimpio))).first();

           return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
       } catch (IllegalArgumentException e) {
           System.err.println("Error: ID inválido: " + entidad.getId());
           return null;
       }
   }

   @Override
   public List<Empleado> obtenerLista() {
       List<Empleado> listaFinal = new ArrayList<>();

       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           // Recuperamos los de mongo
           List<EmpleadoMongo> listaMongo = new ArrayList<>();
           coleccion.find().into(listaMongo);

           // Convertimos cada uno a dominio
           for (EmpleadoMongo em : listaMongo) {
               listaFinal.add(EmpleadoMongoAEmpleadoAdapter.toDomain(em));
           }

           return listaFinal;
       }
   }

   public Empleado modificarHorarioActual(Empleado empleado) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           ObjectId id = new ObjectId(empleado.getId());

           // Convertimos el horario de dominio a horario mongo usando el adapter
           // (Asumiendo que agregaste el método toHorarioMongo al adapter)
           var horarioMongo = EmpleadoMongoAEmpleadoAdapter.toMongo(empleado).getHorarioActual();

           coleccion.updateOne(
               Filters.eq("_id", id), 
               Updates.set("horario_actual", horarioMongo)
           );

           return obtener(empleado);
       }
   }
   
}
