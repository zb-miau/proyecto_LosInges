/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author RAMSES
 */
public class DireccionMongo {
    
    private String calle;
    private String colonia;
    @BsonProperty("numero_casa") 
    private Integer numeroCasa;
    @BsonProperty("codigo_postal") 
    private Integer codigoPostal;
    
     public DireccionMongo(String calle, String colonia, Integer numeroCasa, Integer codigoPostal) {
        this.calle = calle;
        this.colonia = colonia;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
    }

    public DireccionMongo() {
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

    @Override
    public String toString() {
        return "Direccion{" + "calle=" + calle + ", colonia=" + colonia + ", numeroCasa=" + numeroCasa + ", codigoPostal=" + codigoPostal + '}';
    }
    
}
