/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import itson.entidades.Empleado;
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
    public abstract Empleado crear(Empleado entidad) throws PersistenciaException;
    
    /**
     * Realiza una busqueda en la coleccion de la base de datos de MongoDB 
     * para encontrar un empleado utilizando su curp como criterio
     * 
     * @param empleado empleado que tiene la curp 
     * @return Un objeto de tipo Empleado perteneciente a la capa de dominio si se 
     * encuentra una coincidencia, null en caso de que no exista ningun registro
     */
    public Empleado obtenerPorCurp(Empleado empleado);
    
    /**
     * Realiza una busqueda en la coleccion de empleados de MongoDB utilizando 
     * el RFC encriptado de manera determinista como criterio de seleccion.
     *
     * @param empleado Objeto de dominio que contiene el RFC en texto plano a buscar.
     * @return Un objeto de tipo Empleado del dominio si se encuentra la coincidencia; 
     * null si no existe el registro o el parametro es invalido.
     */
    public Empleado obtenerPorRfc(Empleado empleado);
    
    /**
     * Realiza una busqueda en la coleccion de empleados de MongoDB utilizando 
     * el NSS encriptado de manera determinista como criterio de seleccion.
     *
     * @param empleado Objeto de dominio que contiene el NSS en texto plano a buscar
     * @return Un objeto de tipo Empleado del dominio si se encuentra la coincidencia,
     * null si no existe el registro o el parametro es invalido
     */
    public Empleado obtenerPorNss(Empleado empleado);
    
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
