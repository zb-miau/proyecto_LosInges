/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;


import itson.entidades.Empleado;
import itson.entidades.HorarioEmpleado;
import itson.entidades.Turno;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ramses
 */
public class EmpleadosDAOTest {

    private EmpleadosDAO dao;
    
    private final String CURP_TEST = "CURP999999HDFXYZ01";
    private final String RFC_TEST = "XAXX010101000";
    private final String NSS_TEST = "11998877665";

    @BeforeEach
    public void setUp() {
        dao = EmpleadosDAO.getInstance();
    }

    // ==========================================
    // METODO: crear
    // ==========================================
    @Test
    public void testCrear_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Empleado Prueba");
        nuevo.setCurp(CURP_TEST);
        nuevo.setRfc(RFC_TEST);
        nuevo.setNss(NSS_TEST);

        Empleado resultado = dao.crear(nuevo);

        assertNotNull(resultado);
        assertNotNull(resultado.getId());
    }

    @Test
    public void testCrear_Fracaso() {
        // Pasar un objeto nulo hara que falle el adapter interno al mapear a Mongo
        assertThrows(Exception.class, () -> {
            dao.crear(null);
        });
    }

    // ==========================================
    // METODO: obtenerPorCurp
    // ==========================================
    @Test
    public void testObtenerPorCurp_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Busqueda Curp");
        nuevo.setCurp(CURP_TEST);
        dao.crear(nuevo);

        Empleado filtro = new Empleado();
        filtro.setCurp(CURP_TEST);

        Empleado encontrado = dao.obtenerPorCurp(filtro);
        assertNotNull(encontrado);
        assertEquals(nuevo.getId(), encontrado.getId());
    }

    @Test
    public void testObtenerPorCurp_Fracaso_NoExiste() {
        Empleado filtro = new Empleado();
        filtro.setCurp("CURP_INEXISTENTE_123");

        Empleado encontrado = dao.obtenerPorCurp(filtro);
        assertNull(encontrado);
    }

    // ==========================================
    // METODO: obtenerPorRfc
    // ==========================================
    @Test
    public void testObtenerPorRfc_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Busqueda Rfc");
        nuevo.setRfc(RFC_TEST);
        dao.crear(nuevo);

        Empleado filtro = new Empleado();
        filtro.setRfc(RFC_TEST);

        Empleado encontrado = dao.obtenerPorRfc(filtro);
        assertNotNull(encontrado);
        assertEquals(nuevo.getId(), encontrado.getId());
    }

    @Test
    public void testObtenerPorRfc_Fracaso_Vacio() {
        Empleado filtro = new Empleado();
        filtro.setRfc("   ");

        Empleado encontrado = dao.obtenerPorRfc(filtro);
        assertNull(encontrado);
    }

    // ==========================================
    // METODO: obtenerPorNss
    // ==========================================
    @Test
    public void testObtenerPorNss_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Busqueda Nss");
        nuevo.setNss(NSS_TEST);
        dao.crear(nuevo);

        Empleado filtro = new Empleado();
        filtro.setNss(NSS_TEST);

        Empleado encontrado = dao.obtenerPorNss(filtro);
        assertNotNull(encontrado);
        assertEquals(nuevo.getId(), encontrado.getId());
    }

    @Test
    public void testObtenerPorNss_Fracaso_Null() {
        Empleado encontrado = dao.obtenerPorNss(null);
        assertNull(encontrado);
    }

    // ==========================================
    // METODO: obtener (Por ID)
    // ==========================================
    @Test
    public void testObtener_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Busqueda ID");
        dao.crear(nuevo);

        Empleado filtro = new Empleado();
        filtro.setId(nuevo.getId());

        Empleado encontrado = dao.obtener(filtro);
        assertNotNull(encontrado);
    }

    @Test
    public void testObtener_Fracaso_IdInvalido() {
        Empleado filtro = new Empleado();
        filtro.setId("12345_no_es_hexadecimal");

        assertThrows(IllegalArgumentException.class, () -> {
            dao.obtener(filtro);
        });
    }

    // ==========================================
    // METODO: obtenerLista
    // ==========================================
    @Test
    public void testObtenerLista_Exito() throws PersistenciaException {
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Lista Test");
        dao.crear(nuevo);

        List<Empleado> lista = dao.obtenerLista();
        assertNotNull(lista);
        assertTrue(lista.size() > 0);
    }

    @Test
    public void testObtenerLista_Fracaso() {
        List<Empleado> lista = dao.obtenerLista();
        assertNotNull(lista);
    }

    // ==========================================
    // METODO: modificarHorarioActual
    // ==========================================
    @Test
    public void testModificarHorarioActual_Exito() throws PersistenciaException {
        // Crear el empleado base
        Empleado nuevo = new Empleado();
        nuevo.setNombre("Modificar Horario");
        dao.crear(nuevo);

        // Establecer el nuevo horario a modificar 
        HorarioEmpleado horario = new HorarioEmpleado();
        horario.setIdHorarioEmpleado(new ObjectId().toHexString());

        Turno turnoDummy = new Turno();
        turnoDummy.setNombre("Matutino");
        horario.setTurno(turnoDummy);
        
        nuevo.setHorarioActual(horario);

        // Modificar en la base de datos real
        Empleado modificado = dao.modificarHorarioActual(nuevo);
        assertNotNull(modificado);
    }

    @Test
    public void testModificarHorarioActual_Fracaso_IdInvalido() {
        Empleado filtro = new Empleado();
        filtro.setId("ID_ERRONEO");

        assertThrows(IllegalArgumentException.class, () -> {
            dao.modificarHorarioActual(filtro);
        });
    }

}
