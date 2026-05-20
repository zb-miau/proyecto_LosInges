/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionarEmpleados;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import dto.DTOHorarioEmpleado;
import dto.DTOIncidencia;
import dto.DTORegistroMarca;
import dto.DTOTurno;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 * Interfaz que define el contrato de servicios para el subsistema de administracion de personal.
 * Expone las operaciones principales para el registro y consulta de registros de trabajadores.
 *
 * @author jesus y RAMSES
 */
public interface IGestionarEmpleados {

    /**
     * Procesa la contratacion de un nuevo empleado realizando las validaciones pertinentes.
     *
     * @param empleado Objeto DTOContratacion con la informacion del candidato.
     * @return El DTOContratacion con los datos del empleado debidamente registrados.
     * @throws NegocioException Si la informacion no cumple con las reglas de negocio o politicas del sistema.
     */
    public DTOContratacion registrarEmpleado(DTOContratacion empleado) throws NegocioException;

    /**
     * Recupera el listado completo de todos los empleados almacenados en el sistema.
     *
     * @return Una lista de objetos DTOEmpleado que representan a los trabajadores.
     */
    public List<DTOEmpleado> obtenerEmpleados();
    
    public DTOEmpleado recuperarEmpleado(DTOEmpleado empleado);

    public DTOIncidencia registrarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia);

    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia);
    
    public DTORegistroMarca crearMarca(DTORegistroMarca marca);
    
    public List<DTORegistroMarca> obtenerLista(String idEmpleado, LocalDate inicio, LocalDate fin);
    
    public int obtenerConteoAsistencia(List<DTORegistroMarca> listaMarcas);
    
    public DTOHorarioEmpleado obtenerHorarioEmpleado(DTOEmpleado empleado);

    public void actualizarHorarioEmpleado(DTOTurno turno, DTOEmpleado empleado, LocalDate fecha_inicio, LocalDate fecha_fin) throws NegocioException;

    public List<DTOHorarioEmpleado> listaHistorial(DTOEmpleado empleado, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException;

}
