/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import itson.entidades.Empleado;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
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
public class EmpleadosDAOTest {

    IAccesoEmpleados<Empleado> dao = EmpleadosDAO.getInstance();

    public EmpleadosDAOTest() {
    }

    @Test
    public void testCrear() {
        Empleado empleado = new Empleado(
                "Zaira",
                "Barajas",
                "Diaz"
        );

        Empleado empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                "Ramses",
                "Contreras",
                "Avila"
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                "Josmara",
                "Quintana",
                "Benitez"
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                "Hector",
                "Flores",
                "Montoya"
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

    

//        empleado = new Empleado(
//                "Juan Carlos",
//                "Pérez",
//                "García"
//        );
//        empleado.setCodigoPostal(03100);
//        empleado.setCurp("PEGJ920515HDFRRN01");
//        empleado.setFechaNacimiento(LocalDate.of(1992, Month.MAY, 15));
//        empleado.setRfc("PEGJ9205151A2");
//        empleado.setCalle("Av. Siempre Viva");
//        empleado.setNss("12345678901");
//        empleado.setColonia("Del Valle");
//
//        empleadoCreado = dao.crear(empleado);
//        assertNotNull(empleadoCreado);
//        assertNotNull(empleadoCreado.getId());
        empleado = new Empleado(
                "Fulanito",
                "Perez",
                ""
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                "Manganito",
                "Juárez",
                ""
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

        empleado = new Empleado(
                "Ana Alicia",
                "Armenta",
                ""
        );

        empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());

    }

}
