/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import gestionIncidencias.FacadeGestionIncidencias;
import gestionIncidencias.IGestionIncidencias;
import java.util.List;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class ControlGestionarEmpleados {

    private EmpleadoBO empleadoBO;

    private IGestionIncidencias gestionIncidencias;

    public ControlGestionarEmpleados(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
        this.gestionIncidencias = new FacadeGestionIncidencias();
    }

    public DTOEmpleado registrarEmpleado(DTOEmpleado empleado) {

        return empleadoBO.crear(empleado);

    }

    public List<DTOEmpleado> obtenerEmpleados() {

        return empleadoBO.obtenerLista();

    }

    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return gestionIncidencias.crearIncidencia(incidencia);

    }

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return gestionIncidencias.validarIncidencia(incidencia);

    }

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return gestionIncidencias.rechazarIncidencia(incidencia);

    }
    

}
