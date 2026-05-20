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
import itson.entidades.Empleado;
import itson.entidades.RegistroMarca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *DAO para la gestión de las asistencias (marcas) de los empleados en MongoDB.
 * Implementa las operaciones de persistencia para registrar entradas/salidas y realizar consultas
 * filtradas por empleado y rangos de fechas.
 * * Esta clase utiliza el patrón Singleton para garantizar una única instancia de acceso.
 * @author josma
 */
public class RegistroMarcaDAO implements IAccesoRegistroMarca<RegistroMarca>, IAccesoMongo {

    private static final String COLECCION_ASISTENCIA = "registro_asistencia";
    private static final String CAMPO_ID_EMPLEADO = "id_empleado";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_ID = "_id";
    /**
     * Instancia unica de la clase.
     */
    private static RegistroMarcaDAO registroMarcaDAO; //Instancia de la DAO

    /**
     * Obtiene la instancia única de RegistroMarcaDAO.
     * Si la instancia no existe, la crea de forma sincronizada para asegurar hilos.
     * @return Instancia única de RegistroMarcaDAO.
     */
    public static synchronized RegistroMarcaDAO getInstance() {
        if (registroMarcaDAO == null) {
            registroMarcaDAO = new RegistroMarcaDAO();
        }
        return registroMarcaDAO;
    }

    /**
     * Constructor privado para evitar instanciación externa (Singleton).
     */
    private RegistroMarcaDAO() {

    }

    /**
     * Recupera la base de datos de MongoDB configurada mediante el cliente proporcionado.
     * @param cliente Instancia activa de MongoClient.
     * @return Objeto MongoDatabase para realizar operaciones.
     */
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }
    /**
     * Recupera la colección de registros de asistencia tipada para mapeo automático de POJOs.
     * @param baseDatos Conexión activa a la base de datos.
     * @return Una MongoCollection configurada para objetos de tipo RegistroMarcaMongo.
     */
    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_ASISTENCIA, RegistroMarcaMongo.class);
    }
    /**
     * Inserta un nuevo registro de asistencia en la base de datos.
     * Realiza la conversión de la entidad de dominio a formato de persistencia y asigna el ID generado.
     * @param marca Objeto de dominio RegistroMarca a persistir.
     * @return El objeto RegistroMarca procesado con su identificador de base de datos asignado.
     * @throws PersistenciaException Si ocurre un error durante la comunicación con MongoDB.
     */
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
    /**
     * Obtiene una lista de asistencias pertenecientes a un empleado específico dentro de un rango de fechas.
     * Utiliza un pipeline de agregación para filtrar por ID de empleado y fechas (gte/lte).
     * @param empleado Identificador único del empleado (formato String para conversión a ObjectId).
     * @param inicio Fecha inicial del rango de búsqueda (inclusive).
     * @param fin Fecha final del rango de búsqueda (inclusive).
     * @return Lista de RegistroMarca que coinciden con los criterios.
     * @throws PersistenciaException Si ocurre un error en la ejecución del pipeline de agregación.
     */
    @Override
    public List<RegistroMarca> obtenerLista(Empleado empleado, LocalDate inicio, LocalDate fin) throws PersistenciaException {
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
                            Filters.eq(CAMPO_ID_EMPLEADO, new ObjectId(empleado.getId())),
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
    /**
     * Actualiza un registro de asistencia existente mediante su identificador único.
     * Realiza un reemplazo completo del documento en la colección.
     * @param marca Objeto RegistroMarca con los datos actualizados e ID válido.
     * @return El mismo objeto {@link RegistroMarca} proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la actualización del documento.
     */
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
    /**
     * Busca un registro de marca específico que coincida exactamente con un empleado y una fecha.
     * Este método es útil para determinar si un empleado ya registró su entrada en un día determinado.
     * @param empleado Identificador único del empleado.
     * @param fecha Fecha exacta de la asistencia buscada.
     * @return El objeto RegistroMarca encontrado, o null si no existe registro.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public RegistroMarca obtenerPorEmpleadoYFecha(Empleado empleado, LocalDate fecha) throws PersistenciaException {
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
                    Filters.eq(CAMPO_ID_EMPLEADO, new ObjectId(empleado.getId())),
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
