/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.com.academia.beans.Producto;
import py.com.academia.beans.enums.TipoProducto;
import py.com.academia.ejb.BasicSessionBean;
import py.com.academia.execao.ExceptionParametroNulo ;

/**
 *
 * @author rafael
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProductoSession extends BasicSessionBean
{

    
    public Producto getProductoById(Integer idProducto) throws ExceptionParametroNulo
    {
        if(idProducto == null)
            throw new ExceptionParametroNulo(" idProducto esta Nulo !!! ");
        
        return getPojo(Producto.class, idProducto);
    }
    
    public List<Producto> getAllProductos()
    {
        return getList(Producto.class ,
                " select al from Producto al ");
    }
    
    public List<Producto> getProductoByName( String name )
    {        
        return getList(Producto.class, 
                "select al from Producto al where al.name like ?1", "%" +  name + "%" );        
    }
    


    public Producto saveProducto(Producto us)
    {            
        getEm().persist(us);        
        return us ;        
    }
    
    public Producto setProducto( Producto us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeProducto(Producto us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeProducto(int idProducto) 
    {
        
        boolean toReturn =  execute(
                " DELETE FROM Producto us WHERE us.id  = ?1 "
                , idProducto) >= 0 ;
        
        return toReturn ;
    }
    
    
    public List<Producto> getAllProductosPorTipoProducto(TipoProducto tipoProducto) 
    {
        return getList(Producto.class, 
                "select al from Producto al where al.tipoProducto = ?1 ",tipoProducto);                
    }
    

    
    
    
    
    //-------------------------Categoria ---------------------------------------
    /*
    
    public Categoria getCategoriaById(int idCategoria)
    {
        return getPojo(Categoria.class, idCategoria);
    }
    
    public List<Categoria> getAllCategorias()
    {
        return getList(Categoria.class ,
                " select al from Categoria al ");
    }
    
    public List<Categoria> getCategoriaByName( String name )
    {        
        return getList(Categoria.class, 
                "select al from Categoria al where al.name like ?1", "%" +  name + "%" );        
    }
    


    public Categoria saveCategoria(Categoria us)
    {            
        getEm().persist(us);        
        return us ;        
    }
    
    public Categoria setCategoria( Categoria us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeCategoria(Categoria us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeCategoria(int idCategoria)
    {
        
        boolean toReturn =  execute(
                " DELETE FROM Categoria us WHERE us.id  = ?1 "
                , idCategoria) >= 0 ;
        
        return toReturn ;
    }
    */

    
    
}
