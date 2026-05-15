/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Turno;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author RAMSES
 */
public class EmpleadoToDTOEmpleadoAdapter {

    private static final Logger LOGGER = Logger.getLogger(EmpleadoToDTOEmpleadoAdapter.class.getName());
    
    public static Empleado adaptarDTO(DTOEmpleado dto) {
        if (dto == null) return null;

        // 1. Creamos la entidad. 
        // Usamos el constructor que recibe ID y datos personales.
        // Los campos que el DTO no tiene (CURP, RFC, etc.) 
        Empleado empleado = new Empleado(
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno()
        );

                
        if (dto.getHorarioActual()!= null){
            HorarioEmpleado horarioActual = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptarConEmpleado(dto.getHorarioActual(), empleado);
            empleado.setHorarioActual(horarioActual);
        }


        return empleado;
    }
    
    public static DTOEmpleado adaptarEntidad(Empleado empleado) {
        if (empleado == null) return null;

        DTOEmpleado empleadoDTO = new DTOEmpleado(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno()
        );
        
        if (empleado.getHorarioActual()!= null){
            DTOHorarioEmpleado horarioActual = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptarConEmpleado(empleado.getHorarioActual(), empleadoDTO);
            empleadoDTO.setHorarioActual(horarioActual);
        }

        return empleadoDTO;
    }
    
}
