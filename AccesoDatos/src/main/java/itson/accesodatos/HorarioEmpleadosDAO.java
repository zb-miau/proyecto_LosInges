/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO implements IAccesoHorarioEmpleado<HorarioEmpleado>, IAccesoMongo {

    private static final String COLECCION_HISTORIAL = "historial";
    private static final String CAMPO_ID_EMPLEADO = "_id";
    
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
        return baseDatos.getCollection(COLECCION_HISTORIAL, HorarioEmpleado.class);
    }
    
    @Override
    public HorarioEmpleado crear(HorarioEmpleado horario) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleado> coleccionHistorial = recuperarColeccion(bd);

            coleccionHistorial.insertOne(horario);
        

            return horario;
        }
    }

    @Override
    public HorarioEmpleado obtener(HorarioEmpleado horario) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID_EMPLEADO, new ObjectId(horario.getEmpleado().getId()));
            
            return coleccionHorarioEmpleados.find(filtro).first();
        }
    }
    

    @Override
    public List<HorarioEmpleado> obtenerLista(HorarioEmpleado horario) {
         List<HorarioEmpleado> listaHorarioEmpleados = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<HorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);
            Document filtro = new Document(CAMPO_ID_EMPLEADO, new ObjectId(horario.getEmpleado().getId()));
            
            coleccionHorarioEmpleados.find(filtro).into(listaHorarioEmpleados);

            return listaHorarioEmpleados;
        }
    }

}
