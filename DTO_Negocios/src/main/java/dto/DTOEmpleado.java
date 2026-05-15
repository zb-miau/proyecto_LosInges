/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.LinkedList;

/**
 *
 * @author jesus
 */
public class DTOEmpleado {
    private String id; 
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private DTOHorarioEmpleado horarioActual;
    LinkedList<DTOHorarioEmpleado> historial;
    LinkedList<DTOIncidencia> incidencias;
    

    public DTOEmpleado() {
    }

    public DTOEmpleado(String id, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.historial = new LinkedList();
    }

    public DTOEmpleado(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.historial = new LinkedList();
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LinkedList<DTOHorarioEmpleado> getHistorial() {
        return historial;
    }

    public void setHistorial(LinkedList<DTOHorarioEmpleado> historial) {
        this.historial = historial;
    }

    public DTOHorarioEmpleado getHorarioActual() {
        return horarioActual;
    }

    public void setHorarioActual(DTOHorarioEmpleado horarioActual) {
        this.horarioActual = horarioActual;
    }

    public LinkedList<DTOIncidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(LinkedList<DTOIncidencia> incidencias) {
        this.incidencias = incidencias;
    }
    
    
    
    @Override
    public String toString() {
        return "DTOEmpleado{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + '}';
    }

}
