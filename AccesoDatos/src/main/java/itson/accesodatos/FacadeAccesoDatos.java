/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import itson.entidades.Turno;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Zaira
 */
public class FacadeAccesoDatos {
    private static IAccesoTurnos<Turno> turnosDAO;
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

    public Turno crearTurno(Turno t) throws PersistenciaException {
        try{
            
            return this.turnosDAO.crear(t);
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al insertar el turno a la base de datos. ");
        }
    }
 
    public Turno eliminarTurno(Turno t) throws PersistenciaException{
        try{
            
            return turnosDAO.eliminar(t);
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar el turno a la base de datos. ");
        }
    }
    
    public Turno modificarTurno(Turno t) throws PersistenciaException{
        try{
            
            return turnosDAO.modificar(t);
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar el turno. ");
        }
    }
    
    public Turno obtenerTurno(Turno t) throws PersistenciaException{
        try{
            
            return turnosDAO.obtener(t);
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar el turno de la base de datos. ");
        }
    }
    
    public List<Turno> obtenerListaTurnos() throws PersistenciaException{
        try{
            
            return turnosDAO.obtenerLista();
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de turnos de la base de datos. ");
        }
    }
}
