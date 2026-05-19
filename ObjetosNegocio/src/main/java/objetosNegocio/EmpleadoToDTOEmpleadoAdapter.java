/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import itson.entidades.Direccion;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Turno;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase adaptadora encargada de transformar datos entre las entidades de dominio
 * del Empleado y sus diferentes Objetos de Transferencia de Datos (DTO).
 *
 * @author RAMSES
 */
public class EmpleadoToDTOEmpleadoAdapter {

    private static final Logger LOGGER = Logger.getLogger(EmpleadoToDTOEmpleadoAdapter.class.getName());
    
    /**
     * Transforma un DTO de contratacion en una entidad de dominio Empleado.
     * Realiza el parseo de los datos de direccion de cadena de texto a enteros.
     *
     * @param dto Objeto DTOContratacion con los datos capturados en el registro.
     * @return Instancia de la entidad Empleado poblada, o null si el dto es nulo.
     */
    public static Empleado adaptarDTOContratacionAEntidad(DTOContratacion dto){
        if (dto == null) {
            return null;
        }

        // Reconstruir el objeto de valor Direccion
        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setColonia(dto.getColonia());
        direccion.setNumeroCasa(Integer.parseInt(dto.getNumeroCasa()));
        direccion.setCodigoPostal(Integer.parseInt(dto.getCodigoPostal()));

        // Crear la entidad Empleado utilizando el constructor completo
        Empleado empleado = new Empleado(
            dto.getNombre(),
            dto.getApellidoPaterno(),
            dto.getApellidoMaterno(),
            dto.getFechaNacimiento(),
            direccion,
            dto.getCurp(),
            dto.getRfc(),
            dto.getNss()
        );

        return empleado;
    }
    
    /**
     * Transforma una entidad Empleado a un formato simplificado de DTOContratacion.
     *
     * @param empleado Instancia de la entidad de dominio Empleado.
     * @return Objeto DTOContratacion con datos basicos del empleado, o null si es nulo.
     */
    public static DTOContratacion adaptarEntidadADTOContratacion(Empleado empleado){
            if (empleado == null) {
                return null;
            }

            // Crear el DTO usando su constructor completo
            DTOContratacion dto = new DTOContratacion(
                empleado.getNombre(),
                empleado.getApellidoPaterno(),
                empleado.getApellidoMaterno(),
                empleado.getFechaNacimiento()
            );

            return dto;
        
    }
    
    /**
     * Convierte un DTO de uso general a una entidad de dominio Empleado.
     * Vincula de forma recursiva el horario actual del empleado si este existe.
     *
     * @param dto Objeto DTOEmpleado con los datos de transporte.
     * @return Entidad Empleado con la informacion mapeada, o null si el dto es nulo.
     */
    public static Empleado adaptarDTO(DTOEmpleado dto) {
        if (dto == null) return null;

        // Creamos la entidad. 
        Empleado empleado = new Empleado(
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno()
        );
        
        if (dto.getId()!=null){
            empleado.setId(dto.getId());
        }
                
        if (dto.getHorarioActual()!= null){
            HorarioEmpleado horarioActual = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptarConEmpleado(dto.getHorarioActual(), empleado);
            empleado.setHorarioActual(horarioActual);
        }


        return empleado;
    }
    
    /**
     * Convierte una entidad de dominio Empleado a un objeto DTOEmpleado de uso general.
     * Incluye la conversion anidada del horario asociado a la entidad.
     *
     * @param empleado Instancia de la entidad de dominio Empleado.
     * @return Objeto DTOEmpleado listo para ser transferido, o null si es nulo.
     */
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
