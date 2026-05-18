/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignarHorario;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.TurnoBO;
import objetosNegocio.EmpleadoBO;
import java.util.logging.Logger;
import objetosNegocio.HorarioEmpleadoBO;
import objetosNegocio.NegocioException;

/**
 * Esta clase permita el control total de los horarios de empleados
 *
 * @author RAMSES
 */
public class ControlAsignarHorario {
    private EmpleadoBO empleadoBO;
    private HorarioEmpleadoBO horarioEmpleadoBO;
    private TurnoBO turnoBO;

    protected ControlAsignarHorario() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.horarioEmpleadoBO = HorarioEmpleadoBO.getInstance();
        turnoBO = TurnoBO.getInstance();
    }

    /**
     * Creamos la lista mock de los empleadosRegistrados para despues
     * regresarlos
     *
     * @return List DTOEmpleado
     */
    protected List<DTOEmpleado> recuperarEmpleados() {

        return empleadoBO.obtenerLista();
    }

    /**
     * Regresa el horario del horario empleado
     *
     * @return DTOHorarioEmpleado
     * @param empleado
     */
    protected DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado) {
        DTOHorarioEmpleado horarioBusqueda = new DTOHorarioEmpleado();
        DTOEmpleado emp = empleadoBO.obtener(empleado);
        horarioBusqueda = emp.getHorarioActual();
        return horarioBusqueda;

    }

    /**
     * Metodo que nos da una lista de turnos con los cuales usaremos para
     * modificar el horario
     *
     * @return lista con los turnos registrados en la base de datos
     */
    protected List<DTOTurno> recuperarTurnos() throws NegocioException {
        return turnoBO.obtenerLista();
    }

    /**
     * Este memtodo permite la modificación de un horario
     * @param turno
     * @param idEmpleado
     * @param fechaInicio
     * @param fechaFin
     */
    protected void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        // Intentamos modificar primero
        DTOHorarioEmpleado resultado = empleado.getHorarioActual();
        DTOHorarioEmpleado nuevo = new DTOHorarioEmpleado(empleado, turno, fechaInicio, fechaFin);
        
        
        if (fechaFin != null && (fechaInicio.isAfter(fechaFin) || fechaFin.isBefore(fechaInicio))){
            throw new NegocioException("La fecha de inicio no puede ser anterior a la fecha de fin.");
        }
        
        if (resultado != null) {
            if (nuevo.getFechaInicio().isAfter(resultado.getFechaInicio())) {
                if ((resultado.getFechaFin() != null && (nuevo.getFechaFin() != null && nuevo.getFechaFin().isBefore(resultado.getFechaFin())))
                    || resultado.getFechaFin() == null){
                    resultado.setFechaFin(nuevo.getFechaInicio().minusDays(1));
                }
            }   
            
            resultado.setFechaCambio(LocalDate.now());  
            if (resultado.getEmpleado() == null) {
                resultado.setEmpleado(empleado);
            }
            horarioEmpleadoBO.crear(resultado);
            
            List<DTOHorarioEmpleado> traslapeHistorial = horarioEmpleadoBO.obtenerActivo(nuevo);
            
            if (traslapeHistorial != null  && !traslapeHistorial.isEmpty()){
                for (DTOHorarioEmpleado t: traslapeHistorial){
                    //si el horario registrado empieza antes que el nuevo
                    if (t.getFechaInicio().isBefore(nuevo.getFechaInicio())) {
                        //y si la fecha de fin del horario registrado es nula o termina despues de que
                        // el horario nuevo inicie
                        if (t.getFechaFin() == null || t.getFechaFin().isAfter(nuevo.getFechaInicio().minusDays(1))) {
                            //el horario registrado se modifica para que termine un dia antes que el horario nuevo
                            t.setFechaFin(nuevo.getFechaInicio().minusDays(1));
                            if (t.getEmpleado() == null) { 
                                t.setEmpleado(empleado); 
                            }
                            t.getEmpleado().setId(empleado.getId());
                            horarioEmpleadoBO.modificarHorarioInfinito(t);
                        }
                    }
                    
                    //si el horario registrado inicia antes de que termine el nuevo
                    else if (!t.getFechaInicio().isAfter(nuevo.getFechaFin())) {
                        //se cambia su fecha de inicio a un dia de la fecha fin del nuevo
                        t.setFechaInicio(nuevo.getFechaFin().plusDays(1));
                        //pero si el horario registrado tiene fecha de fin
                        //y ahora la fecha de fin es mayor a la de inicio
                        if (t.getEmpleado() == null) { 
                                t.setEmpleado(empleado); 
                            }
                            t.getEmpleado().setId(empleado.getId());
                            
                        if (t.getFechaFin() != null && t.getFechaInicio().isAfter(t.getFechaFin())) {
                            //se borra por completo del historial porque otros horarios se lo comieron
                            horarioEmpleadoBO.eliminarHistorial(t);
                        } else {
                            
                            horarioEmpleadoBO.modificarHorarioInfinito(t);
                        }
                    }
                }
            }
           
        }
        empleado.setHorarioActual(nuevo);
        empleadoBO.modificarHorarioActual(empleado);
    }

    /**
     * Este es un metodo que agrega el turno creado a la base de datos.
     *
     * @param dtoTurno
     */
    protected void agregarTurno(DTOTurno dtoTurno) throws NegocioException{
        if (!turnoBO.turnoDuplicado(dtoTurno)){
            turnoBO.crear(dtoTurno);
        } else {
            throw new NegocioException("Ya existe un horario con los datos ingresados.");
        }
    }
    
    protected DTOTurno consultarTurno(DTOTurno dtoTurno) throws NegocioException{
        return turnoBO.obtener(dtoTurno);
    }

    /**
     * Itera en la lista de los turnos existentes y si el turno existe lo
     * elimina
     *
     * @param dtoTurno
     */
    protected void eliminarTurno(DTOTurno dtoTurno) throws NegocioException{
        turnoBO.eliminar(dtoTurno);
    }

    /**
     * Itera en el arreglo de turnos, si el turno se encuentra, lo reemplaza con
     * el nuevo turno
     *
     * @param dtoTurno el turno que va a recibir el metodo para modificar
     */
    protected void modificarTurno(DTOTurno dtoTurno) throws NegocioException{
        turnoBO.modificar(dtoTurno);
    }

    /**
     * Crea una lista de empleadosDTO en la que busca el empleado solicitado si
     * el empleado existe lo regresa, en caso contrario devuelve null
     * @param empleado del empleado
     * @return DTOEmpleado
     */
    protected DTOEmpleado recuperarEmpleado(DTOEmpleado empleado) {

        if (empleado.getId() == null){
            System.out.println("Control asignar horario");
            return null;
        }
        return empleadoBO.obtener(empleado);

    }

    /**
     * Actualiza la lista de los empleados para que se agreguen nuevos
     *
     * @param listaActualizada de los empleados
     */
    public void guardarEmpleados(List<DTOEmpleado> listaActualizada) {
        for (DTOEmpleado emp : listaActualizada) {
            empleadoBO.modificar(emp);
        }
    }
    
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado,  LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException{
        DTOHorarioEmpleado horario;
        if (empleado.getHorarioActual() != null){
            horario = empleado.getHorarioActual();
        } else {
            horario = new DTOHorarioEmpleado();
            horario.setEmpleado(empleado);
        }
        return horarioEmpleadoBO.obtenerLista(horario, fechaInicio, fechaFin);
    }

    
    public boolean turnoDuplicado(DTOTurno turno) throws NegocioException{
        return turnoBO.turnoDuplicado(turno);
    }
}
