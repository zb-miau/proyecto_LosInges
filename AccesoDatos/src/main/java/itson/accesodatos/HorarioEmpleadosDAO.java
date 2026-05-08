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
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO implements IAccesoDatos<HorarioEmpleado>, IAccesoMongo {

    private static final String COLECCION_EMPLEADOS = "empleados";
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
        return baseDatos.getCollection(COLECCION_EMPLEADOS, Empleado.class);
    }
    
    @Override
    public HorarioEmpleado crear(HorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getEmpleado().getId()));
            Bson operacion = Updates.set("horario_actual", entidad);

            coleccionEmpleados.updateOne(filtro, operacion);
        

            return entidad;
        }
    }

    @Override
    public HorarioEmpleado eliminar(HorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getEmpleado().getId()));
            Bson operacion = Updates.unset("horario_actual");

            return entidad;
        }
    }

    @Override
    public HorarioEmpleado modificar(HorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Empleado> coleccionEmpleados = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getEmpleado().getId()));

            List<Bson> listaUpdates = new ArrayList<>();

            if (entidad.getFechaFin() != null) {
                listaUpdates.add(Updates.set("horario_actual.fecha_fin", entidad.getFechaFin()));
            }

            if (entidad.getFechaInicio() != null) {
                listaUpdates.add(Updates.set("horario_actual.fecha_inicio", entidad.getFechaInicio()));
            }

            if (entidad.getTurno() != null) {
                Empleado empleadoBD = coleccionEmpleados.find(filtro).first();
                if (empleadoBD != null && empleadoBD.getHorarioActual() != null) {
                    listaUpdates.add(Updates.push("historial", empleadoBD.getHorarioActual()));
                }
                listaUpdates.add(Updates.set("horario_actual.turno", entidad.getTurno()));
            }

            if (!listaUpdates.isEmpty()) {
                coleccionEmpleados.updateOne(filtro, Updates.combine(listaUpdates));
            }

            Empleado resultado = coleccionEmpleados.find(filtro).first();
            return (resultado != null) ? resultado.getHorarioActual() : null;
            }
    }

    @Override
    public HorarioEmpleado obtener(HorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);
            Document filtro = new Document("_id", new ObjectId(entidad.getEmpleado().getId()));
            
            return coleccionHorarioEmpleados.find(filtro).first();
        }
    }

    @Override
    public List<HorarioEmpleado> obtenerLista() {
         List<HorarioEmpleado> listaHorarioEmpleados = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);

            coleccionHorarioEmpleados.find().into(listaHorarioEmpleados);

            return listaHorarioEmpleados;
        }
    }

}
