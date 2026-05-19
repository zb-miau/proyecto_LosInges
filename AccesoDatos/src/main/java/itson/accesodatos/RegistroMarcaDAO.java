/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.RegistroMarcaMongoARegistroMarcaAdapter;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.match;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import entidadesMongo.RegistroMarcaMongo;
import itson.entidades.RegistroMarca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * DAO para la gestión de asistencias del empleado, podemos crear y consultar
 * nada más, no hay métodos de eliminación porque no son necearios para mi caso
 * de uso invidual.
 *
 * @author josma
 */
public class RegistroMarcaDAO implements IAccesoRegistroMarca<RegistroMarca>, IAccesoMongo {

    private static final String COLECCION_ASISTENCIA = "registro_asistencia";
    private static final String CAMPO_ID_EMPLEADO = "id_empleado";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_ID = "_id";

    private static RegistroMarcaDAO registroMarcaDAO; //Instancia de la DAO

    /**
     * Método singleton
     *
     * @return
     */
    public static synchronized RegistroMarcaDAO getInstance() {
        if (registroMarcaDAO == null) {
            registroMarcaDAO = new RegistroMarcaDAO();
        }
        return registroMarcaDAO;
    }

    /**
     * Constructor privado por defecto.
     */
    private RegistroMarcaDAO() {

    }

    /**
     * Obtiene la referencia a la base de datos de MongoDB configurada en el
     * manejador.
     *
     * @param cliente Instancia activa de MongoClient.
     * @return El objeto MongoDatabase correspondiente.
     */
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }
    /**
     * Obtiene la colección de registro de marca tipada explícitamente para trabajar
     * con mapeo de POJOs de tipo RegistroMarcaMongo.
     *
     * @param baseDatos Conexión activa a la base de datos.
     * @return La MongoCollection configurada para la entidad RegistroMarcaMongo.
     */
    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_ASISTENCIA, RegistroMarcaMongo.class);
    }

    @Override
    public RegistroMarca crear(RegistroMarca marca) throws PersistenciaException {
        //1. Establecemos conexion con la base de datos
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            //2. Recuperamos la colección de nuestra bd
            MongoCollection<RegistroMarcaMongo> coleccion = recuperarColeccion(bd);
            //3. Adaptamos nuestras entidad para que ahora sea Mongo
            RegistroMarcaMongo registroMongo = RegistroMarcaMongoARegistroMarcaAdapter.toMongo(marca);
            //4. Insertamos
            InsertOneResult result = coleccion.insertOne(registroMongo);
            //5.Procedemos a obtener el id si este no es nulo
            if (result.getInsertedId() != null) {
                //Guardamos el id
                registroMongo.setId(result.getInsertedId().asObjectId().getValue().toString());
            }
            //Regresamos una entidad limpia
            return RegistroMarcaMongoARegistroMarcaAdapter.toPersistencia(registroMongo);
        } catch (MongoException e) {
            throw new PersistenciaException("Algo fallo al intentar insertar el registro marca: " + e.getMessage());
        }
    }

    @Override
    public List<RegistroMarca> obtenerLista(String idEmpleado, LocalDate inicio, LocalDate fin) throws PersistenciaException {
        //1. Creamos la lista de RegistroMarcaMongo 
        List<RegistroMarcaMongo> registrosMongo = new ArrayList<>();
        //2. Establecemos conexion con la base de datos
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            //3. Recuperamos la colección de nuestra bd
            MongoCollection<RegistroMarcaMongo> coleccion = recuperarColeccion(bd);
            //4. Hacemos uso de un pipeline para poder hacer el filtro y traer unicamente los registros
            //que sean necesarios 
            coleccion.aggregate(Arrays.asList(
                    match(Filters.and(
                            Filters.eq(CAMPO_ID_EMPLEADO, new ObjectId(idEmpleado)),
                            Filters.gte(CAMPO_FECHA, inicio), //Mayor o igual
                            Filters.lte(CAMPO_FECHA, fin) //Menos o igual
                    ))
            )).into(registrosMongo);

            //5. Creamos una lista de entidades limpia 
            List<RegistroMarca> listaEntidadLimpia = new ArrayList<>();
            for (RegistroMarcaMongo regisMongo : registrosMongo) {
                listaEntidadLimpia.add(RegistroMarcaMongoARegistroMarcaAdapter.toPersistencia(regisMongo));
            }
            return listaEntidadLimpia;

        } catch (MongoException e) {
            throw new PersistenciaException("Algo fallo al intentar obtener los registros: " + e.getMessage());
        }

    }

    @Override
    public RegistroMarca modificar(RegistroMarca marca) throws PersistenciaException {
        //1. Establecemos conexion con la base de datos
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            //2. Recuperamos la colección de nuestra bd
            MongoCollection<RegistroMarcaMongo> coleccion = recuperarColeccion(bd);
            //3. Aplicamos el filtro con el cual vamos a buscar el registro que queremos
            //actualizar
            Document filtro = new Document(CAMPO_ID, new ObjectId(marca.getIdRegistroMarca()));
            //4. Lo adaptamos
            RegistroMarcaMongo registroMongo = RegistroMarcaMongoARegistroMarcaAdapter.toMongo(marca); //<-- OJO JOS, estamos pasando de entidad limpia A mongo
            //5. Hacemos que se actualice
            coleccion.findOneAndReplace(filtro, registroMongo);

            return marca;
        } catch (MongoException e) {
            throw new PersistenciaException("Algo fallo al intentar modificar el registro marca: " + e.getMessage());
        }
    }

    @Override
    public RegistroMarca obtenerPorEmpleadoYFecha(String idEmpleado, LocalDate fecha) throws PersistenciaException {
        //1. Establecemos conexion con la base de datos
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            //2. Recuperamos la colección de nuestra bd
            MongoCollection<RegistroMarcaMongo> coleccion = recuperarColeccion(bd);
            //3. Aplicamos el filtro con Bson, usamos este porque es de cierta manera
            //más seguro (no es propenso a errores de dedo) 

            //Este filtro lo que hace es que busca un registro de asistencia que conincida con la fecha
            //y el empleado para asi poder actualizar, practimanete se esta usando como un método auxiliar
            //para cuando llegue la hora de registrar o una entrada  o una salida
            Bson filtro = Filters.and(
                    Filters.eq(CAMPO_ID_EMPLEADO, new ObjectId(idEmpleado)),
                    Filters.eq(CAMPO_FECHA, fecha)
            );
            //4. Obtenemos el resultado
            RegistroMarcaMongo registroMongo = coleccion.find(filtro).first();
            //Dependiendo de lo que pase se hace un adapter de entidades
            if (registroMongo != null) {
                //OJO JOS -> PASA DE *MONGO* A *ENTIDAD LIMPIA*
                return RegistroMarcaMongoARegistroMarcaAdapter.toPersistencia(registroMongo);
            } else {
                return null;
            }
        } catch (MongoException e) {
            throw new PersistenciaException("Algo fallo al intentar obtener el registro marca: " + e.getMessage());
        }
    }

}
