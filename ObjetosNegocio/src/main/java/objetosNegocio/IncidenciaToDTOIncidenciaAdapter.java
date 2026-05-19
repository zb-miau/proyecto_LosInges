/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import itson.entidades.Incidencia;
import java.util.logging.Logger;

/**
 * Clase adaptadora (Mapeador/Mapper) encargada de la transformación
 * bidireccional entre los objetos del Modelo de Dominio y los Objetos de
 * Transferencia de Datos (DTO).
 *
 * Sigue el patrón de diseño **Adapter**. Su función primordial es actuar como
 * un puente de aislamiento entre la capa de negocio interna y las capas
 * externas (presentación o servicios), evitando que los cambios estructurales
 * en las entidades de dominio impacten directamente a los clientes del sistema.
 *
 *
 * @author jesus
 */
public class IncidenciaToDTOIncidenciaAdapter {

    /**
     * Componente de logging para registrar auditorías o rastreos del proceso de
     * mapeo si fuese necesario.
     */
    private static final Logger LOGGER = Logger.getLogger(IncidenciaToDTOIncidenciaAdapter.class.getName());

    /**
     * Adapta y transforma un objeto de transferencia DTOIncidencia a una
     * entidad pura del modelo de dominio Incidencia.
     *
     * El método realiza la homologación de tipos de datos estándar, incluyendo
     * la conversión de los Enums homónimos mediante el uso seguro de valueOf().
     *
     *
     * @param DTOIncidencia El objeto DTO que proviene de las capas externas.
     * Puede ser nulo.
     * @return Una nueva instancia de la entidad de dominio Incidencia, o null
     * si el parámetro de entrada es nulo.
     * @throws IllegalArgumentException Si los valores de los Enums en el DTO no
     * coinciden exactamente con los nombres definidos en el dominio.
     */
    public static Incidencia adaptar(DTOIncidencia DTOIncidencia) {

        if (DTOIncidencia == null) {
            return null;
        }

        Incidencia incidencia = new Incidencia(
                DTOIncidencia.getIdIncidencia(),
                Incidencia.TiposIncidencia.valueOf(DTOIncidencia.getTipo().name()),
                DTOIncidencia.getEmpleado().getId(),
                DTOIncidencia.getDescripcion(),
                DTOIncidencia.getFecha(),
                Incidencia.Estado.valueOf(DTOIncidencia.getEstado().name()),
                DTOIncidencia.getObservaciones());

//Incidencia.Estado.valueOf(DTOIncidencia.getEstado().name()));
        return incidencia;

    }

    /**
     * Adapta y transforma una entidad del modelo de dominio {@link Incidencia}
     * a un objeto de transferencia de datos {@link DTOIncidencia} apto para ser
     * expuesto externamente.
     *
     * Este método gestiona de forma inteligente la carga del empleado asociado
     * de la siguiente manera:
     *
     * Si la entidad cuenta con el objeto completo de dominio {@code Empleado},
     * delega su conversión al adaptador especializado
     * {@code EmpleadoToDTOEmpleadoAdapter}. Si la entidad solo posee la clave
     * de referencia {@code idEmpleado}, inicializa un DTO vacío asignándole
     * únicamente dicho identificador para preservar la relación.
     *
     *
     * @param incidencia La entidad de dominio recuperada del núcleo de la
     * aplicación. No debe ser nula.
     * @return Un nuevo objeto DTOIncidencia listo para su transporte entre
     * capas.
     * @throws NullPointerException Si el objeto incidencia es nulo.
     * @throws IllegalArgumentException Si los Enums de dominio no logran
     * emparejarse con las constantes del DTO.
     */
    public static DTOIncidencia adaptar(Incidencia incidencia) {

        DTOEmpleado empleado = new DTOEmpleado();
        if (incidencia.getEmpleado() != null) {
            empleado = EmpleadoToDTOEmpleadoAdapter.adaptarEntidad(incidencia.getEmpleado());
        } else if (incidencia.getIdEmpleado() != null) {
            empleado.setId(incidencia.getIdEmpleado());
        }

        DTOIncidencia dtoIncidencia = new DTOIncidencia(
                incidencia.getIdIncidencia(),
                DTOIncidencia.TiposIncidencia.valueOf(incidencia.getTipo().name()),
                empleado,
                incidencia.getDescripcion(),
                incidencia.getFecha(),
                DTOIncidencia.Estado.valueOf(incidencia.getEstado().name()),
                incidencia.getObservaciones());

        dtoIncidencia.getEmpleado().setId(incidencia.getIdEmpleado());

        return dtoIncidencia;

    }

}
