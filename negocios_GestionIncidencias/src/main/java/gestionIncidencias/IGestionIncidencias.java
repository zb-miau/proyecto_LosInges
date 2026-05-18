/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface IGestionIncidencias {

    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException;

    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException;

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) throws NegocioException;

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) throws NegocioException;

    public void enviarSupervisor(DTOIncidencia incidencia);

}
