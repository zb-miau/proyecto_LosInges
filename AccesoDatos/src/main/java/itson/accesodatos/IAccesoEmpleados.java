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
public interface IAccesoEmpleados<Empleado> {
    
    /**
     * Método para crear un objeto de la entidad que lo llama y la agrega
     * a la base de datos.
     * @param entidad entidad que llama al método, entidad a agregar.
     * @return regresa la entidad creada en la base de datos.
     */
    public abstract Empleado crear(Empleado entidad);
    
    /**
     * Método para eliminar un objeto de la entidad que lo llama de
     * a la base de datos.
     * @param entidad entidad que llama al método, entidad a eliminar.
     * @return regresa la entidad eliminada en la base de datos.
     */
    public abstract Empleado eliminar(Empleado entidad);
    
    /**
     * Método para modificar un objeto de la entidad que lo llama de
     * la base de datos.
     * @param entidad entidad que llama al método, entidad a modificar.
     * @return regresa la entidad modificada en la base de datos.
     */
    public abstract Empleado modificar(Empleado entidad);
    
    /**
     * Método para obtener un objeto de la entidad que lo llama desde
     * la base de datos.
     * @param entidad entidad que llama al método, entidad a obtener.
     * @return regresa la entidad que se busca en la base de datos.
     */
    public abstract Empleado obtener(Empleado entidad);
    
    /**
     * Método para obtener una lista de objetos de la entidad que lo llama 
     * @return regresa la lista de entidades que se busca en la base de datos.
     */
    public abstract List<Empleado> obtenerLista();
    
    /**
     * Método que actualiza solamente el horario actual del empleado.
     * @param empleado entidad empleado que contiene la entidad horarioEmpleado
     * para reemplazar el horario actual.
     * @return regresa la entidad empleado con el horario actual actualizado.
     * @throws PersistenciaException Lanza un error al existir un problema al acceder
     * a la base de datos
     */
    public Empleado modificarHorarioActual(Empleado empleado) throws PersistenciaException;
}
