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
