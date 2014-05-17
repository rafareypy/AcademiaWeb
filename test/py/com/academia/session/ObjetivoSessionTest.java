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
import py.com.academia.beans.Objetivo;

/**
 *
 * @author rafael
 */
public class ObjetivoSessionTest {
    
    private static  EJBContainer container ;
    private static  Integer idObjetivo;
    
    public ObjetivoSessionTest() {
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
    public void testSaveObjetivo() throws Exception {
        System.out.println("saveObjetivo");
        
       
        ObjetivoSession instance = getObjetivoSession() ;
        
        
        Objetivo objetivo = new Objetivo();
        objetivo.setDescripcion("des");
        
        
        Objetivo result = instance.saveObjetivo(objetivo);
        
        
        assertNotNull(result);
        idObjetivo = result.getId();
        assertNotNull(idObjetivo);
    }
    
    
    @Test
    public void testGetEjercicioById() throws Exception {
        System.out.println("getEjercicioById");

        ObjetivoSession instance = getObjetivoSession();

        Objetivo result = instance.getEjercicioById(idObjetivo);
        
        assertNotNull(result);
        
        

    }

    /**
     * Test of getAllObjetivos method, of class ObjetivoSession.
     */
    @Test
    public void testGetAllObjetivos() throws Exception {
        System.out.println("getAllObjetivos");
        
        ObjetivoSession instance = getObjetivoSession();
        
        List result = instance.getAllObjetivos();

        assertNotNull(result);
        assertTrue(!result.isEmpty());

    }


    /**
     * Test of setObjetivo method, of class ObjetivoSession.
     */
    @Test
    public void testSetObjetivo() throws Exception 
    {

  
       
        ObjetivoSession instance = getObjetivoSession() ;
        
        
        Objetivo objetivo = new Objetivo();
        objetivo.setDescripcion("des");
        
        
        
        Objetivo result = instance.getEjercicioById(idObjetivo);
        
        result.setDescripcion("o");
        
        
        Objetivo r = instance.setObjetivo(result);
        
        
        assertNotNull(r);
        assertEquals(result.getDescripcion(), r.getDescripcion());
                
        
    }

    

    @Test
    public void testRemoveObjetivo_int() throws Exception 
    {
        System.out.println("removeObjetivo");

    }

    private ObjetivoSession getObjetivoSession() throws NamingException 
    {
        return  (ObjetivoSession)container.getContext().lookup("java:global/classes/ObjetivoSession") ;
    }
}