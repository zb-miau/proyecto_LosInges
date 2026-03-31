/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignarHorario;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author RAMSES
 */
public class ControlAsignarHorario{

    /**
     *  Creamos la lista mock de los empleados para despues regresarlos
     * @return List DTOEmpleado
     */
    public List<DTOEmpleado> recuperarEmpleados() {
        List<DTOEmpleado> empleados = new ArrayList<>();
        empleados.add(new DTOEmpleado(Long.valueOf("1"), "Ramses", "Contreras Avila", LocalDate.of(2006, Month.SEPTEMBER, 15)));
        empleados.add(new DTOEmpleado(Long.valueOf("2"), "Josmara", "Quintana Benitez", LocalDate.of(2006, Month.SEPTEMBER, 26)));
        empleados.add(new DTOEmpleado(Long.valueOf("3"), "Hector", "Flores Montoya", LocalDate.of(2006, Month.OCTOBER, 20)));
        empleados.add(new DTOEmpleado(Long.valueOf("4"), "Zaira", "Barajaz Diaz", LocalDate.of(1998, Month.AUGUST, 24)));
        
        return empleados;
    }
    
    /**
     * Regresa el horario del horario empleado
     * @return DTOHorarioEmpleado
     */
    public DTOHorarioEmpleado obtenerHorarioEmpleado(Long id) {
        DTOEmpleado empleado = new DTOEmpleado();
        long idEmpleado = id;
        switch ((int) idEmpleado){
            case 1: 
                DTOEmpleado empleado1 = new DTOEmpleado(Long.valueOf("1"), "Ramses", "Contreras Avila", LocalDate.of(2006, Month.SEPTEMBER, 15));
                empleado = empleado1;
            case 2:
                DTOEmpleado empleado2 = new DTOEmpleado(Long.valueOf("2"), "Josmara", "Quintana Benitez", LocalDate.of(2006, Month.SEPTEMBER, 26));
                empleado = empleado2;
            case 3: 
                DTOEmpleado empleado3 = new DTOEmpleado(Long.valueOf("3"), "Hector", "Flores Montoya", LocalDate.of(2006, Month.OCTOBER, 20));
                empleado = empleado3;
            case 4:
                DTOEmpleado empleado4 = new DTOEmpleado(Long.valueOf("4"), "Zaira", "Barajaz Diaz", LocalDate.of(1998, Month.AUGUST, 24));
                empleado = empleado4;
        }
        
        //Al no haber un turno en existencia para este horario se queda como nulo
        DTOHorarioEmpleado horario_empleado = new DTOHorarioEmpleado(empleado, null, null, null);
        
        return horario_empleado;
        
    }
    
    /**
     * Metodo que nos da una lista de turnos con los cuales usaremos para modificar el horario
     * @return 
     */
    public List<DTOTurno> recuperarTurnos() {
        //Creamos los dias en los que se trabajara cada turno
        Set<DayOfWeek> manana = new HashSet<>();
        manana.add(DayOfWeek.MONDAY);
        manana.add(DayOfWeek.WEDNESDAY);
        manana.add(DayOfWeek.FRIDAY);
        
        Set<DayOfWeek> tarde = new HashSet<>();
        tarde.add(DayOfWeek.TUESDAY);
        tarde.add(DayOfWeek.THURSDAY);
        
        Set<DayOfWeek> noche = new HashSet<>();
        noche.add(DayOfWeek.MONDAY);
        noche.add(DayOfWeek.TUESDAY);
        noche.add(DayOfWeek.WEDNESDAY);
        //Creamos los turnos con sus dias, nombres, y horas de inicio y fin
        DTOTurno turno_matutino = new DTOTurno("Turno matutino", LocalTime.of(8, 0), LocalTime.of(13, 0), manana);
        DTOTurno turno_vespertino = new DTOTurno("Turno Vespertino", LocalTime.of(13, 0), LocalTime.of(18, 0), manana);
        DTOTurno turno_nocturno = new DTOTurno("Turno nocturno", LocalTime.of(18, 0), LocalTime.of(23, 0), manana);
        //Creamos la lista de turnos que hay
        List<DTOTurno> lista = new ArrayList<DTOTurno>();
        lista.add(turno_matutino);
        lista.add(turno_vespertino);
        lista.add(turno_nocturno);
        
        return lista;
        
    }
    
    /**
     * Modificamos el horario del empleado con el turno que se selecciono, la fecha de inicio y la fecha en que termina
     * @param turno
     * @param empleado
     * @param fecha_inicio
     * @param fecha_fin 
     */
    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        DTOHorarioEmpleado horario_empleado = new DTOHorarioEmpleado(empleado, turno, fecha_inicio, fecha_fin);
    }
    
}
