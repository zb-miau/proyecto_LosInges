/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import itson.entidades.Empleado;
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
    IAccesoDatos<Empleado> dao = EmpleadosDAO.getInstance();
    
    public EmpleadosDAOTest() {
    }

    @Test
    public void testCrear(){
        Empleado empleado = new Empleado(
                "Zaira",
                "Barajas",
                "Diaz"
        );
        
        Empleado empleadoCreado = dao.crear(empleado);
        assertNotNull(empleadoCreado);
        assertNotNull(empleadoCreado.getId());
    }
    
}
