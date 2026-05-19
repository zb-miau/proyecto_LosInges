/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import java.time.LocalDate;
import java.util.List;

/**
 *Interfaz padre para heredar los metodos a clases hijas de RegistroMarca
 * @author josma
 */
public interface IAccesoRegistroMarca<RegistroMarca> {
    /**
     * Método para crear un RegistroMarca para un empleado
     * @param marca
     * @return 
     */
    public abstract RegistroMarca crear(RegistroMarca marca);
    /**
     * Método para obtener la lista de todas las asistencias del empleado por filtración de fecha
     * @param fecha
     * @return 
     */
    public abstract List<RegistroMarca> obtenerLista(LocalDate fecha);
}
