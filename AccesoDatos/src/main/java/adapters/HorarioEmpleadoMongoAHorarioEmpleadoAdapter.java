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
