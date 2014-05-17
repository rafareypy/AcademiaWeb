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
import py.com.academia.beans.Alumno;
import py.com.academia.beans.Ciudad;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Servicio;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ServicioSession extends BasicSessionBean 
{
   
    public Servicio getServicioById(int idServicio)
    {
        return getPojo(Servicio.class, idServicio);
    }
    
    public List<Servicio> getAllServicios()
    {
        return getList(Servicio.class ,
                " select al from Servicio al ");
    }

    
    public Servicio saveServicio(Servicio us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Servicio setServicio( Servicio us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeServicio(Servicio us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeServicio(int idServicio)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Servicio us WHERE us.id  = ?1 "
                , idServicio) >= 0 ;
        
        return toReturn ;
    }

    public Servicio getServicioByDescripcion(String descripcion)
    {
        return getPojo(Servicio.class,
                "select servicio from Servicio servicio "
                + " where  servicio.descripcion = ?1"
                ,descripcion);           
    }
    
    
    


}
