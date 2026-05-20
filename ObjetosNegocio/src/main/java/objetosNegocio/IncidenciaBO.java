/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOIncidencia;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.IAccesoDatos;
import itson.entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;
import itson.accesodatos.PersistenciaException;
import java.util.logging.Logger;

/**
 * Clase de Objeto de Negocio (Business Object - BO) encargada de centralizar la
 * lógica y las reglas operativas del ciclo de vida de las Incidencias.
 *
 * Implementa el patrón **Singleton** para garantizar una única instancia global
 * en la aplicación. Se encarga de validar la integridad de los datos de
 * entrada, atrapar excepciones de la capa de datos (PersistenciaException),
 * registrarlas en el log del sistema y transformarlas en excepciones
 * comprensibles para el negocio (NegocioException).
 *
 * @author jesus
 */
public class IncidenciaBO {

    /**
     * Fachada de acceso a datos para delegar la persistencia de los cambios.
     */
    private static IAccesoDatos fachadaDAO;
    /**
     * Instancia única de la clase (Patrón Singleton).
     */
    private static IncidenciaBO incidenciaBO;
    /**
     * Componente de logging para el rastreo y auditoría de errores técnicos en
     * producción.
     */
    private static final Logger LOGGER = Logger.getLogger(IncidenciaBO.class.getName());

    /**
     * Recupera de forma sincronizada la instancia única de IncidenciaBO.
     * Garantiza un comportamiento seguro ante accesos concurrentes
     * (Thread-Safe).
     *
     * @return Instancia única de IncidenciaBO.
     */
    public static synchronized IncidenciaBO getInstance() {
        if (incidenciaBO == null) {
            incidenciaBO = new IncidenciaBO();
        }
        return incidenciaBO;
    }

    /**
     * Constructor privado que restringe la instanciación externa directa,
     * inicializando la referencia a la capa de persistencia mediante su
     * respectiva fachada.
     */
    private IncidenciaBO() {
        this.fachadaDAO = FacadeAccesoDatos.getInstance();

    }

    /**
     * Registra una nueva incidencia ejecutando las validaciones iniciales de
     * negocio.
     *
     * Adapta el DTO a un objeto de dominio, delega el almacenamiento físico a
     * la capa de datos y muta el DTO original asignándole el ID generado por el
     * repositorio.
     *
     *
     * @param incidencia DTO con la información de la incidencia a crear. No
     * debe ser nulo.
     * @return El mismo objeto DTOIncidencia de entrada, enriquecido con su ID
     * definitivo.
     * @throws NegocioException Si la incidencia provista es nula o si la capa
     * de persistencia falla al insertar el documento.
     */
    public DTOIncidencia crear(DTOIncidencia incidencia) throws NegocioException {

        try {

            if (incidencia == null) {

                throw new NegocioException("Error al insertar incidencia: no es posible guardar una incidencia nula");

            }

            Incidencia incidenciaCrear = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);

            incidenciaCrear = fachadaDAO.crearIncidencia(incidenciaCrear);
            incidencia.setIdIncidencia(incidenciaCrear.getIdIncidencia());

            return incidencia;

        } catch (PersistenciaException e) {

            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al insertal la incidencia: " + e.getMessage());

        }

    }

    /**
     * Elimina una incidencia del sistema basándose en la información
     * proporcionada.
     *
     * @param incidencia DTO con la información de la incidencia a remover. No
     * debe ser nulo.
     * @return El DTOIncidencia evaluado en el proceso de eliminación.
     * @throws NegocioException Si la incidencia provista es nula o si ocurre un
     * fallo crítico de conexión/escritura en el repositorio.
     */
    public DTOIncidencia eliminar(DTOIncidencia incidencia) throws NegocioException {

        try {

            if (incidencia == null) {

                throw new NegocioException("Error al eliminar incidencia: no es posible elimianr una incidencia nula");

            }

            Incidencia incidenciaEliminar = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
            fachadaDAO.eliminarIncidencia(incidenciaEliminar);

            return incidencia;

        } catch (PersistenciaException e) {

            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al eliminar la incidencia: " + e.getMessage());

        }
    }

    /**
     * Modifica las propiedades y valores de una incidencia persistida en la
     * base de datos.
     *
     * El método comprueba rigurosamente que el objeto a actualizar contenga un
     * identificador de referencia válido antes de realizar la conversión y
     * reescritura.
     *
     *
     * @param incidencia DTO que contiene los cambios y el ID del documento a
     * actualizar.
     * @return Un nuevo objeto DTOIncidencia refrescado directamente desde el
     * repositorio.
     * @throws NegocioException Si el DTO es nulo, carece de identificador
     * único, o si el mecanismo de persistencia no logra aplicar el cambio.
     */
    public DTOIncidencia modificar(DTOIncidencia incidencia) throws NegocioException {
        try {

            if (incidencia == null) {

                throw new NegocioException("Error al modificar la incidencia: incidencia nula.");

            }

            if (incidencia.getIdIncidencia() == null) {
                throw new NegocioException("Error al modificar la incidencia: incidencia sin identificador.");
            }

            Incidencia incidenciaModificar = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);

            incidenciaModificar = fachadaDAO.modificarIncidencia(incidenciaModificar);

            DTOIncidencia incidenciaModificada = IncidenciaToDTOIncidenciaAdapter.adaptar(incidenciaModificar);

            return incidenciaModificada;
        } catch (PersistenciaException e) {

            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al modificar la incidencia: " + e.getMessage());

        }
    }

    /**
     * Recupera una incidencia específica de manera detallada.
     *
     * @param incidencia DTO de consulta que porta el ID del registro que se
     * desea buscar.
     * @return Un DTOIncidencia completo con la información recuperada de la
     * base de datos.
     * @throws NegocioException Si el criterio es nulo o si no es posible
     * contactar o consultar la base de datos.
     */
    public DTOIncidencia obtener(DTOIncidencia incidencia) throws NegocioException {

        try {

            if (incidencia == null) {

                throw new NegocioException("Error al recuperar la incidencia: incidencia nula.");

            }

            Incidencia incidenciaObtener = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
            incidenciaObtener = fachadaDAO.obtenerIncidencia(incidenciaObtener);
            DTOIncidencia incidenciaRecuperada = IncidenciaToDTOIncidenciaAdapter.adaptar(incidenciaObtener);
            return incidenciaRecuperada;

        } catch (PersistenciaException e) {

            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al obtener la incidencia: " + e.getMessage());

        }

    }

    /**
     * Obtiene el listado completo de incidencias que se encuentren en un estado
     * operacional determinado, transformando la colección de dominio interna a
     * una estructura transportable de DTOs.
     *
     * @param estado Criterio de estado por el cual filtrar las incidencias (Ej:
     * "PENDIENTE", "VALIDADA").
     * @return Una lista List que contiene los objetos DTOIncidencia
     * correspondientes.
     * @throws NegocioException Si la consulta a la capa inferior dispara una
     * anomalía técnica.
     */
    public List<DTOIncidencia> obtenerLista(String estado) throws NegocioException {

        try {

            List<Incidencia> incidencias = fachadaDAO.obtenerListaIncidencia(estado);
            List<DTOIncidencia> listaIncidencias = new ArrayList();

            if (!incidencias.isEmpty()) {
                for (Incidencia i : incidencias) {
                    DTOIncidencia incidencia = IncidenciaToDTOIncidenciaAdapter.adaptar(i);
                    listaIncidencias.add(incidencia);
                }
            }
            return listaIncidencias;

        } catch (PersistenciaException e) {

            LOGGER.severe(e.getMessage());
            throw new NegocioException("Error al obtener las incidencia: " + e.getMessage());

        }
    }

}
