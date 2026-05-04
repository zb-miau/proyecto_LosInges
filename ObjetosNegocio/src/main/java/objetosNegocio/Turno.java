/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.accesodatos.IAccesoDatos;
import itson.accesodatos.TurnosDAO;
import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author josma
 */
public class Turno{
    private final IAccesoDatos<DTOTurno> dao;

    public Turno() {
        this.dao = new TurnosDAO();
    }
    
    
    
    public DTOTurno crear(DTOTurno turno){
        return dao.crear(turno);
    }
    
    public DTOTurno eliminar(DTOTurno turno){
        return dao.eliminar(turno);
    }
    
    public DTOTurno modificar(DTOTurno turno){
        return dao.modificar(turno);
    }
    
    public DTOTurno obtener(DTOTurno turno){
        return dao.obtener(turno);
    }
    
    public List<DTOTurno> obtenerLista(){
        return dao.obtenerLista();
    }
   
}
