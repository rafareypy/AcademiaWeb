/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.com.academia.beans.ItemVenta;
import py.com.academia.beans.Venta;
import py.com.academia.beans.enums.StatusVentaType;
import py.com.academia.ejb.BasicSessionBean;

/**
 *
 * @author rafael
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class VentaSession extends BasicSessionBean
{

    
    public Venta getVentaById(int idVenta)
    {
        Venta v = getPojo(Venta.class, idVenta);
        if(v != null)
            getEm().refresh(v);        
        return v ;
    }
    
    public List<Venta> getAllVentas()
    {
        return getList(Venta.class ,
                " select al from Venta al ");
    }
    

    public List<Venta> getVentasPorFecha(Calendar inio, Calendar fin)
    {
        return getList(Venta.class ,
                " select al from Venta al"
               +" where al.fechaVenta >= ?1  "
               +" and al.fechaVenta <= ?2 "
               +" order by al.fechaVenta desc "
                , inio.getTime(), fin.getTime());
    }

    
    public List<Venta> getVentasPorFechaYStatusVentaType(Calendar inio, 
                                Calendar fin, StatusVentaType statusVentaType)
    {
        return getList(Venta.class ,
                " select al from Venta al"
               +" where al.fechaVenta >= ?1  "
               +" and al.fechaVenta <= ?2 "
               +" and al.statusVentaType = ?3 "
               +" order by al.fechaVenta desc "
                , inio.getTime(), fin.getTime(), statusVentaType);
    }
    
    
    public List<Venta> getLastVentas() {
        return getLimitedList(Venta.class, 
                "select al from Venta "
                + " al order by al.id desc",
                10);
    }    


    public Venta saveVenta(Venta us)
    {    
        getEm().persist(obtnerVentaSumadoValorTotal(us));        
        return us ;        
    }
    
    public Venta setVenta( Venta us )
    {        
        getEm().merge(obtnerVentaSumadoValorTotal(us));
        return us ;
    }
    
  ////------------------- Item Venta -------------------------------------------
    
    
    public ItemVenta getItemVentaById(int idItemVenta)
    {
        return getPojo(ItemVenta.class, idItemVenta);
    }
    
    public List<ItemVenta> getAllItemVentas()
    {
        return getList(ItemVenta.class ,
                " select al from ItemVenta al ");
    }

    private Venta obtnerVentaSumadoValorTotal(Venta us) {
        Double vlrTotal = 0.0 ;
        
        for (ItemVenta itemVenta : us.getItensVenta()) 
        {
            vlrTotal+= itemVenta.getValorVenta();
        }
        us.setTotal(vlrTotal);
        
        return us ;
    }

    public boolean removerItemVenta(ItemVenta itemVenta)
    {

        Integer idVenta = itemVenta.getVenta().getId();
        
        boolean toReturn =  execute(
                " DELETE FROM ItemVenta us WHERE us.id  = ?1 "
                , itemVenta.getId()) >= 0 ;
        
        
        setVenta(getVentaById(idVenta));
        
        return toReturn ;        
    }

    
    

}
