/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author RAMSES
 */
public class DTO_Contratacion {
    
    private String rfc;
    private String nss;

    public DTO_Contratacion() {
    }

    public DTO_Contratacion(String rfc, String nss) {
        this.rfc = rfc;
        this.nss = nss;
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
    
    
    
}
