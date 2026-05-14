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
                dto.getId(),
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno()
        );

                
        if (dto.getHorarioActual()!= null){
            HorarioEmpleado horarioActual = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptarConEmpleado(dto.getHorarioActual(), empleado);
            empleado.setHorarioActual(horarioActual);
        }

        // 2. Mapeamos la lista de historial (DTO -> Entidad)
        if (dto.getHistorial() != null && !dto.getHistorial().isEmpty()) {
            LinkedList<HorarioEmpleado> historialEntidad = dto.getHistorial().stream()
                .map(dtoH -> {
                    // Mapeamos el DTOTurno de vuelta a la Entidad Turno
                    Turno turnoEntidad = (dtoH.getTurno() != null) 
                    ? TurnoToDTOTurnoAdapter.adaptar(dtoH.getTurno())
                    : null;
                    
                    // Creamos el objeto de negocio HorarioEmpleadoBO
                    return new HorarioEmpleado(
                            empleado,
                            turnoEntidad,
                            dtoH.getFechaInicio(),
                            dtoH.getFechaFin()
                    );
                })
                .collect(Collectors.toCollection(LinkedList<HorarioEmpleado>::new));

            // 3. Inyectamos la lista convertida en la entidad
            empleado.setHistorial(historialEntidad);
        } else {
            // Si el historial es nulo en el DTO, inicializamos una lista vacía
            empleado.setHistorial(new LinkedList<>());
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

        if (empleado.getHistorial() != null && !empleado.getHistorial().isEmpty()) {
            // Se especifica el tipo en el colector para evitar "cannot find symbol"
            LinkedList<DTOHorarioEmpleado> historialDTO = empleado.getHistorial().stream()
                .map(horario -> {
                    DTOTurno turnoDTO = (horario.getTurno() != null) 
                    ? TurnoToDTOTurnoAdapter.adaptar(horario.getTurno()) 
                    : null;

                    return new DTOHorarioEmpleado(
                            empleadoDTO,
                            turnoDTO,
                            horario.getFechaInicio(),
                            horario.getFechaFin()
                    );
                })
                .collect(Collectors.toCollection(LinkedList<DTOHorarioEmpleado>::new));

            empleadoDTO.setHistorial(historialDTO);
        }

        return empleadoDTO;
    }
    
}
