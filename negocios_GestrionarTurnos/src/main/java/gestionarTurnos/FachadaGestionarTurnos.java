/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarTurnos;

import dto.DTOTurno;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class FachadaGestionarTurnos implements IGestionarTurnos {

    private ControlGestionarTurnos control;

    public FachadaGestionarTurnos() {
        this.control = new ControlGestionarTurnos();
    }

    @Override
    public List<DTOTurno> recuperarTurno() throws NegocioException {

        return control.recuperarTurnos();

    }

    @Override
    public DTOTurno agregarTurno(DTOTurno turnoNuevo) throws NegocioException {

        return control.agregarTurno(turnoNuevo);

    }

    @Override
    public DTOTurno consultarTurno(DTOTurno turnoConsultar) throws NegocioException {

        return control.consultarTurno(turnoConsultar);

    }

    @Override
    public DTOTurno eliminarTurno(DTOTurno turnoEliminar) throws NegocioException {

        return control.eliminarTurno(turnoEliminar);

    }

    @Override
    public DTOTurno modificarTurno(DTOTurno turnoModificar) throws NegocioException {

        return control.modificarTurno(turnoModificar);

    }

    @Override
    public boolean turnoDuplicado(DTOTurno turnoVerificar) throws NegocioException {

        return control.turnoDuplicado(turnoVerificar);

    }

}
