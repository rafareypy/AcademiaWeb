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
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Evaluacion;
import py.com.academia.beans.Genero;
import py.com.academia.beans.Limitacion;
import py.com.academia.beans.Objetivo;
import py.com.academia.beans.Servicio;

/**
 *
 * @author rafael
 */
public class EvaluacionSessionTest 
{
    
    private static EJBContainer container ;
    private static Integer idEvaluacion;
    
    public EvaluacionSessionTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
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
    public void testSaveEvaluacion() throws Exception {
        System.out.println("saveEvaluacion");
        
        Double vlorD = 45.6 ;
        
        Alumno alumno = obtnerAlumno();
        
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setAlumno(alumno);
        evaluacion.setBazoDerecho(vlorD );
        evaluacion.setBazoIzquierdo(vlorD);
        evaluacion.setCircunferenciaAbdominal(vlorD);
        evaluacion.setCircunferenciaToracica(vlorD);
        evaluacion.setFecha(new Date());
        evaluacion.setImc(vlorD);
        
        Objetivo objetivo = new Objetivo();
        objetivo.setDescripcion("objetivo");
        
        evaluacion.setObjetivo(objetivo);
        evaluacion.setPantorillaDerecha(vlorD);
        evaluacion.setPantorillaEzquierda(vlorD);
        evaluacion.setPeso(vlorD);
        evaluacion.setPiernaDerecha(vlorD);
        evaluacion.setPiernaIzquierda(vlorD);
        evaluacion.setTalha(vlorD);
                
        EvaluacionSession instance = getEvaluacionSession();
        
        Evaluacion result = instance.saveEvaluacion(evaluacion);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idEvaluacion = result.getId();
        
    }   
    
    @Test
    public void testGetEvaluacionById() throws Exception {
        System.out.println("getEvaluacionById");
        
        
        
        EvaluacionSession instance = getEvaluacionSession();
        
       
        Evaluacion result = instance.getEvaluacionById(idEvaluacion);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        
    }

    @Test
    public void testGetAllEvaluaciones() throws Exception {
        System.out.println("getAllEvaluaciones");

        EvaluacionSession instance = getEvaluacionSession();

        List result = instance.getAllEvaluaciones();

        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
        
    }


    @Test
    public void testSetEvaluacion() throws Exception {
        System.out.println("setEvaluacion");

        EvaluacionSession instance = getEvaluacionSession();

        Evaluacion evaluacion = instance.getEvaluacionById(idEvaluacion);
        evaluacion.setCircunferenciaAbdominal(new Double(10.2));
        
        Evaluacion result = instance.setEvaluacion(evaluacion);
        
        assertNotNull(result);
        assertEquals(result.getCircunferenciaAbdominal(), evaluacion.getCircunferenciaAbdominal());

    }


    @Test
    public void testRemoveEvaluacion_Evaluacion() throws Exception {
       
    }

    /**
     * Test of removeEvaluacion method, of class EvaluacionSession.
     */
    @Test
    public void testRemoveEvaluacion_int() throws Exception {
       
    }

    private EvaluacionSession getEvaluacionSession() throws NamingException 
    {
        return (EvaluacionSession)container.getContext().lookup("java:global/classes/EvaluacionSession") ;
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