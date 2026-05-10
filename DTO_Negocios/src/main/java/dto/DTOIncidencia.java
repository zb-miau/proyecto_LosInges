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

    private String tipo;

    private DTOEmpleado empleado;

    private String descripcion;

    private LocalDate fecha;

    private Estado estado;

    public enum Estado {
        VALIDADA, RECHAZADA, PENDIENTE
    };

    public DTOIncidencia() {
    }

    public DTOIncidencia(String idIncidencia, String tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public DTOIncidencia(String tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public String getTipo() {
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

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setTipo(String tipo) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
