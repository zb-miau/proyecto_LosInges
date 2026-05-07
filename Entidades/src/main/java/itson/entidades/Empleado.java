/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.util.LinkedList;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

/**
 *
 * @author RAMSES
 */
public class Empleado {
    
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id; 
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String calle;
    private String colonia;
    private Integer numeroCasa;
    private Integer codigoPostal;
    private String curp;
    private String rfc;
    private String nss;
    LinkedList<HorarioEmpleado> historial;
    

    public Empleado() {
    }

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String calle, String colonia, Integer numeroCasa, Integer codigoPostal, String curp, String rfc, String nss) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
        this.historial = new LinkedList();
    }

    public Empleado(String id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String calle, String colonia, Integer numeroCasa, Integer codigoPostal, String curp, String rfc, String nss) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public LinkedList<HorarioEmpleado> getHistorial() {
        return historial;
    }

    public void setHistorial(LinkedList<HorarioEmpleado> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", calle=" + calle + ", colonia=" + colonia + ", numeroCasa=" + numeroCasa + ", codigoPostal=" + codigoPostal + ", curp=" + curp + ", rfc=" + rfc + ", nss=" + nss + '}';
    }
    
    
    
}
