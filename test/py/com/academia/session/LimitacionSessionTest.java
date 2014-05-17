/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.awt.Container;
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
import py.com.academia.beans.Limitacion;

/**
 *
 * @author rafael
 */
public class LimitacionSessionTest {
    
    private static EJBContainer container  ;
    private static Integer idLimitacion;
    
    public LimitacionSessionTest() {
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
    public void testSaveLimitacion() throws Exception {
        System.out.println("saveLimitacion");
        Limitacion us = new Limitacion();
        us.setDescripcion("limitacion");
        
        LimitacionSession instance = getLimitacionSession();
        Limitacion limitacion = instance.saveLimitacion(us);
        
        assertNotNull(limitacion);
        
        idLimitacion = limitacion.getId() ;
        
        assertNotNull(idLimitacion);
    }
    
    
    
    @Test
    public void testGetLimitacionById() throws Exception 
    {
        System.out.println("getLimitacion");
        
        
        LimitacionSession instance = getLimitacionSession();
        
        
        Limitacion  limitacion = instance.getLimitacionById(idLimitacion);
                
        
        assertNotNull(limitacion);
        
        idLimitacion = limitacion.getId() ;
        
        assertNotNull(idLimitacion);
        
    }

    /**
     * Test of getAllLimitaciones method, of class LimitacionSession.
     */
    @Test
    public void testGetAllLimitaciones() throws Exception 
    {
        
        LimitacionSession instance = getLimitacionSession();
        
        
        List<Limitacion>  limitacion = instance.getAllLimitaciones();
                
        
        assertNotNull(limitacion);
                
        assertTrue(!limitacion.isEmpty());
        
        assertTrue(limitacion.size()>0);
        
        
        
    }


    @Test
    public void testSetEjercicio() throws Exception 
    {
    
        
        LimitacionSession instance = getLimitacionSession();
        
        
       Limitacion  limitacion = instance.getLimitacionById(idLimitacion);
                
        Limitacion  resultado = instance.setEjercicio(limitacion);
       
        
        assertNotNull(resultado);
        assertNotNull(resultado.getId());
        
        
                
        
        
        
    }

    /**
     * Test of removeLimitacion method, of class LimitacionSession.
     */
    @Test
    public void testRemoveLimitacion_Limitacion() throws Exception {

    }


    private LimitacionSession getLimitacionSession() throws NamingException 
    {
        return (LimitacionSession)container.getContext().lookup("java:global/classes/LimitacionSession");    
    }
}