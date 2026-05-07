/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.entidades.Turno;
import java.awt.Color;

/**
 *
 * @author Zaira
 */
public class TurnoToDTOTurnoAdapter {
    
    public static Turno adaptar(DTOTurno turno){
        if (turno == null) {
            return null;
        }
        Turno turnoCrear = new Turno(
                turno.getNombre(),
                turno.getHoraInicio(),
                turno.getHoraFin(),
                turno.getDiasTrabajo(),
                turno.getColorHexadecimal()
        );
        
        return turnoCrear;
    }
    
    public static DTOTurno adaptar(Turno turno){
        Color color = Color.decode(turno.getColorHexadecimal());
        
        DTOTurno turnoCrear = new DTOTurno(
                turno.getIdTurno(),
                turno.getNombre(),
                turno.getHoraInicio(),
                turno.getHoraFin(),
                turno.getDiasTrabajo(),
                color
        );
        
        return turnoCrear;
    }
}
