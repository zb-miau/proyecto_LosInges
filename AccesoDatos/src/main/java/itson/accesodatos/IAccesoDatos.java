/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import java.util.List;

/**
 *
 * @author Zaira
 */
public interface IAccesoDatos<T> {
    
    /**
     * Método para crear un objeto de la entidad que lo llama y la agrega
     * a la base de datos.
     * @param entidad entidad que llama al método, entidad a agregar.
     * @return regresa la entidad creada en la base de datos.
     */
    public abstract T crear(T entidad);
    
    /**
     * Método para eliminar un objeto de la entidad que lo llama de
     * a la base de datos.
     * @param entidad entidad que llama al método, entidad a eliminar.
     * @return regresa la entidad eliminada en la base de datos.
     */
    public abstract T eliminar(T entidad);
    
    /**
     * Método para modificar un objeto de la entidad que lo llama de
     * la base de datos.
     * @param entidad entidad que llama al método, entidad a modificar.
     * @return regresa la entidad modificada en la base de datos.
     */
    public abstract T modificar(T entidad);
    
    /**
     * Método para obtener un objeto de la entidad que lo llama desde
     * la base de datos.
     * @param entidad entidad que llama al método, entidad a obtener.
     * @return regresa la entidad que se busca en la base de datos.
     */
    public abstract T obtener(T entidad);
    
    /**
     * Método para obtener una lista de objetos de la entidad que lo llama 
     * @return regresa la lista de entidades que se busca en la base de datos.
     */
    public abstract List<T> obtener();
    
}
