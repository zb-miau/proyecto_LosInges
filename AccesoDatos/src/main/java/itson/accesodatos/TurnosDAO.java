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
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Turno;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoDatos{
    private static final String COLECCION_TURNOS = "turnos";
    
    @Override
    public Object crear(Object entidad) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, Turno.class);
            
            InsertOneResult resultado = coleccionTurnos.insertOne((Turno) entidad);
            
            return entidad;
         }
    }

    @Override
    public Object eliminar(Object entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            Turno turno = (Turno) entidad;
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, Turno.class);

            Document filtro = new Document("_id", new ObjectId(turno.getId()));
            
            Turno eliminado = coleccionTurnos.findOneAndDelete(filtro);
            
            return eliminado;
        }
    }

    @Override
    public Object modificar(Object entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            Turno turno = (Turno) entidad;
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, Turno.class);
            
            Document filtro = new Document("_id", new ObjectId(turno.getId()));
            
            
            if (turno.getNombre() != null){
                coleccionTurnos.updateOne(filtro, Updates.set("nombre", turno.getNombre()));
            }
            
            if (turno.getHoraInicio()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("horaInicio", turno.getHoraInicio()));
            }
            
            if (turno.getHoraFin()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("horaFin", turno.getHoraFin()));
            }
            
            if (turno.getDiasTrabajo()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("diasTrabajo", turno.getDiasTrabajo()));
            }
            
            if (turno.getColorEvento()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("colorEvento", turno.getColorEvento()));
            }
            
            return coleccionTurnos.find(filtro).first();

        }
    }

    @Override
    public Object obtener(Object entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            Turno turno = (Turno) entidad;
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, Turno.class);
            
            Document filtro = new Document("_id", new ObjectId(turno.getId()));

            return coleccionTurnos.find(filtro).first();
        }
    }
    
    @Override
    public List obtenerLista() {
        List<Turno> listaTurnos = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
            MongoCollection<Turno> coleccionTurnos = bd.getCollection(COLECCION_TURNOS, Turno.class);

            coleccionTurnos.find().into(listaTurnos);
            return listaTurnos;
        }
    }
    
}
