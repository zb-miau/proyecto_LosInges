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
import itson.accesodatos.IAccesoDatos;
import itson.accesodatos.EmpleadosDAO;
import objetosNegocio.TurnoBO;
import objetosNegocio.EmpleadoBO;
import itson.accesodatos.HorarioEmpleadosDAO;
import objetosNegocio.HorarioEmpleadoBO;

/**
 * Esta clase permita el control total de los horarios de empleados
 *
 * @author RAMSES
 */
public class ControlAsignarHorario {

    List<DTOEmpleado> empleadosRegistrados = new ArrayList<>();
    private EmpleadoBO empleadoBO;
    private HorarioEmpleadoBO horarioEmpleadoBO;
    private TurnoBO turno;

    protected ControlAsignarHorario() {
        this.empleadoBO = EmpleadoBO.getInstanceEmpleadoBO();
        this.horarioEmpleadoBO = HorarioEmpleadoBO.getInstanceEmpleadoBO();
        turno = new TurnoBO();
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
        horarioBusqueda.setIdEmpleado(empleado.getId());
        return horarioEmpleadoBO.obtener(horarioBusqueda);

    }

    /**
     * Metodo que nos da una lista de turnos con los cuales usaremos para
     * modificar el horario
     *
     * @return lista con los turnos registrados en la base de datos
     */
    protected List<DTOTurno> recuperarTurnos() {
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
    protected void actualizarHorarioEmpleado(DTOTurno turno, String idEmpleado, LocalDate fechaInicio, LocalDate fechaFin) {
        DTOHorarioEmpleado horarioDTO = new DTOHorarioEmpleado(idEmpleado, turno, fechaInicio, fechaFin);

        // Intentamos modificar primero
        DTOHorarioEmpleado resultado = horarioEmpleadoBO.modificar(horarioDTO);

        if (resultado != null) {
            System.out.println("Horario actualizado correctamente para el empleado: " + idEmpleado);
        } else {
            // Si modificar devolvió null lo creamos
            System.out.println("EL HORARIO NO EXISTE. SE CREA");
            resultado = horarioEmpleadoBO.crear(horarioDTO);

            if (resultado != null) {
                System.out.println("Horario creado exitosamente.");
            } else {
                System.err.println("Error crítico: No se pudo ni actualizar ni crear el horario.");
            }
        }
    }

    /**
     * Este es un metodo que agrega el turno creado a la base de datos.
     *
     * @param dtoTurno
     */
    protected void agregarTurno(DTOTurno dtoTurno) {
        turno.crear(dtoTurno);
    }

    /**
     * Itera en la lista de los turnos existentes y si el turno existe lo
     * elimina
     *
     * @param dtoTurno
     */
    protected void eliminarTurno(DTOTurno dtoTurno) {
        turno.eliminar(dtoTurno);
    }

    /**
     * Itera en el arreglo de turnos, si el turno se encuentra, lo reemplaza con
     * el nuevo turno
     *
     * @param dtoTurno el turno que va a recibir el metodo para modificar
     */
    protected void modificarTurno(DTOTurno dtoTurno) {
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

}
