/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author jesus
 */
public class DTOTurno {
    private Long idTurno;
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Set<DayOfWeek> diasTrabajo;
    private Color colorEvento;

    public DTOTurno() {
    }

    public DTOTurno(Long idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public DTOTurno(Long idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, Color colorEvento) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorEvento = colorEvento;
    }
    

    public String getNombre() {
        return nombre;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Color getColorEvento() {
        return colorEvento;
    }

    public void setColorEvento(Color colorEvento) {
        this.colorEvento = colorEvento;
    }

    public DTOTurno(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idTurno);
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.horaInicio);
        hash = 47 * hash + Objects.hashCode(this.horaFin);
        hash = 47 * hash + Objects.hashCode(this.diasTrabajo);
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
        final DTOTurno other = (DTOTurno) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.idTurno, other.idTurno)) {
            return false;
        }
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        return Objects.equals(this.diasTrabajo, other.diasTrabajo);
    }



    
    
    
}
