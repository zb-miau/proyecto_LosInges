/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import itson.entidades.Turno;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Clase Fachada que sirve como puente entre la capa de negocios y la de persistencia.
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
            
            return this.turnosDAO.crear(turno);
            
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
            
            return turnosDAO.eliminar(turno);
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar el turno a la base de datos. ");
        }
    }
    
    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     * @param cambios mapa que contiene los cambios a realizar en el turno.
     * @throws PersistenciaException Lanza una excepción si hay
     * un problema al acceder a la base de datos.
     */
    public Turno modificarTurno(Turno turno, Map<String,Object> cambios) throws PersistenciaException{
        try{
            
            return turnosDAO.modificar(turno, cambios);
            
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
            
            return turnosDAO.obtener(turno);
            
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
            
            return turnosDAO.obtenerLista();
            
        } catch (MongoException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de turnos de la base de datos. ");
        }
    }
}
