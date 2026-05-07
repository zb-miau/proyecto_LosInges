/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOHorarioEmpleado;
import itson.entidades.HorarioEmpleado;

/**
 *
 * @author josma
 */
public class HorarioEmpleadoToDTOHorarioEmpleadoAdapter {

    public static HorarioEmpleado adaptar(DTOHorarioEmpleado horarioEmpleado) {

        if (horarioEmpleado == null) {
            return null;
        }

        HorarioEmpleado horarioEmpleadoCrear = new HorarioEmpleado(
                horarioEmpleado.getEmpleado(),
                /**
                 * Aqui como estamos haciendo uso de una entidad A que contiene
                 * una B necesitamos usar el adapter de la entidad b para poder
                 * adaptarla
                 */
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()),
                horarioEmpleado.getFechaIncio(),
                horarioEmpleado.getFechaFin()
        );

        return horarioEmpleadoCrear;
    }

    public static DTOHorarioEmpleado adaptar(HorarioEmpleado horarioEmpleado) {

        if (horarioEmpleado == null) {
            return null;
        }

        DTOHorarioEmpleado horarioEmpleadoCrear = new DTOHorarioEmpleado(
                horarioEmpleado.getEmpleado(),
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()),
                horarioEmpleado.getFechaInicio(),
                horarioEmpleado.getFechaFin()
                
        );

        return horarioEmpleadoCrear;
    }
}
