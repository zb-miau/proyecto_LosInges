/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author RAMSES
 */
public class HorarioEmpleado {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idEmpleado;
    
    private Turno turno;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;

    public HorarioEmpleado() {
    }

    public HorarioEmpleado(String id, Turno turno, LocalDate fechaIncio, LocalDate fechaFin) {
        this.idEmpleado = id;
        this.turno = turno;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public String getEmpleado() {
        return idEmpleado;
    }

    public Turno getTurno() {
        return turno;
    }

    public LocalDate getFechaIncio() {
        return fechaIncio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setEmpleado(String empleado) {
        this.idEmpleado = empleado;
    }

    public void setTurno(Turno turno) {
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
        hash = 29 * hash + Objects.hashCode(this.idEmpleado);
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
        return Objects.equals(this.idEmpleado, other.idEmpleado);
    }
    
}
