/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidadesMongo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.*;

/**
 *Entidad de persistencia que representa un registro de asistencia en MongoDB.
 * Esta clase está diseñada para ser utilizada con el CodecRegistry de MongoDB,
 * permitiendo el mapeo automático entre documentos BSON y objetos Java.
 * contiene la información de entrada, salida y fecha de la jornada laboral
 * de un empleado específico.
 * @author josma
 */
public class RegistroMarcaMongo {
    /**
     * Identificador único del documento en MongoDB.
     * Se representa como un ObjectId en la base de datos pero se maneja
     * como String en la aplicación.
     */
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    /**
     * Identificador del empleado asociado al registro.
     * Almacenado bajo la propiedad "id_empleado" en el documento BSON.
     */
    @BsonProperty("id_empleado")
    private ObjectId idEmpleado;
    /**
     * Nombre del empleado al momento de realizar el registro.
     * Almacenado bajo la propiedad "nombre_empleado".
     */
    @BsonProperty("nombre_empleado")
    private String nombreEmpleado;
    /**
     * Hora exacta  de la marca de entrada.
     */
    private LocalTime entrada;
    /**
     * Hora exacta de la marca de salida.
     */
    private LocalTime salida;
    /**
     * Fecha calendario del registro de asistencia.
     */
    private LocalDate fecha;
    /**
     * Constructor por defecto requerido para la serialización/deserialización de POJOs
     * por el driver de MongoDB.
     */
    public RegistroMarcaMongo() {
    }
    /**
     * Constructor completo para inicializar un registro con un ID existente.
     * @param id Identificador del documento.
     * @param idEmpleado ObjectId del empleado.
     * @param nombreEmpleado Nombre completo del empleado.
     * @param entrada Hora de entrada.
     * @param salida Hora de salida.
     * @param fecha Fecha del registro.
     */
    public RegistroMarcaMongo(String id, ObjectId idEmpleado, String nombreEmpleado, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }
    /**
     * Constructor para nuevos registros (sin ID asignado).
     * Útil para operaciones de inserción donde MongoDB genera el ID automáticamente.
     * @param idEmpleado ObjectId del empleado.
     * @param nombreEmpleado Nombre completo del empleado.
     * @param entrada Hora de entrada.
     * @param salida Hora de salida.
     * @param fecha Fecha del registro.
     */
    public RegistroMarcaMongo(ObjectId idEmpleado, String nombreEmpleado, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }
    /**
     * 
     * @return El identificador hexadecimal del documento.
     */
    public String getId() {
        return id;
    }
    /**
     * 
     * @param id el idenficadro hexadecimal a establecer.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 
     * @return El ObjectId del empleado.
     */
    public ObjectId getIdEmpleado() {
        return idEmpleado;
    }
    /**
     * 
     * @param idEmpleado El ObjectId del empleado a establecer.
     */
    public void setIdEmpleado(ObjectId idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    /**
     * 
     * @return El nombre del empleado registrado.
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    /**
     * 
     * @param nombreEmpleado El nombre del empleado a establecer.
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    /**
     * 
     * @return La hora de entrada.
     */
    public LocalTime getEntrada() {
        return entrada;
    }
    /**
     * 
     * @param entrada La hora de entrada a establecer.
     */
    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }
    /**
     * 
     * @return La hora de salida.
     */
    public LocalTime getSalida() {
        return salida;
    }
    /**
     * 
     * @param salida La hora de salida a establecer.
     */
    public void setSalida(LocalTime salida) {
        this.salida = salida;
    }
    /**
     * 
     * @return La fecha del registro de la marca
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * 
     * @param fecha La fecha a establecer.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
}
