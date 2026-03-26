/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 *
 * @author jesus
 */
public class DTOTurno {

    private String tipo;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Set<DayOfWeek> diasTrabajo;

    public DTOTurno() {
    }

    public DTOTurno(String tipo, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.tipo = tipo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Set<DayOfWeek> getDiasTrabajo() {
        return diasTrabajo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDiasTrabajo(Set<DayOfWeek> diasTrabajo) {
        this.diasTrabajo = diasTrabajo;
    }

}
