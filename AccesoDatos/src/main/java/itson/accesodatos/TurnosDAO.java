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
import dto.DTOTurno;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoDatos<DTOTurno>{
    private static final String COLECCION_TURNOS = "turnos";
    
    @Override
    public DTOTurno crear(DTOTurno entidad) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOTurno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, DTOTurno.class);
            
            InsertOneResult resultado = coleccionTurnos.insertOne(entidad);
            
            return entidad;
         }
    }

    @Override
    public DTOTurno eliminar(DTOTurno entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOTurno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, DTOTurno.class);

            Document filtro = new Document("_id", new ObjectId(entidad.getIdTurno()));
            
            DTOTurno eliminado = coleccionTurnos.findOneAndDelete(filtro);
            
            return eliminado;
        }
    }

    @Override
    public DTOTurno modificar(DTOTurno entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOTurno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, DTOTurno.class);
            
            Document filtro = new Document("_id", new ObjectId(entidad.getIdTurno()));
            
            
            if (entidad.getNombre() != null){
                coleccionTurnos.updateOne(filtro, Updates.set("nombre", entidad.getNombre()));
            }
            
            if (entidad.getHoraInicio()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("horaInicio", entidad.getHoraInicio()));
            }
            
            if (entidad.getHoraFin()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("horaFin", entidad.getHoraFin()));
            }
            
            if (entidad.getDiasTrabajo()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("diasTrabajo", entidad.getDiasTrabajo()));
            }
            
            if (entidad.getColorEvento()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("colorEvento", entidad.getColorEvento()));
            }
            
            return coleccionTurnos.find(filtro).first();

        }
    }

    @Override
    public DTOTurno obtener(DTOTurno entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOTurno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, DTOTurno.class);
            
            Document filtro = new Document("_id", new ObjectId(entidad.getIdTurno()));

            return coleccionTurnos.find(filtro).first();
        }
    }
    
    @Override
    public List obtenerLista() {
        List<DTOTurno> listaTurnos = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<DTOTurno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, DTOTurno.class);

            coleccionTurnos.find().into(listaTurnos);
            
            return listaTurnos;
        }
    }

}
