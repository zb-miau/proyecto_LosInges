/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Incidencia;
import itson.entidades.Turno;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 * Clase Fachada que sirve como puente entre la capa de negocios y la de
 * persistencia.
 *
 * @author Zaira
 */
public class FacadeAccesoDatos {

    private static IAccesoTurnos<Turno> turnosDAO;
    private static IAccesoEmpleados<Empleado> empleadosDAO;
    private static IAccesoHorarioEmpleado<HorarioEmpleado> horarioEmpleadosDAO;
    private static IAccesoIncidencias<Incidencia> incidenciasDAO;
    private static FacadeAccesoDatos fachadaDAO;
    private static final Logger LOGGER = Logger.getLogger(FacadeAccesoDatos.class.getName());

    public static synchronized FacadeAccesoDatos getInstance() {
        if (fachadaDAO == null) {
            fachadaDAO = new FacadeAccesoDatos();
        }
        return fachadaDAO;
    }

    private FacadeAccesoDatos() {
        this.empleadosDAO = EmpleadosDAO.getInstance();
        this.turnosDAO = TurnosDAO.getInstance();
        this.horarioEmpleadosDAO = HorarioEmpleadosDAO.getInstance();
        this.incidenciasDAO = IncidenciasDAO.getInstance();
    }

    /**
     * /**
     * Método para crear un turno y lo agrega a la base de datos.
     *
     * @param turno el turno a agregar.
     * @return regresa el turno creado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Turno crearTurno(Turno turno) throws PersistenciaException {
        try {
            return turnosDAO.crear(turno);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al insertar el turno a la base de datos. ");
        }
    }

    /**
     * Método para eliminar un turno de la base de datos.
     *
     * @param turno turno a eliminar.
     * @return regresa el turno eliminado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Turno eliminarTurno(Turno turno) throws PersistenciaException {
        try {
            return turnosDAO.eliminar(turno);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar el turno a la base de datos. ");
        }
    }

    /**
     * Método para modificar un turno en la base de datos.
     *
     * @param turno turno a modificar.
     * @return regresa el turno modificado en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Turno modificarTurno(Turno turno) throws PersistenciaException {
        try {

            return turnosDAO.modificar(turno);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar el turno. ");
        }
    }

    /**
     * Método para obtener un turno de la base de datos.
     *
     * @param turno turno a obtener.
     * @return regresa el turno que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Turno obtenerTurno(Turno turno) throws PersistenciaException {
        try {
            return turnosDAO.obtener(turno);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar el turno de la base de datos. ");
        }
    }

    /**
     * Método para obtener una lista de turnos.
     *
     * @return regresa la lista de turnos registrados en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public List<Turno> obtenerListaTurnos() throws PersistenciaException {
        try {

            return turnosDAO.obtenerLista();

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de turnos de la base de datos. ");
        }
    }
    
    public boolean turnoDuplicado(Turno turno) throws PersistenciaException {
        try {
            return turnosDAO.turnoDuplicado(turno);
            
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al verificar los turnos de la base de datos. ");
        }
    }

    public Empleado modificarHorarioActual(Empleado empleado) throws PersistenciaException {
        try {
            if (empleado.getHorarioActual().getIdHorarioEmpleado() == null) {
                empleado.getHorarioActual().setIdHorarioEmpleado(new ObjectId().toString());
            }

            return empleadosDAO.modificarHorarioActual(empleado);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar el horario actual del empleado. ");
        }
    }

    public HorarioEmpleado crearHorarioHistorial(HorarioEmpleado horario) throws PersistenciaException {
        try {

            return horarioEmpleadosDAO.crear(horario);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error crear el historial. ");
        }
    }

    public HorarioEmpleado obtenerHorarioActivo(HorarioEmpleado horario) throws PersistenciaException {
        try {
            return horarioEmpleadosDAO.obtenerActivo(horario);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error obtener horario del historial. ");
        }
    }

    public List<HorarioEmpleado> obtenerHistorial(HorarioEmpleado horario,LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try {
            return horarioEmpleadosDAO.obtenerListaPorFecha(horario, fechaInicio, fechaFin);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al obtener el historial. ");
        }
    }

    public HorarioEmpleado modificarHistorial(HorarioEmpleado horario) throws PersistenciaException {
        try {
            return horarioEmpleadosDAO.modificar(horario);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar el historial. ");
        }
    }

    /**
     *
     * Método para crear una incidencia y la agrega a la base de datos.
     *
     * @param incidencia incidencia a agregar
     * @return regresa la incidencia creada en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Incidencia crearIncidencia(Incidencia incidencia) throws PersistenciaException {
        try {
            return incidenciasDAO.crear(incidencia);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al insertar la incidencia a la base de datos. ");
        }
    }

    /**
     * Método para eliminar una incidencia de la base de datos.
     *
     * @param incidencia incidencia a eliminar
     * @return regresa la incidencia eliminada en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Incidencia eliminarIncidencia(Incidencia incidencia) throws PersistenciaException {
        try {
            return incidenciasDAO.eliminar(incidencia);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar la incidencia en la base de datos. ");
        }
    }

    /**
     * Método para modificar una incidencia en la base de datos.
     *
     * @param incidencia incidencia a modificar
     * @return regresa la incidencia modificada en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Incidencia modificarIncidencia(Incidencia incidencia) throws PersistenciaException {
        try {

            return incidenciasDAO.modificar(incidencia);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al modificar la incidencia. ");
        }
    }

    /**
     * Método para obtener una incidencia de la base de datos.
     *
     * @param incidencia incidencia a obtener
     * @return regresa el turno que se busca en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public Incidencia obtenerIncidencia(Incidencia incidencia) throws PersistenciaException {
        try {
            return incidenciasDAO.obtener(incidencia);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la incidencia de la base de datos. ");
        }
    }

    /**
     * Método para obtener una lista de incidencias.
     *
     * @return regresa la lista de incidencias registradas en la base de datos.
     * @throws PersistenciaException Lanza una excepción si hay un problema al
     * acceder a la base de datos.
     */
    public List<Incidencia> obtenerListaIncidencia() throws PersistenciaException {
        try {

            return incidenciasDAO.obtenerLista();

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de incidencias de la base de datos. ");
        }
    }

}
