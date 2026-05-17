/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.IncidenciaMongo;
import itson.entidades.Incidencia;
import org.bson.types.ObjectId;

/**
 *
 * @author Zaira
 */
public class IncidenciaMongoAIncidenciaAdapter {

    public static Incidencia adaptarAIncidencia(IncidenciaMongo incidenciaMongo) {

        String idEmpleado = incidenciaMongo.getIdEmpleado().toHexString();
        
        Incidencia incidencia = new Incidencia(
                incidenciaMongo.getIdIncidencia(),
                Incidencia.TiposIncidencia.valueOf(incidenciaMongo.getTipo().name()),
                idEmpleado,
                incidenciaMongo.getDescripcion(),
                incidenciaMongo.getFecha(),
                Incidencia.Estado.valueOf(incidenciaMongo.getEstado().name()),
                incidenciaMongo.getObservaciones());

        if (incidenciaMongo.getEmpleado() != null){
            incidencia.setEmpleado(EmpleadoMongoAEmpleadoAdapter.toDomain(incidenciaMongo.getEmpleado()));
        }
        
        return incidencia;

    }

    public static IncidenciaMongo adaptarAIncidenciaMongo(Incidencia incidencia) {

        IncidenciaMongo incidenciaMongo = new IncidenciaMongo(
                incidencia.getIdIncidencia(),
                IncidenciaMongo.TiposIncidencia.valueOf(incidencia.getTipo().name()),
                new ObjectId(incidencia.getIdEmpleado()),
                incidencia.getDescripcion(),
                incidencia.getFecha(),
                IncidenciaMongo.Estado.valueOf(incidencia.getEstado().name()),
                incidencia.getObservaciones());

        return incidenciaMongo;

    }

}
