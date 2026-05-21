/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import itson.entidades.Empleado;
import itson.entidades.Incidencia;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Zaira
 */
public class TestInicializar {
    private EmpleadosDAO daoEmpleado;
    private IncidenciasDAO daoIncidencias;
    
    public TestInicializar() {
    }
    
   @Test
   public void crearEmpleados(){
       assertDoesNotThrow( () -> {
        daoEmpleado = EmpleadosDAO.getInstance();
        Empleado empleado = new Empleado(
                 "Zaira",
                 "Barajas",
                 "Diaz"
         );

         Empleado empleadoCreado = daoEmpleado.crear(empleado);
         assertNotNull(empleadoCreado);
         assertNotNull(empleadoCreado.getId());

         empleado = new Empleado(
                 "Ramses",
                 "Contreras",
                 "Avila"
         );

         empleadoCreado = daoEmpleado.crear(empleado);
         assertNotNull(empleadoCreado);
         assertNotNull(empleadoCreado.getId());

         empleado = new Empleado(
                 "Josmara",
                 "Quintana",
                 "Benitez"
         );

         empleadoCreado = daoEmpleado.crear(empleado);
         assertNotNull(empleadoCreado);
         assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                 "Hector",
                 "Flores",
                 "Montoya"
         );

         empleadoCreado = daoEmpleado.crear(empleado);
         assertNotNull(empleadoCreado);
         assertNotNull(empleadoCreado.getId());
         
//         Incidencia incidencia = new Incidencia(
//                 Incidencia.TiposIncidencia.AUSENTISMO,
//                 empleadoCreado.getId(),
//                 "Grosero"
//         );
//         
//         incidencia.setFecha(LocalDate.now());
//         
//         daoIncidencias = IncidenciasDAO.getInstance();
//         daoIncidencias.crear(incidencia);
//         
//       });
       
     
   }
   
  
}
