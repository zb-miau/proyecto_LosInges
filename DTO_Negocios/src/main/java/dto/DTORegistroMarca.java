/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Objeto de Transferencia de Datos (DTO) que representa un registro de marca de
 * asistencia. Esta clase se utiliza para transportar la información de las
 * entradas, salidas y fechas de los empleados entre las distintas capas del
 * sistema, evitando exponer directamente las entidades de dominio o
 * persistencia.
 *
 * @author josma
 */
public class DTORegistroMarca {

    /**
     * Identificador único del registro de marca.
     */
    private String idRegistroMarca;
    /**
     * Objeto DTO que contiene la información del empleado asociado.
     */
    private DTOEmpleado empleadoDTO;

    /**
     * Objeto DTO que contiene la información del horario del empleado.
     */
    private DTOHorarioEmpleado horarioEmpledoDTO;

    /**
     * Hora registrada para la entrada.
     */
    private LocalTime entrada;

    /**
     * Hora registrada para la salida.
     */
    private LocalTime salida;

    /**
     * Fecha en la que se realizó el registro de asistencia.
     */
    private LocalDate fecha;

    /**
     * Constructor por defecto para la instanciación vacía del DTO.
     */
    public DTORegistroMarca() {
    }

    /**
     * Constructor completo para inicializar el DTO con todos sus atributos,
     * incluyendo IDs y objetos anidados.
     *
     * @param idRegistroMarca Identificador del registro.
     * @param empleadoDTO DTO del empleado asociado.
     * @param horarioEmpledoDTO DTO del horario asociado al empleado.
     * @param entrada Hora de entrada.
     * @param salida Hora de salida.
     * @param fecha Fecha de la asistencia.
     */
    public DTORegistroMarca(String idRegistroMarca, DTOEmpleado empleadoDTO, DTOHorarioEmpleado horarioEmpledoDTO, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.empleadoDTO = empleadoDTO;
        this.horarioEmpledoDTO = horarioEmpledoDTO;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    /**
     * Constructor para la creación de nuevos registros que aún no poseen un ID
     * asignado.
     *
     * @param empleadoDTO DTO del empleado asociado.
     * @param horarioEmpledoDTO DTO del horario asociado al empleado.
     * @param entrada Hora de entrada.
     * @param salida Hora de salida.
     * @param fecha Fecha de la asistencia.
     */
    public DTORegistroMarca(DTOEmpleado empleadoDTO, DTOHorarioEmpleado horarioEmpledoDTO, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.empleadoDTO = empleadoDTO;
        this.horarioEmpledoDTO = horarioEmpledoDTO;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    /**
     * Constructor simplificado para actualizar datos de tiempo y fecha de un
     * registro existente.
     *
     * @param idRegistroMarca Identificador del registro.
     * @param entrada Hora de entrada.
     * @param salida Hora de salida.
     * @param fecha Fecha de la asistencia.
     */
    public DTORegistroMarca(String idRegistroMarca, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.idRegistroMarca = idRegistroMarca;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    /**
     * @return El identificador del registro de marca.
     */
    public String getIdRegistroMarca() {
        return idRegistroMarca;
    }

    /**
     * @param idRegistroMarca El identificador a establecer.
     */
    public void setIdRegistroMarca(String idRegistroMarca) {
        this.idRegistroMarca = idRegistroMarca;
    }

    /**
     * @return El DTO del empleado asociado.
     */
    public DTOEmpleado getEmpleadoDTO() {
        return empleadoDTO;
    }

    /**
     * @param empleadoDTO El DTO del empleado a establecer.
     */
    public void setEmpleadoDTO(DTOEmpleado empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    /**
     * @return El DTO del horario del empleado.
     */
    public DTOHorarioEmpleado getHorarioEmpledoDTO() {
        return horarioEmpledoDTO;
    }

    /**
     * @param horarioEmpledoDTO El DTO del horario a establecer.
     */
    public void setHorarioEmpledoDTO(DTOHorarioEmpleado horarioEmpledoDTO) {
        this.horarioEmpledoDTO = horarioEmpledoDTO;
    }

    /**
     * @return La hora de entrada registrada.
     */
    public LocalTime getEntrada() {
        return entrada;
    }

    /**
     * @param entrada La hora de entrada a establecer.
     */
    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    /**
     * @return La hora de salida registrada.
     */
    public LocalTime getSalida() {
        return salida;
    }

    /**
     * @param salida La hora de salida a establecer.
     */
    public void setSalida(LocalTime salida) {
        this.salida = salida;
    }

    /**
     * @return La fecha del registro de asistencia.
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
