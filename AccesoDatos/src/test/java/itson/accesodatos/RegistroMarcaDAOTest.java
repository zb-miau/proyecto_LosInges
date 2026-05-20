/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.accesodatos;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import entidadesMongo.EmpleadoMongo;
import entidadesMongo.RegistroMarcaMongo;
import itson.entidades.Empleado;
import itson.entidades.RegistroMarca;
import java.time.LocalDate;
import java.util.List;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Clase Test para poner a prueba de maner aislada todos los metodos de
 * RegistroMarcaDAO
 *
 * @author josma
 */
public class RegistroMarcaDAOTest {

    private RegistroMarcaDAO registroMarcaDAO;
    private MongoClient mockCliente;
    private MongoDatabase mockBaseDatos;
    private MongoCollection<RegistroMarcaMongo> mockColeccion;
    private MockedStatic<ManejadorConexiones> mockManejador;

    @BeforeEach
    public void setUp() {
        registroMarcaDAO = RegistroMarcaDAO.getInstance();

        mockCliente = mock(MongoClient.class);
        mockBaseDatos = mock(MongoDatabase.class);
        mockColeccion = mock(MongoCollection.class);

        mockManejador = mockStatic(ManejadorConexiones.class);
        mockManejador.when(ManejadorConexiones::crearConexion).thenReturn(mockCliente);

        when(mockCliente.getDatabase(any())).thenReturn(mockBaseDatos);
        when(mockBaseDatos.getCollection(any(String.class), eq(RegistroMarcaMongo.class))).thenReturn(mockColeccion);
    }

    @AfterEach
    public void tearDown() {
        mockManejador.close();
    }
    //----------------------------------------
    //TEST: CREAR MARCA - Exitoso
    //----------------------------------------
    @Test
    public void testCrearMarca_Exito() throws PersistenciaException {
        // 1. Preparamos el Empleado para que no truene
        Empleado mockEmpleado = new Empleado();
        mockEmpleado.setId(new ObjectId().toHexString());
        mockEmpleado.setNombre("DaniBoy");

        // 2. Creamos la entidad RegistroMarca con su empleado
        RegistroMarca registro = new RegistroMarca();
        registro.setFecha(LocalDate.now());
        registro.setEmpleado(mockEmpleado);

        // 3. Preparamos los mocks de mongo
        InsertOneResult mockResult = mock(InsertOneResult.class);
        ObjectId nuevoId = new ObjectId();
        when(mockResult.getInsertedId()).thenReturn(new BsonObjectId(nuevoId));
        when(mockColeccion.insertOne(any(RegistroMarcaMongo.class))).thenReturn(mockResult);

        // 4. Ejecutamos la simulación
        RegistroMarca resultado = registroMarcaDAO.crear(registro);

        // 5. Verificamos que si se haya "insertado"
        assertNotNull(resultado);
        verify(mockColeccion, times(1)).insertOne(any(RegistroMarcaMongo.class));
    }
    //----------------------------------------
    //TEST: CREAR MARCA - Fracaso
    //----------------------------------------
    @Test
    public void testCrearMarca_Fracaso_MongoException() {
        // 1. Preparamos el objeto completo
        Empleado emp = new Empleado();
        emp.setId(new org.bson.types.ObjectId().toHexString());
        
        RegistroMarca marca = new RegistroMarca();
        marca.setFecha(LocalDate.now());
        marca.setEmpleado(emp);

        // 2. Hacemos que a fuerza se lance una excepcion
        doThrow(new MongoException("Error de conexión con el servidor"))
            .when(mockColeccion).insertOne(any(RegistroMarcaMongo.class));

        // 3. La dao atrapa el error 
        assertThrows(PersistenciaException.class, () -> {
            registroMarcaDAO.crear(marca);
        }, "Se esperaba PersistenciaException cuando falla la base de datos");
        
        // 4. Verificamos que se intento llamar al metodo al menos una vez
        verify(mockColeccion, times(1)).insertOne(any(RegistroMarcaMongo.class));
    }

    //----------------------------------------
    //TEST: OBTENER LISTA - Exitoso
    //----------------------------------------
    @Test
    public void testObtenerLista_Exito() throws PersistenciaException {
        //1. Preparamos los datos que le vamos a pasar a la dao
        String idEmp = new ObjectId().toHexString();
        LocalDate inicio = LocalDate.now().minusDays(7);
        LocalDate fin = LocalDate.now();
        //2. Simulamos el pipeline con AggragateIterable
        AggregateIterable<RegistroMarcaMongo> mockAggregate = mock(AggregateIterable.class);
        when(mockColeccion.aggregate(anyList())).thenReturn(mockAggregate);
        //Lista
        doAnswer(invocation -> {
            List<RegistroMarcaMongo> list = invocation.getArgument(0);
            RegistroMarcaMongo m = new RegistroMarcaMongo();
            m.setFecha(LocalDate.now());
            m.setIdEmpleado(new ObjectId(idEmp));
            m.setId(new ObjectId().toString());

            list.add(m);
            return null;
        }).when(mockAggregate).into(anyList());
        //3. Llamamos a la dao para realizar la obtencion simulada
        List<RegistroMarca> resultado = registroMarcaDAO.obtenerLista(idEmp, inicio, fin);
        //4. Por ultimo verifica que no se nula
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    //----------------------------------------
    //TEST: OBTENER LISTA - Fracaso
    //----------------------------------------
    @Test
    public void testObtenerLista_Fracaso_MongoException() {
        when(mockColeccion.aggregate(anyList())).thenThrow(new MongoException("Error de conexión"));

        assertThrows(PersistenciaException.class, () -> {
            registroMarcaDAO.obtenerLista(new ObjectId().toHexString(), LocalDate.now(), LocalDate.now());
        });
    }
    //----------------------------------------
    //TEST: MODIFICAR MARCA - Exitoso
    //----------------------------------------
    @Test
    public void testModificar_Exito() throws PersistenciaException {
        //1. Preparamos un empleado para que no truene
        Empleado emp = new Empleado();
        emp.setId(new ObjectId().toHexString());
        //Preparamos la marca que se va a modificar
        RegistroMarca marca = new RegistroMarca();
        marca.setIdRegistroMarca(new ObjectId().toHexString());
        marca.setEmpleado(emp);
        marca.setFecha(LocalDate.now());
        //2. Se hace la llamada al método
        RegistroMarca resultado = registroMarcaDAO.modificar(marca);
        //3. Verificamos que no sea nulo
        assertNotNull(resultado);
        assertEquals(marca.getIdRegistroMarca(), resultado.getIdRegistroMarca());
        verify(mockColeccion).findOneAndReplace(any(Document.class), any(RegistroMarcaMongo.class));
    }
    //----------------------------------------
    //TEST: MODIFICAR MARCA - Fracaso
    //----------------------------------------
    @Test
    public void testModificar_Fracaso_MongoException() {
        //1. Preparamos las entidades
        Empleado emp = new Empleado();
        emp.setId(new ObjectId().toHexString());
        RegistroMarca marca = new RegistroMarca();
        marca.setIdRegistroMarca(new ObjectId().toHexString());
        marca.setEmpleado(emp);

        //2. Simular el error
        when(mockColeccion.findOneAndReplace(any(Document.class), any(RegistroMarcaMongo.class)))
                .thenThrow(new MongoException("Error al reemplazar"));

        assertThrows(PersistenciaException.class, () -> registroMarcaDAO.modificar(marca));
    }
    //----------------------------------------
    //TEST: OBTENER MARCA - Exitoso
    //----------------------------------------
    @Test
    public void testObtenerPorEmpleadoYFecha_Exito() throws PersistenciaException {
        // 1. Preparamos un ID ficticio para el empleado
        ObjectId idEmp = new ObjectId();

        // 2. Mock del FindIterable
        FindIterable<RegistroMarcaMongo> mockFind = mock(FindIterable.class);
        when(mockColeccion.find(any(Bson.class))).thenReturn(mockFind);

        // 3. Creamos el objeto Mongo
        RegistroMarcaMongo encontrado = new RegistroMarcaMongo();
        encontrado.setFecha(LocalDate.now());
        encontrado.setIdEmpleado(idEmp);
        encontrado.setId(new ObjectId().toString()); 

        // 4. Hacemos que  el mock  devuelva la entidad llena
        when(mockFind.first()).thenReturn(encontrado);

        // 5. Ejecutamos la dao
        RegistroMarca resultado = registroMarcaDAO.obtenerPorEmpleadoYFecha(idEmp.toHexString(), LocalDate.now());

        // 6. Verificamos que sea correcto y no nulo
        assertNotNull(resultado);
        assertEquals(idEmp.toHexString(), resultado.getEmpleado().getId());
    }
    //----------------------------------------
    //TEST: MARCA  - Fracaso
    //----------------------------------------
    @Test
    public void testObtenerPorEmpleadoYFecha_NoExiste() throws PersistenciaException {
        //1. Configurar el mock
        FindIterable<RegistroMarcaMongo> mockFind = mock(FindIterable.class);
        when(mockColeccion.find(any(Bson.class))).thenReturn(mockFind);
        
        // 2.Simulamos que el registro no existe en la bd
        when(mockFind.first()).thenReturn(null);
        //3. Ejecutamos la DAO
        RegistroMarca resultado = registroMarcaDAO.obtenerPorEmpleadoYFecha(new ObjectId().toHexString(), LocalDate.now());
        //4. Veficar el resultado
        assertNull(resultado, "Debe retornar null si no hay coincidencia");
    }
    //----------------------------------------
    //TEST: OBTENER MARCA - Fracaso
    //----------------------------------------
    @Test
    public void testObtenerPorEmpleadoYFecha_Fracaso_ErrorID() {
        //Pasar un id incorrecto y hacerlo tronar
        assertThrows(Exception.class, () -> {
            registroMarcaDAO.obtenerPorEmpleadoYFecha("id-invalido", LocalDate.now());
        });
    }

}
