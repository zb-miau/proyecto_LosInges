/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.TurnoMongo;
import itson.entidades.Turno;

/**
 *
 * @author Zaira
 */
public class TurnoMongoATurnoAdapter {
    public static Turno adaptarATurno(TurnoMongo turnoMongo){
        Turno turno = new Turno(
                turnoMongo.getIdTurno(),
                turnoMongo.getNombre(),
                turnoMongo.getHoraInicio(),
                turnoMongo.getHoraFin(),
                turnoMongo.getDiasTrabajo(),
                turnoMongo.getColorHexadecimal()
        );
        
        return turno;
    }
    
    public static TurnoMongo adaptarATurnoMongo(Turno turno){
        TurnoMongo turnoMongo = new TurnoMongo(
                turno.getNombre(),
                turno.getHoraInicio(),
                turno.getHoraFin(),
                turno.getDiasTrabajo(),
                turno.getColorHexadecimal()
        );
        
        return turnoMongo;
    }
}
