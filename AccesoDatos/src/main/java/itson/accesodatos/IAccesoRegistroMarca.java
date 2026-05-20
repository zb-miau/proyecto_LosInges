/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import itson.entidades.Empleado;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase interfaz padre que contiene los metodos que la dao implementará.
 * @author josma
 * @param <RegistroMarca> 
 */
public interface IAccesoRegistroMarca<RegistroMarca> {
    /**
     * Método para crear un RegistroMarca para un empleado.
     * @param marca
     * @return 
     * @throws PersistenciaException
     */
    public abstract RegistroMarca crear(RegistroMarca marca) throws PersistenciaException;
    /**
     * Este metodo trae todos los registros de asistencia de un empleado
     * es meramente para reporte.
     * @param empleado
     * @param inicio
     * @param fin
     * @return 
     * @throws PersistenciaException
     */
    public abstract List<RegistroMarca> obtenerLista(Empleado empleado,LocalDate inicio, LocalDate fin) throws PersistenciaException;
    /**
     * Este metodo es para cuando va a actualizar la marca, es decir se marca la salida.
     * @param marca
     * @return 
     * @throws PersistenciaException
     */
    public abstract RegistroMarca modificar(RegistroMarca marca) throws PersistenciaException;
    /**
     * Metodo auxiliar para la lógica de crear y modificar la marca.
     * @param empleado
     * @param fecha
     * @return 
     * @throws PersistenciaException
     */
    public abstract RegistroMarca obtenerPorEmpleadoYFecha(Empleado empleado, LocalDate fecha) throws PersistenciaException;
}
