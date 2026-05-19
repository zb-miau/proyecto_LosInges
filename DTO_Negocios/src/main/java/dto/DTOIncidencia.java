/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) que representa una incidencia en el sistema.
 *
 * Esta clase se utiliza exclusivamente para transportar datos de incidencias de
 * manera optimizada y desacoplada entre las diferentes capas de la aplicación
 * (por ejemplo, entre la capa de negocio y la interfaz de usuario o servicios
 * REST), evitando exponer directamente las entidades de dominio o de
 * persistencia.
 *
 *
 * @author jesus
 */
public class DTOIncidencia {

    /**
     * Identificador único de la incidencia en formato String.
     */
    private String idIncidencia;
    /**
     * Tipo de la incidencia tipificada.
     */
    private TiposIncidencia tipo;
    /**
     * Información agregada del empleado asociado, representada mediante otro
     * DTO.
     */
    private DTOEmpleado empleado;
    /**
     * Detalle textual descriptivo del suceso.
     */
    private String descripcion;
    /**
     * Fecha exacta en la que ocurrió o se registró la incidencia.
     */
    private LocalDate fecha;
    /**
     * Estado de la gestión o flujo de la incidencia.
     */
    private Estado estado;
    /**
     * Notas aclaratorias, comentarios o justificaciones adicionales.
     */
    private String observaciones;

    /**
     * Estados posibles en los que puede encontrarse la incidencia para su
     * visualización.
     */
    public enum Estado {
        VALIDADA, RECHAZADA, PENDIENTE
    };

    /**
     * Enumeración de los tipos de incidencias soportadas por el sistema,
     * adaptadas con un formato de texto legible para el usuario final.
     */
    public enum TiposIncidencia {
        AUSENTISMO("Ausentismo"),
        RETARDO("Retardo"),
        INDISCIPLINA("Indisciplina"),
        INCUMPLIMIENTO_SEGURIDAD("Incumplimiento de Seguridad"),
        BAJO_RENDIMIENTO("Bajo Rendimiento"),
        SANCION_ADMINISTRATIVA("Sanción Administrativa"),
        ROBO("Robo o Hurto"),
        ACOSO("Acoso / Hostigamiento"),
        CONSUMO_SUSTANCIAS("Consumo de Sustancias"),
        ABANDONO_PUESTO("Abandono de Puesto");

        /**
         * Texto amigable e internacionalizable para mostrar en la interfaz.
         */
        private final String nombre;

        /**
         * Constructor interno del Enum.
         *
         * @param nombre Nombre descriptivo asociado.
         */
        TiposIncidencia(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Sobrescribe el método toString para facilitar el enlazado directo
         * (Data Binding) con componentes visuales de la interfaz de usuario
         * (como ComboBoxes).
         *
         * @return El nombre legible del tipo de incidencia.
         */
        @Override
        public String toString() {
            return nombre;
        }
    }

    /**
     * Constructor por defecto requerido para la serialización/deserialización
     * de datos (por ejemplo, mediante librerías como Jackson o Gson).
     */
    public DTOIncidencia() {
    }

    /**
     * Constructor completo para transferir incidencias existentes que ya poseen
     * un identificador.
     *
     * @param idIncidencia Identificador de la incidencia.
     * @param tipo Tipo de la incidencia.
     * @param empleado DTO con los datos del empleado asociado.
     * @param descripcion Detalles o motivos del reporte.
     * @param fecha Fecha de registro de la incidencia.
     * @param estado Estado operativo actual.
     * @param observaciones Anotaciones adicionales.
     */
    public DTOIncidencia(String idIncidencia, TiposIncidencia tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    /**
     * Constructor para transferir nuevas incidencias que se van a registrar y
     * cuyo ID aún no ha sido asignado por el sistema de almacenamiento.
     *
     * @param tipo Tipo de la incidencia.
     * @param empleado DTO con los datos del empleado asociado.
     * @param descripcion Detalles o motivos del reporte.
     * @param fecha Fecha de registro de la incidencia.
     * @param estado Estado operativo inicial.
     * @param observaciones Anotaciones adicionales.
     */
    public DTOIncidencia(TiposIncidencia tipo, DTOEmpleado empleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    /**
     * Constructor simplificado para la transmisión rápida de datos esenciales
     * de una incidencia.
     *
     * @param tipo Tipo de la incidencia.
     * @param empleado DTO con los datos del empleado asociado.
     * @param descripcion Detalles o motivos preliminares.
     */
    public DTOIncidencia(TiposIncidencia tipo, DTOEmpleado empleado, String descripcion) {
        this.tipo = tipo;
        this.empleado = empleado;
        this.descripcion = descripcion;
    }

    //           GETTERS Y SETTERS
    public String getIdIncidencia() {
        return idIncidencia;
    }

    public TiposIncidencia getTipo() {
        return tipo;
    }

    public DTOEmpleado getEmpleado() {
        return empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public void setTipo(TiposIncidencia tipo) {
        this.tipo = tipo;
    }

    public void setEmpleado(DTOEmpleado empleado) {
        this.empleado = empleado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Calcula el valor hash del objeto basándose en el identificador único.
     *
     * @return Código hash generado.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idIncidencia);
        return hash;
    }

    /**
     * Compara este DTO con otro objeto para verificar su igualdad.
     *
     * La igualdad está determinada de forma exclusiva por la coincidencia del
     * campo idIncidencia, lo que permite gestionar listas de DTOs de manera
     * lógica.
     *
     *
     * @param obj Objeto a comparar con la instancia actual.
     * @return true si los objetos comparten el mismo ID; false en caso
     * contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DTOIncidencia other = (DTOIncidencia) obj;
        return Objects.equals(this.idIncidencia, other.idIncidencia);
    }

}
