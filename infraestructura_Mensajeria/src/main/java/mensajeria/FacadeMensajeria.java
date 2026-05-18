/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensajeria;

import dto.DTOIncidencia;

/**
 *
 * @author jesus
 */
public class FacadeMensajeria implements IMensajeria {
    
    private ControlMensajeria control;
    
    public FacadeMensajeria(ControlMensajeria control) {
        this.control = control;
    }
    
    @Override
    public void enviarGmailSupervisor(DTOIncidencia incidencia) {
        
        control.enviarSupervisor(incidencia);
        
    }
    
}
