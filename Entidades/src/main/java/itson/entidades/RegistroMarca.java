/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Entidad de dominio que representa el registro de asistencia de un empleado.
 * Esta clase es el núcleo del modelo de negocio para la gestión de marcas,
 * encargada de encapsular la información de cumplimiento de jornada laboral.
 * Contiene las asociaciones hacia el empleado y su horario, permitiendo validar
 * la puntualidad y el registro de horas de entrada y salida.
 *
 * @author josma
 */
public class RegistroMarca {

    /**
     * Identificador único del registro de marca en el sistema.
     */
    private String idRegistroMarca;

    /**
     * Referencia al empleado que realiza la marca.
     */
    private Empleado empleado;

    /**
     * Horario asignado al empleado para el día del registro.
     */
    private HorarioEmpleado horarioEmpleado;

    /**
     * Hora exacta en la que el empleado registró su ingreso.
     */
    private LocalTime registroEntrada;

    /**
     * Hora exacta en la que el empleado registró su egreso.
     */
    private LocalTime registroSalida;

    /**
     * Fecha calendario correspondiente a la jornada laboral registrada.
     */
    private LocalDate fecha;

    /**
     * Constructor por defecto para la creación de instancias vacías.
     */
    public RegistroMarca() {
    }

    /**
     * Constructor completo para inicializar una marca con todos sus atributos.
     *
     * @param idRegistroMarca Identificador único del registro.
     * @param empleado Instancia del empleado asociado.
     * @param horarioEmpleado Horario de trabajo correspondiente.
     * @param registroEntrada Tiempo de entrada capturado.
     * @param registroSalida Tiempo de salida capturado.
     * @param fecha Fecha de la jornada.
     */
    public RegistroMarca(String idRegistroMarca, Empleado empleado, HorarioEmpleado horarioEmpleado, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    /**
     * Constructor para la creación de nuevos registros de asistencia (sin ID
     * previo).
     *
     * @param empleado Instancia del empleado asociado.
     * @param horarioEmpleado Horario de trabajo correspondiente.
     * @param registroEntrada Tiempo de entrada capturado.
     * @param registroSalida Tiempo de salida capturado.
     * @param fecha Fecha de la jornada.
     */
    public RegistroMarca(Empleado empleado, HorarioEmpleado horarioEmpleado, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.empleado = empleado;
        this.horarioEmpleado = horarioEmpleado;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    /**
     * Constructor simplificado utilizado principalmente para actualizaciones de
     * tiempos en registros ya existentes en el sistema.
     *
     * @param idRegistroMarca Identificador único del registro.
     * @param registroEntrada Tiempo de entrada capturado.
     * @param registroSalida Tiempo de salida capturado.
     * @param fecha Fecha de la jornada.
     */
    public RegistroMarca(String idRegistroMarca, LocalTime registroEntrada, LocalTime registroSalida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.registroEntrada = registroEntrada;
        this.registroSalida = registroSalida;
        this.fecha = fecha;
    }

    /**
     * @return El identificador único de la marca.
     */
    public String getIdRegistroMarca() {
        return idRegistroMarca;
    }

    /**
     * @param idRegistroMarca El identificador único a establecer.
     */
    public void setIdRegistroMarca(String idRegistroMarca) {
        this.idRegistroMarca = idRegistroMarca;
    }

    /**
     * @return El objeto {@link Empleado} vinculado a esta marca.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado El empleado a vincular.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return El objeto {@link HorarioEmpleado} asignado.
     */
    public HorarioEmpleado getHorarioEmpleado() {
        return horarioEmpleado;
    }

    /**
     * @param horarioEmpleado El horario a establecer para este registro.
     */
    public void setHorarioEmpleado(HorarioEmpleado horarioEmpleado) {
        this.horarioEmpleado = horarioEmpleado;
    }

    /**
     * @return La hora de registro de entrada.
     */
    public LocalTime getRegistroEntrada() {
        return registroEntrada;
    }

    /**
     * @param registroEntrada La hora de entrada a establecer.
     */
    public void setRegistroEntrada(LocalTime registroEntrada) {
        this.registroEntrada = registroEntrada;
    }

    /**
     * @return La hora de registro de salida.
     */
    public LocalTime getRegistroSalida() {
        return registroSalida;
    }

    /**
     * @param registroSalida La hora de salida a establecer.
     */
    public void setRegistroSalida(LocalTime registroSalida) {
        this.registroSalida = registroSalida;
    }

    /**
     * @return La fecha de la marca de asistencia.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha La fecha a establecer.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
