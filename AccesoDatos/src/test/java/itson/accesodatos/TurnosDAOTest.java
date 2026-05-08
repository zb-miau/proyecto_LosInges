/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import itson.entidades.Turno;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class TurnosDAOTest {
    IAccesoDatos<Turno> dao = TurnosDAO.getInstance();
    
    public TurnosDAOTest() {
    }
//    
//    @Test
//    public void testCrear(){
//        Set<DayOfWeek> dias = new HashSet();
//        dias.add(DayOfWeek.MONDAY);
//        
//        Turno turno1 = new Turno (
//                "Turno 1",
//                LocalTime.of(12,15),
//                LocalTime.of(20,30),
//                dias,
//                "#e69dfb"
//        );
//        
//        dias.add(DayOfWeek.TUESDAY);
//        Turno turno2 = new Turno (
//                "Turno 2",
//                LocalTime.of(7,15),
//                LocalTime.of(13,30),
//                dias,
//                "#ff6633"
//        );
//        
//        Turno turnoAgregado1 = dao.crear(turno1);
//        assertNotNull(turnoAgregado1.getIdTurno());
//        assertEquals(turnoAgregado1.getNombre(), turno1.getNombre());
//        
//        Turno turnoAgregado2 = dao.crear(turno2);
//        assertNotNull(turnoAgregado2.getIdTurno());
//        assertEquals(turnoAgregado2.getNombre(), turno2.getNombre());
//        
//    }
   
    
}
