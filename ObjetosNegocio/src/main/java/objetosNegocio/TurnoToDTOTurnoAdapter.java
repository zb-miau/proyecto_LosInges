/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOTurno;
import itson.entidades.Turno;
import java.awt.Color;
import java.util.logging.Logger;

/**
 *
 * @author Zaira
 */
public class TurnoToDTOTurnoAdapter {

    private static final Logger LOGGER = Logger.getLogger(TurnoToDTOTurnoAdapter.class.getName());
    
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
        String colorString = turno.getColorHexadecimal().trim();
        int r = Integer.parseInt(colorString.substring(1, 3), 16);
        int g = Integer.parseInt(colorString.substring(3, 5), 16);
        int b = Integer.parseInt(colorString.substring(5, 7), 16);

        Color color = new Color(r, g, b);
        
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
