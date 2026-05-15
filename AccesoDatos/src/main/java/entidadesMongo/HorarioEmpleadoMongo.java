/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import itson.entidades.Empleado;
import itson.entidades.Turno;
import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author Zaira
 */
public class HorarioEmpleadoMongo {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idHorarioEmpleado;
    
    @BsonIgnore
    private Empleado empleado;
    
    @BsonProperty("id_empleado") 
    private String idEmpleado;
    
    private Turno turno;
    
    @BsonProperty("fecha_inicio") 
    private LocalDate fechaInicio;
    
    @BsonProperty("fecha_fin") 
    private LocalDate fechaFin;
    
    @BsonProperty("fecha_fin") 
    private LocalDate fechaCambio;

    public HorarioEmpleadoMongo() {
    }

    public HorarioEmpleadoMongo(String idHorarioEmpleado, Empleado empleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idHorarioEmpleado = idHorarioEmpleado;
        this.empleado = empleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    public HorarioEmpleadoMongo(Empleado empleado, Turno turno, LocalDate fechaInicio, LocalDate fechaFin) {
        this.empleado = empleado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
        hash = 79 * hash + Objects.hashCode(this.idHorarioEmpleado);
        hash = 79 * hash + Objects.hashCode(this.empleado);
        hash = 79 * hash + Objects.hashCode(this.idEmpleado);
        hash = 79 * hash + Objects.hashCode(this.turno);
        hash = 79 * hash + Objects.hashCode(this.fechaInicio);
        hash = 79 * hash + Objects.hashCode(this.fechaFin);
        hash = 79 * hash + Objects.hashCode(this.fechaCambio);
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
        final HorarioEmpleadoMongo other = (HorarioEmpleadoMongo) obj;
        if (!Objects.equals(this.idHorarioEmpleado, other.idHorarioEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.idEmpleado, other.idEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        if (!Objects.equals(this.turno, other.turno)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFin, other.fechaFin)) {
            return false;
        }
        return Objects.equals(this.fechaCambio, other.fechaCambio);
    }

    
}
