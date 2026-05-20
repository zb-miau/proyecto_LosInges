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
 *
 * @author josma
 */
public class RegistroMarcaMongo {
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    private String id;
    //guardamos solo el id del empleado y nombre 
    @BsonProperty("id_empleado")
    private ObjectId idEmpleado;
    @BsonProperty("nombre_empleado")
    private String nombreEmpleado;
    private LocalTime entrada;
    private LocalTime salida;
    private LocalDate fecha;

    public RegistroMarcaMongo() {
    }

    public RegistroMarcaMongo(String id, ObjectId idEmpleado, String nombreEmpleado, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public RegistroMarcaMongo(ObjectId idEmpleado, String nombreEmpleado, LocalTime entrada, LocalTime salida, LocalDate fecha) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.entrada = entrada;
        this.salida = salida;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(ObjectId idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getSalida() {
        return salida;
    }

    public void setSalida(LocalTime salida) {
        this.salida = salida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
}
