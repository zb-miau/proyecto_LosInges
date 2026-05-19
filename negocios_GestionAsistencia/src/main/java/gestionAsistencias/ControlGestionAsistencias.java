/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionAsistencias;

import dto.DTORegistroMarca;
import java.time.LocalDate;
import java.time.LocalTime;
import objetosNegocio.HorarioEmpleadoBO;
import objetosNegocio.NegocioException;
import objetosNegocio.RegistroMarcaBO;

/**
 * Clase Control encargada de del flujo de trabajo del subistema, se encarga de controlar los
 * flujos de trabajo y las pantallas relacionadas con la administración y gestión de asistencias.
 * @author josma
 */
public class ControlGestionAsistencias {
    private RegistroMarcaBO registroMarcaBO;
    private HorarioEmpleadoBO horarioEmpleadoBO;

    public ControlGestionAsistencias() {
        this.registroMarcaBO = RegistroMarcaBO.getInstance();
    }
    
    protected DTORegistroMarca agregarMarca(DTORegistroMarca registroDTO) throws NegocioException{
        LocalDate fechaHoy = LocalDate.now();
        LocalTime tiempoHoy = LocalTime.now();
        String idEmpleado = registroDTO.getIdEmpleado();
        
        //1. Validar si al empleado le toca laborar ese dia 
        
        DTOHOrarioEmpleado horarioHoy = obtenerActivo(idEmpleado,)
    }
}
