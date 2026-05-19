/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTORegistroMarca;
import itson.entidades.Empleado;
import itson.entidades.RegistroMarca;

/**
 *Clase que sirve como adaptador para convertir de entidad a dto y viceversa
 * @author josma
 */
public class RegistroMarcaToDTORegistroMarca {
    /**
     * Convertir de DTO a persistencia (entidad limpia) 
     * @param registroDTO
     * @return 
     */
    public static RegistroMarca toPersistencia(DTORegistroMarca registroDTO){
        if (registroDTO  == null) {
            return null; 
        }
        //Primero contruimos el objeto completo
        Empleado empleado = new Empleado();
        empleado.setId(registroDTO.getIdEmpleado());
        empleado.setNombre(registroDTO.getNombreEmpleado());
        
        RegistroMarca marca = new RegistroMarca(
                registroDTO.getIdRegistroMarca(),
                empleado,
                registroDTO.getEntrada(),
                registroDTO.getSalida(),
                registroDTO.getFecha()
        );
        
        return marca; 
    }
    /**
     * Convertir de entidad limpia (persistencia) a DTO
     * @param registroPersistencia
     * @return 
     */
    public static DTORegistroMarca toDTO(RegistroMarca registroPersistencia){
        if (registroPersistencia == null) {
            return null;
        }
        
        //Sacamos los datos que nos interesan 
        
        String idEmpleado = registroPersistencia.getEmpleado().getId();
        String nombreEmpleado = registroPersistencia.getEmpleado().getNombre();
        
        DTORegistroMarca marca = new DTORegistroMarca(
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
