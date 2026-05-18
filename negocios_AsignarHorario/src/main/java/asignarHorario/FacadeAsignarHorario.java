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
import objetosNegocio.NegocioException;

/**
 * Siguiente la práctica del huevo, esta clase es una capa entre capas para el
 * uso de los metodos
 *
 * @author RAMSES
 */
public class FacadeAsignarHorario implements IAsignarHorario {

    private ControlAsignarHorario controlHorario;

    public FacadeAsignarHorario() {
        this.controlHorario = new ControlAsignarHorario();
    }

    /**
     * Por medio de controlHorario obtiene el horario de un empleado en
     * especifico por id
     *
     * @param id
     * @return DTOHorarioEmpleado
     */
    @Override
    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado) {
        return controlHorario.obtenerHorarioEmpleado(empleado);
    }

    /**
     * Por medio de controlHorario actualiza el horario del empleado
     *
     * @param turno
     * @param idEmpleado
     * @param fecha_inicio
     * @param fecha_fin
     */
    @Override
    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException {
        controlHorario.actualizarHorarioEmpleado(turno, empleado, fecha_inicio, fecha_fin);
    }

    @Override
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        return controlHorario.listaHistorial(empleado, fechaInicio, fechaFin);
    }

}
