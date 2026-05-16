/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package itson.accesodatos;

import itson.entidades.Incidencia;

/**
 *
 * @author Zaira
 */
public class IncidenciasDAOTest {
    IAccesoIncidencias<Incidencia> dao = IncidenciasDAO.getInstance();
    
    public IncidenciasDAOTest() {
    }
    
//    @Test
//    public void testCrear(){
//        Incidencia incidencia1 = new Incidencia(
//                Incidencia.TiposIncidencia.ACOSO,
//                "6a08ea6657aad96ae8fc19f9",
//                "Anda acosando gente. Hay que correrlo."
//        );
//        
//        dao.crear(incidencia1);
//    }
   
}
