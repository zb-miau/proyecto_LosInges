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
    private String idHorarioEmpleado;
    private DTOEmpleado empleado;
    private DTOTurno turno;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaCambio;

    public DTOHorarioEmpleado() {
    }

    public DTOHorarioEmpleado(String idEmpleado, DTOTurno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.idHorarioEmpleado = idEmpleado;
        this.turno = turno;
        this.fechaInicio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public DTOHorarioEmpleado(DTOEmpleado empleado, DTOTurno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.turno = turno;
        this.fechaInicio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public DTOEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(DTOEmpleado empleado) {
        this.empleado = empleado;
    }

    public DTOTurno getTurno() {
        return turno;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setTurno(DTOTurno turno) {
        this.turno = turno;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
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
        final DTOHorarioEmpleado other = (DTOHorarioEmpleado) obj;
        return Objects.equals(this.idHorarioEmpleado, other.idHorarioEmpleado);
    }

    public String getIdHorarioEmpleado() {
        return idHorarioEmpleado;
    }

    public void setIdHorarioEmpleado(String idHorarioEmpleado) {
        this.idHorarioEmpleado = idHorarioEmpleado;
    }

}
