/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidadesMongo.EmpleadoMongo;
import itson.entidades.Empleado;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 *
 * @author Zaira
 */
public class EmpleadosDAOTest {

    private EmpleadosDAO dao;
    private MongoClient mockCliente;
    private MongoDatabase mockBaseDatos;
    private MongoCollection<EmpleadoMongo> mockColeccion;
    private FindIterable<EmpleadoMongo> mockIterable;
    private MockedStatic<ManejadorConexiones> mockManejador;

    @BeforeEach
    public void setUp() {
        dao = EmpleadosDAO.getInstance();
        
        // Inicializar mocks del driver de MongoDB
        mockCliente = mock(MongoClient.class);
        mockBaseDatos = mock(MongoDatabase.class);
        mockColeccion = mock(MongoCollection.class);
        mockIterable = mock(FindIterable.class);

        // Interceptamos la conexion estatica para devolver nuestro cliente mock
        mockManejador = mockStatic(ManejadorConexiones.class);
        mockManejador.when(ManejadorConexiones::crearConexion).thenReturn(mockCliente);
        
        // Comportamiento encadenado por defecto
        when(mockCliente.getDatabase(any())).thenReturn(mockBaseDatos);
        when(mockBaseDatos.getCollection(any(String.class), eq(EmpleadoMongo.class))).thenReturn(mockColeccion);
        when(mockColeccion.find(any(Bson.class))).thenReturn(mockIterable);
        when(mockColeccion.find()).thenReturn(mockIterable);
    }

    @AfterEach
    public void tearDown() {
        mockManejador.close(); // Liberamos el mock estatico
    }

    // ==========================================
    // METODO: crear
    // ==========================================
    @Test
    public void testCrear_Exito() throws PersistenciaException {
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");

        Empleado resultado = dao.crear(empleado);
        
        assertNotNull(resultado);
        verify(mockColeccion, times(1)).insertOne(any(EmpleadoMongo.class));
    }

    @Test
    public void testCrear_Fracaso() {
        doThrow(new MongoException("Error")).when(mockColeccion).insertOne(any(EmpleadoMongo.class));
        
        assertThrows(PersistenciaException.class, () -> dao.crear(new Empleado()));
    }

    // ==========================================
    // METODO: obtenerPorCurp
    // ==========================================
    @Test
    public void testObtenerPorCurp_Exito() {
        Empleado filtro = new Empleado();
        filtro.setCurp("CURP123");
        
        when(mockIterable.first()).thenReturn(new EmpleadoMongo());

        assertNotNull(dao.obtenerPorCurp(filtro));
    }

    @Test
    public void testObtenerPorCurp_Fracaso_Vacio() {
        Empleado filtro = new Empleado();
        filtro.setCurp(""); // Dispara la validacion inicial del metodo

        assertNull(dao.obtenerPorCurp(filtro));
    }

    // ==========================================
    // METODO: obtenerPorRfc
    // ==========================================
    @Test
    public void testObtenerPorRfc_Exito() {
        Empleado filtro = new Empleado();
        filtro.setRfc("RFC123");
        
        when(mockIterable.first()).thenReturn(new EmpleadoMongo());

        assertNotNull(dao.obtenerPorRfc(filtro));
    }

    @Test
    public void testObtenerPorRfc_Fracaso_NoEncontrado() {
        Empleado filtro = new Empleado();
        filtro.setRfc("RFC_INEXISTENTE");
        
        when(mockIterable.first()).thenReturn(null); // No hay coincidencias

        assertNull(dao.obtenerPorRfc(filtro));
    }

    // ==========================================
    // METODO: obtenerPorNss
    // ==========================================
    @Test
    public void testObtenerPorNss_Exito() {
        Empleado filtro = new Empleado();
        filtro.setNss("NSS123");
        
        when(mockIterable.first()).thenReturn(new EmpleadoMongo());

        assertNotNull(dao.obtenerPorNss(filtro));
    }

    @Test
    public void testObtenerPorNss_Fracaso_ErrorDriver() {
        Empleado filtro = new Empleado();
        filtro.setNss("NSS123");
        
        when(mockIterable.first()).thenThrow(new RuntimeException()); // Forzamos el catch del metodo

        assertNull(dao.obtenerPorNss(filtro));
    }

    // ==========================================
    // METODO: obtener (Por ID)
    // ==========================================
    @Test
    public void testObtener_Exito() {
        Empleado filtro = new Empleado();
        filtro.setId(new ObjectId().toHexString()); // ID Hexadecimal valido para MongoDB
        
        when(mockIterable.first()).thenReturn(new EmpleadoMongo());

        assertNotNull(dao.obtener(filtro));
    }

    @Test
    public void testObtener_Fracaso_IdInvalido() {
        Empleado filtro = new Empleado();
        filtro.setId("ID_INVALIDO"); // Provocara un fallo al intentar crear el new ObjectId()

        assertThrows(IllegalArgumentException.class, () -> dao.obtener(filtro));
    }

    // ==========================================
    // METODO: obtenerLista
    // ==========================================
    @Test
    public void testObtenerLista_Exito() {
        // Al no mockear el metodo into(), la lista final estara vacia pero no sera nula, lo que valida la ejecucion
        List<Empleado> resultado = dao.obtenerLista();
        
        assertNotNull(resultado);
    }

    @Test
    public void testObtenerLista_Fracaso() {
        when(mockColeccion.find()).thenThrow(new MongoException("Fallo del cursor"));

        assertThrows(MongoException.class, () -> dao.obtenerLista());
    }

}
