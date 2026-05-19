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
    
    /**
     * Constructor
     */
    private HorarioEmpleadosDAO(){
        
    }

     /**
     * Obtiene la referencia a la base de datos de MongoDB configurada en el
     * manejador.
     * @param cliente Instancia activa de MongoClient.
     * @return El objeto MongoDatabase correspondiente.
     */
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    /**
     * Obtiene la colección del historial configurada específicamente para trabajar
     * con mapeo de POJOs de tipo HorarioEmpleadoMongo.
     * @param baseDatos Conexión activa a la base de datos.
     * @return La MongoCollection configurada para la entidad HorarioEmpleadoMongo.
     */
    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_HISTORIAL, HorarioEmpleadoMongo.class);
    }
    /**
     * Método para crear un horario y lo agrega a la base de datos.
     * @param horario el horario a agregar.
     * @return regresa el horario creado en la base de datos.
     */
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

    /**
     * Método para obtener una lista de horarios de la base de datos
     * que se traslapan con el horario del parámetro.
     * @param horario horario a obtener.
     * @return regresa la lista de horarios que se traslapan con el horario
     * del parámetro.
     */
    @Override
    public List<HorarioEmpleado> obtenerActivo(HorarioEmpleado horario) {
        List<HorarioEmpleadoMongo> traslapes = new ArrayList<>();
        List<HorarioEmpleado> traslapesLimpios = new ArrayList<>();
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHorarioEmpleados = recuperarColeccion(bd);
            
            Document filtroEmpleado =  new Document(CAMPO_ID_EMPLEADO, horario.getIdEmpleado());
            
            Document filtroInicio;
            if (horario.getFechaFin() != null) {
                filtroInicio = new Document(CAMPO_FECHA_INICIO, new Document("$lt", horario.getFechaFin()));
            } else {
                filtroInicio = new Document(CAMPO_ID_EMPLEADO, horario.getIdEmpleado());
            }
            
            Document filtroFin = new Document("$or", Arrays.asList(
                    new Document(CAMPO_FECHA_FIN, new Document("$gt", horario.getFechaInicio())),
                    new Document(CAMPO_FECHA_FIN, null),
                    new Document(CAMPO_FECHA_FIN, new Document("$exists", false))
                ));
            
            Document filtroFinal = new Document("$and", Arrays.asList(filtroEmpleado, filtroInicio, filtroFin));
            
            coleccionHorarioEmpleados.find(filtroFinal).into(traslapes);
            
            for (HorarioEmpleadoMongo horarioMongo: traslapes) {
                traslapesLimpios.add(HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(horarioMongo));
            }
            
            
            return traslapesLimpios;
        }
    }
    

    /**
     *  Método para obtener una lista de horarios dentro de una fecha específica.
     * @param horario horario con el id del empleado a filtrar
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFin fecha de fin del rango
     * @return regresa la lista de horarios dentro del rango de fechas.
     */
    @Override
    public List<HorarioEmpleado> obtenerListaPorFecha(HorarioEmpleado horario, LocalDate fechaInicio, LocalDate fechaFin) {
         List<HorarioEmpleadoMongo> listaHistorial = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHistorial = recuperarColeccion(bd);
            Document filtroEmpleado = new Document(CAMPO_ID_EMPLEADO, horario.getEmpleado().getId());
            
            Document filtroInicio = new Document(CAMPO_FECHA_INICIO, new Document("$lte", fechaFin));
            Document filtroFin = new Document("$or", Arrays.asList(
                new Document(CAMPO_FECHA_FIN, new Document("$gte", fechaInicio)),
                new Document(CAMPO_FECHA_FIN, null),   
                new Document(CAMPO_FECHA_FIN, new Document("$exists", false))
            ));

            Document filtroFinal = new Document("$and", Arrays.asList(filtroEmpleado, filtroInicio, filtroFin));
            
            coleccionHistorial.find(filtroFinal).into(listaHistorial);
            List<HorarioEmpleado> listaLimpia = new ArrayList();
            for (HorarioEmpleadoMongo horarioMongo: listaHistorial) {
                listaLimpia.add(HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(horarioMongo));
            }
            
            return listaLimpia;
        }
    }

    /**
     * Método para modificar un horario de la base de datos.
     * @param horario el horario a modificar.
     * @return regresa el horario modificado en la base de datos.
     */
    @Override
    public HorarioEmpleado modificar(HorarioEmpleado horario) {
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

    /**
     * Método para eliminar un horario de la base de datos.
     * @param horario el horario a eliminar.
     * @return regresa el horario eliminado en la base de datos.
     */
    @Override
    public HorarioEmpleado eliminar(HorarioEmpleado horario) throws PersistenciaException {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleadoMongo> coleccionHistorial = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID, new ObjectId(horario.getIdHorarioEmpleado()));
            
             HorarioEmpleadoMongo mongo = coleccionHistorial.findOneAndDelete(filtro);
            
            return HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarAHorario(mongo);
        }
    }
    
    

}
