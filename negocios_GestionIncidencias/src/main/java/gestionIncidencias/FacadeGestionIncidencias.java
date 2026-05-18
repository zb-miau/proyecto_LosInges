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
    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        return control.crearIncidencia(dTOIncidencia);

    }

    @Override
    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException {

        return control.obtenerIncidencias(estado);

    }

    @Override
    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return control.validarIncidencia(incidencia);

    }

    @Override
    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return control.rechazarIncidencia(incidencia);

    }

    @Override
    public void enviarSupervisor(DTOIncidencia incidencia) {

        control.enviarSupervisor(incidencia);
    }

}
