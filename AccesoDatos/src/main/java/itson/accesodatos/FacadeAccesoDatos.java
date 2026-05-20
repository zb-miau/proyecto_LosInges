/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Incidencia;
import itson.entidades.RegistroMarca;
import itson.entidades.Turno;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 * Clase Fachada que sirve como puente entre la capa de negocios y la de
 * persistencia.
 *
 * @author Zaira
 */
public class FacadeAccesoDatos implements IAccesoDatos {

    private static IAccesoTurnos<Turno> turnosDAO;
    private static IAccesoEmpleados<Empleado> empleadosDAO;
    private static IAccesoHorarioEmpleado<HorarioEmpleado> horarioEmpleadosDAO;
    private static IAccesoIncidencias<Incidencia> incidenciasDAO;
    private static IAccesoRegistroMarca<RegistroMarca> registroMarcaDAO;
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
        this.registroMarcaDAO = RegistroMarcaDAO.getInstance();
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public List<Turno> obtenerListaTurnos() throws PersistenciaException {
        try {

            return turnosDAO.obtenerLista();

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de turnos de la base de datos. ");
        }
    }

    /**
     * Método para verificar que el turno no se está duplicando en la base de
     * datos.
     *
     * @param turno el turno a agregar.
     * @return true si existe otro turno igual, false en caso contrario
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
    public boolean turnoDuplicado(Turno turno) throws PersistenciaException {
        try {
            return turnosDAO.turnoDuplicado(turno);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al verificar los turnos de la base de datos. ");
        }
    }

    /**
     * Registra un nuevo empleado en la base de datos de MongoDB.
     *
     * @param entidad Objeto de dominio con los datos del empleado a guardar.
     * @return El objeto de dominio actualizado con el ID asignado por MongoDB.
     * @throws PersistenciaException Si ocurre un error al intentar guardar en
     * la base de datos.
     */
    @Override
    public Empleado crear(Empleado entidad) throws PersistenciaException {
        try {
            return empleadosDAO.crear(entidad);
        } catch (PersistenciaException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al registrar el empleado en la base de datos.");
        }
    }

    /**
     * Busca un empleado utilizando su CURP encriptada de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene la CURP en texto plano a
     * buscar.
     * @return El empleado convertido al modelo de dominio; null si no se
     * encuentra.
     */
    @Override
    public Empleado obtenerPorCurp(Empleado empleado) {
        try {
            return empleadosDAO.obtenerPorCurp(empleado);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            return null;
        }
    }

    /**
     * Busca un empleado utilizando su RFC encriptado de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene el RFC en texto plano a
     * buscar.
     * @return El empleado convertido al modelo de dominio; null si no se
     * encuentra.
     */
    @Override
    public Empleado obtenerPorRfc(Empleado empleado) {
        try {
            return empleadosDAO.obtenerPorRfc(empleado);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            return null;
        }
    }

    /**
     * Busca un empleado utilizando su NSS encriptado de forma determinista.
     *
     * @param empleado Objeto de dominio que contiene el NSS en texto plano a
     * buscar.
     * @return El empleado convertido al modelo de dominio; null si no se
     * encuentra.
     */
    @Override
    public Empleado obtenerPorNss(Empleado empleado) {
        try {
            return empleadosDAO.obtenerPorNss(empleado);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            return null;
        }
    }

    /**
     * Busca un empleado en la base de datos por medio de su identificador unico
     * ID.
     *
     * @param entidad Objeto de dominio que contiene el ID del empleado a
     * buscar.
     * @return El empleado convertido al modelo de dominio; null si no existe.
     */
    @Override
    public Empleado obtener(Empleado entidad) {
        try {
            return empleadosDAO.obtener(entidad);
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            return null;
        }
    }

    /**
     * Recupera todos los registros de empleados almacenados en la base de
     * datos.
     *
     * @return Una lista de objetos Empleado convertidos al modelo de dominio.
     */
    @Override
    public List<Empleado> obtenerLista() {
        try {
            return empleadosDAO.obtenerLista();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Método que recupera el empleado y modifica su atributo de horario actual.
     *
     * @param empleado empleado al que se le va a modificar su horario.
     * @return el empleado con su horario modificado.
     * @throws PersistenciaException Lanza error si el horario se encuentra
     * vacío o si hay un error al acceder a la base de datos.
     */
    @Override
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

    /**
     * Método para crear un horario y lo agrega a la base de datos.
     *
     * @param horario el horario a agregar.
     * @return regresa el horario creado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
    public HorarioEmpleado crearHorarioHistorial(HorarioEmpleado horario) throws PersistenciaException {
        try {

            return horarioEmpleadosDAO.crear(horario);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error crear el historial. ");
        }
    }

    /**
     * Método para obtener una lista de horarios de la base de datos que se
     * traslapan con el horario del parámetro.
     *
     * @param horario horario a obtener.
     * @return regresa la lista de horarios que se traslapan con el horario del
     * parámetro.
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
    public List<HorarioEmpleado> obtenerHorarioActivo(HorarioEmpleado horario) throws PersistenciaException {
        try {
            return horarioEmpleadosDAO.obtenerActivo(horario);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error obtener horario del historial. ");
        }
    }

    /**
     * Método para obtener una lista de horarios dentro de una fecha específica.
     *
     * @return regresa la lista de horarios dentro del rango de fechas.
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
    public List<HorarioEmpleado> obtenerHistorial(HorarioEmpleado horario, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        try {
            return horarioEmpleadosDAO.obtenerListaPorFecha(horario, fechaInicio, fechaFin);
        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al obtener el historial. ");
        }
    }

    /**
     * Método para modificar un horario de la base de datos.
     *
     * @param horario el horario a modificar.
     * @return regresa el horario modificado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public List<Incidencia> obtenerListaIncidencia(String estado) throws PersistenciaException {
        try {

            return incidenciasDAO.obtenerLista(estado);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al recuperar la lista de incidencias de la base de datos. ");
        }
    }

    /**
     * Método para eliminar un horario de la base de datos.
     *
     * @param horario el horario a eliminar.
     * @return regresa el horario eliminado en la base de datos.
     * @throws PersistenciaException Lanza una excepción al presentar errores al
     * acceder a la base de datos.
     */
    @Override
    public HorarioEmpleado eliminarHistorial(HorarioEmpleado horario) throws PersistenciaException {
        try {

            return horarioEmpleadosDAO.eliminar(horario);

        } catch (MongoException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al eliminar el horario del historial. ");
        }
    }

    /**
     * Método para crear un registro al empleado
     *
     * @param registroMarca la entidad que se va a crear.
     * @return
     * @throws PersistenciaException
     */
    @Override
    public RegistroMarca crearMarca(RegistroMarca registroMarca) throws PersistenciaException {

        try {
            return registroMarcaDAO.crear(registroMarca);
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al insertar la indencia a la base de datos:");
        }
    }

    /**
     * Método que permite modificar el registro de la marca para poderb insertar
     * la salida
     *
     * @param registroMarca es la entidad que se va a modificar
     * @return
     * @throws PersistenciaException
     */
    @Override
    public RegistroMarca modificarMarca(RegistroMarca registroMarca) throws PersistenciaException {
        try {
            return registroMarcaDAO.modificar(registroMarca);
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al intentar acutalizar el registro");
        }
    }

    /**
     * Método para poder recuperar un registro en concreto, es auxiliar para
     * poder modificar la marca
     *
     * @param empleado atributo del empleado asociado a la marca
     * @param fecha atributo de la fecha del registro
     * @return
     * @throws PersistenciaException
     */
    @Override
    public RegistroMarca obtenerPorEmpleadoYFechaMarca(Empleado empleado, LocalDate fecha) throws PersistenciaException {
        try {
            return registroMarcaDAO.obtenerPorEmpleadoYFecha(empleado, fecha);
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al intentar recuperar el registro");
        }

    }

    @Override
    public List<RegistroMarca> obtenerListaRegistroMarca(Empleado empleado, LocalDate inicio, LocalDate fin) throws PersistenciaException {
        try {
            return registroMarcaDAO.obtenerLista(empleado, inicio, fin);
        } catch (MongoException e) {
            LOGGER.severe(e.getMessage());
            throw new PersistenciaException("Error al intentar recuperar la lista");
        }
    }

}
