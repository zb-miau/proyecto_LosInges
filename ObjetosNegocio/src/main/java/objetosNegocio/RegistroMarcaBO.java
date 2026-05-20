/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTORegistroMarca;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.IAccesoDatos;
import itson.accesodatos.PersistenciaException;
import itson.entidades.Empleado;
import itson.entidades.RegistroMarca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Business Object (BO) encargado de centralizar las reglas de negocio para la 
 * gestión de registros de asistencia de los empleados.
 * Esta clase actúa como mediadora entre la capa de presentación y la capa de datos,
 * realizando validaciones de integridad, orquestando conversiones de datos 
 * y manejando la lógica de cálculo de asistencias.
 * @author josma
 */
public class RegistroMarcaBO {
    private static final Logger LOGGER = Logger.getLogger(EmpleadoBO.class.getName());
    /** Fachada para el acceso a las operaciones de persistencia. */
    IAccesoDatos fachadaDAO;
    /** Instancia única de la clase (Patrón Singleton). */
    private static RegistroMarcaBO registroMarcaBO;
    /**
     * Recupera la instancia única y global de RegistroMarcaBO.
     * @return Instancia única del controlador de negocio de marcas.
     */
    public static synchronized RegistroMarcaBO getInstance() {
        if (registroMarcaBO == null) {
            registroMarcaBO = new RegistroMarcaBO();
        }
        return registroMarcaBO;
    }
    
    /**
     * Constructor privado que inicializa la referencia a la fachada de acceso a datos.
     * Implementa el patrón Singleton para restringir la instanciación externa.
     */
    private RegistroMarcaBO(){
        this.fachadaDAO = FacadeAccesoDatos.getInstance();

    }
    /**
     * Crea un nuevo registro de marca de asistencia.
     * Valida que los datos obligatorios del DTO no sean nulos y transforma el 
     * objeto para su persistencia a través de la fachada de datos.
     * @param registroDTO Objeto de transferencia con los datos de la marca a crear.
     * @return El DTORegistroMarca persistido con su ID generado.
     * @throws NegocioException Si los datos son nulos, el empleado es inválido o 
     * ocurre un error en la capa de persistencia.
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
     * Actualiza un registro de marca existente, típicamente para registrar una salida.
     * @param registroDTO DTO que contiene el ID de la marca y los datos actualizados.
     * @return El DTORegistroMarca actualizado.
     * @throws NegocioException Si el DTO o el ID de la marca son nulos, o si falla la persistencia.
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
     * Busca una marca de asistencia específica por empleado y fecha.
     * @param empleado Identificador único del empleado.
     * @param fecha Fecha de la marca a consultar.
     * @return DTORegistroMarca encontrado, o null si no existe registro.
     * @throws NegocioException Si los parámetros de búsqueda son nulos o hay error en la base de datos.
     */
    public DTORegistroMarca obtenerPorEmpleadoYFecha(DTOEmpleado empleado, LocalDate fecha) throws NegocioException{
        //1. Validar que los campos no sean nulos
        if (empleado == null || fecha == null) {
            throw new NegocioException("Error: fecha o idEmpleado nulo");
        }
        try{
            //2. Obtener el resultado por los filtros de busqueda
            Empleado empleadoLimpio = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
            RegistroMarca resultado = fachadaDAO.obtenerPorEmpleadoYFechaMarca(empleadoLimpio, fecha);
            
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
     * Obtiene una lista de marcas de un empleado dentro de un rango de fechas determinado.
     * @param empleado Identificador del empleado para el reporte.
     * @param inicio Fecha de inicio del rango (inclusive).
     * @param fin Fecha de fin del rango (inclusive).
     * @return Lista de DTORegistroMarca que cumplen con los criterios.
     * @throws NegocioException Si alguno de los parámetros de rango es nulo.
     */
    public List<DTORegistroMarca> obtenerLista(DTOEmpleado empleado, LocalDate inicio, LocalDate fin) throws NegocioException{
        //1.Validar que ningun campo sea nulo 
        if (empleado == null) {
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
            Empleado empleadoLimpio = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
            List<RegistroMarca> listaEntidadLimpia = fachadaDAO.obtenerListaRegistroMarca(empleadoLimpio, inicio, fin);
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
     * Realiza el cálculo lógico del total de asistencias completas en una lista de registros.
     * Se considera una asistencia válida si el registro tiene tanto hora de entrada como de salida.
     * @param listaMarcas Lista de DTOs de marcas a procesar.
     * @return Cantidad total de asistencias completas.
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

