/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;
import mensajeria.ControlMensajeria;
import mensajeria.FacadeMensajeria;
import mensajeria.IMensajeria;
import objetosNegocio.EmpleadoBO;
import objetosNegocio.IncidenciaBO;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public class ControlGestionIncidencias {

    private IncidenciaBO incidenciaBO;

    private IMensajeria mensajeria;

    public ControlGestionIncidencias() {
        mensajeria = new FacadeMensajeria(new ControlMensajeria());

        this.incidenciaBO = IncidenciaBO.getInstance();
    }

    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        return incidenciaBO.crear(dTOIncidencia);

    }

    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException {

        return incidenciaBO.obtenerLista(estado);

    }

    public DTOIncidencia validarIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.VALIDADA);

        return incidenciaBO.modificar(dTOIncidencia);

    }

    public DTOIncidencia rechazarIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        dTOIncidencia.setEstado(DTOIncidencia.Estado.RECHAZADA);

        return incidenciaBO.modificar(dTOIncidencia);

    }

    public void enviarSupervisor(DTOIncidencia incidencia) {

        mensajeria.enviarGmailSupervisor(incidencia);

    }

}
