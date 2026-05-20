/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTORegistroMarca;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.PersistenciaException;
import itson.entidades.RegistroMarca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase centraliza exclusivamente en las reglas de neogcio (BO).
 * Se enfoca en las reglas para la gestión de registro de asistencias del empleado
 * @author josma
 */
public class RegistroMarcaBO {
    private static final Logger LOGGER = Logger.getLogger(EmpleadoBO.class.getName());
    
    FacadeAccesoDatos fachadaDAO;
    private static RegistroMarcaBO registroMarcaBO;
    /**
     * Recupera la variable global y única de la clase RegistroMarcaBO
     * @return 
     */
    public static synchronized RegistroMarcaBO getInstance() {
        if (registroMarcaBO == null) {
            registroMarcaBO = new RegistroMarcaBO();
        }
        return registroMarcaBO;
    }
    
    /**
     * Constructor privado que evita que se cree una instancia externa de la clase.
     * Inicializa la referencia unica hacia la fachada de acceso a datos.
     */
    private RegistroMarcaBO(){
        this.fachadaDAO = FacadeAccesoDatos.getInstance();

    }
    /**
     * Método para crear un registro de marca 
     * @param registroDTO
     * @return
     * @throws NegocioException 
     */
    public DTORegistroMarca crear(DTORegistroMarca registroDTO) throws NegocioException{
        //1. Se valida que nada venga vacio y que todo sea válido
        if (registroDTO == null) {
            throw new NegocioException("Ocurrio un erro al intentar registrar: los datos son nulos");
        }
        if (registroDTO.getEmpleadoDTO().getId()== null) {
            throw new NegocioException("Se requiere un empleado que sea válido para poder registrar la marca");
        }
        
        try{
            //2. Adaptar la entidad DTO -> entidad limpia
            RegistroMarca registroLimpio = RegistroMarcaToDTORegistroMarca.toPersistencia(registroDTO);
            //3. Persistir por medio de la fachada
            registroLimpio = fachadaDAO.crearMarca(registroLimpio);
            //Regresamos la entidad adaptada hacia arriba entidad limpia -> DTO
            return RegistroMarcaToDTORegistroMarca.toDTO(registroLimpio);
        }catch(PersistenciaException e){
            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al intentar crear el registro");
        }
    }
    /**
     * Método para actualizar el registro de marca
     * @param registroDTO con la marca y la hora de salida seteada.
     * @return DTO del registro actualizado
     * @throws NegocioException 
     */
    public DTORegistroMarca modificar(DTORegistroMarca registroDTO) throws NegocioException{
        //1. Corroborar que no sea nulo 
        if (registroDTO == null || registroDTO.getIdRegistroMarca() == null) {
            throw new NegocioException("Error al intentar modificar: los datos son nulos");
        }
        try{
            //2. Adaptamos la entidad a una limpia
            RegistroMarca entidadLimpia = RegistroMarcaToDTORegistroMarca.toPersistencia(registroDTO);
            entidadLimpia = fachadaDAO.modificarMarca(entidadLimpia);
            //3. Regresamos la entidad hacia arriba 
            return RegistroMarcaToDTORegistroMarca.toDTO(entidadLimpia);
        }catch(PersistenciaException e){
            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al intentar modificar la marca");
        }               
    }
    /**
     * Método para obtener una marca en especifico 
     * @param idEmpleado que tiene la marca
     * @param fecha del dia en que se registro la marca
     * @return DTO de la marca
     * @throws NegocioException 
     */
    public DTORegistroMarca obtenerPorEmpleadoYFecha(String idEmpleado, LocalDate fecha) throws NegocioException{
        //1. Validar que los campos no sean nulos
        if (idEmpleado == null || fecha == null) {
            throw new NegocioException("Error: fecha o idEmpleado nulo");
        }
        try{
            //2. Obtener el resultado por los filtros de busqueda
            RegistroMarca resultado = fachadaDAO.obtenerPorEmpleadoYFechaMarca(idEmpleado, fecha);
            
            if (resultado == null) {
                return null;
            }
            return RegistroMarcaToDTORegistroMarca.toDTO(resultado);
        }catch(PersistenciaException e){
            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al intentar obtener la marca");
        }                  
    }
    /**
     * Método para obtener todas las marcas en un rango de fechas de un empleado en concreto
     * @param idEmpleado empleado del cual queremos generar su reporte
     * @param inicio fecha de inicio igual o mayor
     * @param fin fecha de fin menor o igual
     * @return una lista de DTO de las marcas
     * @throws NegocioException 
     */
    public List<DTORegistroMarca> obtenerLista(String idEmpleado, LocalDate inicio, LocalDate fin) throws NegocioException{
        //1.Validar que ningun campo sea nulo 
        if (idEmpleado == null) {
            throw new NegocioException("El id del empleado es nulo");
        }
        if (inicio == null) {
            throw new NegocioException("La fecha de incio es nula");
        }
        if (fin == null) {
            throw new NegocioException("La fecha de fin es nula");
        }
        
        try{
            //2. Primero obtener la lista de marcas limpia
            List<RegistroMarca> listaEntidadLimpia = fachadaDAO.obtenerListaRegistroMarca(idEmpleado, inicio, fin);
            //3. Crear la lista de DTO
            List<DTORegistroMarca> listaDTOMarca = new ArrayList<>();
            //Adaptar las entidades limpia -> DTO
            for(RegistroMarca marca : listaEntidadLimpia){
                listaDTOMarca.add(RegistroMarcaToDTORegistroMarca.toDTO(marca));
            }
            
            return listaDTOMarca; 
        }catch(PersistenciaException e){
            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al intentar obtener la lista de marcas");
        }
    }
    /**
     * Este metodo se trae la lista de los registros de asistencia de los empleados y los cuenta.
     * @param listaMarcas
     * @return 
     */
    public int calcularAsistencias(List<DTORegistroMarca> listaMarcas){
        int total = 0; 
        for(DTORegistroMarca marca : listaMarcas){
            if (marca.getEntrada() != null && marca.getSalida() != null) {
                total++;
            }
        }
        return total;
        
    }
}

