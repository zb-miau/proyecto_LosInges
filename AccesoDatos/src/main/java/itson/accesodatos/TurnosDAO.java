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
import entidadesMongo.TurnoMongo;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoTurnos<TurnoMongo>, IAccesoMongo{
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
        return baseDatos.getCollection(COLECCION_TURNOS, Turno.class);
    }

    
    /**
     * Método para crear un turno y lo agrega a la base de datos.
     * @param turno el turno a agregar.
     * @return regresa el turno creado en la base de datos.
     */
    @Override
    public TurnoMongo crear(TurnoMongo turno) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            InsertOneResult resultado = coleccionTurnos.insertOne(turno);
            
            return turno;
         }
    }

    /**
     * Método para eliminar un turno de la base de datos.
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     */
    @Override
    public TurnoMongo eliminar(TurnoMongo turno) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);

            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));
            
            TurnoMongo eliminado = coleccionTurnos.findOneAndDelete(filtro);
            
            return eliminado;
        }
    }

    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     */
    @Override
    public TurnoMongo modificar(TurnoMongo turno) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));
           
           
            return coleccionTurnos.findOneAndUpdate(filtro, new Document());
        }
    }

    /**
     * Método para obtener un turno de la base de datos.
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     */
    @Override
    public TurnoMongo obtener(TurnoMongo turno) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtro = new Document(CAMPO_ID, new ObjectId(turno.getIdTurno()));

            return coleccionTurnos.find(filtro).first();
        }
    }
    
    /**
     * Método para obtener una lista de turnos.
     * @return regresa la lista de turnos registrados en la base de datos.
     */
    @Override
    public List<TurnoMongo> obtenerLista() {
        List<TurnoMongo> listaTurnos = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<TurnoMongo> coleccionTurnos = recuperarColeccion(bd);

            coleccionTurnos.find().into(listaTurnos);
            
            return listaTurnos;
        }
    }

}
