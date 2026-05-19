/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.EmpleadoMongoAEmpleadoAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import encriptador.Encriptador;
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
   public Empleado crear(Empleado entidad) throws PersistenciaException{
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

           EmpleadoMongo empleadoMongo = EmpleadoMongoAEmpleadoAdapter.toMongo(entidad);
           coleccion.insertOne(empleadoMongo);

           entidad.setId(empleadoMongo.getId());
           return entidad;
       }catch(MongoException ex){
           throw new PersistenciaException("No fue posible persistir el empleado en la base de datos.");
       }
   }
   
   @Override
    public Empleado obtenerPorCurp(Empleado empleado) {
        if (empleado == null || empleado.getCurp() == null || empleado.getCurp().trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            // Pasamos la CURP limpia por tu metodo estatico para obtener la cadena cifrada exacta
            String curpEncriptada = Encriptador.encriptar(empleado.getCurp().trim());

            // Realizamos el filtro apuntando a la propiedad raiz "curp" de tu documento en Mongo
            EmpleadoMongo resultado = coleccion.find(Filters.eq("curp", curpEncriptada)).first();

            if (resultado == null) {
                return null;
            }

            // Convertimos al modelo de dominio usando adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            System.err.println("Error al consultar el empleado por CURP en MongoDB: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Empleado obtenerPorRfc(Empleado empleado) {
        if (empleado == null || empleado.getRfc() == null || empleado.getRfc().trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            // Pasamos el RFC limpio por el metodo estatico para obtener la cadena cifrada exacta
            String rfcEncriptado = Encriptador.encriptar(empleado.getRfc().trim());

            // Busqueda exacta apuntando al atributo raiz "rfc" del documento en MongoDB
            EmpleadoMongo resultado = coleccion.find(Filters.eq("rfc", rfcEncriptado)).first();

            // Validamos si MongoDB encontro el documento para evitar NullPointerException
            if (resultado == null) {
                return null;
            }

            // Convertimos el modelo de base de datos al modelo de dominio usando tu adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            System.err.println("Error al consultar el empleado por RFC en MongoDB: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Empleado obtenerPorNss(Empleado empleado) {
        if (empleado == null || empleado.getNss() == null || empleado.getNss().trim().isEmpty()) {
            System.out.println("Busqueda abortada: El objeto empleado o su NSS estan vacios.");
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            // Pasamos el NSS limpio por el metodo estatico para obtener la cadena cifrada exacta
            String nssEncriptado = Encriptador.encriptar(empleado.getNss().trim());

            System.out.println("-> [DEBUG PERSISTENCIA] Buscando empleado con el NSS cifrado: " + nssEncriptado);

            // Busqueda exacta apuntando al atributo raiz "nss" del documento en MongoDB
            EmpleadoMongo resultado = coleccion.find(Filters.eq("nss", nssEncriptado)).first();

            // Validamos si MongoDB encontro el documento para evitar NullPointerException
            if (resultado == null) {
                System.out.println("-> [DEBUG PERSISTENCIA] No se encontro ningun empleado con el NSS proporcionado.");
                return null;
            }

            // Convertimos el modelo de base de datos al modelo de dominio usando tu adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            System.err.println("Error al consultar el empleado por NSS en MongoDB: " + e.getMessage());
            return null;
        }
    }

   @Override
   public Empleado obtener(Empleado entidad) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);
           
           EmpleadoMongo resultado = coleccion.find(Filters.eq("_id", new ObjectId(entidad.getId()))).first();

           return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
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

   /**
     * Método que recupera el empleado y modifica su atributo de horario actual.
     * @param empleado empleado al que se le va a modificar su horario.
     * @return el empleado con su horario modificado.
     */
   public Empleado modificarHorarioActual(Empleado empleado) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection(COLECCION_EMPLEADOS, EmpleadoMongo.class);
           Document filtro = new Document(CAMPO_ID, new ObjectId(empleado.getId()));
           
           EmpleadoMongo mongo = EmpleadoMongoAEmpleadoAdapter.toMongo(empleado);
           mongo = coleccion.findOneAndUpdate(filtro, Updates.set(CAMPO_HORARIO_ACTUAL, mongo.getHorarioActual()));
           
           return EmpleadoMongoAEmpleadoAdapter.toDomain(mongo);
       }
   }
   
}
