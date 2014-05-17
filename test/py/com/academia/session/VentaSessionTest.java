/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import py.com.academia.beans.ItemVenta;
import py.com.academia.beans.Producto;
import py.com.academia.beans.Venta;
import py.com.academia.beans.enums.StatusVentaType;
import py.com.academia.beans.enums.TipoProducto;

/**
 *
 * @author rafael
 */
public class VentaSessionTest {
    
    private static  EJBContainer container ;
    private static Integer idVenta ;
    private static Integer idProduct = 11 ;
    private static Date fechaVenta = new Date();
    private Double bd = new Double(200.9);
    

    
    public VentaSessionTest() {
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
    public void testSaveVenta() throws Exception {
        System.out.println("saveVenta");

        VentaSession instance = getVentaSession();

        Venta venta = new Venta();
        venta.setFechaVenta(fechaVenta);
        venta.setStatusVentaType(StatusVentaType.ACTIVA);
        venta.setTotal(bd);
        
        
        
        
        ProductoSession productoSession = getProductoSession();
        
        

        Producto producto = productoSession.getProductoById(11);
        
        if(producto == null || producto.getId() == null){
            System.out.println("Producto esta nulo ");
            Producto p = new Producto();
            p.setCost(200.0);
            p.setName("anem");
            p.setSpec("esp");
            p.setTipoProducto(TipoProducto.ROPAS);
            p.setValorVenta(300.0);
            producto = productoSession.saveProducto(p);
        }
            
        
        
        
        
        
//        ItemVenta itemVenta = new ItemVenta();
//        itemVenta.setProducto(producto);
//        itemVenta.setQnt(10);
//        itemVenta.setVenta(venta);
        
//        venta.addItem(itemVenta);
        
        
        for (int i = 2; i < 5; i++) 
        {
            ItemVenta itemVenta = new ItemVenta();
            itemVenta.addProducto(producto);
            itemVenta.setQnt(i);
            venta.addItem(itemVenta);
            
//        ItemVenta itemVenta = new ItemVenta();
//        itemVenta.setProducto(producto);
//        itemVenta.setQnt(10);
////        itemVenta.setVenta(venta);
//        
//        venta.addItem(itemVenta);
            
            
        }
        
        Venta result = instance.saveVenta(venta);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
        idVenta = result.getId();

    }
    
    
    @Test
    public void testGetVentaById() throws Exception {
        System.out.println("getVentaById");
        
        
        VentaSession instance = getVentaSession();

        Venta result = instance.getVentaById(idVenta);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(idVenta, result.getId());
        

    }

    
    @Test
    public void testGetAllVentas() throws Exception {
        System.out.println("getAllVentas");
    
        VentaSession instance = getVentaSession();

        List result = instance.getAllVentas();

        assertNotNull(result);
        assertTrue(result.size() >= 0);
        
    }

//    @Test
    public void testGetVentasPorFecha() throws Exception {
        
        Calendar inio = null;
        Calendar fin = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VentaSession instance = (VentaSession)container.getContext().lookup("java:global/classes/VentaSession");
        List expResult = null;
        List result = instance.getVentasPorFecha(inio, fin);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastVentas method, of class VentaSession.
     */
    @Test
    public void testGetLastVentas() throws Exception {
        System.out.println("getLastVentas");
        
        VentaSession instance = getVentaSession();

        List result = instance.getLastVentas();

        assertNotNull(result);
        assertTrue(result.size()>=0);
        
    }

    

//    @Test
    public void testSetVenta() throws Exception {
        System.out.println("setVenta");
        Venta us = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VentaSession instance = (VentaSession)container.getContext().lookup("java:global/classes/VentaSession");
        Venta expResult = null;
        Venta result = instance.setVenta(us);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemVentaById method, of class VentaSession.
     */
//    @Test
    public void testGetItemVentaById() throws Exception {
        System.out.println("getItemVentaById");
        
        
        VentaSession instance = getVentaSession();

//        ItemVenta result = instance.getItemVentaById(idItemVenta);

    }

    /**
     * Test of getAllItemVentas method, of class VentaSession.
     */
//    @Test
    public void testGetAllItemVentas() throws Exception {
        System.out.println("getAllItemVentas");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        VentaSession instance = (VentaSession)container.getContext().lookup("java:global/classes/VentaSession");
        List expResult = null;
        List result = instance.getAllItemVentas();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private VentaSession getVentaSession() throws NamingException 
    {
        return (VentaSession)container.getContext().lookup("java:global/classes/VentaSession");
    }

    private ProductoSession getProductoSession() throws NamingException 
    {
            return (ProductoSession)container.getContext().lookup("java:global/classes/ProductoSession");
    }
}