/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package presentacion;

import coordinador.Coordinador;
import dto.DTOEmpleado;

/**
 *
 * @author Zaira
 */
public class Presentacion_main {
    
    public static void main(String[] args) {
        Coordinador coor = new Coordinador();
        coor.cambioDeVentana(Coordinador.LISTA_DE_EMPLEADOS);
//        DTOEmpleado empleado = new DTOEmpleado();
//        empleado.setId("69fc574637e1a5b2ce44152e");
//        Presentacion_gestionDeTurnos main = new Presentacion_gestionDeTurnos(empleado);

//        Presentacion_validacionIncidenciasTabla validarIncidencias = new Presentacion_validacionIncidenciasTabla();
//        validarIncidencias.setVisible(true);
//        DTOEmpleado empleado = new DTOEmpleado("6a0a2bf19169ab12da42ec29", "Hector", "Flores", "Montoya");
//        Presentacion_registroDeIncidencias presentacion_registroDeIncidenciasMenu = new Presentacion_registroDeIncidencias(empleado);
//        presentacion_registroDeIncidenciasMenu.setVisible(true);
    }
}
