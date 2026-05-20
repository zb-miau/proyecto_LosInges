/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTORegistroMarca;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.RegistroMarca;

/**
 * Clase adaptadora (Mapper) encargada de la conversión de datos entre la entidad 
 * de dominio RegistroMarca y su objeto de transferencia DTORegistroMarca.
 * Esta clase facilita el flujo de información hacia la capa de presentación, 
 * asegurando que los objetos de negocio permanezcan encapsulados y protegidos 
 * de cambios en la interfaz de usuario.
 * @author josma
 */
public class RegistroMarcaToDTORegistroMarca {
    /**
     * Convierte un objeto DTORegistroMarca proveniente de la capa de presentación 
     * en una entidad RegistroMarca de la capa de negocio.
     * * El proceso reconstruye las asociaciones internas necesarias (Empleado y Horario) 
     * para que la entidad resultante sea apta para el procesamiento de reglas de negocio 
     * o persistencia.
     * @param registroDTO Objeto de transferencia de datos a convertir.
     * @return Una instancia de RegistroMarca con la información del DTO, 
     * o null si el parámetro de entrada es nulo.
     */
    public static RegistroMarca toPersistencia(DTORegistroMarca registroDTO){
        if (registroDTO  == null) {
            return null; 
        }
        //Primero contruimos el objeto completo de empleado 
        Empleado empleado = new Empleado();
        empleado.setId(registroDTO.getEmpleadoDTO().getId());
        empleado.setNombre(registroDTO.getEmpleadoDTO().getNombre());
        //Horario empleado
        HorarioEmpleado horario = new HorarioEmpleado();
        horario.setEmpleado(empleado);
        RegistroMarca marca = new RegistroMarca(
                registroDTO.getIdRegistroMarca(),
                empleado,
                horario,
                registroDTO.getEntrada(),
                registroDTO.getSalida(),
                registroDTO.getFecha()
        );
        
        return marca; 
    }
    /**
     * Convierte una entidad RegistroMarca de la capa de negocio en un 
     * DTORegistroMarca para su uso en la capa de presentación.
     * Utiliza otros adaptadores especializados para transformar los objetos anidados 
     * (Empleado y Horario) a sus versiones DTO correspondientes.
     * @param registroPersistencia La entidad de dominio a convertir.
     * @return Un objeto DTORegistroMarca con la información formateada 
     * para la vista, o null si la entrada es nula.
     */
    public static DTORegistroMarca toDTO(RegistroMarca registroPersistencia){
        if (registroPersistencia == null) {
            return null;
        }
       //Adaptar el empleado a DTO
       DTOEmpleado empleado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(registroPersistencia.getEmpleado());
       DTOHorarioEmpleado horarioEmpleado =HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(registroPersistencia.getHorarioEmpleado());
       
       DTORegistroMarca registroDTO = new DTORegistroMarca(
               registroPersistencia.getIdRegistroMarca(),
               empleado,
               horarioEmpleado,
               registroPersistencia.getRegistroEntrada(),
               registroPersistencia.getRegistroSalida(),
               registroPersistencia.getFecha()      
       );
       
       return registroDTO; 
    }
}
