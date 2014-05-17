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
import py.com.academia.beans.Ciudad;
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Evaluacion;
import py.com.academia.beans.Genero;
import py.com.academia.beans.Limitacion;
import py.com.academia.beans.Objetivo;
import py.com.academia.beans.Pais;
import py.com.academia.beans.PlanDePago;
import py.com.academia.beans.Servicio;

/**
 *
 * @author rafael
 */

public class AlumnoSessionTest {
    
    private static EJBContainer container ;    
    private static Integer idAlumno ;        
    private static Integer idEstadoCivil;
    private static Integer idGenero;
    
    public AlumnoSessionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        container =  javax.ejb.embeddable.EJBContainer.createEJBContainer()  ;           
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
    public void testSaveAlumno() throws Exception {
        System.out.println("saveAlumno");
        double vlorD =54.5 ;
        
        AlumnoSession instance = getAlumnoSession();

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
        
        
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setAlumno(a);
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

        a.addEvaluacion(evaluacion);
        
        
        Alumno result = instance.saveAlumno(a);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idAlumno = result.getId();
        
        if( result.getServicios() == null)
        {
            
            for (int i = 0; i < 10; i++) 
            {
                System.out.println("Esta nulo");    
            }
            
        }

        if( result.getServicios().isEmpty())
        {
            
            for (int i = 0; i < 10; i++) 
            {
                System.out.println("Esta vacio");    
            }
            
        }
                
        
        if(result.getServicios() != null && !result.getServicios().isEmpty())
        {

            System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            
            System.out.println(" Servico  " + result.getServicios().get(1).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(1).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(1).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            System.out.println(" Servico  " + result.getServicios().get(1).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());System.out.println(" Servico  " + result.getServicios().get(0).getDescripcion());
            
            
            
        }
        
        
    }
    

    
    
    @Test
    public void testGetAlumnoById() throws Exception {
        System.out.println("getAlumnoById");
       
        AlumnoSession instance = getAlumnoSession();
        
        Alumno result = instance.getAlumnoById(idAlumno);

        
        assertNotNull(result);
        assertNull(instance.getAlumnoById(123123));
        
        
    }

    
    @Test
    public void testGetAllAlumnos() throws Exception {
        System.out.println("getAllAlumnos");
        
        AlumnoSession instance = getAlumnoSession();
        
        
        
        List<Alumno> result = instance.getAllAlumnos();
        
        
        for (Alumno alumno : result) 
        {
            System.out.println(" alumno " + result.get(0).getNombre() );
        }
        
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
        
        
    }

    /**
     * Test of getAlumnosByName method, of class AlumnoSession.
     */
    @Test
    public void testGetAlumnosByName() throws Exception {
        System.out.println("getAlumnosByName");
        String name = "";
        
        AlumnoSession instance = getAlumnoSession();
        
        List result = instance.getAlumnosByName(name);
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());

    }

    
    @Test
    public void testGetLastUsers() throws Exception {
        System.out.println("getLastUsers");
    
        AlumnoSession instance = getAlumnoSession();
        
        List result = instance.getLastUsers();

        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

   
//    @Test
    public void testSetAlumno() throws Exception {
        System.out.println("setAlumno");
        
        AlumnoSession instance = getAlumnoSession();

        Alumno us = instance.getAlumnoById(idAlumno);
        
        
        Alumno result = instance.setAlumno(us);

        assertNotNull(result);
        assertNotNull(result.getId());
        
    }
   
    @Test
    public void testRemoveAlumno_Alumno() throws Exception {
        System.out.println("removeAlumno");

        AlumnoSession instance = getAlumnoSession();
        
        Alumno alumno = instance.getAlumnoById(idAlumno);
        
         instance.removeAlumno(alumno);
        

    }

    /**
     * Test of removeAlumno method, of class AlumnoSession.
     */
    @Test
    public void testRemoveAlumno_int() throws Exception {
       
    }

   
//    ----------------------------- Estado Civil -------------------------------
    
    @Test
    public void testSaveEstadoCivil() throws Exception {
        System.out.println("saveEstadoCivil");
        
        
        AlumnoSession instance = getAlumnoSession();
        
        EstadoCivil us = new EstadoCivil();
        us.setDescripcion("Soltero");
                
        EstadoCivil result = instance.saveEstadoCivil(us);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idEstadoCivil = result.getId() ;
        
    }
    
    
    
    @Test
    public void testGetEstadoCivilId() throws Exception {
        System.out.println("getEstadoCivilId");
        
        AlumnoSession instance = getAlumnoSession();
        
        EstadoCivil result = instance.getEstadoCivilId(idEstadoCivil);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
    }

    /**
     * Test of getAllEstadoCivil method, of class AlumnoSession.
     */
    @Test
    public void testGetAllEstadoCivil() throws Exception {
        System.out.println("getAllEstadoCivil");
        
        AlumnoSession instance = getAlumnoSession();
        
        List result = instance.getAllEstadoCivil();
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }

    

    /**
     * Test of setEstadoCivil method, of class AlumnoSession.
     */
    @Test
    public void testSetEstadoCivil() throws Exception {
        System.out.println("setEstadoCivil");        
        
        AlumnoSession instance = getAlumnoSession();
        
        EstadoCivil estadoCivil = instance.getEstadoCivilId(idEstadoCivil);
        estadoCivil.setDescripcion("modificado");
        
        EstadoCivil result = instance.setEstadoCivil(estadoCivil);
        
        assertNotNull(result);
        assertEquals(result.getDescripcion(), estadoCivil.getDescripcion());
        
        
    }

    /**
     * Test of removeEstadoCivil method, of class AlumnoSession.
     */
    @Test
    public void testRemoveEstadoCivil_EstadoCivil() throws Exception {
        System.out.println("removeEstadoCivil");

        AlumnoSession instance = getAlumnoSession();
        

    }


    @Test
    public void testSaveGenero() throws Exception {
        System.out.println("saveGenero");
        Genero us = new Genero();
        us.setDescripcion("no sabe");

        AlumnoSession instance = getAlumnoSession();

        Genero result = instance.saveGenero(us);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idGenero = result.getId() ;

        
    }
    
    
    @Test
    public void testRemoveEstadoCivil_int() throws Exception {
        
    }

    /**
     * Test of getGeneroId method, of class AlumnoSession.
     */
    @Test
    public void testGetGeneroId() throws Exception {
        AlumnoSession instance = getAlumnoSession() ;
        
        Genero result = instance.getGeneroId(idGenero);

        assertNotNull(result);
        assertNotNull(result.getId());
        
    }

    @Test
    public void testGetAllGenero() throws Exception {
        System.out.println("getAllGenero");

        AlumnoSession instance = getAlumnoSession();
        
        List result = instance.getAllGenero();
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
    }


    @Test
    public void testSetGenero() throws Exception {
        System.out.println("setGenero");
        
        AlumnoSession instance = getAlumnoSession();
        
        Genero genero = instance.getGeneroId(idGenero);
        genero.setDescripcion("modiicadoGenero");
        
        Genero result = instance.setGenero(genero);

        
        assertNotNull(result);
        assertEquals(result.getDescripcion(), genero.getDescripcion());
        
    }


    @Test
    public void testRemoveGenero_Genero() throws Exception {

    }
    
    
    @Test
    public void testRemoveGenero_int() throws Exception {
    
    }

    private AlumnoSession getAlumnoSession() throws NamingException
    {
        return (AlumnoSession)container.getContext().lookup("java:global/classes/AlumnoSession");
    }


}