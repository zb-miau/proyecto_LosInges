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
import java.util.stream.Collectors;

/**
 *
 * @author RAMSES
 */
public class EmpleadoToDTOEmpleadoAdapter {
    
    public static Empleado adaptarDTO(DTOEmpleado dto) {
        if (dto == null) return null;

        // 1. Creamos la entidad. 
        // Usamos el constructor que recibe ID y datos personales.
        // Los campos que el DTO no tiene (CURP, RFC, etc.) se pasan como null 
        // para no perder la estructura del constructor.
        Empleado empleado = new Empleado(
                dto.getId(),
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno(),
                null, null, null, null, null, null, null, null
        );

        // 2. Mapeamos la lista de historial (DTO -> Entidad)
        if (dto.getHistorial() != null) {
            LinkedList<HorarioEmpleado> historialEntidad = dto.getHistorial().stream()
                .map(dtoH -> {
                    // Mapeamos el DTOTurno de vuelta a la Entidad Turno
                    Turno turnoEntidad = null;
                    if (dtoH.getTurno() != null) {
                        DTOTurno dt = dtoH.getTurno();
                        turnoEntidad = new Turno(
                                dt.getIdTurno(),
                                dt.getNombre(),
                                dt.getHoraInicio(),
                                dt.getHoraFin(),
                                dt.getDiasTrabajo(),
                                dt.getColorHexadecimal()
                        );
                    }
                    
                    // Creamos el objeto de negocio HorarioEmpleadoBO
                    return new HorarioEmpleado(
                            dtoH.getEmpleado(),
                            turnoEntidad,
                            dtoH.getFechaIncio(),
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

        if (empleado.getHistorial() != null) {
            // Se especifica el tipo en el colector para evitar "cannot find symbol"
            LinkedList<DTOHorarioEmpleado> historialDTO = empleado.getHistorial().stream()
                .map(horario -> {
                    DTOTurno turnoDTO = null;
                    if (horario.getTurno() != null) {
                        Turno t = horario.getTurno();
                        turnoDTO = new DTOTurno(
                                t.getIdTurno(),
                                t.getNombre(),
                                t.getHoraInicio(),
                                t.getHoraFin(),
                                t.getDiasTrabajo()
                        );
                    }

                    return new DTOHorarioEmpleado(
                            horario.getEmpleado(),
                            turnoDTO,
                            horario.getFechaIncio(),
                            horario.getFechaFin()
                    );
                })
                .collect(Collectors.toCollection(LinkedList<DTOHorarioEmpleado>::new));

            empleadoDTO.setHistorial(historialDTO);
        }

        return empleadoDTO;
    }
    
}
