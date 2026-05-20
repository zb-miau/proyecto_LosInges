/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionAsistencias;

import dto.DTORegistroMarca;
import java.time.LocalDate;
import java.util.List;
import objetosNegocio.NegocioException;

/**
 *
 * @author josma
 */
public class FacadeGestionAsistencias implements IGestionAsistencias {
    private ControlGestionAsistencias control;
    
    /**
     * Constructor por defecto que inicializa la fachada.
     * Crea la instancia del controlador interno encargado de procesar la logica.
     */
    public FacadeGestionAsistencias() {
        this.control = new ControlGestionAsistencias();
    }
    
    @Override
    public DTORegistroMarca crearMarca(DTORegistroMarca marcaDTO) throws NegocioException {
        return control.agregarMarca(marcaDTO);
    }
    
    @Override
    public List<DTORegistroMarca> obtenerListaMarca(String idEmpleado, LocalDate inicio, LocalDate fin) throws NegocioException {
        return control.reporteAsistencia(idEmpleado, inicio, fin);
    }

    @Override
    public int conteoAsistencia(List<DTORegistroMarca> listaMarcas) throws NegocioException {
        return control.ObtenerConteo(listaMarcas);
    }
    
}
