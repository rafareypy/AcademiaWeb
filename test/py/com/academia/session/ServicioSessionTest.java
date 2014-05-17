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
import py.com.academia.beans.Servicio;

/**
 *
 * @author rafael
 */
public class ServicioSessionTest {
    
    private static EJBContainer container ;
    private static Integer idServicio ;
    
    public ServicioSessionTest()
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
    public void testSaveServicio() throws Exception {
        System.out.println("saveServicio");
        
        
        ServicioSession instance = getServicioSession();

        Servicio servicio = new Servicio();
        servicio.setDescripcion("Servicio2");
        
        
        Servicio result = instance.saveServicio(servicio);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        
        idServicio = result.getId();
                

    }
    
    


    @Test
    public void testSetServicio() throws Exception {
        System.out.println("setServicio");

        
        ServicioSession instance = getServicioSession();
        Servicio us = instance.getServicioById(idServicio);
        us.setDescripcion("ServicioModificado");
        
        Servicio result = instance.setServicio(us);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getDescripcion(), us.getDescripcion());
        
        
    }
    
    
    @Test
    public void testGetServicioById() throws Exception {
        System.out.println("getServicioById");

        

        ServicioSession instance = getServicioSession();

        Servicio result = instance.getServicioById(idServicio);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        


    }

    /**
     * Test of getAllServicios method, of class ServicioSession.
     */
    @Test
    public void testGetAllServicios() throws Exception {
        System.out.println("getAllServicios");
        
        ServicioSession instance = getServicioSession();

        List result = instance.getAllServicios();
        
        assertNotNull(result);
        assertTrue(!result.isEmpty());
        
        

    }


    @Test
    public void testRemoveServicio_Servicio() throws Exception 
    {

    }

    /**
     * Test of removeServicio method, of class ServicioSession.
     */
    @Test
    public void testRemoveServicio_int() throws Exception 
    {
    
    }

    private ServicioSession getServicioSession() throws NamingException 
    {
        return (ServicioSession)container.getContext().lookup("java:global/classes/ServicioSession") ;   
    }
}