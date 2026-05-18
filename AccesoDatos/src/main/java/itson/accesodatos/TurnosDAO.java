/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.TurnoMongoATurnoAdapter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import entidadesMongo.TurnoMongo;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoTurnos<Turno>, IAccesoMongo{
    private static final String COLECCION_TURNOS = "turnos";
    private static final String CAMPO_ID = "_id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_HORA_INICIO = "hora_inicio";
    public static final String CAMPO_HORA_FIN = "hora_fin";
    public static final String CAMPO_DIAS_TRABAJO = "dias_trabajo";
    public static final String CAMPO_COLOR_HEXADECIMAL = "color_hexadecimal";
    private static TurnosDAO turnosDAO;

    public static synchronized TurnosDAO getInstance() {
        if (turnosDAO == null) {
            turnosDAO = new TurnosDAO();
        }
        return turnosDAO;
    }
    
    /**
     * Constructor por defecto
     */
    private TurnosDAO(){
        
    }
    
    /**
     * Método que permite recuperar la base de datos con pojoCodec 
     * desde el cliente.
     * @param cliente Cliente de la base de datos.
     * @return Base de datos de MongoDB.
     */
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    /**
     * Método que permite recuperar la colección en la que va a 
     * trabajarse utilizando la base de datos.
     * @param baseDatos Base de datos de MongoDB.
     * @return Colección en la que va a trabajarse.
     */
    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_TURNOS, TurnoMongo.class);
    }

    
    /**
     * Método para crear un turno y lo agrega a la base de datos.
     * @param turno el turno a agregar.
     * @return regresa el turno creado en la base de datos.
     */
    @Override
    public Turno crear(Turno turno) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            TurnoMongo turnoMongo = TurnoMongoATurnoAdapter.adaptarATurnoMongo(turno);
            
            InsertOneResult resultado = coleccionTurnos.insertOne(turnoMongo);
            turnoMongo = coleccionTurnos.find(new Document(CAMPO_ID, resultado.getInsertedId())).first();
            return TurnoMongoATurnoAdapter.adaptarATurno(turnoMongo);
         }
    }

    /**
     * Método para eliminar un turno de la base de datos.
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     */
    @Override
    public Turno eliminar(Turno turno) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));
            
            TurnoMongo eliminado = coleccionTurnos.findOneAndDelete(filtro);
            
            return TurnoMongoATurnoAdapter.adaptarATurno(eliminado);
        }
    }

    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     */
    @Override
    public Turno modificar(Turno turno) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));
           
            TurnoMongo mongo = coleccionTurnos.findOneAndReplace(filtro, TurnoMongoATurnoAdapter.adaptarATurnoMongo(turno));
            
            return TurnoMongoATurnoAdapter.adaptarATurno(mongo);
        }
    }

    /**
     * Método para obtener un turno de la base de datos.
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     */
    @Override
    public Turno obtener(Turno turno) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));

            return TurnoMongoATurnoAdapter.adaptarATurno(coleccionTurnos.find(filtro).first());
        }
    }
    
    /**
     * Método para obtener una lista de turnos.
     * @return regresa la lista de turnos registrados en la base de datos.
     */
    @Override
    public List<Turno> obtenerLista() {
        List<TurnoMongo> listaTurnos = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);

            coleccionTurnos.find().into(listaTurnos);
            List<Turno> listaLimpia = new ArrayList();
            for (TurnoMongo turnoMongo: listaTurnos) {
                listaLimpia.add(TurnoMongoATurnoAdapter.adaptarATurno(turnoMongo));
            }
            
            return listaLimpia;
        }
    }

    @Override
    public boolean turnoDuplicado(Turno turno) throws PersistenciaException {
         try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtroAnd = new Document("$and", Arrays.asList(
                    new Document(CAMPO_HORA_INICIO, turno.getHoraInicio()),
                    new Document(CAMPO_HORA_FIN, turno.getHoraFin()), 
                    new Document(CAMPO_DIAS_TRABAJO, turno.getDiasTrabajo())));
            
            
            return coleccionTurnos.find(filtroAnd).first() != null;
         }
    }

}
