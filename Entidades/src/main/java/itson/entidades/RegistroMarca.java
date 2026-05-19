/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *Clase para la entidad limpia de RegistroMarca que es la entidad encargada de guardar
 * las asistencias del empleado
 * @author josma
 */
public class RegistroMarca {
    private String idRegistroMarca;
    private Empleado empleado;
    private LocalDateTime registroEntrada;
    private LocalDateTime registroSalida;
    private LocalDate fecha;

    public RegistroMarca() {
    }

    public RegistroMarca(String idRegistroMarca, Empleado empleado, LocalDateTime registroEntrada, LocalDateTime registroSalida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleado = empleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    public RegistroMarca(Empleado empleado, LocalDateTime registroEntrada, LocalDateTime registroSalida, LocalDate fecha) {
        this.empleado = empleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    public String getIdRegistroMarca() {
        return idRegistroMarca;
    }

    public void setIdRegistroMarca(String idRegistroMarca) {
        this.idRegistroMarca = idRegistroMarca;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    

    public LocalDateTime getRegistroEntrada() {
        return registroEntrada;
    }

    public void setRegistroEntrada(LocalDateTime registroEntrada) {
        this.registroEntrada = registroEntrada;
    }

    public LocalDateTime getRegistroSalida() {
        return registroSalida;
    }

    public void setRegistroSalida(LocalDateTime registroSalida) {
        this.registroSalida = registroSalida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}