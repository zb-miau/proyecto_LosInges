/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author josma
 */
public class DTORegistroMarca {
    private String idRegistroMarca;
    private DTOEmpleado empleadoDTO; 
    private DTOHorarioEmpleado horarioEmpledoDTO;
    private LocalDateTime entrada;
    private LocalDateTime salida;
    private LocalDate fecha; 

    public DTORegistroMarca() {
    }

    public DTORegistroMarca(String idRegistroMarca, DTOEmpleado empleadoDTO, DTOHorarioEmpleado horarioEmpledoDTO, LocalDateTime entrada, LocalDateTime salida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleadoDTO = empleadoDTO;
        this.horarioEmpledoDTO = horarioEmpledoDTO;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public DTORegistroMarca(DTOEmpleado empleadoDTO, DTOHorarioEmpleado horarioEmpledoDTO, LocalDateTime entrada, LocalDateTime salida, LocalDate fecha) {
        this.empleadoDTO = empleadoDTO;
        this.horarioEmpledoDTO = horarioEmpledoDTO;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public DTORegistroMarca(String idRegistroMarca, LocalDateTime entrada, LocalDateTime salida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public String getIdRegistroMarca() {
        return idRegistroMarca;
    }

    public void setIdRegistroMarca(String idRegistroMarca) {
        this.idRegistroMarca = idRegistroMarca;
    }

    public DTOEmpleado getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(DTOEmpleado empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public DTOHorarioEmpleado getHorarioEmpledoDTO() {
        return horarioEmpledoDTO;
    }

    public void setHorarioEmpledoDTO(DTOHorarioEmpleado horarioEmpledoDTO) {
        this.horarioEmpledoDTO = horarioEmpledoDTO;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    
}
