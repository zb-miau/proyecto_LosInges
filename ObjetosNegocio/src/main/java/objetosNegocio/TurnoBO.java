/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.PersistenciaException;
import itson.accesodatos.TurnosDAO;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
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
  
    
    
    public DTOTurno crear(DTOTurno turno) throws NegocioException{
        Turno turnoCrear = TurnoToDTOTurnoAdapter.adaptar(turno);

        try {
            //todo validaciones
            
        turnoCrear = fachadaDAO.crearTurno(turnoCrear);
        turno.setIdTurno(turnoCrear.getIdTurno());
        return turno;
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al insertar el turno");
        }
    }
    
    public DTOTurno eliminar(DTOTurno turno) throws NegocioException{
        Turno turnoEliminar = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            //todo validaciones
        turnoEliminar = fachadaDAO.eliminarTurno(turnoEliminar);
        return turno;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al eliminar el turno");
        }
        
        
    }
    
    public DTOTurno modificar(DTOTurno turno)throws NegocioException{
        Turno turnoModificar = TurnoToDTOTurnoAdapter.adaptar(turno);
        
        try {
            //todo validaciones
        turnoModificar = fachadaDAO.modificarTurno(turnoModificar);
        DTOTurno turnoModificado = TurnoToDTOTurnoAdapter.adaptar(turnoModificar);
        return turnoModificado;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el turno");
        }
        
        
    }
    
    public DTOTurno obtener(DTOTurno turno)throws NegocioException{
        Turno turnoObtener = TurnoToDTOTurnoAdapter.adaptar(turno);
        try {
            //todo validaciones
        turnoObtener = fachadaDAO.obtenerTurno(turnoObtener);
        DTOTurno turnoRecuperado = TurnoToDTOTurnoAdapter.adaptar(turnoObtener);
        return turnoRecuperado;
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al recuperar el turno");
        }
        
    }
    
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
            throw new NegocioException("Error al recuperar la lista de turnos");
        }
    }
   
}
