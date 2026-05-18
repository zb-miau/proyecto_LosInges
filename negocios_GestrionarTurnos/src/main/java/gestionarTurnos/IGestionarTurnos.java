/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionarTurnos;

import dto.DTOTurno;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public interface IGestionarTurnos {

    public List<DTOTurno> recuperarTurno() throws NegocioException;

    public DTOTurno agregarTurno(DTOTurno turnoNuevo) throws NegocioException;

    public DTOTurno consultarTurno(DTOTurno turnoConsultar) throws NegocioException;

    public DTOTurno eliminarTurno(DTOTurno turnoEliminar) throws NegocioException;

    public DTOTurno modificarTurno(DTOTurno turnoModificar) throws NegocioException;

    public boolean turnoDuplicado(DTOTurno turnoVerificar) throws NegocioException;

}
