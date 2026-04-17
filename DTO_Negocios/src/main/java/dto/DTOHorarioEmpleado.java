/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author jesus
 */
public class DTOHorarioEmpleado {
    private Long idHorarioEmpleado;
    private DTOTurno turno;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;

    public DTOHorarioEmpleado() {
    }

    public DTOHorarioEmpleado(Long id, DTOTurno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.idHorarioEmpleado = id;
        this.turno = turno;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public Long getEmpleado() {
        return idHorarioEmpleado;
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

    public void setEmpleado(Long empleado) {
        this.idHorarioEmpleado = empleado;
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
        final DTOHorarioEmpleado other = (DTOHorarioEmpleado) obj;
        return Objects.equals(this.idHorarioEmpleado, other.idHorarioEmpleado);
    }

    public Long getIdHorarioEmpleado() {
        return idHorarioEmpleado;
    }

    public void setIdHorarioEmpleado(Long idHorarioEmpleado) {
        this.idHorarioEmpleado = idHorarioEmpleado;
    }

}
