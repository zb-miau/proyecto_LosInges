/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.IncidenciaMongoAIncidenciaAdapter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.unwind;
import com.mongodb.client.result.InsertOneResult;
import entidadesMongo.IncidenciaMongo;
import itson.entidades.Incidencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Data Access Object (DAO) para la gestión de incidencias en MongoDB.
 *
 * Implementa el patrón **Singleton** para centralizar el acceso a la colección
 * y hereda comportamientos de IAccesoIncidencias y IAccesoMongo. Se encarga de
 * realizar las operaciones CRUD transformando las entidades de dominio en
 * entidades de persistencia mapeadas mediante el adaptador
 * IncidenciaMongoAIncidenciaAdapter.
 *
 *
 * @author jesus
 */
public class IncidenciasDAO implements IAccesoIncidencias<Incidencia>, IAccesoMongo {

    //       CONSTANTES DE COLECCIONES Y CAMPOS
    private static final String COLECCION_INCIDENCIAS = "incidencias";
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final String CAMPO_ID = "_id";
    private static final String CAMPO_TIPO = "tipo";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_EMPLEADO = "id_empleado";
    private static final String CAMPO_DESCRIPCION = "descripcion";
    private static final String CAMPO_ESTADO = "estado";
    private static final String CAMPO_LOOKUP_EMPLEADO = "empleado";

    /**
     * Instancia única de la clase (Patrón Singleton).
     */
    private static IncidenciasDAO incidenciasDAO;

    /**
     * Recupera la instancia única y sincronizada de IncidenciasDAO. Garantiza
     * la seguridad en entornos multihilo (Thread-Safe).
     *
     * @return Instancia única de IncidenciasDAO.
     */
    public static synchronized IncidenciasDAO getInstance() {
        if (incidenciasDAO == null) {
            incidenciasDAO = new IncidenciasDAO();
        }
        return incidenciasDAO;
    }

    /**
     * Constructor público por defecto.
     */
    public IncidenciasDAO() {
    }

    /**
     * Registra una nueva incidencia en la base de datos.
     *
     * Modifica el objeto mapeado asignándole el ID generado automáticamente por
     * MongoDB antes de retornarlo transformado de vuelta a la entidad de
     * dominio.
     *
     *
     * @param incidencia La entidad de dominio Incidencia a persistir.
     * @return La Incidencia registrada incluyendo su nuevo ID de base de datos.
     */
    @Override
    public Incidencia crear(Incidencia incidencia) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            IncidenciaMongo incidenciaMongo = IncidenciaMongoAIncidenciaAdapter.adaptarAIncidenciaMongo(incidencia);

            InsertOneResult result = coleccionIncidencias.insertOne(incidenciaMongo);
            if (result.getInsertedId() != null) {
                incidenciaMongo.setIdIncidencia(result.getInsertedId().asObjectId().getValue().toString());
            }

            return IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(incidenciaMongo);
        }
    }

    /**
     * Elimina físicamente una incidencia de la colección basándose en su ID.
     *
     * @param incidencia Entidad que contiene el ID de la incidencia a eliminar.
     * @return El objeto Incidencia que ha sido eliminado, o null si no existía.
     */
    @Override
    public Incidencia eliminar(Incidencia incidencia) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(incidencia.getIdIncidencia()));

            IncidenciaMongo eliminado = coleccionIncidencias.findOneAndDelete(filtro);

            return IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(eliminado);
        }
    }

    /**
     * Reemplaza por completo el documento de una incidencia existente con los
     * nuevos datos provistos.
     *
     * @param incidencia Entidad de dominio con los cambios actualizados.
     * @return El documento original Incidencia antes de ser modificado.
     */
    @Override
    public Incidencia modificar(Incidencia incidencia) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(incidencia.getIdIncidencia()));

            IncidenciaMongo incidenciaMongo = coleccionIncidencias.findOneAndReplace(filtro, IncidenciaMongoAIncidenciaAdapter.adaptarAIncidenciaMongo(incidencia));

            return IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(incidenciaMongo);

        }
    }

    /**
     * Busca y obtiene una incidencia por su identificador único. realiza un
     * findOneAndUpdate lo cual recupera el documento sin alterarlo.
     *
     *
     * @param incidencia Entidad que contiene el ID buscado.
     * @return La Incidencia encontrada, o null si no coincide ningún registro.
     */
    @Override
    public Incidencia obtener(Incidencia incidencia) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(incidencia.getIdIncidencia()));

            return IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(coleccionIncidencias.findOneAndUpdate(filtro, new Document()));
        }
    }

    /**
     * Recupera una lista filtrada de incidencias según su estado, resolviendo
     * en profundidad la relación con la colección de empleados mediante un
     * pipeline de agregación.
     *
     * Pasos del pipeline:
     *
     * $match: Filtra los documentos por el estado proporcionado.
     *
     * $lookup: Realiza un Left Outer Join con la colección "empleados" uniendo
     * el campo "id_empleado" con el "_id" del empleado.
     *
     * $unwind: Descompone el array de resultados devuelto por el lookup en
     * objetos individuales.
     *
     *
     * @param estado Criterio de filtrado (Ej: "PENDIENTE", "VALIDADA",
     * "RECHAZADA").
     * @return Una lista List de objetos Incidencia que cumplen con el estado.
     */
    @Override
    public List<Incidencia> obtenerLista(String estado) {
        List<IncidenciaMongo> listaIncidencias = new ArrayList();

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            coleccionIncidencias.aggregate(Arrays.asList(
                    match(
                            new Document(CAMPO_ESTADO, estado)),
                    lookup(
                            COLECCION_EMPLEADOS,
                            CAMPO_EMPLEADO,
                            CAMPO_ID,
                            CAMPO_LOOKUP_EMPLEADO
                    ),
                    unwind(
                            "$" + CAMPO_LOOKUP_EMPLEADO)
            )).into(listaIncidencias);

            List<Incidencia> incidenciasLimpias = new ArrayList<>();

            for (IncidenciaMongo incidenciaMongo : listaIncidencias) {
                incidenciasLimpias.add(IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(incidenciaMongo));

            }

            return incidenciasLimpias;
        }
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
     * Obtiene la colección de incidencias tipada explícitamente para trabajar
     * con mapeo de POJOs de tipo IncidenciaMongo.
     *
     * @param baseDatos Conexión activa a la base de datos.
     * @return La MongoCollection configurada para la entidad IncidenciaMongo.
     */
    @Override
    public MongoCollection<IncidenciaMongo> recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_INCIDENCIAS, IncidenciaMongo.class);
    }

}
