/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class FacadeGestionIncidencias implements IGestionIncidencias {

    private ControlGestionIncidencias control;

    public FacadeGestionIncidencias() {
        this.control = new ControlGestionIncidencias();
    }

    @Override
    public List<DTOEmpleado> obtenerEmpleados() {

        return control.obtenerEmpleados();

    }

    @Override
    public void crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        control.crearIncidencia(dTOIncidencia);

    }

    @Override
    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException {

        return control.obtenerIncidencias(estado);

    }

    @Override
    public void validarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        control.validarIncidencia(incidencia);

    }

    @Override
    public void RechazarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        control.rechazarIncidencia(incidencia);

    }

    @Override
    public void enviarSupervisor() {

        control.enviarSupervisor();

    }

}
