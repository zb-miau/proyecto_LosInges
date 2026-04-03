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
 *
 * @author RAMSES
 */
public class FacadeAsignarHorario implements IAsignarHorario{
    private ControlAsignarHorario controlHorario;

    public FacadeAsignarHorario() {
        this.controlHorario = new ControlAsignarHorario();
    }
    
    
    
    @Override
    public List<DTOEmpleado> recuperarEmpleados() {
        return controlHorario.recuperarEmpleados();
    }

    @Override
    public DTOHorarioEmpleado obtenerHorarioEmpleado(Long id) {
        return controlHorario.obtenerHorarioEmpleado(id);
    }

    @Override
    public List<DTOTurno> recuperarTurno() {
        return controlHorario.recuperarTurnos();
    }

    @Override
    public void actualizarHorarioEmpleado(DTOTurno turno, Long idEmpleado, LocalDate fecha_inicio, LocalDate fecha_fin) {
        controlHorario.actualizarHorarioEmpleado(turno, idEmpleado, fecha_inicio, fecha_fin);
    }

    @Override
    public void agregarTurno(DTOTurno turnoNuevo) {
        controlHorario.agregarTurno(turnoNuevo);
    }

    @Override
    public void eliminarTurno(DTOTurno turnoEliminar) {
        controlHorario.eliminarTurno(turnoEliminar);
    }

    @Override
    public DTOEmpleado recuperarEmpleado(Long id) {
        return controlHorario.recuperarEmpleado(id);
    }

    @Override
    public void modificarTurno(DTOTurno turnoModificar) {
        controlHorario.modificarTurno(turnoModificar);
    }
    
}
