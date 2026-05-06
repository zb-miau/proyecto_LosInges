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
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class TurnosDAO implements IAccesoDatos<Turno>, IAccesoMongo{
    private static final String COLECCION_TURNOS = "turnos";
    
    
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_TURNOS, Turno.class);
    }

    
    @Override
    public Turno crear(Turno entidad) {
         try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Turno> coleccionTurnos = recuperarColeccion(bd);
            
            InsertOneResult resultado = coleccionTurnos.insertOne(entidad);
            
            return entidad;
         }
    }

    @Override
    public Turno eliminar(Turno entidad) {
        try(MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Turno> coleccionTurnos = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getIdTurno()));
            
            Turno eliminado = coleccionTurnos.findOneAndDelete(filtro);
            
            return eliminado;
        }
    }

    @Override
    public Turno modificar(Turno entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Turno> coleccionTurnos = recuperarColeccion(bd);
            
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
            
            if (entidad.getColorHexadecimal()!= null){
                coleccionTurnos.updateOne(filtro, Updates.set("colorEvento", entidad.getColorHexadecimal()));
            }
            
            return coleccionTurnos.find(filtro).first();

        }
    }

    @Override
    public Turno obtener(Turno entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Turno> coleccionTurnos = recuperarColeccion(bd);
            
            Document filtro = new Document("_id", new ObjectId(entidad.getIdTurno()));

            return coleccionTurnos.find(filtro).first();
        }
    }
    
    @Override
    public List obtenerLista() {
        List<Turno> listaTurnos = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<Turno> coleccionTurnos = recuperarColeccion(bd);

            coleccionTurnos.find().into(listaTurnos);
            
            return listaTurnos;
        }
    }

}
