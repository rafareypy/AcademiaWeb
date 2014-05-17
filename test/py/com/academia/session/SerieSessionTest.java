/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Genero;
import py.com.academia.beans.Limitacion;
import py.com.academia.beans.Serie;
import py.com.academia.beans.Servicio;

/**
 *
 * @author rafael
 */
public class SerieSessionTest {
    
    private static   EJBContainer container ;
    private static Integer idSerie ;
    
    
    public SerieSessionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
         container =  javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        container.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    

    @Test
    public void testSaveSerie() throws Exception {
        System.out.println("saveSerie");
        Serie serie = new Serie();
        serie.setAlumno(obtnerAlumno());
        
        AparatoMusculacion aparatoMusculacion = new AparatoMusculacion();
        aparatoMusculacion.setDescripcion("pino");
        
        
        
        
        
        SerieSession instance = getSerieSession();
        Serie result = instance.saveSerie(serie);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idSerie = result.getId() ;
        

    }
   
    
    
    
    
    @Test
    public void testGetEjercicioById() throws Exception {
        System.out.println("getEjercicioById");

        SerieSession instance = getSerieSession();

        Serie result = instance.getSerieById(idSerie);

        assertNotNull(result);        
        
    }
   
    @Test
    public void testGetAllSeries() throws Exception {
        System.out.println("getAllSeries");
        SerieSession instance = getSerieSession();

        List result = instance.getAllSeries();
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());

    }

    
    /**
     * Test of setSerie method, of class SerieSession.
     */
    @Test
    public void testSetSerie() throws Exception {
        System.out.println("setSerie");

        SerieSession instance = getSerieSession();

        Serie serie = instance.getSerieById(idSerie);
        
        
        Serie result = instance.setSerie(serie);
        
        assertNotNull(result);
//        assertEquals(serie.getNroSeries(), result.getNroSeries());
        
        
    }


    @Test
    public void testRemoveSerie_Serie() throws Exception 
    {

        
        
    }


    @Test
    public void testRemoveSerie_int() throws Exception 
    {

        
    }

    private SerieSession getSerieSession() throws NamingException 
    {
        return (SerieSession)container.getContext().lookup("java:global/classes/SerieSession");
    }

    private Alumno obtnerAlumno() 
    {
 Double vlorD = 45.6 ;
        
        Alumno a = new Alumno();
        a.setFechaNascimiento(new Date());
        a.setNombre("teste");
        a.setFechaMatricula(new Date());
        a.setEmail("rafa@gmail.com");
        a.setBarrio("barrio");
        a.setDireccion("direccion");
        a.setCedula("123");
        a.setApelido("123");
        a.setLineaBaja("123");
        a.setCelular("123");
        a.setNombreCasoEemergencia("emergencia");
        a.setParentesco("parente");
        a.setTelefonoParente("123");
        a.setCelularParente("123");
        
        Genero genero = new Genero();
        genero.setDescripcion("masc");
        
        a.setGenero(genero);

        EstadoCivil estadoCivil = new EstadoCivil();
        estadoCivil.setDescripcion("soltero");        
        a.setEstadoCivil(estadoCivil);     
        
        
        Limitacion limitacion = new Limitacion();
        limitacion.setDescripcion("limitacion");
        
        a.addLimitacion(limitacion);
        
        Servicio servicio = new Servicio();
        servicio.setDescripcion("Servicio 1");
        a.addServicio(servicio);
        
        Servicio servicio2 = new Servicio();
        servicio2.setDescripcion("Servicio 2");
        a.addServicio(servicio2);
        
        
        return a ;    
        
    }
}