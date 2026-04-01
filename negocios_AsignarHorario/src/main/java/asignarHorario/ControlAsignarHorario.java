/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignarHorario;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author RAMSES
 */
public class ControlAsignarHorario{
    List<DTOTurno> turnosRegistrados = new ArrayList();
    List<DTOEmpleado> empleadosRegistrados = new ArrayList<>();

    public ControlAsignarHorario(){
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
        DTOTurno turno_matutino = new DTOTurno(Long.valueOf("1"), "Turno matutino", LocalTime.of(8, 0), LocalTime.of(13, 0), manana, Color.YELLOW);
        DTOTurno turno_vespertino = new DTOTurno(Long.valueOf("2"), "Turno Vespertino", LocalTime.of(13, 0), LocalTime.of(18, 0), tarde, Color.BLUE);
        DTOTurno turno_nocturno = new DTOTurno(Long.valueOf("3"), "Turno nocturno", LocalTime.of(18, 0), LocalTime.of(23, 0), noche, Color.BLACK);
        //Agregamos los turnos a la lista de turnos que declaramos como variable para la clase
        
        turnosRegistrados.add(turno_matutino);
        turnosRegistrados.add(turno_vespertino);
        turnosRegistrados.add(turno_nocturno);
        
        empleadosRegistrados.add(new DTOEmpleado(Long.valueOf("1"), "Ramses", "Contreras Avila", LocalDate.of(2006, Month.SEPTEMBER, 15)));
        empleadosRegistrados.add(new DTOEmpleado(Long.valueOf("2"), "Josmara", "Quintana Benitez", LocalDate.of(2006, Month.SEPTEMBER, 26)));
        empleadosRegistrados.add(new DTOEmpleado(Long.valueOf("3"), "Hector", "Flores Montoya", LocalDate.of(2006, Month.OCTOBER, 20)));
        empleadosRegistrados.add(new DTOEmpleado(Long.valueOf("4"), "Zaira", "Barajaz Diaz", LocalDate.of(1998, Month.AUGUST, 24)));
        
    }
    
    /**
     *  Creamos la lista mock de los empleadosRegistrados para despues regresarlos
     * @return List DTOEmpleado
     */
    public List<DTOEmpleado> recuperarEmpleados() {
        
        return empleadosRegistrados;
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
        DTOHorarioEmpleado horario_empleado = new DTOHorarioEmpleado(empleado.getId(), null, null, null);
        
        return horario_empleado;
        
    }
    
    /**
     * Metodo que nos da una lista de turnos con los cuales usaremos para modificar el horario
     * @return 
     */
    public List<DTOTurno> recuperarTurnos() {
        
        //Recuperamos los turnos agregados en el constructor
        return turnosRegistrados;
        
    }
    
    
    public void actualizarHorarioEmpleado(DTOTurno turno, Long idEmpleado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        DTOHorarioEmpleado horarioEmpleado = new DTOHorarioEmpleado(idEmpleado, turno, fecha_inicio, fecha_fin);
        List<DTOEmpleado> empleados = recuperarEmpleados();
        boolean encontrado = false;
        for (DTOEmpleado e : empleados) {
            if (e.getId().equals(idEmpleado)) {
                LinkedList<DTOHorarioEmpleado> historial = e.getHistorial();
                if (historial == null) {
                    historial = new LinkedList<>();
                    e.setHistorial(historial); 
                }

                historial.add(horarioEmpleado);
                encontrado = true;
                break; 
            }
        }
        
        if (encontrado){
            guardarEmpleados(empleados);
        }
    }
    
    
    public void agregarTurno(DTOTurno turno){
        turnosRegistrados.add(turno);
    }
    
    public void eliminarTurno(DTOTurno turno){
        for (DTOTurno t: turnosRegistrados){
            if(t.getIdTurno().equals(turno.getIdTurno())){
                turnosRegistrados.remove(t);
                break;
            }
        }
    }
    
    public DTOEmpleado recuperarEmpleado(Long id){
        List<DTOEmpleado> empleados = empleadosRegistrados;
        for (DTOEmpleado e : empleados){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
    
    public void guardarEmpleados(List<DTOEmpleado> listaActualizada) {
        this.empleadosRegistrados = listaActualizada; 
    }
    
}
