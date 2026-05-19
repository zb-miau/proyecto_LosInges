/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author RAMSES
 */
public class DTOContratacion {
    
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String calle;
    private String colonia;
    private String numeroCasa;
    private String codigoPostal;
    private String curp;
    private String rfc;
    private String nss;

    public DTOContratacion() {
    }

    public DTOContratacion(String id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String calle, String colonia, String numeroCasa, String codigoPostal, String curp, String rfc, String nss) {
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
    }
    
    public DTOContratacion(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String calle, String colonia, String numeroCasa, String codigoPostal, String curp, String rfc, String nss) {
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
    }

    public DTOContratacion(String nombre, String apellidoPaterno, LocalDate fechaNacimiento, String calle, String colonia, String numeroCasa, String codigoPostal, String curp, String rfc, String nss) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
        this.curp = curp;
        this.rfc = rfc;
        this.nss = nss;
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

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
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

    @Override
    public String toString() {
        return "DTOContratacion{" + "id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", calle=" + calle + ", colonia=" + colonia + ", numeroCasa=" + numeroCasa + ", codigoPostal=" + codigoPostal + ", curp=" + curp + ", rfc=" + rfc + ", nss=" + nss + '}';
    }
    
    
    
}
