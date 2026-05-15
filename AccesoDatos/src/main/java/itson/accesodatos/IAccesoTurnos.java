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
public interface IAccesoTurnos<TurnoMongo> {
    
    /**
     * Método para crear un turno y lo agrega a la base de datos.
     * @param turno el turno a agregar.
     * @return regresa el turno creado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract TurnoMongo crear(TurnoMongo turno) throws PersistenciaException;
    
    /**
     * Método para eliminar un turno de la base de datos.
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract TurnoMongo eliminar(TurnoMongo turno) throws PersistenciaException;
    
    /**
     * Método para modificar un turno en la base de datos.
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract TurnoMongo modificar(TurnoMongo turno) throws PersistenciaException;
    
    /**
     * Método para obtener un turno de la base de datos.
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract TurnoMongo obtener(TurnoMongo turno) throws PersistenciaException;
    
    /**
     * Método para obtener una lista de turnos.
     * @return regresa la lista de turnos que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar
     * errores al acceder a la base de datos.
     */
    public abstract List<TurnoMongo> obtenerLista() throws PersistenciaException;
}
