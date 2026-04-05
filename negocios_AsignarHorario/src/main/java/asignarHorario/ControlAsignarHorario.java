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
 * Esta clase permita el control total de los horarios de empleados 
 * @author RAMSES
 */
public class ControlAsignarHorario{
    List<DTOTurno> turnosRegistrados = new ArrayList();
    List<DTOEmpleado> empleadosRegistrados = new ArrayList<>();

    protected ControlAsignarHorario(){
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
    protected List<DTOEmpleado> recuperarEmpleados() {
        
        return empleadosRegistrados;
    }
    
    /**
     * Regresa el horario del horario empleado
     * @return DTOHorarioEmpleado
     * @param id
     */
    protected DTOHorarioEmpleado obtenerHorarioEmpleado(Long id) {
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
     * @return DTOTurno 
     */
    protected List<DTOTurno> recuperarTurnos() {
        
        //Recuperamos los turnos agregados en el constructor
        return turnosRegistrados;
        
    }
    
    /**
     * Este método permite actualizar el horario de un empleado ya existente,
     * recupera una lista de empleados, se establece una bandera para saber si se encontro
     * una vez que se encuentra el empleado se verifica que tenga un historial, en caso
     * de no tenerlo se le crea uno nuevo, para despues crear un nuevo horario o reemplazar el anterior
     * @param turno
     * @param idEmpleado
     * @param fechaInicio
     * @param fechFin 
     */
    protected void actualizarHorarioEmpleado(DTOTurno turno, Long idEmpleado, LocalDate fechaInicio, LocalDate fechFin) {
        DTOHorarioEmpleado horarioEmpleado = new DTOHorarioEmpleado(idEmpleado, turno, fechaInicio, fechFin);
        List<DTOEmpleado> empleados = recuperarEmpleados();
        boolean encontrado = false;
        for (DTOEmpleado e : empleados) {
            if (e.getId().equals(idEmpleado)) {
                LinkedList<DTOHorarioEmpleado> historial = e.getHistorial();
                if (historial == null) {
                    historial = new LinkedList<>();
                    e.setHistorial(historial); 
                }
                
                for (DTOHorarioEmpleado hAntiguo : historial) {
                    if (hAntiguo.getFechaFin() == null || hAntiguo.getFechaFin().isAfter(fechaInicio)) {
                        LocalDate diaAnterior = fechaInicio.minusDays(1);
                        hAntiguo.setFechaFin(diaAnterior);

                    }
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
    
    /**
     * Este es un metodo que agrega el turno creado a la lista de turnos
     * @param turno 
     */
    protected void agregarTurno(DTOTurno turno){
        turnosRegistrados.add(turno);
    }
    /**
     * Itera en la lista de los turnos existentes y si el turno existe lo elimina
     * @param turno guardado en una lista
     */
    protected void eliminarTurno(DTOTurno turno){
        for (DTOTurno t: turnosRegistrados){
            if(t.getIdTurno().equals(turno.getIdTurno())){
                turnosRegistrados.remove(t);
                break;
            }
        }
    }
    /**
     * Itera en el arreglo de turnos, si el turno se encuentra, lo reemplaza con 
     * el nuevo turno
     * @param turno el turno que va a recibir el metodo para modificar
     */
    protected void modificarTurno(DTOTurno turno){
        for (DTOTurno t: turnosRegistrados){
            if(t.getIdTurno().equals(turno.getIdTurno())){
                t = turno;
            }
        }
    }
    /**
     * Crea una lista de empleadosDTO en la que busca el empleado solicitado
     * si el empleado existe lo regresa, en caso contrario devuelve null
     * @param id del empleado 
     * @return DTOEmpleado 
     */
    protected DTOEmpleado recuperarEmpleado(Long id){
        List<DTOEmpleado> empleados = empleadosRegistrados;
        for (DTOEmpleado e : empleados){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }
    /**
     * Actualiza la lista de los empleados para que se agreguen nuevos
     * @param listaActualizada  de los empleados
     */
    public void guardarEmpleados(List<DTOEmpleado> listaActualizada) {
        this.empleadosRegistrados = listaActualizada; 
    }
    
}
