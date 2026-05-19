/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;

/**
 *
 * @author RAMSES
 */
public class Empleado {
    
    private String id; 
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private Direccion direccion;
    
    private String curp;
    private String rfc;
    private String nss;
    
    private HorarioEmpleado horarioActual;
    

    public Empleado() {
    }

    public Empleado(String id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, Direccion direccion, String curp, String rfc, String nss, HorarioEmpleado horarioActual) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
        this.horarioActual = horarioActual;
    }

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, Direccion direccion, String curp, String rfc, String nss, HorarioEmpleado horarioActual) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
        this.horarioActual = horarioActual;
    }
    
    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, Direccion direccion, String curp, String rfc, String nss) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
    }

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Empleado(String id, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.horarioActual = null;
    }
    
    

    public HorarioEmpleado getHorarioActual() {
        return horarioActual;
    }

    public void setHorarioActual(HorarioEmpleado horarioActual) {
        this.horarioActual = horarioActual;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + ", curp=" + curp + ", rfc=" + rfc + ", nss=" + nss + '}';
    }
    
}
