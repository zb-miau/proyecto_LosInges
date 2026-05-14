/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
public interface IGestionIncidencias {

    public List<DTOEmpleado> obtenerEmpleados();

    public void crearIncidencia(DTOIncidencia dTOIncidencia);

    public List<DTOIncidencia> obtenerIncidencias();

    public void validarIncidencia(DTOIncidencia incidencia);

    public void RechazarIncidencia(DTOIncidencia incidencia);

    public void enviarSupervisor();

}
