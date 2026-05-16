/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOIncidencia;
import itson.accesodatos.FacadeAccesoDatos;
import itson.accesodatos.IncidenciasDAO;
import itson.entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;
import itson.accesodatos.IAccesoEmpleados;
import itson.accesodatos.IAccesoIncidencias;
import itson.accesodatos.PersistenciaException;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class IncidenciaBO {

    private static FacadeAccesoDatos fachadaDAO;
    private static IncidenciaBO incidenciaBO;
    private static final Logger LOGGER = Logger.getLogger(IncidenciaBO.class.getName());

    public static synchronized IncidenciaBO getInstance() {
        if (incidenciaBO == null) {
            incidenciaBO = new IncidenciaBO();
        }
        return incidenciaBO;
    }

    private IncidenciaBO() {
        this.fachadaDAO = FacadeAccesoDatos.getInstance();

    }

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

    public DTOIncidencia modificar(DTOIncidencia incidencia) throws NegocioException {
        try {

            if (incidencia == null) {

                throw new NegocioException("Error al modificar la incidencia: incidencia nula.");

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

    public List<DTOIncidencia> obtenerLista() throws NegocioException {

        try {

            List<Incidencia> incidencias = fachadaDAO.obtenerListaIncidencia();
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
