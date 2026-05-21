/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class DTO_CorreoElectronico {

    private String idEmpleado;

    private String idIncidencia;

    private String nombreEmpleado;

    private String tipoIncidencia;

    private String descripcion;

    public DTO_CorreoElectronico(String idEmpleado, String idIncidencia, String nombreEmpleado, String tipoIncidencia, String descripcion) {
        this.idEmpleado = idEmpleado;
        this.idIncidencia = idIncidencia;
        this.nombreEmpleado = nombreEmpleado;
        this.tipoIncidencia = tipoIncidencia;
        this.descripcion = descripcion;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
