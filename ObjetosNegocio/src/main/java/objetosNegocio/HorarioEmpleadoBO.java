/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOHorarioEmpleado;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.IAccesoDatos;
import itson.accesodatos.PersistenciaException;
import itson.entidades.HorarioEmpleado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author josma
 */
public class HorarioEmpleadoBO {
    private final IAccesoDatos fachada;
    private static HorarioEmpleadoBO horarioEmpleadosBO;
    private static final Logger LOGGER = Logger.getLogger(HorarioEmpleadoBO.class.getName());
    

    public static synchronized HorarioEmpleadoBO getInstance() {
        if (horarioEmpleadosBO == null) {
            horarioEmpleadosBO = new HorarioEmpleadoBO();
        }
        return horarioEmpleadosBO;
    }
    
    /**
     * constructor
     */
    private HorarioEmpleadoBO(){
        this.fachada = FacadeAccesoDatos.getInstance();

    }

    /**
     * Método para crear un nuevo horario en el historial del empleado.
     * @param horarioEmpleado nuevo horario a agregar al historial
     * @return regresa los datos del historial registrado.
     * @throws NegocioException Lanza un error si algún dato relevante es nulo o si
     * existió un error al acceder a la base de datos.
     */
    public DTOHorarioEmpleado crear(DTOHorarioEmpleado horarioEmpleado) throws NegocioException {
        if (horarioEmpleado == null){
            throw new NegocioException("Error al insertar el horario: no es posible insertar un horario vacío." );
        }
        if (horarioEmpleado.getEmpleado().getId() == null){
            throw new NegocioException("Error al insertar el horario: empleado vacío." );
        }
        
        if (horarioEmpleado.getFechaInicio() == null){
            throw new NegocioException("Error al insertar el horario: la fecha de inicio no puede estar vacía." );
        }
        
        if (horarioEmpleado.getTurno() == null){
            throw new NegocioException("Error al insertar el horario: el turno no puede estar vacío o nulo." );
        }
                
        try {
            HorarioEmpleado horarioEmpleadoCrear = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
            horarioEmpleadoCrear.setIdEmpleado(horarioEmpleado.getEmpleado().getId());
            horarioEmpleadoCrear.getTurno().setColorHexadecimal(horarioEmpleado.getTurno().getColorHexadecimal());
            
            horarioEmpleadoCrear = fachada.crearHorarioHistorial(horarioEmpleadoCrear);
            DTOHorarioEmpleado horario =  HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleadoCrear);
        
        return horario;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al insertar el horario: " + ex.getMessage());
        }
    }

    /**
    * Método que obtiene una lista de los horarios activos en el historial que se 
    * traslapan con el horario a agregar. 
    * @param horarioEmpleado horario nuevo a agregar.
    * @return lista con todos los horarios que se traslapan con el horario nuevo.
    * @throws NegocioException Lanza un error si el horario nuevo es nulo, si no tiene empleado, o
    * si hay un error al acceder a la base de datos.
    */
    public List<DTOHorarioEmpleado> obtenerActivo(DTOHorarioEmpleado horarioEmpleado) throws NegocioException {
        if (horarioEmpleado == null){
            throw new NegocioException("Error al recuperar el horario: horario vacío.");
        }
        if (horarioEmpleado.getEmpleado().getId() == null || horarioEmpleado.getEmpleado() == null){
            throw new NegocioException("Error al obtener el horario: no se puede obtener un horario sin el identificador del empleado.");
        }
        
        try {
            HorarioEmpleado horarioEmpleadoObtener = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
            List<HorarioEmpleado> horariosTraslape = fachada.obtenerHorarioActivo(horarioEmpleadoObtener);
            List<DTOHorarioEmpleado> horariosTraslapeLimpios = new ArrayList();
            for (HorarioEmpleado h : horariosTraslape) {
                DTOHorarioEmpleado horarioEmpleadoEmpalmado = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(h);
                horariosTraslapeLimpios.add(horarioEmpleadoEmpalmado);
            }
            
            return horariosTraslapeLimpios;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al obtener el horario: " + ex.getMessage());
        }
    }

    /**
     * Método que recupera todos los horarios de un rango de fechas específico. Utilizado para
     * recuperar los horarios del mes que está siendo desplegado en el calendario.
     * @param empleado horario con el id del empleado a filtrar en el historial.
     * @param fechaInicio fecha de inicio de la busqueda.
     * @param fechaFin fecha de fin de la busqueda.
     * @return regresa la lista de horarios dentro del rango de fechas.
     * @throws NegocioException Lanza un error si el horario no tiene el id del empleado o si hay
     * un error al acceder a la base de datos.
     */
    public List<DTOHorarioEmpleado> obtenerLista(DTOHorarioEmpleado empleado,  LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        if (empleado.getEmpleado().getId() == null){
            throw new NegocioException("Error al obtener los horarios: no se puede obtener un horario sin el identificador del empleado.");
        }
        try {
            List<HorarioEmpleado> horarioEmpleados = fachada.obtenerHistorial(
                    HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(empleado),
                    fechaInicio, fechaFin);
            List<DTOHorarioEmpleado> listaTurnos = new ArrayList();
            for (HorarioEmpleado h : horarioEmpleados) {
                DTOHorarioEmpleado horarioEmpleadoNuevo = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(h);
                listaTurnos.add(horarioEmpleadoNuevo);
            }

            return listaTurnos;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al obtener los horarios: " + ex.getMessage());
        }
    }

    /**
     * Método que recupera y modifica un horario en el historial. Utilizado para modificar
     * las fechas de inicio y fin de un historial cuando hay traslapes.
     * @param horario horario a editar.
     * @return regresa el horario modificado.
     * @throws NegocioException Lanza un error si hay un error al acceder a la base de datos.
     */
    public DTOHorarioEmpleado modificarHorarioInfinito(DTOHorarioEmpleado horario) throws NegocioException {
        try {
            HorarioEmpleado horarioModificar = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horario);
            
            return HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(fachada.modificarHistorial(horarioModificar));
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el historial: " + ex.getMessage());
        }
    }
    
    /**
     * Método que elimina un horario del historial. Utilizado cuando un traslape es tan grande
     * que elimina un horario por completo del calendario.
     * @param horario horario a eliminar.
     * @return regresa el horario eliminado.
     * @throws NegocioException Lanza un error si hay un error al acceder a la base de datos.
     */
    public DTOHorarioEmpleado eliminarHistorial(DTOHorarioEmpleado horario)throws NegocioException {
        try {
            HorarioEmpleado horarioEliminar = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horario);
            
            return HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(fachada.eliminarHistorial(horarioEliminar));
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el historial: " + ex.getMessage());
        }
    }
}
