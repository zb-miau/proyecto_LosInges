/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionIncidencias;

import dto.DTOEmpleado;
import dto.DTOIncidencia;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 * Implementación del patrón de diseño **Facade (Fachada)** para el subsistema
 * de gestión de incidencias.
 *
 * Esta clase expone un punto de acceso unificado, limpio y simplificado hacia
 * todas las operaciones y casos de uso del módulo de incidencias al implementar
 * la interfaz IGestionIncidencias. Reduce el acoplamiento del sistema al
 * enmascarar las interacciones complejas del controlador
 * ControlGestionIncidencias y sus componentes internos.
 *
 *
 * @author jesus
 */
public class FacadeGestionIncidencias implements IGestionIncidencias {

    /**
     * Referencia al controlador de flujo que coordina la lógica y ejecución
     * real de los casos de uso.
     */
    private ControlGestionIncidencias control;

    /**
     * Construye una nueva fachada e inicializa internamente el controlador de
     * gestión de incidencias asociado a este subsistema.
     */
    public FacadeGestionIncidencias() {
        this.control = new ControlGestionIncidencias();
    }

    /**
     * Delegue la creación y persistencia de una nueva incidencia al controlador
     * del subsistema.
     *
     * @param dTOIncidencia Objeto de transferencia con los datos de la
     * incidencia a registrar.
     * @return El DTOIncidencia creado, complementado con su identificador
     * único.
     * @throws NegocioException Si el registro infringe alguna de las reglas de
     * negocio validadas.
     */
    @Override
    public DTOIncidencia crearIncidencia(DTOIncidencia dTOIncidencia) throws NegocioException {

        return control.crearIncidencia(dTOIncidencia);

    }

    /**
     * Delegue la consulta y filtrado de incidencias por estado al controlador
     * del subsistema.
     *
     * @param estado Estado por el cual filtrar el listado de incidencias (Ej:
     * "PENDIENTE").
     * @return Una lista List conteniendo los DTOIncidencia que cumplen el
     * criterio.
     * @throws NegocioException Si ocurre un error o inconsistencia durante la
     * consulta en la capa de negocio.
     */
    @Override
    public List<DTOIncidencia> obtenerIncidencias(String estado) throws NegocioException {

        return control.obtenerIncidencias(estado);

    }

    /**
     * Delegue el proceso de aprobación y validación de una incidencia al
     * controlador del subsistema.
     *
     * @param incidencia La incidencia que se desea validar.
     * @return El DTOIncidencia actualizado con el nuevo estado aplicado.
     * @throws NegocioException Si la incidencia no es elegible para validación
     * o no se encuentra registrada.
     */
    @Override
    public DTOIncidencia validarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return control.validarIncidencia(incidencia);

    }

    /**
     * Delegue el proceso de declinación o rechazo de una incidencia al
     * controlador del subsistema.
     *
     * @param incidencia La incidencia que se desea rechazar.
     * @return El DTOIncidencia actualizado con el nuevo estado aplicado.
     * @throws NegocioException Si el estado actual impide su rechazo o el
     * registro no existe.
     */
    @Override
    public DTOIncidencia rechazarIncidencia(DTOIncidencia incidencia) throws NegocioException {

        return control.rechazarIncidencia(incidencia);

    }

    /**
     * Delegue la solicitud de notificación por correo electrónico hacia el
     * supervisor al controlador del subsistema.
     *
     * @param incidencia Objeto con los datos informativos que se adjuntarán al
     * correo.
     */
    @Override
    public void enviarSupervisor(DTOIncidencia incidencia) {

        control.enviarSupervisor(incidencia);
    }

}
