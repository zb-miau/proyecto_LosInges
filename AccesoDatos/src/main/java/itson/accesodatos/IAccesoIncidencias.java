/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import entidadesMongo.IncidenciaMongo;
import java.util.List;

/**
 *
 * @author Zaira
 */
public interface IAccesoIncidencias<Incidencia> {
    
    /**
     * Método para crear un objeto de la incidencia que lo llama y la agrega
     * a la base de datos.
     * @param incidencia incidencia que llama al método, incidencia a agregar.
     * @return regresa la incidencia creada en la base de datos.
     */
    public abstract Incidencia crear(Incidencia incidencia);
    
    /**
     * Método para eliminar un objeto de la incidencia que lo llama de
     * a la base de datos.
     * @param incidencia incidencia que llama al método, incidencia a eliminar.
     * @return regresa la incidencia eliminada en la base de datos.
     */
    public abstract Incidencia eliminar(Incidencia incidencia);
    
    /**
     * Método para modificar un objeto de la incidencia que lo llama de
     * la base de datos.
     * @param incidencia incidencia que llama al método, incidencia a modificar.
     * @return regresa la incidencia modificada en la base de datos.
     */
    public abstract Incidencia modificar(Incidencia incidencia);
    
    /**
     * Método para obtener un objeto de la incidencia que lo llama desde
     * la base de datos.
     * @param incidencia incidencia que llama al método, incidencia a obtener.
     * @return regresa la incidencia que se busca en la base de datos.
     */
    public abstract Incidencia obtener(Incidencia incidencia);
    
    /**
     * Método para obtener una lista de objetos de la incidencia que lo llama 
     * @return regresa la lista de incidenciaes que se busca en la base de datos.
     */
    public abstract List<Incidencia> obtenerLista();
    
    
}
