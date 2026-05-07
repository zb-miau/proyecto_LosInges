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
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadosDAO  {

    private static final String COLECCION_HORARIO_EMPLEADO = "horario_empleados";
    private static HorarioEmpleadosDAO horarioEmpleadoDAO;

    /**
     * Obtener la instancia del HorarioEmpleadosDAO
     *
     * @return HorarioEmpleadosDAO
     */
    public static HorarioEmpleadosDAO getInstanceHorarioEmpleadosDAO() {
        if (horarioEmpleadoDAO == null) {
            horarioEmpleadoDAO = new HorarioEmpleadosDAO();
        }

        return horarioEmpleadoDAO;
    }

    
    @Override
    public MongoDatabase recuperarBaseDatos(MongoClient cliente) {
        return cliente.getDatabase(ManejadorConexiones.BASE_DATOS);
    }

    @Override
    public MongoCollection recuperarColeccion(MongoDatabase baseDatos) {
        return baseDatos.getCollection(COLECCION_HORARIO_EMPLEADO, HorarioEmpleado.class);
    }
    
    @Override
    public DTOHorarioEmpleado crear(DTOHorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<DTOHorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);

            InsertOneResult resultado = coleccionRestaurante.insertOne(entidad);

            return entidad;
        }
    }

    @Override
    public DTOHorarioEmpleado eliminar(DTOHorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<DTOHorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getIdEmpleado()));

            DTOHorarioEmpleado eliminado = coleccionHorarioEmpleados.findOneAndDelete(filtro);

            return eliminado;
        }
    }

    @Override
    public DTOHorarioEmpleado modificar(DTOHorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<DTOHorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);

            Document filtro = new Document("_id", new ObjectId(entidad.getIdEmpleado()));

            if (entidad.getFechaFin() != null) {
                coleccionHorarioEmpleados.updateOne(filtro, Updates.set("fechaFin", entidad.getFechaFin()));
            }

            if (entidad.getFechaIncio() != null) {
                coleccionHorarioEmpleados.updateOne(filtro, Updates.set("fechaInicio", entidad.getFechaIncio()));
            }

            if (entidad.getTurno() != null) {
                coleccionHorarioEmpleados.updateOne(filtro, Updates.set("turno", entidad.getTurno()));
            }
            if (entidad.getIdEmpleado() != null) {
                coleccionHorarioEmpleados.updateOne(filtro, Updates.set("idEmpleado", entidad.getIdEmpleado()));
            }

            return coleccionHorarioEmpleados.find(filtro).first();

        }
    }

    @Override
    public DTOHorarioEmpleado obtener(DTOHorarioEmpleado entidad) {
        try (MongoClient cliente = ManejadorConexiones.crearConexion()) {
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<DTOHorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);
            Document filtro = new Document("_id", new ObjectId(entidad.getIdEmpleado()));
            
            return coleccion.find(filtro).first();
        }
    }

    @Override
    public List<DTOHorarioEmpleado> obtenerLista() {
         List<DTOHorarioEmpleado> listaHorarioEmpleados = new ArrayList();
        
        try (MongoClient cliente = ManejadorConexiones.crearConexion()){
            MongoDatabase bd = recuperarBaseDatos(cliente);
            MongoCollection<DTOHorarioEmpleado> coleccionHorarioEmpleados = recuperarColeccion(bd);

            coleccionHorarioEmpleados.find().into(listaHorarioEmpleados);

            return listaHorarioEmpleados;
        }
    }

}
