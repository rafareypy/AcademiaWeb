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
import py.com.academia.beans.Pais;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CiudadSession extends BasicSessionBean 
{

    public Ciudad getCiudadnById(int idCiudad)
    {
        return getPojo(Ciudad.class, idCiudad);
    }
    
    public List<Ciudad> getAllCiudad()
    {
        return getList(Ciudad.class ,
                " select al from Ciudad al ");
    }


    public List<Pais> getAllPais()
    {
        return getList(Pais.class ,
                " select al from Pais al ");
    }
    
    
    public Ciudad saveCiudad(Ciudad us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Ciudad setCiudad( Ciudad us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeCiudad(Ciudad us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeCiudad(int idCiudad)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Ciudad us WHERE us.id  = ?1 "
                , idCiudad) >= 0 ;
        
        return toReturn ;
    }
    
    
    
    
    


}
