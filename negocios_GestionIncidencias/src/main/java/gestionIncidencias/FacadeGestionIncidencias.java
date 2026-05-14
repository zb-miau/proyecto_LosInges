/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;

/**
 *
 * @author jesus
 */
public class FacadeGestionIncidencias implements IGestionIncidencias {
    
    private ControlGestionIncidencias control;
    
    public FacadeGestionIncidencias() {
        this.control = new ControlGestionIncidencias();
    }
    
    @Override
    public List<DTOEmpleado> obtenerEmpleados() {
        
        return control.obtenerEmpleados();
        
    }
    
    @Override
    public void crearIncidencia(DTOIncidencia dTOIncidencia) {
        
        control.crearIncidencia(dTOIncidencia);
        
    }
    
    @Override
    public List<DTOIncidencia> obtenerIncidencias() {
        
        return control.obtenerIncidencias();
        
    }
    
    @Override
    public void validarIncidencia(DTOIncidencia incidencia) {
        
        control.validarIncidencia(incidencia);
        
    }
    
    @Override
    public void RechazarIncidencia(DTOIncidencia incidencia) {
        
        control.rechazarIncidencia(incidencia);
        
    }
    
    @Override
    public void enviarSupervisor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
