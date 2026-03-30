/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jesus
 */
public class DTOHorarioEmpleado {

    private DTOEmpleado empleado;
    private DTOTurno turno;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;

    public DTOHorarioEmpleado() {
    }

    public DTOHorarioEmpleado(DTOEmpleado empleado, DTOTurno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.turno = turno;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public DTOEmpleado getEmpleado() {
        return empleado;
    }

    public DTOTurno getTurno() {
        return turno;
    }

    public LocalDate getFechaIncio() {
        return fechaIncio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setEmpleado(DTOEmpleado empleado) {
        this.empleado = empleado;
    }

    public void setTurno(DTOTurno turno) {
        this.turno = turno;
    }

    public void setFechaIncio(LocalDate fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.empleado);
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
        final DTOHorarioEmpleado other = (DTOHorarioEmpleado) obj;
        return Objects.equals(this.empleado, other.empleado);
    }
    
    

}
