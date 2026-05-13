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
import itson.entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author jesus
 */
public class IncidenciasDAO implements IAccesoIncidencias<Incidencia>, IAccesoMongo {

    private static final String COLECCION_INCIDENCIAS = "incidencias";
    private static final String CAMPO_ID = "_id";
    private static final String CAMPO_TIPO = "tipo";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_EMPLEADO = "id_empleado";
    private static final String CAMPO_DESCRIPCION = "descripcion";
    private static final String CAMPO_ESTADO = "estado";
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
    public Incidencia crear(Incidencia entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Incidencia> coleccionIncidencias = recuperarColeccion(bd);

            coleccionIncidencias.insertOne(entidad);

            return entidad;
        }
    }

    @Override
    public Incidencia eliminar(Incidencia entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Incidencia> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(entidad.getIdIncidencia()));

            Incidencia eliminado = coleccionIncidencias.findOneAndDelete(filtro);

            return eliminado;
        }
    }

    @Override
    public Incidencia modificar(Incidencia entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Incidencia> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(entidad.getIdIncidencia()));

            if (entidad.getTipo() != null) {
                coleccionIncidencias.updateOne(filtro, Updates.set("tipo", entidad.getTipo()));
            }
            if (entidad.getIdEmpleado() != null) {
                coleccionIncidencias.updateOne(filtro, Updates.set("id_empleado", entidad.getIdEmpleado()));
            }
            if (entidad.getDescripcion() != null) {
                coleccionIncidencias.updateOne(filtro, Updates.set("descripcion", entidad.getDescripcion()));
            }
            if (entidad.getFecha() != null) {
                coleccionIncidencias.updateOne(filtro, Updates.set("fecha", entidad.getFecha()));
            }
            if (entidad.getEstado() != null) {
                coleccionIncidencias.updateOne(filtro, Updates.set("estado", entidad.getEstado()));
            }

            return coleccionIncidencias.find(filtro).first();

        }
    }

    @Override
    public Incidencia obtener(Incidencia entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Incidencia> coleccionIncidencias = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(entidad.getIdIncidencia()));

            return coleccionIncidencias.find(filtro).first();
        }
    }

    @Override
    public List<Incidencia> obtenerLista() {
        List<Incidencia> listaIncidencias = new ArrayList();

        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Incidencia> coleccionIncidencias = recuperarColeccion(bd);

            coleccionIncidencias.find().into(listaIncidencias);

            return listaIncidencias;
        }
    }

    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_INCIDENCIAS, Incidencia.class);
    }

}
