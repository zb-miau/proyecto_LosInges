/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import entidadesMongo.TurnoMongo;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Zaira
 */
public class TurnosDAOTest {
    IAccesoTurnos<TurnoMongo> dao = TurnosDAO.getInstance();
    
    public TurnosDAOTest() {
    }
    
    @Test
    public void testCrear(){
        Set<DayOfWeek> dias = new HashSet();
        dias.add(DayOfWeek.MONDAY);
        
        TurnoMongo turno1 = new TurnoMongo (
                "Turno 1",
                LocalTime.of(12,15),
                LocalTime.of(20,30),
                dias,
                "#e69dfb"
        );
        
        dias.add(DayOfWeek.TUESDAY);
        TurnoMongo turno2 = new TurnoMongo (
                "Turno 2",
                LocalTime.of(7,15),
                LocalTime.of(13,30),
                dias,
                "#ff6633"
        );
        
        assertDoesNotThrow( () -> {
            TurnoMongo turnoAgregado1 = dao.crear(turno1);
            assertNotNull(turnoAgregado1.getIdTurno());
            assertEquals(turnoAgregado1.getNombre(), turno1.getNombre());

            TurnoMongo turnoAgregado2 = dao.crear(turno2);
            assertNotNull(turnoAgregado2.getIdTurno());
            assertEquals(turnoAgregado2.getNombre(), turno2.getNombre());
        });
        
    }
   
    
}
