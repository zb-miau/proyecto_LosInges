/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptador;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author RAMSES
 */
public class Encriptador {
    private static final String KEY = "ClAvEsEcReTa_123"; 
    private static final String ALGORITHM = "AES";

    /**
     * Cifra una cadena de texto plano utilizando el algoritmo simetrico AES.
     * El metodo inicializa el cifrador en modo de encriptacion con una llave estatica,
     * procesa los bytes del texto de entrada y codifica el resultado final en formato Base64
     * para asegurar una representacion legible de caracteres legibles en la base de datos.
     *
     * @param valor Cadena de texto plano que contiene el dato sensible que se desea proteger.
     * @return Una cadena de texto codificada en Base64 con el contenido cifrado; 
     * retorna el valor original si este se encuentra nulo o vacio; 
     * retorna null en caso de ocurrir una excepcion durante el proceso.
     */
    public static String encriptar(String valor) {
        if (valor == null || valor.trim().isEmpty()) return valor;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            byte[] valoresEncriptados = cipher.doFinal(valor.getBytes());
            return Base64.getEncoder().encodeToString(valoresEncriptados);
        } catch (Exception e) {
            System.err.println("Error al encriptar dato: " + e.getMessage());
            return null;
        }
    }

    /**
     * Descifra una cadena de texto que previamente fue encriptada bajo el algoritmo AES y codificada en Base64.
     * El metodo decodifica el texto de entrada, inicializa el cifrador en modo de desencriptacion 
     * utilizando la llave estatica de la aplicacion y revierte el bloque de bytes a su estado original de texto plano.
     *
     * @param valorEncriptado Cadena de texto en formato Base64 que representa el dato cifrado.
     * @return La cadena de texto original restaurada en texto plano,
     * retorna el valor recibido originalmente si este se encuentra nulo o vacio,
     * retorna null en caso de ocurrir un fallo o error en el proceso de descifrado.
     */
    public static String desencriptar(String valorEncriptado) {
        if (valorEncriptado == null || valorEncriptado.trim().isEmpty()) return valorEncriptado;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            byte[] valoresDesencifrados = cipher.doFinal(Base64.getDecoder().decode(valorEncriptado));
            return new String(valoresDesencifrados);
        } catch (Exception e) {
            System.err.println("Error al desencriptar dato: " + e.getMessage());
            return null; // O retornar el valor original si sospechas que no estaba cifrado
        }
    }
}
