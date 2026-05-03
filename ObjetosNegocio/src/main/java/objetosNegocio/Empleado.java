/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author josma
 */
public class Empleado {

    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private LinkedList<HorarioEmpleado> historial;

    public Empleado() {
    }

    public Empleado(String id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, LinkedList<HorarioEmpleado> historial) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = historial;
    }

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, LinkedList<HorarioEmpleado> historial) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = historial;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LinkedList<HorarioEmpleado> getHistorial() {
        return historial;
    }

    public void setHistorial(LinkedList<HorarioEmpleado> historial) {
        this.historial = historial;
    }
    
    

}
