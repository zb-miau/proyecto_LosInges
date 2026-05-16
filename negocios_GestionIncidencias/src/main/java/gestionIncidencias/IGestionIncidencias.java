/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author jesus
 */
public interface IGestionIncidencias {

    public List<DTOEmpleado> obtenerEmpleados();

    public void crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException;

    public List<DTOIncidencia> obtenerIncidencias()throws NegocioException;

    public void validarIncidencia(DTOIncidencia incidencia)throws NegocioException;

    public void RechazarIncidencia(DTOIncidencia incidencia)throws NegocioException;

    public void enviarSupervisor();

}
