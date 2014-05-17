/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import py.com.academia.beans.Producto;

/**
 *
 * @author rafael
 */
public class ProductoSessionTest {
    
    private static   EJBContainer container ;
    private static Integer idCategoria ;
    private static Integer idProducto ;
    private static String nameProducto = "Producto";
    private static String nameCategoria = "Categoria";
    
    
    public ProductoSessionTest() {
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

    private ProductoSession getProductoSession() throws NamingException 
    {
        return (ProductoSession)container.getContext().lookup("java:global/classes/ProductoSession");
    }
    
    /*
    
    @Test
    public void testSaveCategoria() throws Exception {
        System.out.println("saveCategoria");

        List<Categoria> lista = new LinkedList<Categoria>();
        
        
        
        for (int i = 0; i < 10; i++) 
        {
            Categoria c = new Categoria();

            c.setActive(true);
                         
                c.setName(nameCategoria+i);
                
            lista.add(c);
        }
        
        

        ProductoSession instance = getProductoSession();
        
        for (Categoria c : lista) 
        {
                c = instance.saveCategoria(c);

                assertNotNull(c);
                assertTrue(c.getId() != null );
                assertTrue(c.getId() >= 0 );
                
                idCategoria = c.getId();

        }
        
        

    }
     */
        
//    @Test
//    public void testGetCategoriaById() throws Exception {
//        System.out.println("getCategoriaById");
//        
//        
//        ProductoSession instance = getProductoSession();
//
//        Categoria result = instance.getCategoriaById(idCategoria);
//        
//        assertNotNull(result);
//        assertEquals(result.getId(), idCategoria);
//
//    }
    
        
        
//        for (int i = 0; i < 5; i++) 
//        {
//            Producto p = Producto();
//            p.setCategoria(c);
//            p.setCost(bd);
//            p.setDiscountValue(bd);
//            p.setName("producto  "+ i);
//            p.setPhotoUrl("photo");
//            p.setSpec("espe"+i);
//            p.setStock(0);
//            c.getProductos().add(p);
//        }

    /*
    @Test
    public void testSaveProducto() throws Exception {
        System.out.println("saveProducto");
            ProductoSession instance = getProductoSession();
 
            BigDecimal bd = new BigDecimal(100.0);

            
            
            Producto p = new  Producto();
            
            p.setCost(bd);
            p.setDiscountValue(bd);
            p.setName(nameProducto);
            p.setPhotoUrl("photo");
            p.setSpec("espe");
            p.setStock(0);
            
            
            p.setCategoria(instance.getCategoriaById(idCategoria));
       
        
        Producto result = instance.saveProducto(p);
        assertNotNull(result);
        assertNotNull(result.getId());        
        
        idProducto = result.getId();
        
    }
    
    */
    @Test
    public void testGetProductoById() throws Exception {
        System.out.println("getProductoById");
        
        
        ProductoSession instance = getProductoSession();
        
        
        
        Producto result = instance.getProductoById(idProducto);

        assertNotNull(result);
        assertEquals(result.getId(), idProducto);
        
    }

    /**
     * Test of getAllProductos method, of class ProductoSession.
     */
    @Test
    public void testGetAllProductos() throws Exception {
        System.out.println("getAllProductos");
        
        ProductoSession instance = getProductoSession();

        List result = instance.getAllProductos();

        assertNotNull(result);
        assertTrue(result.size() >= 0);

    }

    
    @Test
    public void testGetProductoByName() throws Exception {
        System.out.println("getProductoByName");
        
        
        ProductoSession instance = getProductoSession();
        
        List result = instance.getProductoByName(nameProducto);
        
        assertNotNull(result);
        assertTrue(result.size()>=0);
        
        
    }

 
    @Test
    public void testSetProducto() throws Exception {
        System.out.println("setProducto");
        
        String nameMod = nameProducto + "mod";
        
        ProductoSession instance = getProductoSession();

        Producto mod = instance.getProductoById(idProducto);
        mod.setName(nameMod);
        
        Producto result = instance.setProducto(mod);
        
        assertEquals(nameMod, result.getName());

    }


    /*
    @Test
    public void testGetAllCategorias() throws Exception {
        System.out.println("getAllCategorias");
        
        ProductoSession instance = getProductoSession();

        List result = instance.getAllCategorias();

        assertNotNull(result);
        assertTrue(result.size()>=0);
        
    }

    
     /*
    @Test
    public void testGetCategoriaByName() throws Exception {
        System.out.println("getCategoriaByName");
        String name = "";

        ProductoSession instance = getProductoSession();

        List result = instance.getCategoriaByName(nameCategoria);
        
        assertNotNull(result);
        assertTrue(result.size()>=0);

    }

    /*
    @Test
    public void testSetCategoria() throws Exception {
        System.out.println("setCategoria");
        Categoria us = null;
        
        ProductoSession instance = getProductoSession();

        us = instance.getCategoriaById(idCategoria);
        us.setName("mod");
        
        Categoria result = instance.setCategoria(us);
        
        assertNotNull(result);
        assertEquals(result.getName(), "mod");

    }
*/
    /**
     * Test of removeCategoria method, of class ProductoSession.
     */
//    @Test
    public void testRemoveCategoria_Categoria() throws Exception {
        System.out.println("removeCategoria");
//   
    }

    /*
//    @Test
    public void testRemoveCategoria_int() throws Exception {
        System.out.println("removeCategoria");
        int idCategoria = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ProductoSession instance = (ProductoSession)container.getContext().lookup("java:global/classes/ProductoSession");
        boolean expResult = false;
        boolean result = instance.removeCategoria(idCategoria);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
     */
//    @Test
    public void testRemoveProducto_Producto() throws Exception {
        System.out.println("removeProducto");
        Producto us = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ProductoSession instance = (ProductoSession)container.getContext().lookup("java:global/classes/ProductoSession");
        instance.removeProducto(us);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


//    @Test
    public void testRemoveProducto_int() throws Exception {
        System.out.println("removeProducto");
        int idProducto = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        ProductoSession instance = (ProductoSession)container.getContext().lookup("java:global/classes/ProductoSession");
        boolean expResult = false;
        boolean result = instance.removeProducto(idProducto);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}