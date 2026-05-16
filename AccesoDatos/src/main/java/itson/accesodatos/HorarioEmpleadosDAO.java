/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.HorarioEmpleadoMongoAHorarioEmpleadoAdapter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import entidadesMongo.HorarioEmpleadoMongo;
import itson.entidades.HorarioEmpleado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO implements IAccesoHorarioEmpleado<HorarioEmpleado>, IAccesoMongo {

    private static final String COLECCION_HISTORIAL = "historial";
    private static final String CAMPO_ID_EMPLEADO = "id_empleado";
    private static final String CAMPO_ID = "_id";
    private static final String CAMPO_FECHA_FIN = "fecha_fin";
    private static final String CAMPO_FECHA_INICIO = "fecha_inicio";
    
    private static HorarioEmpleadosDAO horarioEmpleadosDAO;

    public static synchronized HorarioEmpleadosDAO getInstance() {
        if (horarioEmpleadosDAO == null) {
            horarioEmpleadosDAO = new HorarioEmpleadosDAO();
        }
        return horarioEmpleadosDAO;
    }
    
    private HorarioEmpleadosDAO(){
        
    }

    
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_HISTORIAL, HorarioEmpleadoMongo.class);
    }
    
    @Override
    public HorarioEmpleado crear(HorarioEmpleado horario) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHistorial = recuperarColeccion(bd);

            HorarioEmpleadoMongo horarioMongo = HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAMongo(horario);
            
            InsertOneResult resultado = coleccionHistorial.insertOne(horarioMongo);
            horarioMongo = coleccionHistorial.find(new Document(CAMPO_ID, resultado.getInsertedId())).first();
            return HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(horarioMongo);
        }
    }

    @Override
    public HorarioEmpleado obtenerActivo(HorarioEmpleado horario) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHorarioEmpleados = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID_EMPLEADO, horario.getIdEmpleado());
            filtro.append(CAMPO_FECHA_FIN, new Document("$exists", false));
            
            HorarioEmpleadoMongo horarioMongo = coleccionHorarioEmpleados.find(filtro).first();
            return HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarConEmpleado(horarioMongo, horario.getEmpleado());
        }
    }
    

    @Override
    public List<HorarioEmpleado> obtenerListaPorFecha(HorarioEmpleado horario, LocalDate fechaInicio, LocalDate fechaFin) {
         List<HorarioEmpleadoMongo> listaHistorial = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHistorial = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID_EMPLEADO, horario.getEmpleado().getId());
            filtro.append(CAMPO_FECHA_INICIO, new Document("$lte", fechaFin));
        
            Document condicionFin = new Document("$or", Arrays.asList(
                new Document(CAMPO_FECHA_FIN, new Document("$gte", fechaInicio)),
                new Document(CAMPO_FECHA_FIN, new Document("$exists", false))
            ));

            Document filtroFinal = new Document("$and", Arrays.asList(filtro, condicionFin));
            
            coleccionHistorial.find(filtroFinal).into(listaHistorial);
            List<HorarioEmpleado> listaLimpia = new ArrayList();
            for (HorarioEmpleadoMongo horarioMongo: listaHistorial) {
                listaLimpia.add(HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(horarioMongo));
            }
            
            return listaLimpia;
        }
    }

    @Override
    public HorarioEmpleado modificar(HorarioEmpleado horario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHistorial = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID, new ObjectId(horario.getIdHorarioEmpleado()));
            
           
            HorarioEmpleadoMongo mongo = coleccionHistorial.findOneAndReplace(
                    filtro, 
                    HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAMongo(horario));
            
            return HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(mongo);
        }
    }

}
