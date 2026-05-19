/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaira
 */
public interface IAccesoHorarioEmpleado<HorarioEmpleado> {
    
     /**
     * Método para crear un horario y lo agrega a la base de datos.
     * @param horario el horario a agregar.
     * @return regresa el horario creado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract HorarioEmpleado crear(HorarioEmpleado horario) throws PersistenciaException;
    
    /**
     * Método para eliminar un horario de la base de datos.
     * @param horario el horario a eliminar.
     * @return regresa el horario eliminado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract HorarioEmpleado eliminar(HorarioEmpleado horario) throws PersistenciaException;
    
    /**
     * Método para modificar un horario de la base de datos.
     * @param horario el horario a modificar.
     * @return regresa el horario modificado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract HorarioEmpleado modificar(HorarioEmpleado horario) throws PersistenciaException;
    
    /**
     * Método para obtener una lista de horarios de la base de datos
     * que se traslapan con el horario del parámetro.
     * @param horario horario a obtener.
     * @return regresa la lista de horarios que se traslapan con el horario
     * del parámetro.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract List<HorarioEmpleado> obtenerActivo(HorarioEmpleado horario) throws PersistenciaException;
    
    /**
     * Método para obtener una lista de horarios dentro de una fecha específica.
     * @return regresa la lista de horarios dentro del rango de fechas.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract List<HorarioEmpleado> obtenerListaPorFecha(HorarioEmpleado horario, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}
