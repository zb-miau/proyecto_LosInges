/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.RegistroMarcaMongo;
import itson.entidades.Empleado;
import itson.entidades.RegistroMarca;
import org.bson.types.ObjectId;

/**
 *Adaptador para convertir entre la entidad limpia y la entidad de mongo
 * @author josma
 */
public class RegistroMarcaMongoARegistroMarcaAdapter {
    /**
     * convertir de entidad mongo (dominio) a entidad limpia (persistencia)
     * @param registroMongo
     * @return 
     */
    public static RegistroMarca toMongo(RegistroMarcaMongo registroMongo){
        if(registroMongo == null){
            return null; 
        }
        //Primero tenemos que crear un empleado parcial para poderle asignar unicamente los datos que
        //nos interesan, ya que la entidad de persistencia tiene el objeto completo 
        Empleado empleado = new Empleado();
        empleado.setId(registroMongo.getIdEmpleado().toHexString());
        empleado.setNombre(registroMongo.getNombreEmpleado());
        
        //Ahora si hacemos la conversion de mongo -> persistencia 
        
        RegistroMarca marca = new RegistroMarca(
                registroMongo.getId(),
                empleado,
                registroMongo.getEntrada(),
                registroMongo.getSalida(),
                registroMongo.getFecha()
        );
        
        return marca; 
    }
    /**
     * Convertir de entidad limpia (persistencia) a entidad mongo (dominio)
     * @param registroPersistencia
     * @return 
     */
    public static RegistroMarcaMongo toPersistencia(RegistroMarca registroPersistencia){
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
