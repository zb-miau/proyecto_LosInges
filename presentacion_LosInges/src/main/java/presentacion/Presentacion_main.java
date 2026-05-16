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
//            Coordinador coor = new Coordinador();
//            coor.abrirVentanaGestionHorariosMenu();
        DTOEmpleado empleado = new DTOEmpleado();
        empleado.setId("69fc574637e1a5b2ce44152e");
        Presentacion_gestionDeTurnos main = new Presentacion_gestionDeTurnos(empleado);
    }
}
