/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.RegistroMarcaMongo;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.RegistroMarca;
import org.bson.types.ObjectId;

/**
 * Clase adaptadora encargada de la transformación de datos entre el modelo de dominio
 * y el modelo de persistencia de MongoDB.
 * * Permite desacoplar la lógica de negocio (entidades limpias) de los detalles
 * de implementación de la base de datos (POJOs de MongoDB).
 * @author josma
 */
public class RegistroMarcaMongoARegistroMarcaAdapter {
    /**
     * Convierte un objeto de persistencia de MongoDB a una entidad de dominio limpia.
     * Este método reconstruye la jerarquía de objetos necesaria para la lógica de negocio,
     * creando instancias parciales de Empleado y HorarioEmpleado a partir
     * de los datos aplanados almacenados en el documento de Mongo.
     * @param registroMongo Objeto de tipo RegistroMarcaMongo proveniente de la base de datos.
     * @return Una instancia de RegistroMarca lista para ser utilizada por la capa de negocio,
     * o null si el parámetro de entrada es nulo.
     */
    public static RegistroMarca toPersistencia(RegistroMarcaMongo registroMongo){
        if(registroMongo == null){
            return null; 
        }
        //Primero tenemos que crear un empleado parcial para poderle asignar unicamente los datos que
        //nos interesan, ya que la entidad de persistencia tiene el objeto completo 
        Empleado empleado = new Empleado();
        empleado.setId(registroMongo.getIdEmpleado().toHexString());
        empleado.setNombre(registroMongo.getNombreEmpleado());
        //Obtenemos el horario
        HorarioEmpleado horario = new HorarioEmpleado();
        horario.setEmpleado(empleado);
        //Ahora si hacemos la conversion de mongo -> persistencia 
        
        RegistroMarca marca = new RegistroMarca(
                registroMongo.getId(),
                empleado,
                horario,
                registroMongo.getEntrada(),
                registroMongo.getSalida(),
                registroMongo.getFecha()
        );
        
        return marca; 
    }
    /**
     * Convierte una entidad de dominio limpia a un objeto de persistencia compatible con MongoDB.
     * Extrae los identificadores y atributos necesarios de la entidad RegistroMarca
     * y sus objetos anidados para conformar un documento plano representado por RegistroMarcaMongo.
     * @param registroPersistencia La entidad de dominio RegistroMarca a convertir.
     * @return Una instancia de RegistroMarcaMongo lista para ser persistida, 
     * o null si el parámetro de entrada es nulo.
     * @throws IllegalArgumentException Si el ID del empleado en la entidad no tiene un formato 
     * válido para convertirse en ObjectId.
     */
    public static RegistroMarcaMongo toMongo(RegistroMarca registroPersistencia){
        if (registroPersistencia == null) {
            return null; 
        }
        
        //Obtenemos los datos en concreto del objeto de empleado       
        ObjectId idEmpleado = new ObjectId(registroPersistencia.getEmpleado().getId());
        String nombreEmpleado = registroPersistencia.getEmpleado().getNombre();
        
        RegistroMarcaMongo marca = new RegistroMarcaMongo(
                registroPersistencia.getIdRegistroMarca(),
                idEmpleado,
                nombreEmpleado,
                registroPersistencia.getRegistroEntrada(),
                registroPersistencia.getRegistroSalida(),
                registroPersistencia.getFecha()
                
        
        );
        
        return marca; 
    }
}
