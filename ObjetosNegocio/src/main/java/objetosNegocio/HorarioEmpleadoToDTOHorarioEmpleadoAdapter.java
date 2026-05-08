/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import java.util.logging.Logger;

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
                EmpleadoToDTOEmpleadoAdapter.adaptarDTO(horarioEmpleado.getEmpleado()),
                /**
                 * Aqui como estamos haciendo uso de una entidad A que contiene
                 * una B necesitamos usar el adapter de la entidad b para poder
                 * adaptarla
                 */
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()),
                horarioEmpleado.getFechaInicio(),
                horarioEmpleado.getFechaFin()
        );

        return horarioEmpleadoCrear;
    }
    
    public static DTOHorarioEmpleado adaptarConEmpleado(HorarioEmpleado entidad, DTOEmpleado dtoEmpleado) {
        if (entidad == null) return null;

        return new DTOHorarioEmpleado(
                dtoEmpleado, 
                TurnoToDTOTurnoAdapter.adaptar(entidad.getTurno()), 
                entidad.getFechaInicio(),
                entidad.getFechaFin()
        );
    }
    
        public static HorarioEmpleado adaptarConEmpleado(DTOHorarioEmpleado entidad, Empleado empleado) {
        if (entidad == null) return null;

        return new HorarioEmpleado(
                empleado, 
                TurnoToDTOTurnoAdapter.adaptar(entidad.getTurno()), 
                entidad.getFechaInicio(),
                entidad.getFechaFin()
        );
    }

    public static DTOHorarioEmpleado adaptar(HorarioEmpleado horarioEmpleado) {

        if (horarioEmpleado == null) {
            return null;
        }

        DTOHorarioEmpleado horarioEmpleadoCrear = new DTOHorarioEmpleado(
                EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(horarioEmpleado.getEmpleado()),
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()),
                horarioEmpleado.getFechaInicio(),
                horarioEmpleado.getFechaFin()
                
        );

        return horarioEmpleadoCrear;
    }
}
