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
import py.com.academia.beans.NivelUsuario;
import py.com.academia.beans.Usuario;

/**
 *
 * @author rafael
 */
public class UsuarioSessionTest {
    
    private static EJBContainer container ;
    private static Integer idUsuario;
    
    public UsuarioSessionTest() {
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
    public void testSaveUsuario() throws Exception {
        System.out.println("saveUsuario");


        UsuarioSession instance = getUsuarioSession();
        
        Usuario u = new Usuario();
        u.setApelido("apelido");
        u.setEmail("rafae@gmail.com");
        u.setLogin("login");
        u.setNombre("nombre");
        u.setPassword("password");
        
        NivelUsuario  nivelUsuario = new NivelUsuario();
        nivelUsuario.setDescripcion("Basico");
        
        
        u.setNivelUsuario(nivelUsuario);
        
        Usuario result = instance.saveUsuario(u);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idUsuario = result.getId();
        

    }
    
    
    
    @Test
    public void testGetUsuarioById() throws Exception {
        System.out.println("getUsuarioById");
        
    
        UsuarioSession instance = getUsuarioSession();
        
        Usuario result = instance.getUsuarioById(idUsuario);
        
        assertNotNull(result);
        
    }

    /**
     * Test of getAllUsuarios method, of class UsuarioSession.
     */
    @Test
    public void testGetAllUsuarios() throws Exception {
        System.out.println("getAllUsuarios");
        
        UsuarioSession instance = getUsuarioSession();
        
        List result = instance.getAllUsuarios();
       
        
        assertNotNull(result);
        assertTrue(result.size() >= 0);
        
        
    }

   
   
    @Test
    public void testSetUsuario() throws Exception {
        System.out.println("setUsuario");
        
        
        UsuarioSession instance = getUsuarioSession();

        Usuario u = instance.getUsuarioById(idUsuario);
        u.setLogin("mod");
        
        Usuario result = instance.setUsuario(u);
        
        
        assertEquals(u.getLogin(), result.getLogin());

        
        
    }

    
    @Test
    public void testRemoveUsuario_Usuario() throws Exception {
        System.out.println("removeUsuario");
    
    
        UsuarioSession instance =getUsuarioSession();
     
    
    }

    /**
     * Test of removeUsuario method, of class UsuarioSession.
     */
    @Test
    public void testRemoveUsuario_int() throws Exception {
        System.out.println("removeUsuario");
        
        
    }

    private UsuarioSession getUsuarioSession() throws NamingException 
    {
        return (UsuarioSession)container.getContext().lookup("java:global/classes/UsuarioSession") ;
    }
}