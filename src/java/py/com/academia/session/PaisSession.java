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
import py.com.academia.beans.NivelUsuario;
import py.com.academia.beans.Pais;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PaisSession extends BasicSessionBean 
{

    public Pais getCiudadnById(int idPais)
    {
        return getPojo(Pais.class, idPais);
    }
    
    public List<Pais> getAllPais()
    {
        return getList(Pais.class ,
                " select al from Pais al ");
    }

    
    public Pais getPaisByDescripcion(String name)
    {
        return getPojo(Pais.class,
                "select cat from Pais cat "
                + " where  cat.descripcion = ?1"
                ,name);
    }
    
    
    public Pais savePais(Pais us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Pais setPais( Pais us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removePais(Pais us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removePais(int idPais)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Pais us WHERE us.id  = ?1 "
                , idPais) >= 0 ;
        
        return toReturn ;
    }
    
    
    


}
