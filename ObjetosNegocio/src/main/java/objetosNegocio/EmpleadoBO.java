/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOContratacion;
import dto.DTOEmpleado;
import itson.accesodatos.EmpleadosDAO;
import itson.accesodatos.FacadeAccesoDatos;
import itson.entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import itson.accesodatos.IAccesoEmpleados;
import itson.accesodatos.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author RAMSES
 */
public class EmpleadoBO {

    private static final Logger LOGGER = Logger.getLogger(EmpleadoBO.class.getName());
    
    private final IAccesoEmpleados<Empleado> dao;
    FacadeAccesoDatos fachadaDAO;
    private static EmpleadoBO empleadosBO;

    public static synchronized EmpleadoBO getInstance() {
        if (empleadosBO == null) {
            empleadosBO = new EmpleadoBO();
        }
        return empleadosBO;
    }
    
    private EmpleadoBO(){
        this.fachadaDAO = FacadeAccesoDatos.getInstance();
        this.dao = EmpleadosDAO.getInstance();

    }
    

    public DTOContratacion crear(DTOContratacion empleado) throws NegocioException{
        
        if (empleado.getNombre() == null) {
            throw new NegocioException("El nombre esta vacio.");
        }
        
        if (empleado.getApellidoPaterno()== null) {
            throw new NegocioException("El apellido paterno esta vacio.");
        }
        
        if (empleado.getCalle()== null) {
            throw new NegocioException("El nombre de la calle esta vacio.");
        }
        
        if (empleado.getCodigoPostal()== null) {
            throw new NegocioException("El codigo postal esta vacio.");
        }
        
        if (empleado.getColonia()== null) {
            throw new NegocioException("El nombre de la colonia esta vacio.");
        }
        
        if (empleado.getCurp()== null) {
            throw new NegocioException("La CURP esta vacia.");
        }
        
        if (empleado.getFechaNacimiento()== null) {
            throw new NegocioException("La fecha de nacimiento esta vacia.");
        }
        
        if (empleado.getNss()== null) {
            throw new NegocioException("El NSS esta vacio.");
        }
        
        if (empleado.getNumeroCasa()== null) {
            throw new NegocioException("El numero de casa esta vacio.");
        }
        
        if (empleado.getRfc()== null) {
            throw new NegocioException("El RFC esta vacio.");
        }

        Empleado empleadoCrear = EmpleadoToDTOEmpleadoAdapter.adaptarDTOContratacionAEntidad(empleado);
        try {
            Empleado buscadoCurp = dao.obtenerPorCurp(empleadoCrear);
            Empleado buscadoNss = dao.obtenerPorNss(empleadoCrear);
            Empleado buscadoRfc = dao.obtenerPorRfc(empleadoCrear);
            
            
            if (buscadoCurp != null) {
                if (buscadoCurp.getCurp().equalsIgnoreCase(empleadoCrear.getCurp())) {
                    throw new NegocioException("El empleado que quiere contratar ya ha sido registrado.");
                }
            }
            
            if (buscadoNss != null) {
                if (buscadoNss.getNss().equalsIgnoreCase(empleadoCrear.getNss())) {
                    throw new NegocioException("El NSS que ingreso ya ha sido registrado.");
                }
            }
            
            if (buscadoRfc != null) {
                if (buscadoRfc.getRfc().equalsIgnoreCase(empleadoCrear.getRfc())) {
                    throw new NegocioException("El RFC que ingreso ya ha sido registrado.");
                }
            }
            
            
            empleadoCrear = dao.crear(empleadoCrear);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException("Persistencia fallo al ingresar el empleado.");
            
        }
        
        DTOContratacion empleadoCreado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidadADTOContratacion(empleadoCrear);   

        return empleadoCreado;

    }

    

    public DTOEmpleado obtener(DTOEmpleado turno){

        
        Empleado empleadoObtener = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(turno);
        empleadoObtener = dao.obtener(empleadoObtener);
        DTOEmpleado turnoRecuperado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(empleadoObtener);
        return turnoRecuperado;

    }

    

    public List<DTOEmpleado> obtenerLista(){

        List<Empleado> empleados = dao.obtenerLista();
        List<DTOEmpleado> listaEmpleados = new ArrayList();
        for (Empleado e: empleados){
            DTOEmpleado empleadoNuevo = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(e);
            listaEmpleados.add(empleadoNuevo);
        }

        return listaEmpleados;

    }
    
    /**
     * Método que recupera el empleado y modifica su atributo de horario actual.
     * @param empleado empleado al que se le va a modificar su horario.
     * @return el empleado con su horario modificado.
     * @throws NegocioException Lanza error si el horario se encuentra vacío o si hay un error
     * al acceder a la base de datos.
     */
    public DTOEmpleado modificarHorarioActual(DTOEmpleado empleado) throws NegocioException{
        if (empleado.getHorarioActual() == null ){
            throw new NegocioException("Error al modificar el horario del empleado: no se puede asignar un horario vacío");
        }
        
        try {
            Empleado empleadoModificar = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
            
            empleadoModificar = fachadaDAO.modificarHorarioActual(empleadoModificar);
            DTOEmpleado empleadoModificado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(empleadoModificar);
           
            return empleadoModificado;
        } catch (PersistenciaException ex){
            LOGGER.severe(ex.getMessage());
            throw new NegocioException("Error al modificar el horario del empleado: " + ex.getMessage());
        }
        
    }
}
