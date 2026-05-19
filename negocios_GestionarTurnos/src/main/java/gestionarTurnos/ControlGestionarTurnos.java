/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionarTurnos;

import dto.DTOTurno;
import java.util.List;
import objetosNegocio.NegocioException;
import objetosNegocio.TurnoBO;

/**
 *
 * @author jesus
 */
public class ControlGestionarTurnos {

    private TurnoBO turnoBO;

    public ControlGestionarTurnos() {
        this.turnoBO = TurnoBO.getInstance();
    }

    /**
     * Metodo que nos da una lista de turnos con los cuales usaremos para
     * modificar el horario
     *
     * @return lista con los turnos registrados en la base de datos
     * @throws objetosNegocio.NegocioException
     */
    protected List<DTOTurno> recuperarTurnos() throws NegocioException {
        return turnoBO.obtenerLista();
    }

    /**
     * Este es un metodo que agrega el turno creado a la base de datos.
     *
     * @param dtoTurno
     */
    protected DTOTurno agregarTurno(DTOTurno dtoTurno) throws NegocioException {
        if (!turnoBO.turnoDuplicado(dtoTurno)) {
            return turnoBO.crear(dtoTurno);
        } else {
            throw new NegocioException("Ya existe un horario con los datos ingresados.");
        }
    }

    protected DTOTurno consultarTurno(DTOTurno dtoTurno) throws NegocioException {
        return turnoBO.obtener(dtoTurno);
    }

    /**
     * Itera en la lista de los turnos existentes y si el turno existe lo
     * elimina
     *
     * @param dtoTurno
     */
    protected DTOTurno eliminarTurno(DTOTurno dtoTurno) throws NegocioException {
        return turnoBO.eliminar(dtoTurno);
    }

    /**
     * Itera en el arreglo de turnos, si el turno se encuentra, lo reemplaza con
     * el nuevo turno
     *
     * @param dtoTurno el turno que va a recibir el metodo para modificar
     */
    protected DTOTurno modificarTurno(DTOTurno dtoTurno) throws NegocioException {
        return turnoBO.modificar(dtoTurno);
    }

    public boolean turnoDuplicado(DTOTurno turno) throws NegocioException {
        return turnoBO.turnoDuplicado(turno);
    }

}
