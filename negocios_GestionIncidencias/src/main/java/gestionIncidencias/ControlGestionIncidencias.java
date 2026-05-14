/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.IncidenciaBO;

/**
 *
 * @author jesus
 */
public class ControlGestionIncidencias {

    private EmpleadoBO empleadoBO;

    private IncidenciaBO incidenciaBO;

    public ControlGestionIncidencias() {
        this.empleadoBO = EmpleadoBO.getInstance();
        this.incidenciaBO = IncidenciaBO.getInstance();
    }

    public List<DTOEmpleado> obtenerEmpleados() {

        return empleadoBO.obtenerLista();

    }

    public void crearIncidencia(DTOIncidencia dTOIncidencia) {

        incidenciaBO.crear(dTOIncidencia);

    }

    public List<DTOIncidencia> obtenerIncidencias() {

        return incidenciaBO.obtenerLista();

    }

    public void validarIncidencia(DTOIncidencia dTOIncidencia) {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.VALIDADA);

        incidenciaBO.modificar(dTOIncidencia);

    }

    public void rechazarIncidencia(DTOIncidencia dTOIncidencia) {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.RECHAZADA);

        incidenciaBO.modificar(dTOIncidencia);

    }

}
