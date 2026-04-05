/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asignarHorario;

import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOTurno;
import java.time.LocalDate;
import java.util.List;

/**
 * Siguiente la práctica del huevo, esta clase es una capa entre capas para el uso
 * de los metodos 
 * @author RAMSES
 */
public class FacadeAsignarHorario implements IAsignarHorario{
    private ControlAsignarHorario controlHorario;

    public FacadeAsignarHorario() {
        this.controlHorario = new ControlAsignarHorario();
    }
    
    
    /**
     * Por medio de controlHorario recupera una lista de empleados 
     * @return DTOEmpleado
     */
    @Override
    public List<DTOEmpleado> recuperarEmpleados() {
        return controlHorario.recuperarEmpleados();
    }
    /**
     * Por medio de controlHorario obtiene el horario de un empleado en especifico por id
     * @param id
     * @return DTOHorarioEmpleado
     */
    @Override
    public DTOHorarioEmpleado obtenerHorarioEmpleado(Long id) {
        return controlHorario.obtenerHorarioEmpleado(id);
    }
    /**
     * Por medio de controlHorario recupera una lista de los turnos
     * @return DTOTurno
     */
    @Override
    public List<DTOTurno> recuperarTurno() {
        return controlHorario.recuperarTurnos();
    }
    /**
     * Por medio de controlHorario actualiza el horario del empleado
     * @param turno
     * @param idEmpleado
     * @param fecha_inicio
     * @param fecha_fin 
     */
    @Override
    public void actualizarHorarioEmpleado(DTOTurno turno, Long idEmpleado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        controlHorario.actualizarHorarioEmpleado(turno, idEmpleado, fecha_inicio, fecha_fin);
    }
    /**
     * Por medio de controlHorario agrega un turno nuevo
     * @param turnoNuevo 
     */
    @Override
    public void agregarTurno(DTOTurno turnoNuevo) {
        controlHorario.agregarTurno(turnoNuevo);
    }
    /**
     * Por medio de controlHorarui elimina el turno que se desea eliminar
     * @param turnoEliminar 
     */
    @Override
    public void eliminarTurno(DTOTurno turnoEliminar) {
        controlHorario.eliminarTurno(turnoEliminar);
    }
    /**
     * Por medio de controlHorario podemos recuperar un empleado en especifico con su id
     * @param id
     * @return DTOEmpleado
     */
    @Override
    public DTOEmpleado recuperarEmpleado(Long id) {
        return controlHorario.recuperarEmpleado(id);
    }
    /**
     * Por medio de controlHorario se modifica el turno deseado
     * @param turnoModificar 
     */
    @Override
    public void modificarTurno(DTOTurno turnoModificar) {
        controlHorario.modificarTurno(turnoModificar);
    }
    
}
