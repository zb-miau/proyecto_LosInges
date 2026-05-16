/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOHorarioEmpleado;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.HorarioEmpleadosDAO;
import itson.accesodatos.IAccesoHorarioEmpleado;
import itson.accesodatos.PersistenciaException;
import itson.entidades.HorarioEmpleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author josma
 */
public class HorarioEmpleadoBO {
    private final FacadeAccesoDatos fachada;
    private static HorarioEmpleadoBO horarioEmpleadosBO;
    private static final Logger LOGGER = Logger.getLogger(HorarioEmpleadoBO.class.getName());
    

    public static synchronized HorarioEmpleadoBO getInstance() {
        if (horarioEmpleadosBO == null) {
            horarioEmpleadosBO = new HorarioEmpleadoBO();
        }
        return horarioEmpleadosBO;
    }
    
    private HorarioEmpleadoBO(){
        this.fachada = FacadeAccesoDatos.getInstance();

    }


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

   
    public DTOHorarioEmpleado obtenerActivo(DTOHorarioEmpleado horarioEmpleado) throws NegocioException {
        if (horarioEmpleado == null){
            throw new NegocioException("Error al recuperar el horario: horario vacío.");
        }
        if (horarioEmpleado.getEmpleado().getId() == null || horarioEmpleado.getEmpleado() == null){
            throw new NegocioException("Error al obtener el horario: no se puede obtener un horario sin el identificador del empleado.");
        }
        
        try {
            HorarioEmpleado horarioEmpleadoObtener = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
            horarioEmpleadoObtener = fachada.obtenerHorarioActivo(horarioEmpleadoObtener);
            DTOHorarioEmpleado horarioEmpleadoRecuperado = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptarConEmpleado(
                    horarioEmpleadoObtener, 
                    horarioEmpleado.getEmpleado());
           
            
            return horarioEmpleadoRecuperado;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al obtener el horario: " + ex.getMessage());
        }
    }

    public List<DTOHorarioEmpleado> obtenerLista(DTOHorarioEmpleado empleado) throws NegocioException {
        if (empleado.getEmpleado().getId() == null){
            throw new NegocioException("Error al obtener los horarios: no se puede obtener un horario sin el identificador del empleado.");
        }
        try {
            List<HorarioEmpleado> horarioEmpleados = fachada.obtenerHistorial(HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(empleado));
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

    public DTOHorarioEmpleado modificarHorarioInfinito(DTOHorarioEmpleado horario) throws NegocioException {
        try {
            HorarioEmpleado horarioModificar = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horario);
            
            return HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(fachada.modificarHistorial(horarioModificar));
        
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el historial: " + ex.getMessage());
        }
    }
}
