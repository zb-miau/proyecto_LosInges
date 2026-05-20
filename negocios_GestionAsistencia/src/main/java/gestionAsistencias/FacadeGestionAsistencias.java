/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionAsistencias;

import dto.DTOEmpleado;
import dto.DTORegistroMarca;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 * Fachada del subsistema de Gestión de Asistencias.
 * Implementa el patrón de diseño Facade para proporcionar una interfaz unificada y 
 * simplificada hacia las funcionalidades del subsistema. Delega todas las operaciones 
 * al controlador ControlGestionAsistencias, abstrayendo la complejidad de las 
 * reglas de negocio y las validaciones internas.
 * @author josma
 */
public class FacadeGestionAsistencias implements IGestionAsistencias {
    /** Instancia del controlador que gestiona la lógica interna del subsistema. */
    private ControlGestionAsistencias control;
    
    /**
     * Constructor por defecto que inicializa la fachada.
     * Crea una nueva instancia de ControlGestionAsistencias para manejar 
     * las peticiones enviadas a través de esta fachada.
     */
    public FacadeGestionAsistencias() {
        this.control = new ControlGestionAsistencias();
    }
    
    /**
     * Registra o actualiza una marca de asistencia (entrada/salida) para un empleado.
     * @param marcaDTO Objeto de transferencia de datos con la información de la marca.
     * @return El DTORegistroMarca procesado y persistido.
     * @throws NegocioException Si ocurren errores en las reglas de validación (horario, días laborables, etc.).
     */
    @Override
    public DTORegistroMarca crearMarca(DTORegistroMarca marcaDTO) throws NegocioException {
        return control.agregarMarca(marcaDTO);
    }
    /**
     * Recupera una lista de marcas de asistencia de un empleado en un rango de fechas determinado.
     * @param empleado Identificador único del empleado.
     * @param inicio Fecha inicial del rango de consulta.
     * @param fin Fecha final del rango de consulta.
     * @return Lista de DTORegistroMarca que coinciden con el criterio de búsqueda.
     * @throws NegocioException Si los parámetros de búsqueda son inválidos.
     */
    @Override
    public List<DTORegistroMarca> obtenerListaMarca(DTOEmpleado empleado, LocalDate inicio, LocalDate fin) throws NegocioException {
        return control.reporteAsistencia(empleado, inicio, fin);
    }
    /**
     * Calcula el número total de asistencias completas a partir de una lista de registros.
     * @param listaMarcas Lista de registros de marcas a contabilizar.
     * @return Cantidad entera que representa el total de asistencias válidas.
     * @throws NegocioException Si ocurre un error durante el cálculo de negocio.
     */
    @Override
    public int conteoAsistencia(List<DTORegistroMarca> listaMarcas) throws NegocioException {
        return control.ObtenerConteo(listaMarcas);
    }
    /**
     * Obtiene un registro de marca específico basado en el ID del empleado y una fecha.
     * @param empleado Identificador único del empleado.
     * @param fecha Fecha exacta del registro buscado.
     * @return El DTORegistroMarca correspondiente, o {@code null} si no se encuentra.
     * @throws NegocioException Si hay errores en la consulta de datos.
     */
    @Override
    public DTORegistroMarca obtenerMarca(DTOEmpleado empleado, LocalDate fecha) throws NegocioException{
        return control.obtenerMarca(empleado, fecha);
    }

    
}
