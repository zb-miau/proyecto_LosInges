/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Zaira
 */
public class Turno {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idTurno;
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Set<DayOfWeek> diasTrabajo;
    private String colorHexadecimal;

    public Turno() {
    }

    public Turno(String idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public Turno(String idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo,  String colorHex) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorHexadecimal = colorHex;
    }

    public Turno(String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public Turno(String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, String colorHex) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorHexadecimal = colorHex;
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

    public String getColorHexadecimal(){
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idTurno);
        hash = 89 * hash + Objects.hashCode(this.horaInicio);
        hash = 89 * hash + Objects.hashCode(this.horaFin);
        hash = 89 * hash + Objects.hashCode(this.diasTrabajo);
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
