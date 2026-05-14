/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

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
     * Método para obtener un horario de la base de datos.
     * @param horario horario a obtener.
     * @return regresa el horario que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract HorarioEmpleado obtener(HorarioEmpleado horario) throws PersistenciaException;
    
    /**
     * Método para obtener una lista de horarios.
     * @return regresa la lista de horarios que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract List<HorarioEmpleado> obtenerLista(HorarioEmpleado horario) throws PersistenciaException;
    
}
