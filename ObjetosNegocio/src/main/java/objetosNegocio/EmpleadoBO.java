/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import itson.accesodatos.EmpleadosDAO;
import itson.accesodatos.IAccesoDatos;
import itson.entidades.Empleado;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author RAMSES
 */
public class EmpleadoBO {
    
    private final IAccesoDatos<Empleado> dao;
    private static EmpleadoBO empleadosBO;

    public static synchronized EmpleadoBO getInstance() {
        if (empleadosBO == null) {
            empleadosBO = new EmpleadoBO();
        }
        return empleadosBO;
    }
    
    private EmpleadoBO(){
        this.dao = EmpleadosDAO.getInstance();

    }
    

    public DTOEmpleado crear(DTOEmpleado empleado){

        Empleado empleadoCrear = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
        empleadoCrear = dao.crear(empleadoCrear);
        DTOEmpleado empleadoCreado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(empleadoCrear);
                

        return empleadoCreado;

    }

    

    public DTOEmpleado eliminar(DTOEmpleado empleado){

        Empleado empleadoEliminar = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
        empleadoEliminar = dao.eliminar(empleadoEliminar);

        

        return empleado;

    }

    

    public DTOEmpleado modificar(DTOEmpleado empleado){

        Empleado empleadoModificar = EmpleadoToDTOEmpleadoAdapter.adaptarDTO(empleado);
        empleadoModificar = dao.modificar(empleadoModificar);
        DTOEmpleado empleadoModificado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(empleadoModificar);

        

        return empleadoModificado;

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
}
