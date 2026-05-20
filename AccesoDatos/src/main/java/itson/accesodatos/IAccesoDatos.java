/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.accesodatos;

import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Incidencia;
import itson.entidades.RegistroMarca;
import itson.entidades.Turno;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jesus
 */
public interface IAccesoDatos {

    public Turno crearTurno(Turno turno) throws PersistenciaException;

    public Turno eliminarTurno(Turno turno) throws PersistenciaException;

    public Turno modificarTurno(Turno turno) throws PersistenciaException;

    public Turno obtenerTurno(Turno turno) throws PersistenciaException;

    public List<Turno> obtenerListaTurnos() throws PersistenciaException;

    public boolean turnoDuplicado(Turno turno) throws PersistenciaException;

    public Empleado crear(Empleado entidad) throws PersistenciaException;

    public Empleado obtenerPorCurp(Empleado empleado);

    public Empleado obtenerPorRfc(Empleado empleado);

    public Empleado obtenerPorNss(Empleado empleado);

    public Empleado obtener(Empleado entidad);

    public List<Empleado> obtenerLista();

    public Empleado modificarHorarioActual(Empleado empleado) throws PersistenciaException;

    public HorarioEmpleado crearHorarioHistorial(HorarioEmpleado horario) throws PersistenciaException;

    public List<HorarioEmpleado> obtenerHorarioActivo(HorarioEmpleado horario) throws PersistenciaException;

    public List<HorarioEmpleado> obtenerHistorial(HorarioEmpleado horario, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;

    public HorarioEmpleado modificarHistorial(HorarioEmpleado horario) throws PersistenciaException;

    public Incidencia crearIncidencia(Incidencia incidencia) throws PersistenciaException;

    public Incidencia eliminarIncidencia(Incidencia incidencia) throws PersistenciaException;

    public Incidencia modificarIncidencia(Incidencia incidencia) throws PersistenciaException;

    public Incidencia obtenerIncidencia(Incidencia incidencia) throws PersistenciaException;

    public List<Incidencia> obtenerListaIncidencia(String estado) throws PersistenciaException;

    public HorarioEmpleado eliminarHistorial(HorarioEmpleado horario) throws PersistenciaException;

    public RegistroMarca crearMarca(RegistroMarca registroMarca) throws PersistenciaException;

    public RegistroMarca modificarMarca(RegistroMarca registroMarca) throws PersistenciaException;

    public RegistroMarca obtenerPorEmpleadoYFechaMarca(Empleado empleado, LocalDate fecha) throws PersistenciaException;

    public List<RegistroMarca> obtenerListaRegistroMarca(Empleado empleado, LocalDate inicio, LocalDate fin) throws PersistenciaException;

}
