/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import itson.accesodatos.EmpleadosDAO;
import itson.entidades.Empleado;
import itson.entidades.Incidencia;
import java.time.LocalDate;
import java.util.logging.Logger;
import itson.accesodatos.IAccesoEmpleados;

/**
 *
 * @author jesus
 */
public class IncidenciaToDTOIncidenciaAdapter {

    private static final Logger LOGGER = Logger.getLogger(IncidenciaToDTOIncidenciaAdapter.class.getName());

    public static Incidencia adaptar(DTOIncidencia DTOIncidencia) {

        if (DTOIncidencia == null) {
            return null;
        }

        Incidencia incidencia = new Incidencia(
                DTOIncidencia.getIdIncidencia(),
                Incidencia.TiposIncidencia.valueOf(DTOIncidencia.getTipo().name()),
                DTOIncidencia.getEmpleado().getId(),
                DTOIncidencia.getDescripcion(),
                DTOIncidencia.getFecha(),
                Incidencia.Estado.valueOf(DTOIncidencia.getEstado().name()),
                DTOIncidencia.getObservaciones());

//Incidencia.Estado.valueOf(DTOIncidencia.getEstado().name()));
        return incidencia;

    }

    public static DTOIncidencia adaptar(Incidencia incidencia) {

        DTOEmpleado empleado = new DTOEmpleado();
        if (incidencia.getEmpleado() != null){
            empleado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(incidencia.getEmpleado()); 
        } else {
            System.out.println("EMPLEADO NULL EN EL ADAPTER DTO");
        }

        DTOIncidencia dtoIncidencia = new DTOIncidencia(
                incidencia.getIdIncidencia(),
                DTOIncidencia.TiposIncidencia.valueOf(incidencia.getTipo().name()),
                empleado,
                incidencia.getDescripcion(),
                incidencia.getFecha(),
                DTOIncidencia.Estado.valueOf(incidencia.getEstado().name()),
                incidencia.getObservaciones());

        dtoIncidencia.getEmpleado().setId(incidencia.getIdEmpleado());
        

        return dtoIncidencia;

    }

}
