/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author RAMSES
 */
public class HorarioEmpleado {
    private String idHorarioEmpleado;
    private Empleado empleado;
    private String idEmpleado;
    
    private Turno turno;
    
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaCambio;

    public HorarioEmpleado() {
    }

    public HorarioEmpleado(String idHorarioEmpleado, Empleado empleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idHorarioEmpleado = idHorarioEmpleado;
        this.empleado = empleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public HorarioEmpleado(Empleado empleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public HorarioEmpleado(String idEmpleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idEmpleado = idEmpleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public HorarioEmpleado(String idHorarioEmpleado, String idEmpleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaCambio) {
        this.idHorarioEmpleado = idHorarioEmpleado;
        this.idEmpleado = idEmpleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCambio = fechaCambio;
    }
    
    
  

    public Turno getTurno() {
        return turno;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIdHorarioEmpleado() {
        return idHorarioEmpleado;
    }

    public void setIdHorarioEmpleado(String idHorarioEmpleado) {
        this.idHorarioEmpleado = idHorarioEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.idEmpleado = empleado.getId();
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idHorarioEmpleado);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorarioEmpleado other = (HorarioEmpleado) obj;
        return Objects.equals(this.idHorarioEmpleado, other.idHorarioEmpleado);
    }
    
}
