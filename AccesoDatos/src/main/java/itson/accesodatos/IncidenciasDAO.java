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
 *
 * @author jesus
 */
public class IncidenciasDAO implements IAccesoIncidencias<Incidencia>, IAccesoMongo {

    private static final String COLECCION_INCIDENCIAS = "incidencias";
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final String CAMPO_ID = "_id";
    private static final String CAMPO_TIPO = "tipo";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_EMPLEADO = "id_empleado";
    private static final String CAMPO_DESCRIPCION = "descripcion";
    private static final String CAMPO_ESTADO = "estado";
    private static final String CAMPO_LOOKUP_EMPLEADO = "empleado";
    
    private static IncidenciasDAO incidenciasDAO;

    public static synchronized IncidenciasDAO getInstance() {
        if (incidenciasDAO == null) {
            incidenciasDAO = new IncidenciasDAO();
        }
        return incidenciasDAO;
    }

    public IncidenciasDAO() {
    }

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

    @Override
    public Incidencia obtener(Incidencia incidencia) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(incidencia.getIdIncidencia()));

            return IncidenciaMongoAIncidenciaAdapter.adaptarAIncidencia(coleccionIncidencias.findOneAndUpdate(filtro, new Document()));
        }
    }

    @Override
    public List<Incidencia> obtenerLista(String estado) {
        List<IncidenciaMongo> listaIncidencias = new ArrayList();

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<IncidenciaMongo> coleccionIncidencias = recuperarColeccion(bd);
            
            coleccionIncidencias.aggregate(Arrays.asList(
                    match(
                            new Document(CAMPO_ESTADO,estado)), 
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

    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection<IncidenciaMongo> recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_INCIDENCIAS, IncidenciaMongo.class);
    }

}
