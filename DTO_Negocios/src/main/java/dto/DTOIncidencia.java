/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jesus
 */
public class DTOIncidencia {
    private String idIncidencia;
    private TiposIncidencia tipo;
    private DTOEmpleado empleado;
    private String descripcion;
    private LocalDate fecha;
    private Estado estado;
    private String observaciones;

    public enum Estado {
        VALIDADA, RECHAZADA, PENDIENTE
    };

    public enum TiposIncidencia {
        AUSENTISMO("Ausentismo"),
        RETARDO("Retardo"),
        INDISCIPLINA("Falta de Indisciplina"),
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

    public DTOIncidencia() {
    }

    public DTOIncidencia(String idIncidencia, TiposIncidencia tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public DTOIncidencia(TiposIncidencia tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public DTOIncidencia(TiposIncidencia tipo, DTOEmpleado empleado, String descripcion) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public TiposIncidencia getTipo() {
        return tipo;
    }

    public DTOEmpleado getEmpleado() {
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setTipo(TiposIncidencia tipo) {
        this.tipo = tipo;
    }

    public void setEmpleado(DTOEmpleado empleado) {
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

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idIncidencia);
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
        final DTOIncidencia other = (DTOIncidencia) obj;
        return Objects.equals(this.idIncidencia, other.idIncidencia);
    }

}
