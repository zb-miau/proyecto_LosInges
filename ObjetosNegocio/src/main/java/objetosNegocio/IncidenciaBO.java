/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.DTOIncidencia;
import itson.accesodatos.IncidenciasDAO;
import itson.entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;
import itson.accesodatos.IAccesoEmpleados;

/**
 *
 * @author jesus
 */
public class IncidenciaBO {

    private final IAccesoEmpleados<Incidencia> dao;
    private static IncidenciaBO incidenciaBO;

    public static synchronized IncidenciaBO getInstance() {
        if (incidenciaBO == null) {
            incidenciaBO = new IncidenciaBO();
        }
        return incidenciaBO;
    }

    private IncidenciaBO() {
        this.dao = IncidenciasDAO.getInstance();

    }

    public DTOIncidencia crear(DTOIncidencia incidencia) {
        Incidencia incidenciaCrear = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
        incidenciaCrear = dao.crear(incidenciaCrear);
        incidencia.setIdIncidencia(incidenciaCrear.getIdIncidencia());

        return incidencia;
    }

    public DTOIncidencia eliminar(DTOIncidencia incidencia) {
        Incidencia incidenciaEliminar = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
        incidenciaEliminar = dao.eliminar(incidenciaEliminar);

        return incidencia;
    }

    public DTOIncidencia modificar(DTOIncidencia incidencia) {
        Incidencia incidenciaModificar = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
        incidenciaModificar = dao.modificar(incidenciaModificar);
        DTOIncidencia incidenciaModificada = IncidenciaToDTOIncidenciaAdapter.adaptar(incidenciaModificar);

        return incidenciaModificada;
    }

    public DTOIncidencia obtener(DTOIncidencia incidencia) {
        Incidencia incidenciaObtener = IncidenciaToDTOIncidenciaAdapter.adaptar(incidencia);
        incidenciaObtener = dao.obtener(incidenciaObtener);
        DTOIncidencia incidenciaRecuperada = IncidenciaToDTOIncidenciaAdapter.adaptar(incidenciaObtener);
        return incidenciaRecuperada;
    }

    public List<DTOIncidencia> obtenerLista() {
        List<Incidencia> incidencias = dao.obtenerLista();
        List<DTOIncidencia> listaIncidencias = new ArrayList();

        if (!incidencias.isEmpty()) {
            for (Incidencia i : incidencias) {
                DTOIncidencia incidencia = IncidenciaToDTOIncidenciaAdapter.adaptar(i);
                listaIncidencias.add(incidencia);
            }
        }
        return listaIncidencias;
    }

}
