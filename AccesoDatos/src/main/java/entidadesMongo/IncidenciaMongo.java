/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import itson.entidades.Empleado;
import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class IncidenciaMongo {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idIncidencia;
    private TiposIncidencia tipo;
    @BsonProperty("id_empleado") 
    private ObjectId idEmpleado;
    private String descripcion;
    private LocalDate fecha;
    private Estado estado;
    private String observaciones;
    private EmpleadoMongo empleado;

    public enum Estado {
        VALIDADA, RECHAZADA, PENDIENTE
    };

    public enum TiposIncidencia {
        AUSENTISMO("Ausentismo"),
        RETARDO("Retardo"),
        INDISCIPLINA("Indisciplina"),
        INCUMPLIMIENTO_SEGURIDAD("Incumplimiento de Seguridad"),
        BAJO_RENDIMIENTO("Bajo Rendimiento"),
        SANCION_ADMINISTRATIVA("Sanción Administrativa"),
        ROBO("Robo o Hurto"),
        ACOSO("Acoso / Hostigamiento"),
        CONSUMO_SUSTANCIAS("Consumo de Sustancias"),
        ABANDONO_PUESTO("Abandono de Puesto");

        // Atributo para guardar el texto amigable
        private final String nombre;

        // Constructor del Enum (es privado por defecto)
        TiposIncidencia(String nombre) {
            this.nombre = nombre;
        }

        // Sobreescribimos toString para que el ComboBox muestre el 'nombre'
        @Override
        public String toString() {
            return nombre;
        }
    }

    public IncidenciaMongo() {
    }

    public IncidenciaMongo(String idIncidencia, TiposIncidencia tipo, ObjectId idEmpleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public IncidenciaMongo(TiposIncidencia tipo, ObjectId idEmpleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public IncidenciaMongo(TiposIncidencia tipo, ObjectId idEmpleado, String descripcion) {
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public TiposIncidencia getTipo() {
        return tipo;
    }

    public ObjectId getIdEmpleado() {
        return idEmpleado;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setTipo(TiposIncidencia tipo) {
        this.tipo = tipo;
    }

    public void setIdEmpleado(ObjectId idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EmpleadoMongo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoMongo empleado) {
        this.empleado = empleado;
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
        final IncidenciaMongo other = (IncidenciaMongo) obj;
        return Objects.equals(this.idIncidencia, other.idIncidencia);
    }

    
}
