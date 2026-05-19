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
    private String idEmpleado;
    private String nombreEmpleado;
    private LocalDateTime entrada;
    private LocalDateTime salida;
    private LocalDate fecha; 

    public DTORegistroMarca() {
    }

    public DTORegistroMarca(String idRegistroMarca, String idEmpleado, String nombreEmpleado, LocalDateTime entrada, LocalDateTime salida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public DTORegistroMarca(String idEmpleado, String nombreEmpleado, LocalDateTime entrada, LocalDateTime salida, LocalDate fecha) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
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

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
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
