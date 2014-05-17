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
import py.com.academia.beans.Ciudad;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Limitacion;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LimitacionSession extends BasicSessionBean 
{
   
    public Limitacion getLimitacionById(int idLimitacion)
    {
        return getPojo(Limitacion.class, idLimitacion);
    }
    
    public List<Limitacion> getAllLimitaciones()
    {
        return getList(Limitacion.class ,
                " select al from Limitacion al ");
    }

    
    public Limitacion saveLimitacion(Limitacion us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Limitacion setEjercicio( Limitacion us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeLimitacion(Limitacion us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeLimitacion(int idLimitacion)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Limitacion us WHERE us.id  = ?1 "
                , idLimitacion) >= 0 ;
        
        return toReturn ;
    }
    
    
    


}
