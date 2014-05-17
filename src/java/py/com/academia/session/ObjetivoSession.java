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
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Objetivo;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ObjetivoSession extends BasicSessionBean 
{
   
    public Objetivo getEjercicioById(int idObjetivo)
    {
        return getPojo(Objetivo.class, idObjetivo);
    }
    
    public List<Objetivo> getAllObjetivos()
    {
        return getList(Objetivo.class ,
                " select al from Objetivo al ");
    }

    
    public Objetivo saveObjetivo(Objetivo us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Objetivo setObjetivo( Objetivo us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeObjetivo(Objetivo us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeObjetivo(int idObjetivo)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Objetivo us WHERE us.id  = ?1 "
                , idObjetivo) >= 0 ;
        
        return toReturn ;
    }

    public Objetivo getObjetivoByDescripcion(String name) 
    {
        return getPojo(Objetivo.class,
                "select cat from Objetivo cat "
                + " where  cat.descripcion = ?1"
                ,name);       
    }
    
    
    


}
