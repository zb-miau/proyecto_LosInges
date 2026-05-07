/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.accesodatos.IAccesoDatos;
import itson.accesodatos.TurnosDAO;
import itson.entidades.Turno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josma
 */
public class TurnoBO{
    private final IAccesoDatos<Turno> dao;

    public TurnoBO(){
        this.dao = new TurnosDAO();
    }
    
    
    
    public DTOTurno crear(DTOTurno turno){
        Turno turnoCrear = TurnoToDTOTurnoAdapter.adaptar(turno);
        turnoCrear = dao.crear(turnoCrear);
        turno.setIdTurno(turnoCrear.getIdTurno());
                
        return turno;
    }
    
    public DTOTurno eliminar(DTOTurno turno){
        Turno turnoEliminar = TurnoToDTOTurnoAdapter.adaptar(turno);
        turnoEliminar = dao.eliminar(turnoEliminar);
        
        return turno;
    }
    
    public DTOTurno modificar(DTOTurno turno){
        Turno turnoModificar = TurnoToDTOTurnoAdapter.adaptar(turno);
        turnoModificar = dao.modificar(turnoModificar);
        DTOTurno turnoModificado = TurnoToDTOTurnoAdapter.adaptar(turnoModificar);
        
        return turnoModificado;
    }
    
    public DTOTurno obtener(DTOTurno turno){
        Turno turnoObtener = TurnoToDTOTurnoAdapter.adaptar(turno);
        turnoObtener = dao.obtener(turnoObtener);
        DTOTurno turnoRecuperado = TurnoToDTOTurnoAdapter.adaptar(turnoObtener);
        return turnoRecuperado;
    }
    
    public List<DTOTurno> obtenerLista(){
        List<Turno> turnos = dao.obtenerLista();
        List<DTOTurno> listaTurnos = new ArrayList();
        
        if (!turnos.isEmpty()){
            for (Turno t: turnos){
                DTOTurno turnoNuevo = TurnoToDTOTurnoAdapter.adaptar(t);
                listaTurnos.add(turnoNuevo);
            }
        }
        return listaTurnos;
    }
   
}
