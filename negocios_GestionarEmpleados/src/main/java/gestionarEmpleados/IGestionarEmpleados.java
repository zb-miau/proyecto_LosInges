/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;

/**
 *
 * @author jesus
 */
public interface IGestionarEmpleados {

    public DTOEmpleado registrarEmpleado(DTOEmpleado empleado);

    public List<DTOEmpleado> obtenerEmpleados();

    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia);

}
