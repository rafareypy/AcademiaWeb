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
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EjercicioSession extends BasicSessionBean 
{
   
    public Ejercicio getEjercicioById(int idEjercicio)
    {
        return getPojo(Ejercicio.class, idEjercicio);
    }
    
    public List<Ejercicio> getAllEjercicios()
    {
        return getList(Ejercicio.class ,
                " select al from Ejercicio al ");
    }

    
    public Ejercicio saveEjercicio(Ejercicio us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Ejercicio setEjercicio( Ejercicio us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeEjercicio(Ejercicio us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeEjercicio(int idEjercicio)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Ejercicio us WHERE us.id  = ?1 "
                , idEjercicio) >= 0 ;
        
        return toReturn ;
    }

    public Ejercicio getEjercicioByDescripcion(String value) 
    {
        return getPojo(Ejercicio.class,
                "select cat from Ejercicio cat "
                + " where  cat.descripcion = ?1"
                ,value);               
    }
    
    
    


}
