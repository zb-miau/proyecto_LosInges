/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author RAMSES
 */
public class Validaciones {
    
    private static final String REGEX_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+(\\s[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)?$";
    private static final String REGEX_APELLIDO_PATERNO = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$";
    private static final String REGEX_APELLIDO_MATERNO = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]*$";
    private static final String REGEX_CALLE = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s\\.\\#\\-]+$";
    private static final String REGEX_COLONIA = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s\\.\\-]+$";
    private static final String REGEX_NUMERO_CASA = "^[0-9]+$";
    private static final String REGEX_CODIGO_POSTAL = "^[0-9]{5}$";
    private static final String REGEX_CURP = "^[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9,A-Z]{2}$";
    private static final String REGEX_RFC = "^[A-ZÑ&]{3,4}[0-9]{6}[A-Z0-9]{3}$";
    private static final String REGEX_NSS = "^[0-9]{11}$";

    /**
     * Valida el nombre o nombres del empleado
     * 
     * @param nombre
     * @return 
     */
    public boolean validarNombre(String nombre) {
        return nombre != null && nombre.trim().matches(REGEX_NOMBRE);
    }

    /**
     * Valida el apellido paterno
     * 
     * @param apellido
     * @return 
     */
    public boolean validarApellidoPaterno(String apellido) {
        return apellido != null && apellido.trim().matches(REGEX_APELLIDO_PATERNO);
    }

    /**
     * Valida el apellido materno
     * 
     * @param apellido
     * @return 
     */
    public boolean validarApellidoMaterno(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) return true;
        return apellido.trim().matches(REGEX_APELLIDO_MATERNO);
    }

    /**
     * Valida el nombre de la calle
     * 
     * @param calle
     * @return 
     */
    public boolean validarCalle(String calle) {
        return calle != null && calle.trim().matches(REGEX_CALLE);
    }

    /**
     * Valida el nombre de la colonia
     * 
     * @param colonia
     * @return 
     */
    public boolean validarColonia(String colonia) {
        return colonia != null && colonia.trim().matches(REGEX_COLONIA);
    }

    /**
     * Valida el numero de la casa de la direccion
     * 
     * @param numeroCasa
     * @return 
     */
    public boolean validarNumeroCasa(String numeroCasa) {
        return numeroCasa.trim().matches(REGEX_NUMERO_CASA);
    }

    /**
     * Valida el codigo postal de la direccion
     * 
     * @param cp
     * @return 
     */
    public boolean validarCodigoPostal(String cp) {
        return cp != null && cp.trim().matches(REGEX_CODIGO_POSTAL);
    }

    /**
     * Valida la estructura de la curp que ingresen
     * 
     * @param curp
     * @return 
     */
    public boolean validarCurp(String curp) {
        return curp != null && curp.trim().toUpperCase().matches(REGEX_CURP);
    }

    /**
     * Valida la estructura del rfc que ingresen 
     * 
     * @param rfc
     * @return 
     */
    public boolean validarRfc(String rfc) {
        return rfc != null && rfc.trim().toUpperCase().matches(REGEX_RFC);
    }

    /**
     * Valida la estructura del Numero de Seguro Social que ingresen
     * 
     * @param nss
     * @return 
     */
    public boolean validarNss(String nss) {
        return nss != null && nss.trim().matches(REGEX_NSS);
    }

    /**
     * Valida que el empleado tenga la edad minima
     * 
     * @param fechaNacimiento
     * @return 
     */
    public boolean validarMayoríaEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        return edad >= 18;
    }
}
