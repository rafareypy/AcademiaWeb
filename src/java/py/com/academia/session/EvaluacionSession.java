/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.session;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.Evaluacion;
import py.com.academia.beans.Objetivo;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EvaluacionSession extends BasicSessionBean 
{
   
    
    public Evaluacion getEvaluacionById(int idEvaluacion)
    {
        return getPojo(Evaluacion.class, idEvaluacion);
    }
    
    public List<Evaluacion> getAllEvaluaciones()
    {
        return getList(Evaluacion.class ,
                " select al from Evaluacion al order by al.fecha desc ");
    }

    public List<Objetivo> getAllObjetivos()
    {
        return getList(Objetivo.class ,
                " select al from Objetivo al  ");
    }

    public List<Alumno> getAllAlumnos()
    {
        return getList(Alumno.class ,
                " select al from Alumno al ");
    }
    
    public Evaluacion saveEvaluacion(Evaluacion us)
    {            
        us.setFecha(new Date());
        getEm().persist(us);        
        return us ;        
    }
    
    public Evaluacion setEvaluacion (Evaluacion us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeEvaluacion(Evaluacion us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeEvaluacion(int idEvaluacion)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Evaluacion us WHERE us.id  = ?1 "
                , idEvaluacion) >= 0 ;
        
        return toReturn ;
    }

    public List<Evaluacion> getAllEvaluacionesPorAlumno(Alumno alumno) 
    {        
                return getList(Evaluacion.class ,
                " select al from Evaluacion al"
                        + " where al.alumno = ?1 "
                        + " order by al.id desc "
                        ,alumno);
        
    }
    
    
    


}
