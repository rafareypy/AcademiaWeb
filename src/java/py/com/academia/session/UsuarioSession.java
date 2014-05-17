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
import py.com.academia.beans.Usuario;
import py.com.academia.ejb.BasicSessionBean;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UsuarioSession extends BasicSessionBean
{
    
     public Usuario getUsuarioById(int idUsuario)
    {
        return getPojo(Usuario.class, idUsuario);
    }
    
    public List<Usuario> getAllUsuarios()
    {
        return getList(Usuario.class ,
                " select al from Usuario al ");
    }

    
    public Usuario saveUsuario(Usuario us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Usuario setUsuario( Usuario us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeUsuario(Usuario us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeUsuario(int idUsuario)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Usuario us WHERE us.id  = ?1 "
                , idUsuario) >= 0 ;
        
        return toReturn ;
    }
   
    
//    ------------------------------- Nivel de Usuario -------------------------
    
    
    public NivelUsuario saveNivelUsuario(NivelUsuario us)
    {    
        getEm().persist(us);        
        return us ;        
    }    
    
     public NivelUsuario getNivelUsuarioById(int idNivelUsuario)
    {
        return getPojo(NivelUsuario.class, idNivelUsuario);
    }
    
    public List<NivelUsuario> getAllNivelUsuarios()
    {
        return getList(NivelUsuario.class ,
                " select al from NivelUsuario al ");
    }

    public NivelUsuario getNivelUsuarioByDescripcion(String name)
    {
        return getPojo(NivelUsuario.class,
                "select cat from NivelUsuario cat "
                + " where  cat.descripcion = ?1"
                ,name);
    }
    

    
    public NivelUsuario setNivelUsuario( NivelUsuario us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeNivelUsuario(NivelUsuario us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeNivelUsuario(int idNivelUsuario)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM NivelUsuario us WHERE us.id  = ?1 "
                , idNivelUsuario) >= 0 ;
        
        return toReturn ;
    }
    

    

}
