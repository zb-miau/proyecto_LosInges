/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author josma
 */
public class Turno {

   private String id;
   private String nombre;
   private LocalTime horaInicio;
   private LocalTime horaFin;
   private Set<DayOfWeek> diasTrabajo;
   private Color colorEvento;

    public Turno() {
    }

    public Turno(String id, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, Color colorEvento) {
        this.id = id;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorEvento = colorEvento;
    }

    public Turno(String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, Color colorEvento) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorEvento = colorEvento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Set<DayOfWeek> getDiasTrabajo() {
        return diasTrabajo;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.horaInicio);
        hash = 37 * hash + Objects.hashCode(this.horaFin);
        hash = 37 * hash + Objects.hashCode(this.diasTrabajo);
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
        final Turno other = (Turno) obj;
        if (!Objects.equals(this.horaInicio, other.horaInicio)) {
            return false;
        }
        if (!Objects.equals(this.horaFin, other.horaFin)) {
            return false;
        }
        return Objects.equals(this.diasTrabajo, other.diasTrabajo);
    }

    
   

}
