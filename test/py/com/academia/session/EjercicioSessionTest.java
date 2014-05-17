/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

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
import py.com.academia.beans.Ejercicio;

/**
 *
 * @author rafael
 */
public class EjercicioSessionTest {
    
    private static  EJBContainer container ;
    private static Integer idEjercicio ;
    
    public EjercicioSessionTest() {
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
    public void testSaveEjercicio() throws Exception {
        System.out.println("saveEjercicio");
        Ejercicio us = null;
        
        EjercicioSession instance = getEjercicioSession();
        
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setDescripcion("desde");
        
        
        Ejercicio ejercicio1 =  instance.saveEjercicio(ejercicio);
        
        assertNotNull(ejercicio1);
        assertNotNull(ejercicio1.getId());
        
        idEjercicio = ejercicio1.getId();
        
        
    }

    
    
    @Test
    public void testGetEjercicioById() throws Exception {
        System.out.println("getEjercicioById");
        
        
        EjercicioSession instance = getEjercicioSession();
        
        Ejercicio result = instance.getEjercicioById(idEjercicio);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        
    }

    
    @Test
    public void testGetAllEjercicios() throws Exception {
        System.out.println("getAllEjercicios");
    
        EjercicioSession instance = getEjercicioSession();
        
        List<Ejercicio> result = instance.getAllEjercicios();
        
        assertTrue(!result.isEmpty());
//        assertTrue(result.size() < 0);
        
        
        for (Ejercicio ejercicio : result) 
        {
            System.out.println("Ejercicio " + ejercicio.getDescripcion() );
        }
        
        
    }

    /**
     * Test of setEjercicio method, of class EjercicioSession.
     */
    @Test
    public void testSetEjercicio() throws Exception {
        System.out.println("setEjercicio");
        
        EjercicioSession instance = getEjercicioSession();
        
        Ejercicio ejercicio = instance.getEjercicioById(idEjercicio);
        ejercicio.setDescripcion("modificado");
        
        Ejercicio result = instance.setEjercicio(ejercicio);

        assertNotNull(result);

    }

    
    @Test
    public void testRemoveEjercicio_Ejercicio() throws Exception {
        System.out.println("removeEjercicio");
    
    
        EjercicioSession instance = getEjercicioSession();
        
        
        
        instance.removeEjercicio(instance.getEjercicioById(idEjercicio));

    }


//    @Test
    public void testRemoveEjercicio_int() throws Exception {
        System.out.println("removeEjercicio");
        int idEjercicio = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        EjercicioSession instance = (EjercicioSession)container.getContext().lookup("java:global/classes/EjercicioSession");
        boolean expResult = false;
        boolean result = instance.removeEjercicio(idEjercicio);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private EjercicioSession getEjercicioSession() throws NamingException 
    {
        return (EjercicioSession)container.getContext().lookup("java:global/classes/EjercicioSession") ;
    }
}