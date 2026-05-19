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
 *
 * @author Zaira
 */
public class EmpleadoMongoAEmpleadoAdapter {
    
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

    // --- Métodos privados de soporte para Dirección ---

    private static DireccionMongo toDireccionMongo(Direccion d) {
        return new DireccionMongo(d.getCalle(), d.getColonia(), d.getNumeroCasa(), d.getCodigoPostal());
    }

    private static Direccion toDireccionDomain(DireccionMongo dm) {
        return new Direccion (dm.getCalle(), dm.getColonia(), dm.getNumeroCasa(), dm.getCodigoPostal());
    }

    
    
}
