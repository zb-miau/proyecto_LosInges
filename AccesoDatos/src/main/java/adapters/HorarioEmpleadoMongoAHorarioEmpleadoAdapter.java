/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.EmpleadoMongo;
import entidadesMongo.HorarioEmpleadoMongo;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadoMongoAHorarioEmpleadoAdapter {
    
    /**
     * Adaptador de Entidad a EntidadMongo; HorarioEmpleado a HorarioEmpleadoMongo.
     * @param horario la entidad a adaptar.
     * @return regresa el equivalente de HorarioEmpleadoMongo de la entidad del parámetro.
     */
    public static HorarioEmpleadoMongo adaptarAMongo(HorarioEmpleado horario){
        
        HorarioEmpleadoMongo horarioMongo = new HorarioEmpleadoMongo(
                EmpleadoMongoAEmpleadoAdapter.toMongo(horario.getEmpleado()),
                TurnoMongoATurnoAdapter.adaptarATurnoMongo(horario.getTurno()),
                horario.getFechaInicio(),
                horario.getFechaFin()
        );
        
        horarioMongo.setIdEmpleado(horario.getIdEmpleado());

        if (horario.getIdHorarioEmpleado() != null){
            horarioMongo.setIdHorarioEmpleado(horario.getIdHorarioEmpleado());
        }
        if (horario.getFechaCambio() != null){
            horarioMongo.setFechaCambio(horario.getFechaCambio());
        }
        
        return horarioMongo;
    }
    
    /**
     * Adaptador de Entidad a EntidadMongo cuando ya se cuenta con el empleado
     * como EmpleadoMongo. Esto evita un StackOverflow ya que los métodos se llamarían entre sí.
     * @param horario Entidad del horario.
     * @param empleado EmpleadoMongo del empleado.
     * @return regresa la entidad Mongo con la información del horario del parámetro y se le asigna a su
     * atributo empleado el EmpleadoMongo del parámetro.
     */
    public static HorarioEmpleadoMongo adaptarConEmpleado(HorarioEmpleado horario, EmpleadoMongo empleado) {
        if (horario == null) return null;

         HorarioEmpleadoMongo horarioMongo = new HorarioEmpleadoMongo(
                empleado, 
                TurnoMongoATurnoAdapter.adaptarATurnoMongo(horario.getTurno()), 
                horario.getFechaInicio(),
                horario.getFechaFin()
        );
         
        
        if (horario.getIdHorarioEmpleado() != null){
            horarioMongo.setIdHorarioEmpleado(horario.getIdHorarioEmpleado());
        }
         
        if(horario.getFechaCambio() != null){
            horarioMongo.setFechaCambio(horario.getFechaCambio());
        }
        
        return horarioMongo;
    }
    
    /**
     * Adaptador de EntidadMongo a Entidad cuando ya se cuenta con el empleado
     * como Entidad. Esto evita un StackOverflow ya que los métodos se llamarían entre sí.
     * @param horarioMongo HorarioEmpleadoMongo del horario.
     * @param empleado Entidad Empleado.
     * @return regresa la Entidad equivalente al HorarioEmpleadoMongo del parámetro y se le asigna a su
     * atributo empleado la Entidad Empleado del parámetro.
     */
    public static HorarioEmpleado adaptarConEmpleado(HorarioEmpleadoMongo horarioMongo, Empleado empleado) {
        if (horarioMongo == null) return null;

        HorarioEmpleado horario = new HorarioEmpleado(
                empleado, 
                TurnoMongoATurnoAdapter.adaptarATurno(horarioMongo.getTurno()), 
                horarioMongo.getFechaInicio(),
                horarioMongo.getFechaFin()
        );
        
        if (horarioMongo.getIdHorarioEmpleado() != null){
            horario.setIdHorarioEmpleado(horarioMongo.getIdHorarioEmpleado());
        }
        
        if (horarioMongo.getFechaCambio() != null){
            horario.setFechaCambio(horarioMongo.getFechaCambio());
        }
        
        return horario;
        
        }
    
    /**
     * Adaptador de entidadMongo a Entidad.
     * @param horarioMongo HorarioEmpleadoMongo a adaptar.
     * @return regresa la entidad equivalente al horarioMongo del parámetro.
     */
    public static HorarioEmpleado adaptarAHorario(HorarioEmpleadoMongo horarioMongo){
         HorarioEmpleado horario = new HorarioEmpleado(
                EmpleadoMongoAEmpleadoAdapter.toDomain(horarioMongo.getEmpleado()),
                TurnoMongoATurnoAdapter.adaptarATurno(horarioMongo.getTurno()),
                horarioMongo.getFechaInicio(),
                horarioMongo.getFechaFin()
        );
        
        if (horarioMongo.getIdHorarioEmpleado() != null){
            horario.setIdHorarioEmpleado(horarioMongo.getIdHorarioEmpleado());
        }
         
        if (horarioMongo.getFechaCambio() != null){
            horario.setFechaCambio(horarioMongo.getFechaCambio());
        }
        
        return horario;
    }
    

}
