/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;

/**
 *Clase para la entidad limpia de RegistroMarca que es la entidad encargada de guardar
 * las asistencias del empleado
 * @author josma
 */
public class RegistroMarca {
    private String idRegistroMarca;
    private Empleado empleado;
    private HorarioEmpleado horarioEmpleado; //<-- aun tengo que ver si si lo utilizo
    private LocalDate registroEntrada;
    private LocalDate registroSalida;

    public RegistroMarca() {
    }

    public RegistroMarca(String idRegistroMarca, Empleado empleado, HorarioEmpleado horarioEmpleado, LocalDate registroEntrada, LocalDate registroSalida) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
    }

    public RegistroMarca(Empleado empleado, HorarioEmpleado horarioEmpleado, LocalDate registroEntrada, LocalDate registroSalida) {
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
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

    public LocalDate getRegistroEntrada() {
        return registroEntrada;
    }

    public void setRegistroEntrada(LocalDate registroEntrada) {
        this.registroEntrada = registroEntrada;
    }

    public LocalDate getRegistroSalida() {
        return registroSalida;
    }

    public void setRegistroSalida(LocalDate registroSalida) {
        this.registroSalida = registroSalida;
    }
    
    
}
