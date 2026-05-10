/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOIncidencia;
import itson.entidades.Incidencia;
import java.time.LocalDate;
import java.util.logging.Logger;

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
                DTOIncidencia.getTipo(),
                EmpleadoToDTOEmpleadoAdapter.adaptarDTO(DTOIncidencia.getEmpleado()),
                DTOIncidencia.getDescripcion(),
                DTOIncidencia.getFecha(),
                Incidencia.Estado.valueOf(DTOIncidencia.getEstado().name()));

        return incidencia;

    }

    public static DTOIncidencia adaptar(Incidencia incidencia) {

        DTOIncidencia dTOincidencia = new DTOIncidencia(
                incidencia.getIdIncidencia(),
                incidencia.getTipo(),
                EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(incidencia.getEmpleado()),
                incidencia.getDescripcion(),
                incidencia.getFecha(),
                DTOIncidencia.Estado.valueOf(incidencia.getEstado().name()));

        return dTOincidencia;

    }

}
