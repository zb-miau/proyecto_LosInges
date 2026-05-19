/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import encriptador.Encriptador;
import entidadesMongo.DireccionMongo;
import entidadesMongo.EmpleadoMongo;
import entidadesMongo.HorarioEmpleadoMongo;
import itson.entidades.Direccion;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;

/**
 * Clase adaptadora encargada de realizar la conversion de datos entre la entidad 
 * de persistencia EmpleadoMongo y el modelo de negocio Empleado.
 *
 * @author Zaira
 */
public class EmpleadoMongoAEmpleadoAdapter {
    
    /**
     * Convierte un objeto de dominio Empleado a su representacion de persistencia EmpleadoMongo.
     * Realiza de forma automatica la encriptacion de los datos sensibles como CURP, RFC y NSS.
     *
     * @param domain Objeto de la capa de dominio con los datos planos del empleado.
     * @return El objeto mapeado listo para ser guardado en MongoDB, o null si el parametro es nulo.
     */
    public static EmpleadoMongo toMongo(Empleado domain) {
        if (domain == null) return null;

        EmpleadoMongo mongo = new EmpleadoMongo();
        mongo.setNombre(domain.getNombre());
        mongo.setApellidoPaterno(domain.getApellidoPaterno());
        mongo.setApellidoMaterno(domain.getApellidoMaterno());
        mongo.setFechaNacimiento(domain.getFechaNacimiento());
        
        // --- NORMAS DE SEGURIDAD: ENCRIPTACION ---
        mongo.setCurp(Encriptador.encriptar(domain.getCurp()));
        mongo.setRfc(Encriptador.encriptar(domain.getRfc()));
        mongo.setNss(Encriptador.encriptar(domain.getNss()));
        
        if (domain.getId() != null){
            mongo.setId(domain.getId());
        }
        
        // Conversion de Direccion
        if (domain.getDireccion() != null) {
            mongo.setDireccion(toDireccionMongo(domain.getDireccion()));
        }

        // Conversion de Horario
        if (domain.getHorarioActual() != null) {
            mongo.setHorarioActual(HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarConEmpleado(domain.getHorarioActual(), mongo));
        }

        return mongo;
    }

    /**
     * Convierte un objeto de persistencia EmpleadoMongo a su representacion de dominio Empleado.
     * Aplica la desencriptacion a los campos protegidos de CURP, RFC y NSS para su uso en texto plano.
     *
     * @param mongo Objeto de persistencia recuperado de la base de datos de MongoDB.
     * @return El objeto de dominio con los datos restaurados, o null si el parametro es nulo.
     */
    public static Empleado toDomain(EmpleadoMongo mongo) {
        if (mongo == null) return null;

        Empleado domain = new Empleado();
        domain.setId(mongo.getId());
        domain.setNombre(mongo.getNombre());
        domain.setApellidoPaterno(mongo.getApellidoPaterno());
        domain.setApellidoMaterno(mongo.getApellidoMaterno());
        domain.setFechaNacimiento(mongo.getFechaNacimiento());
        
        // --- NORMAS DE SEGURIDAD: DESENCRIPTACION ---
        domain.setCurp(Encriptador.desencriptar(mongo.getCurp()));
        domain.setRfc(Encriptador.desencriptar(mongo.getRfc()));
        domain.setNss(Encriptador.desencriptar(mongo.getNss()));

        if (mongo.getDireccion() != null) {
            domain.setDireccion(toDireccionDomain(mongo.getDireccion()));
        }

        if (mongo.getHorarioActual() != null) {
            domain.setHorarioActual(HorarioEmpleadoMongoAHorarioEmpleadoAdapter.adaptarConEmpleado(mongo.getHorarioActual(), domain));
        }

        return domain;
    }

    
    /**
     * Metodo de soporte que adapta una entidad de Direccion del dominio a formato de MongoDB.
     *
     * @param d Objeto de tipo Direccion de la capa de dominio.
     * @return Instancia mapeada de tipo DireccionMongo.
     */
    private static DireccionMongo toDireccionMongo(Direccion d) {
        return new DireccionMongo(d.getCalle(), d.getColonia(), d.getNumeroCasa(), d.getCodigoPostal());
    }

    /**
     * Metodo de soporte que adapta un documento de DireccionMongo al modelo de dominio.
     *
     * @param dm Documento de tipo DireccionMongo extraido de la base de datos.
     * @return Instancia mapeada de tipo Direccion del dominio.
     */
    private static Direccion toDireccionDomain(DireccionMongo dm) {
        return new Direccion (dm.getCalle(), dm.getColonia(), dm.getNumeroCasa(), dm.getCodigoPostal());
    }

    
    
}
