/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author jesus
 */
public class Incidencia {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idIncidencia;
    
    @BsonProperty("tipo") 
    private String tipo;
    
    @BsonProperty("empleado") 
    private Empleado empleado;
    
    @BsonProperty("descripcion") 
    private String descripcion;
    
    @BsonProperty("fecha") 
    private LocalDate fecha;
    
    @BsonProperty("estado") 
    private Estado estado;
    
    
    private enum Estado{VALIDADA, RECHAZADA, PENDIENTE};

    public Incidencia() {
    }

    public Incidencia(String tipo, Empleado empleado, String descripcion, LocalDate fecha, Estado estado) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Incidencia(String idIncidencia, String tipo, Empleado empleado, String descripcion, LocalDate fecha, Estado estado) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Incidencia(String tipo, Empleado empleado) {
        this.tipo = tipo;
        this.empleado = empleado;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public String getTipo() {
        return tipo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idIncidencia);
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
        final Incidencia other = (Incidencia) obj;
        return Objects.equals(this.idIncidencia, other.idIncidencia);
    }
    
    
    
}
