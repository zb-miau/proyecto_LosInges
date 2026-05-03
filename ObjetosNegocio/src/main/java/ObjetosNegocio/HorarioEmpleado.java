/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetosNegocio;

import java.time.LocalDate;

/**
 *
 * @author josma
 */
public class HorarioEmpleado {

    private Empleado empleado;
    private Turno turno;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;

    public HorarioEmpleado() {
    }

    public HorarioEmpleado(Empleado empleado, Turno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.turno = turno;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public LocalDate getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(LocalDate fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    

}
