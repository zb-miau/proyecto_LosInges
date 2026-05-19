/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapters;

import entidadesMongo.IncidenciaMongo;
import itson.entidades.Incidencia;
import org.bson.types.ObjectId;

/**
 * Clase adaptadora (Adapter) encargada de la conversión de datos entre el
 * modelo de persistencia de MongoDB (IncidenciaMongo) y el modelo de dominio
 * (Incidencia).
 *
 * Facilita el desacoplamiento entre la capa de base de datos y la lógica de
 * negocio.
 *
 *
 * @author Zaira
 */
public class IncidenciaMongoAIncidenciaAdapter {

    /**
     * Adapta un objeto de persistencia IncidenciaMongo a un objeto de dominio
     * Incidencia.
     *
     * Convierte los tipos de datos específicos de MongoDB (como ObjectId y
     * Enums propios) a los tipos nativos o estándar del dominio. Además, si la
     * incidencia contiene un empleado mapeado, también lo adapta de forma
     * recursiva.
     *
     *
     * @param incidenciaMongo El objeto de persistencia proveniente de MongoDB.
     * No debe ser nulo.
     * @return Un objeto Incidencia con los datos mapeados para la lógica de
     * negocio.
     * @throws NullPointerException si incidenciaMongo o su idEmpleado son
     * nulos.
     */
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

        if (incidenciaMongo.getEmpleado() != null) {
            incidencia.setEmpleado(EmpleadoMongoAEmpleadoAdapter.toDomain(incidenciaMongo.getEmpleado()));
        }

        return incidencia;

    }

    /**
     * Adapta un objeto de dominio Incidencia a un objeto de persistencia
     * IncidenciaMongo.
     *
     * Prepara el objeto para ser almacenado en MongoDB, transformando el
     * identificador del empleado de un formato String a un ObjectId de Mongo,
     * así como sus respectivos Enums.
     *
     *
     * @param incidencia El objeto de negocio Incidencia. No debe ser nulo.
     * @return Un objeto IncidenciaMongo listo para ser persistido en la base de
     * datos.
     * @throws IllegalArgumentException si el idEmpleado de la incidencia no
     * tiene un formato hexadecimal válido para ObjectId.
     */
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
