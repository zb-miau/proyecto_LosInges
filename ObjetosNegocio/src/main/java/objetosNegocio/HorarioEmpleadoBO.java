/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOHorarioEmpleado;
import itson.accesodatos.HorarioEmpleadosDAO;
import itson.accesodatos.IAccesoDatos;
import itson.entidades.HorarioEmpleado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josma
 */
public class HorarioEmpleadoBO {

    private final IAccesoDatos<HorarioEmpleado> dao;
    private static HorarioEmpleadoBO horarioEmpleadosBO;

    public static synchronized HorarioEmpleadoBO getInstance() {
        if (horarioEmpleadosBO == null) {
            horarioEmpleadosBO = new HorarioEmpleadoBO();
        }
        return horarioEmpleadosBO;
    }
    
    private HorarioEmpleadoBO(){
        this.dao = HorarioEmpleadosDAO.getInstance();

    }


    public DTOHorarioEmpleado crear(DTOHorarioEmpleado horarioEmpleado) {
        HorarioEmpleado horarioEmpleadoCrear = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
        horarioEmpleadoCrear = dao.crear(horarioEmpleadoCrear);
        DTOHorarioEmpleado horario =  HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleadoCrear);
        
        return horario;
    }

    public DTOHorarioEmpleado eliminar(DTOHorarioEmpleado horarioEmpleado) {
        HorarioEmpleado horarioEmpleadoEliminar = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
        horarioEmpleadoEliminar = dao.eliminar(horarioEmpleadoEliminar);

        return horarioEmpleado;
    }

    public DTOHorarioEmpleado modificar(DTOHorarioEmpleado horarioEmpleado) {
        HorarioEmpleado horarioEmpleadoModificar = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
        horarioEmpleadoModificar = dao.modificar(horarioEmpleadoModificar);
        DTOHorarioEmpleado horarioEmpleadoModificado = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleadoModificar);

        return horarioEmpleadoModificado;
    }

    public DTOHorarioEmpleado obtener(DTOHorarioEmpleado horarioEmpleado) {
        HorarioEmpleado horarioEmpleadoObtener = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleado);
        horarioEmpleadoObtener = dao.obtener(horarioEmpleadoObtener);
        DTOHorarioEmpleado horarioEmpleadoRecuperado = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(horarioEmpleadoObtener);
        return horarioEmpleadoRecuperado;
    }

    public List<DTOHorarioEmpleado> obtenerLista() {
        List<HorarioEmpleado> horarioEmpleados = dao.obtenerLista();
        List<DTOHorarioEmpleado> listaTurnos = new ArrayList();
        for (HorarioEmpleado h : horarioEmpleados) {
            DTOHorarioEmpleado horarioEmpleadoNuevo = HorarioEmpleadoToDTOHorarioEmpleadoAdapter.adaptar(h);
            listaTurnos.add(horarioEmpleadoNuevo);
        }

        return listaTurnos;
    }

}
