/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import adapters.TurnoMongoATurnoAdapter;
import com.mongodb.MongoException;
import entidadesMongo.TurnoMongo;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Clase Fachada que sirve como puente entre la capa de negocios y la de persistencia.
 * @author Zaira
 */
public class FacadeAccesoDatos {
    private static IAccesoTurnos<TurnoMongo> turnosDAO;
    private static FacadeAccesoDatos fachadaDAO;
    private static final Logger LOGGER = Logger.getLogger(FacadeAccesoDatos.class.getName());
    

    public static synchronized FacadeAccesoDatos getInstance() {
        if (fachadaDAO == null) {
            fachadaDAO = new FacadeAccesoDatos();
        }
        return fachadaDAO;
    }
    
    private FacadeAccesoDatos(){
        this.turnosDAO = TurnosDAO.getInstance();
    }

    /**
     * /**
     * Método para crear un turno y lo agrega a la base de datos.
     * @param turno el turno a agregar.
     * @return regresa el turno creado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public Turno crearTurno(Turno turno) throws PersistenciaException {
        try{
            TurnoMongo turnoMongo = TurnoMongoATurnoAdapter.adaptar(turno);
            return TurnoMongoATurnoAdapter.adaptar(this.turnosDAO.crear(turnoMongo));
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al insertar el turno a la base de datos. ");
        }
    }
 
    /**
     * Método para eliminar un turno de la base de datos.
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public Turno eliminarTurno(Turno turno) throws PersistenciaException{
        try{
            TurnoMongo turnoMongo = TurnoMongoATurnoAdapter.adaptar(turno);
            return TurnoMongoATurnoAdapter.adaptar(turnosDAO.eliminar(turnoMongo));
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar el turno a la base de datos. ");
        }
    }
    
    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public Turno modificarTurno(Turno turno) throws PersistenciaException{
        try{
            
            TurnoMongo turnoMongo = TurnoMongoATurnoAdapter.adaptar(turno);
            return TurnoMongoATurnoAdapter.adaptar(turnosDAO.modificar(turnoMongo));
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar el turno. ");
        }
    }
    
    /**
     * Método para obtener un turno de la base de datos.
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public Turno obtenerTurno(Turno turno) throws PersistenciaException{
        try{
            TurnoMongo turnoMongo = TurnoMongoATurnoAdapter.adaptar(turno);
            return TurnoMongoATurnoAdapter.adaptar(turnosDAO.obtener(turnoMongo));
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar el turno de la base de datos. ");
        }
    }
    
    /**
     * Método para obtener una lista de turnos.
     * @return regresa la lista de turnos registrados en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public List<Turno> obtenerListaTurnos() throws PersistenciaException{
        try{
            List<Turno> turnosLimpios = new ArrayList();
            List<TurnoMongo> turnosMongo = turnosDAO.obtenerLista();
            for (TurnoMongo turnos: turnosMongo) {
                turnosLimpios.add(TurnoMongoATurnoAdapter.adaptar(turnos));
            }
            
            return turnosLimpios;
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de turnos de la base de datos. ");
        }
    }
}
