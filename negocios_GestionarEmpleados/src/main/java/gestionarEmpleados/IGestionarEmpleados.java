/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
import dto.DTOTurno;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public interface IGestionarEmpleados {

    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException;

    public List<DTOEmpleado> obtenerEmpleados();
    
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado);

    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia);
    
    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado);

    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException;

    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;

}
