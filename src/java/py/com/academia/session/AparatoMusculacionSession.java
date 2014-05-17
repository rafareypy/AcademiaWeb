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
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AparatoMusculacionSession extends BasicSessionBean 
{

    public AparatoMusculacion getAparatoMusculacionById(int idAparatoMusculacion)
    {
        return getPojo(AparatoMusculacion.class, idAparatoMusculacion);
    }
    
    public List<AparatoMusculacion> getAllAparatoMusculacion()
    {
        return getList(AparatoMusculacion.class ,
                " select al from AparatoMusculacion al ");
    }

    
    public AparatoMusculacion saveAparatoMusculacion(AparatoMusculacion us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public AparatoMusculacion setAparatoMusculacion( AparatoMusculacion us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeAparatoMusculacion(AparatoMusculacion us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeAparatoMusculacion(int idAparatoMusculacion)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM AparatoMusculacion us WHERE us.id  = ?1 "
                , idAparatoMusculacion) >= 0 ;
        
        return toReturn ;
    }

    public AparatoMusculacion getAparatoMusculacionByDescripcion(String descripcion)
    {
        return getPojo(AparatoMusculacion.class,
                "select cat from AparatoMusculacion cat "
                + " where  cat.descripcion = ?1"
                ,descripcion);                  
    }
    
    
    

}
