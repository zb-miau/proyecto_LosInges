/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface IAsignarHorario {
    
    public List<DTOEmpleado> recuperarEmpleados();
    
    public DTOHorarioEmpleado obtenerHorarioEmpleado(Long id);
    
    public List<DTOTurno> recuperarTurno();
    
    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin);
    
}
