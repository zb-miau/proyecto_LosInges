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
    private TurnoBO turno;

    protected ControlAsignarHorario() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.horarioEmpleadoBO = HorarioEmpleadoBO.getInstance();
        turno = TurnoBO.getInstance();
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
        return turno.obtenerLista();
    }

    /**
     * Este memtodo permite la modificación de un horario
     *
     * @param turno
     * @param idEmpleado
     * @param fechaInicio
     * @param fechaFin
     */
    protected void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        // Intentamos modificar primero
        DTOHorarioEmpleado resultado = empleado.getHorarioActual();
        DTOHorarioEmpleado nuevo = new DTOHorarioEmpleado(empleado, turno, fechaInicio, fechaFin);
        if (resultado != null) {
            if (((resultado.getFechaFin() == null && nuevo.getFechaInicio().isAfter(resultado.getFechaInicio())) 
                    || resultado.getFechaFin() != null && resultado.getFechaInicio().isBefore(nuevo.getFechaInicio()))){
                resultado.setFechaFin(nuevo.getFechaInicio().minusDays(1));
            }
            horarioEmpleadoBO.crear(resultado);
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
        turno.crear(dtoTurno);
    }
    
    protected DTOTurno consultarTurno(DTOTurno dtoTurno) throws NegocioException{
        return turno.obtener(dtoTurno);
    }

    /**
     * Itera en la lista de los turnos existentes y si el turno existe lo
     * elimina
     *
     * @param dtoTurno
     */
    protected void eliminarTurno(DTOTurno dtoTurno) throws NegocioException{
        turno.eliminar(dtoTurno);
    }

    /**
     * Itera en el arreglo de turnos, si el turno se encuentra, lo reemplaza con
     * el nuevo turno
     *
     * @param dtoTurno el turno que va a recibir el metodo para modificar
     */
    protected void modificarTurno(DTOTurno dtoTurno) throws NegocioException{
        turno.modificar(dtoTurno);
    }

    /**
     * Crea una lista de empleadosDTO en la que busca el empleado solicitado si
     * el empleado existe lo regresa, en caso contrario devuelve null
     * @param empleado del empleado
     * @return DTOEmpleado
     */
    protected DTOEmpleado recuperarEmpleado(DTOEmpleado empleado) {

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
    
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado) throws NegocioException{
        DTOHorarioEmpleado horario;
        if (empleado.getHorarioActual() != null){
            horario = empleado.getHorarioActual();
        } else {
            horario = new DTOHorarioEmpleado();
            horario.setEmpleado(empleado);
        }
        return horarioEmpleadoBO.obtenerLista(horario);
    }

}
