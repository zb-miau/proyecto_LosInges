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
 * Clase de acceso a datos que implementa los contratos de persistencia de empleados.
 * Se encarga de comunicar la aplicacion con la base de datos NoSQL de MongoDB.
 *
 * @author Zaira y Ramses
 */
public class EmpleadosDAO implements IAccesoEmpleados<Empleado>, IAccesoMongo{
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final String CAMPO_HORARIO_ACTUAL = "horario_actual";
    private static final String CAMPO_ID = "_id";
    
    private static EmpleadosDAO empleadosDAO;

    /**
     * Devuelve la instancia unica y global de la clase EmpleadosDAO.
     * Utiliza un bloque sincronizado para asegurar que no se creen duplicados en entornos multi-hilo.
     *
     * @return La instancia unica de EmpleadosDAO para la persistencia de datos.
     */
    public static synchronized EmpleadosDAO getInstance() {
        if (empleadosDAO == null) {
            empleadosDAO = new EmpleadosDAO();
        }
        return empleadosDAO;
    }
    
    /**
     * Constructor privado que restringe la creacion de instancias desde el exterior.
     * Garantiza que la clase solo pueda ser controlada a traves de su propio metodo de acceso.
     */
    private EmpleadosDAO(){
        
    }

    /**
     * Obtiene la instancia de la base de datos de MongoDB.
     *
     * @param cliente Instancia del cliente de conexion de MongoDB.
     * @return Objeto MongoDatabase asociado a la configuracion del sistema.
     */
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    /**
     * Obtiene la coleccion de empleados desde la base de datos.
     *
     * @param baseDatos Instancia activa de la base de datos de MongoDB.
     * @return Objeto MongoCollection mapeado con la clase EmpleadoMongo.
     */
    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_EMPLEADOS, EmpleadoMongo.class);
    }
    
    /**
     * Registra un nuevo empleado en la base de datos de MongoDB.
     *
     * @param entidad Objeto de dominio con los datos del empleado a guardar.
     * @return El objeto de dominio actualizado con el ID asignado por MongoDB.
     * @throws PersistenciaException Si ocurre un error al intentar guardar en la base de datos.
     */
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
   
   /**
     * Busca un empleado utilizando su CURP encriptada de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene la CURP en texto plano a buscar.
     * @return El empleado convertido al modelo de dominio; null si no se encuentra.
     */
   @Override
    public Empleado obtenerPorCurp(Empleado empleado) {
        if (empleado == null || empleado.getCurp() == null || empleado.getCurp().trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            String curpEncriptada = Encriptador.encriptar(empleado.getCurp().trim());

            // Realizamos el filtro apuntando al atributo raiz "curp"
            EmpleadoMongo resultado = coleccion.find(Filters.eq("curp", curpEncriptada)).first();

            if (resultado == null) {
                return null;
            }

            // Convertimos al modelo de dominio usando adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Busca un empleado utilizando su RFC encriptado de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene el RFC en texto plano a buscar.
     * @return El empleado convertido al modelo de dominio; null si no se encuentra.
     */
    @Override
    public Empleado obtenerPorRfc(Empleado empleado) {
        if (empleado == null || empleado.getRfc() == null || empleado.getRfc().trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            String rfcEncriptado = Encriptador.encriptar(empleado.getRfc().trim());

            // Busqueda exacta apuntando al atributo raiz "rfc"
            EmpleadoMongo resultado = coleccion.find(Filters.eq("rfc", rfcEncriptado)).first();

            if (resultado == null) {
                return null;
            }

            // Convertimos el modelo de base de datos al modelo de dominio usando adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Busca un empleado utilizando su NSS encriptado de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene el NSS en texto plano a buscar.
     * @return El empleado convertido al modelo de dominio; null si no se encuentra.
     */
    @Override
    public Empleado obtenerPorNss(Empleado empleado) {
        if (empleado == null || empleado.getNss() == null || empleado.getNss().trim().isEmpty()) {
            return null;
        }

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);

            String nssEncriptado = Encriptador.encriptar(empleado.getNss().trim());

            // Busqueda exacta apuntando al atributo raiz "nss"
            EmpleadoMongo resultado = coleccion.find(Filters.eq("nss", nssEncriptado)).first();

            if (resultado == null) {
                return null;
            }

            // Convertimos el modelo de base de datos al modelo de dominio usando adapter
            return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
            
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca un empleado en la base de datos por medio de su identificador unico ID.
     *
     * @param entidad Objeto de dominio que contiene el ID del empleado a buscar.
     * @return El empleado convertido al modelo de dominio; null si no existe.
     */
   @Override
   public Empleado obtener(Empleado entidad) {
       try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
           MongoDatabase bd = recuperarBaseDatos(cliente);
           MongoCollection<EmpleadoMongo> coleccion = bd.getCollection("empleados", EmpleadoMongo.class);
           
           EmpleadoMongo resultado = coleccion.find(Filters.eq("_id", new ObjectId(entidad.getId()))).first();

           return EmpleadoMongoAEmpleadoAdapter.toDomain(resultado);
       } 
   }

   /**
     * Recupera todos los registros de empleados almacenados en la base de datos.
     *
     * @return Una lista de objetos Empleado convertidos al modelo de dominio.
     */
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
