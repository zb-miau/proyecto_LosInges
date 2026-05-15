/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.PersistenciaException;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Clase de negocios que valida la información de los DTO antes de
 * pasarlos a la capa de persistencia.
 * @author josma
 */
public class TurnoBO{
    private static TurnoBO turnoBO;
    private static FacadeAccesoDatos fachadaDAO;
    private static final Logger LOGGER = Logger.getLogger(TurnoBO.class.getName());

    public static synchronized TurnoBO getInstance() {
        if (turnoBO == null) {
            turnoBO = new TurnoBO();
        }
        return turnoBO;
    }
    
    private TurnoBO(){
        this.fachadaDAO = FacadeAccesoDatos.getInstance();

    }
  
    
    /**
     * Método que valida la información del DTO recibido 
     * para crear un turno y agregarlo a la base de datos.
     * @param turno el turno a agregar.
     * @return regresa el DTO del turno creado.
     * @throws NegocioException Lanza una excepción si los datos necesarios
     * del turno están incompletos, incorrectos o si hay un error al acceder
     * a la base de datos.
     */
    public DTOTurno crear(DTOTurno turno) throws NegocioException{
        Turno turnoCrear = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            if (turno == null){
                throw new NegocioException("Error al insertar el turno: no se puede guardar un turno vacío.");
            }
            
            if (turno.getNombre().isBlank()){
                throw new NegocioException("Error al insertar el turno: el nombre no puede estar vacío.");
            }
            
            if (turno.getHoraInicio() == null){
                throw new NegocioException("Error al insertar el turno: la hora de inicio no puede estar vacía.");
            } else if (turno.getHoraFin() != null && turno.getHoraInicio().isAfter(turno.getHoraFin())){
                throw new NegocioException("Error al insertar el turno: la hora de inicio no puede ser posterior a la hora de fin.");
            }
            
            
            turnoCrear = fachadaDAO.crearTurno(turnoCrear);
            if (turnoCrear.getIdTurno() == null){
                throw new NegocioException("Error al insertar el turno: no pudo guardarse en la base de datos. Identificador nulo.");
            }
            
            turno.setIdTurno(turnoCrear.getIdTurno());
            
            
            return turno;
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al insertar el turno: " + ex.getMessage());
        }
    }
    
    /**
     * Método para eliminar un turno de la base de datos.
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     * @throws NegocioException Lanza una excepción si hay un error al acceder
     * a la base de datos.
     */
    public DTOTurno eliminar(DTOTurno turno) throws NegocioException{
        Turno turnoEliminar = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            if (turno.getIdTurno() == null){
                throw new NegocioException("Error al eliminar el turno: id nulo.");
            }
            turnoEliminar = fachadaDAO.eliminarTurno(turnoEliminar);
            return turno;
            
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al eliminar el turno: " + ex.getMessage());
        }
    }
    
    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     * @throws NegocioException Lanza una excepción si hay un error al acceder
     * a la base de datos. 
     */
    public DTOTurno modificar(DTOTurno turno)throws NegocioException{
        Turno turnoModificar = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            //validaciones
            
            turnoModificar = fachadaDAO.modificarTurno(turnoModificar);
            DTOTurno turnoModificado = TurnoToDTOTurnoAdapter.adaptar(turnoModificar);
            return turnoModificado;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el turno: " + ex.getMessage());
        }
        
        
    }
    
    /**
     * Método para obtener un turno de la base de datos.
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     * @throws NegocioException 
     */
    public DTOTurno obtener(DTOTurno turno)throws NegocioException{
        Turno turnoObtener = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            if (turno.getIdTurno() == null){
                throw new NegocioException("Error al recuperar el turno: id nulo.");
            }
            turnoObtener = fachadaDAO.obtenerTurno(turnoObtener);
            DTOTurno turnoRecuperado = TurnoToDTOTurnoAdapter.adaptar(turnoObtener);
            return turnoRecuperado;
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al recuperar el turno: " + ex.getMessage());
        }
        
    }
    
    /**
     * Método para obtener una lista de turnos.
     * @return regresa la lista de turnos registrados en la base de datos.
     */
    public List<DTOTurno> obtenerLista()throws NegocioException{
        try {
        List<Turno> turnos = fachadaDAO.obtenerListaTurnos();
        
        List<DTOTurno> listaTurnos = new ArrayList();
        
        if (!turnos.isEmpty()){
            for (Turno t: turnos){
                DTOTurno turnoNuevo = TurnoToDTOTurnoAdapter.adaptar(t);
                listaTurnos.add(turnoNuevo);
            }
        }
        return listaTurnos;
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al recuperar la lista de turnos: " + ex.getMessage());
        }
    }
   
}
