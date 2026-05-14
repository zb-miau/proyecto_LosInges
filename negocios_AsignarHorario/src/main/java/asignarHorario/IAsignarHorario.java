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
import objetosNegocio.NegocioException;

/**
 * Clase de interface para los metodos de controlHorario
 * @author RAMSES
 */
public interface IAsignarHorario {
    
    public List<DTOEmpleado> recuperarEmpleados();
    
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado);
    
    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado);
    
    public List<DTOTurno> recuperarTurno() throws NegocioException;
    
    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException;
    
    public void agregarTurno(DTOTurno turnoNuevo)throws NegocioException;
    
    public DTOTurno consultarTurno(DTOTurno turnoConsultar)throws NegocioException;
    
    public void eliminarTurno(DTOTurno turnoEliminar)throws NegocioException;
    
    public void modificarTurno(DTOTurno turnoModificar)throws NegocioException;
    
    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado)throws NegocioException;
    
}
