/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *Clase para la entidad limpia de RegistroMarca que es la entidad encargada de guardar
 * las asistencias del empleado
 * @author josma
 */
public class RegistroMarca {
    private String idRegistroMarca;
    private Empleado empleado;
    private HorarioEmpleado horarioEmpleado; 
    private LocalTime registroEntrada;
    private LocalTime registroSalida;
    private LocalDate fecha;

    public RegistroMarca() {
    }

    public RegistroMarca(String idRegistroMarca, Empleado empleado, HorarioEmpleado horarioEmpleado, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    public RegistroMarca(Empleado empleado, HorarioEmpleado horarioEmpleado, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    public RegistroMarca(String idRegistroMarca, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
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

    public HorarioEmpleado getHorarioEmpleado() {
        return horarioEmpleado;
    }

    public void setHorarioEmpleado(HorarioEmpleado horarioEmpleado) {
        this.horarioEmpleado = horarioEmpleado;
    }

    public LocalTime getRegistroEntrada() {
        return registroEntrada;
    }

    public void setRegistroEntrada(LocalTime registroEntrada) {
        this.registroEntrada = registroEntrada;
    }

    public LocalTime getRegistroSalida() {
        return registroSalida;
    }

    public void setRegistroSalida(LocalTime registroSalida) {
        this.registroSalida = registroSalida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
}