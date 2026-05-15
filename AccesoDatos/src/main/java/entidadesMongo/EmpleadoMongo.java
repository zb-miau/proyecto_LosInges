/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import itson.entidades.Direccion;
import java.time.LocalDate;
import java.util.LinkedList;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;


/**
 *
 * @author Zaira
 */
public class EmpleadoMongo {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id; 
    
    private String nombre;
    
    @BsonProperty("apellido_paterno") 
    private String apellidoPaterno;
    
    @BsonProperty("apellido_materno") 
    private String apellidoMaterno;
    
    @BsonProperty("fecha_nacimiento") 
    private LocalDate fechaNacimiento;
    
    private DireccionMongo direccion;
    
    private String curp;
    private String rfc;
    private String nss;
    
    @BsonProperty("horario_actual") 
    private HorarioEmpleadoMongo horarioActual;
    
    

    public EmpleadoMongo() {
    }

    public EmpleadoMongo(String id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, DireccionMongo direccion, String curp, String rfc, String nss, HorarioEmpleadoMongo horarioActual) {
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

    public EmpleadoMongo(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, DireccionMongo direccion, String curp, String rfc, String nss, HorarioEmpleadoMongo horarioActual) {
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

    public EmpleadoMongo(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public EmpleadoMongo(String id, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.horarioActual = null;
    }
    
    public HorarioEmpleadoMongo getHorarioActual() {
        return horarioActual;
    }

    public void setHorarioActual(HorarioEmpleadoMongo horarioActual) {
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

    public DireccionMongo getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionMongo direccion) {
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
