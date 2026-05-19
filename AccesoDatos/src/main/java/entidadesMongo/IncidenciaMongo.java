/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import java.time.LocalDate;
import java.util.Objects;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.bson.types.ObjectId;

/**
 * Entidad de persistencia que representa el documento "Incidencia" en MongoDB.
 *
 * Esta clase actúa como el Modelo de Datos (POJO) mapeado directamente con la
 * base de datos, manejando tipos nativos de Mongo como {@link ObjectId} y
 * configuraciones específicas de BSON.
 *
 *
 * @author Zaira
 */
public class IncidenciaMongo {

    /**
     * Identificador único de la incidencia en MongoDB. Se almacena como un
     * ObjectId nativo en la base de datos pero se maneja como String en Java.
     */
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String idIncidencia;

    /**
     * Tipo de incidencia tipificada.
     */
    private TiposIncidencia tipo;

    /**
     * Identificador del empleado asociado a la incidencia. Mapeado en la base
     * de datos bajo el nombre "id_empleado".
     */
    @BsonProperty("id_empleado")
    private ObjectId idEmpleado;

    /**
     * Detalle o descripción de lo sucedido en la incidencia.
     */
    private String descripcion;

    /**
     * Fecha en la que se registró o sucedió la incidencia.
     */
    private LocalDate fecha;

    /**
     * Estado de flujo de la incidencia (Validada, Rechazada, Pendiente).
     */
    private Estado estado;

    /**
     * Notas aclaratorias o comentarios adicionales sobre la resolución de la
     * incidencia.
     */
    private String observaciones;

    /**
     * Entidad embebida o relacionada que contiene la información detallada del
     * empleado. Opcional, dependiendo de si se realiza una agregación o carga
     * en profundidad.
     */
    private EmpleadoMongo empleado;

    /**
     * Estados posibles por los que puede pasar una incidencia durante su ciclo
     * de vida.
     */
    public enum Estado {
        VALIDADA, RECHAZADA, PENDIENTE
    };

    /**
     * Enumeración con los tipos de incidencias soportados por el sistema,
     * incluyendo una representación de texto amigable para la interfaz de
     * usuario.
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
         * Texto descriptivo y formateado del enum.
         */
        private final String nombre;

        /**
         * Constructor del Enum.
         *
         * @param nombre Texto amigable asociado al tipo de incidencia.
         */
        TiposIncidencia(String nombre) {
            this.nombre = nombre;
        }

        /**
         * Sobrescribe el método toString para facilitar el renderizado directo
         * en componentes visuales (como ComboBox de Swing o JavaFX).
         *
         * @return El nombre legible de la incidencia.
         */
        @Override
        public String toString() {
            return nombre;
        }
    }

    /**
     * Constructor vacío requerido por el codec POJO de MongoDB para la
     * instanciación por reflexión.
     */
    public IncidenciaMongo() {
    }

    /**
     * Constructor completo para instancias recuperadas de la base de datos con
     * ID ya asignado.
     *
     * @param idIncidencia Identificador único del documento.
     * @param tipo Tipo de la incidencia.
     * @param idEmpleado ID del empleado en formato ObjectId.
     * @param descripcion Detalle de la incidencia.
     * @param fecha Fecha de ocurrencia.
     * @param estado Estado actual de la incidencia.
     * @param observaciones Comentarios adicionales.
     */
    public IncidenciaMongo(String idIncidencia, TiposIncidencia tipo, ObjectId idEmpleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.idIncidencia = idIncidencia;
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    /**
     * Constructor para registrar nuevas incidencias donde el ID será
     * autogenerado por MongoDB.
     *
     * @param tipo Tipo de la incidencia.
     * @param idEmpleado ID del empleado en formato ObjectId.
     * @param descripcion Detalle de la incidencia.
     * @param fecha Fecha de ocurrencia.
     * @param estado Estado inicial de la incidencia.
     * @param observaciones Comentarios adicionales.
     */
    public IncidenciaMongo(TiposIncidencia tipo, ObjectId idEmpleado, String descripcion, LocalDate fecha, Estado estado, String observaciones) {
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    /**
     * Constructor simplificado para la creación rápida de una incidencia,
     * asignando por defecto el estado como {@code Estado.PENDIENTE}.
     *
     * @param tipo Tipo de la incidencia.
     * @param idEmpleado ID del empleado en formato ObjectId.
     * @param descripcion Detalle o motivo.
     */
    public IncidenciaMongo(TiposIncidencia tipo, ObjectId idEmpleado, String descripcion) {
        this.tipo = tipo;
        this.idEmpleado = idEmpleado;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
    }

    //métodos getters y setters
    public String getIdIncidencia() {
        return idIncidencia;
    }

    public TiposIncidencia getTipo() {
        return tipo;
    }

    public ObjectId getIdEmpleado() {
        return idEmpleado;
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

    public void setIdEmpleado(ObjectId idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public EmpleadoMongo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoMongo empleado) {
        this.empleado = empleado;
    }

    /**
     * Genera el hash code basado únicamente en el identificador de la
     * incidencia.
     *
     * @return Valor hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idIncidencia);
        return hash;
    }

    /**
     * Compara la igualdad de dos objetos basándose estrictamente en el campo
     * {@code idIncidencia}. Garantiza que dos instancias apunten
     * conceptualmente al mismo documento en la base de datos.
     *
     * @param obj Objeto a comparar.
     * @return {@code true} si tienen el mismo ID; {@code false} en caso
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
        final IncidenciaMongo other = (IncidenciaMongo) obj;
        return Objects.equals(this.idIncidencia, other.idIncidencia);
    }

}
