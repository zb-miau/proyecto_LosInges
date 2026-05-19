/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;

/**
 *
 * @author josma
 */
public class HorarioEmpleadoToDTOHorarioEmpleadoAdapter {


    /**
     * Adaptador de DTO a Entidad
     * @param horarioEmpleado DTO del horario.
     * @return regresa la Entidad equivalente al DTO recibido en el parámetro.
     */
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
        
        if (horarioEmpleado.getEmpleado() != null) {
            horarioEmpleadoCrear.setIdEmpleado(horarioEmpleado.getEmpleado().getId());
        }
        
        if(horarioEmpleado.getIdHorarioEmpleado()!= null){
            horarioEmpleadoCrear.setIdHorarioEmpleado(horarioEmpleado.getIdHorarioEmpleado());
        }
        
        if(horarioEmpleado.getFechaCambio() != null){
            horarioEmpleadoCrear.setFechaCambio(horarioEmpleado.getFechaCambio());
        }

        return horarioEmpleadoCrear;
    }
    
    /**
     * Adaptador de Entidad a DTO cuando ya se cuenta con el empleado
     * como DTO. Esto evita un StackOverflow ya que los métodos se llamarían entre sí.
     * @param horarioEmpleado Entidad del horario.
     * @param dtoEmpleado DTO del empleado.
     * @return regresa el DTO con la información del horario del parámetro y se le asigna a su
     * atributo empleado el DTOEmpleado del parámetro.
     */
    public static DTOHorarioEmpleado adaptarConEmpleado(HorarioEmpleado horarioEmpleado, DTOEmpleado dtoEmpleado) {
        if (horarioEmpleado == null) return null;

         DTOHorarioEmpleado dtoHorarioEmpleado = new DTOHorarioEmpleado(
                dtoEmpleado, 
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()), 
                horarioEmpleado.getFechaInicio(),
                horarioEmpleado.getFechaFin()
        );
         
        if(horarioEmpleado.getIdHorarioEmpleado()!= null){
            dtoHorarioEmpleado.setIdHorarioEmpleado(horarioEmpleado.getIdHorarioEmpleado());
        }
        
        if(horarioEmpleado.getFechaCambio() != null){
            dtoHorarioEmpleado.setFechaCambio(horarioEmpleado.getFechaCambio());
        }
        
        return dtoHorarioEmpleado;
    }
    
    /**
     * Adaptador de DTO a Entidad cuando ya se cuenta con el empleado
     * como Entidad. Esto evita un StackOverflow ya que los métodos se llamarían entre sí.
     * @param horarioEmpleado DTO del Horario.
     * @param empleado Entidad Empleado.
     * @return regresa la Entidad equivalente al DTO del parámetro y se le asigna a su
     * atributo empleado la Entidad Empleado del parámetro.
     */
    public static HorarioEmpleado adaptarConEmpleado(DTOHorarioEmpleado horarioEmpleado, Empleado empleado) {
        if (horarioEmpleado == null) return null;

        HorarioEmpleado nuevoHorarioEmpleado = new HorarioEmpleado(
                empleado, 
                TurnoToDTOTurnoAdapter.adaptar(horarioEmpleado.getTurno()), 
                horarioEmpleado.getFechaInicio(),
                horarioEmpleado.getFechaFin()
        );
        
        if(horarioEmpleado.getIdHorarioEmpleado()!= null){
            nuevoHorarioEmpleado.setIdHorarioEmpleado(horarioEmpleado.getIdHorarioEmpleado());
        }
        
        if(horarioEmpleado.getFechaCambio() != null){
            nuevoHorarioEmpleado.setFechaCambio(horarioEmpleado.getFechaCambio());
        }
        
        return nuevoHorarioEmpleado;
    }

    /**
     * Adaptador de Entidad a DTO
     * @param horarioEmpleado Entidad del horario.
     * @return regresa el DTO con la informacion de la entidad recibida en el parámetro.
     */
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
        
        if(horarioEmpleado.getIdHorarioEmpleado()!= null){
            horarioEmpleadoCrear.setIdHorarioEmpleado(horarioEmpleado.getIdHorarioEmpleado());
        }
        
        
        if(horarioEmpleado.getFechaCambio() != null){
            horarioEmpleadoCrear.setFechaCambio(horarioEmpleado.getFechaCambio());
        }

        return horarioEmpleadoCrear;

    }
    
   
}
