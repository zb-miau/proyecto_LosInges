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
 *Clase que sirve como adaptador para convertir de entidad a dto y viceversa
 * @author josma
 */
public class RegistroMarcaToDTORegistroMarca {
    /**
     * Convertir de DTO a persistencia (entidad limpia) 
     * @param registroDTO
     * @return 
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
     * Convertir de entidad limpia (persistencia) a DTO
     * @param registroPersistencia
     * @return 
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
