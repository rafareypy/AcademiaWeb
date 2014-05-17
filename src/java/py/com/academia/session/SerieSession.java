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
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Serie;
import py.com.academia.beans.SerieDetalle;
import py.com.academia.ejb.BasicSessionBean;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SerieSession extends BasicSessionBean 
{
   
    public Serie getSerieById(int idSerie)
    {
        return getPojo(Serie.class, idSerie);
    }
    
    public List<Serie> getAllSeries()
    {
        return getList(Serie.class ,
                " select al from Serie al ");
    }

    public List<Alumno> getAllAlumnos()
    {
        return getList(Alumno.class ,
                " select al from Alumno al ");
    }

    public List<AparatoMusculacion> getAllAparatoMusculacion()
    {
        return getList(AparatoMusculacion.class ,
                " select al from AparatoMusculacion al ");
    }
    
    
    public List<SerieDetalle> getAllSerieDetalle()
    {
        return getList(SerieDetalle.class ,
                " select al from SerieDetalle al ");
    }

    public List<SerieDetalle> getListSerieDetallePorSerieId(Serie serie)
    {
        return getList(SerieDetalle.class ,
                " select sd from SerieDetalle sd "
                + " where  sd.serie = ?1 "
                ,serie);
    }
    
    
    
    public Serie saveSerie(Serie us)
    {    
        us.setFechaFin(new Date());
        us.setFechaInicio(new Date());
        
        getEm().persist(us);        
        return us ;        
    }
    
    public Serie setSerie( Serie us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeSerie(Serie us)
    {        
        
        boolean toReturn =  execute(
                " DELETE FROM SerieDetalle serieDetalle "
                + " WHERE serieDetalle.serie.id  = ?1 "
                , us.getId()) >= 0 ;
        
         
        if( toReturn == true  )
        {
            us = getEm().merge(us);
            getEm().remove(us);            
        }
        
        

    }
    
    public boolean removeSerie(int idSerie)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM Serie us WHERE us.id  = ?1 "
                , idSerie) >= 0 ;
        
        return toReturn ;
    }
    
    
    

    public SerieDetalle saveSerieDetalle(SerieDetalle serieDetalle)
    {          
        
        
        if(serieDetalle.getSerie().getId() == null)
        {
                System.out.println("vaosm");
            
                getEm().persist(serieDetalle.getSerie());        
        }
        else
        {

        }
                getEm().persist(serieDetalle);        
                return serieDetalle ;        
        

    }
    
    public SerieDetalle setSerieDetalle( SerieDetalle serieDetalle )
    {
        getEm().merge(serieDetalle);
        return serieDetalle ;
    }
    
    public void removeSerieDetalle(SerieDetalle serieDetalle)
    {        
        serieDetalle = getEm().merge(serieDetalle);
        getEm().remove(serieDetalle);
    }
    
    public boolean removeSerieDetalle(int idSerieDetalle)
    {
        
        
        boolean toReturn =  execute(
                " DELETE FROM SerieDetalle us WHERE us.id  = ?1 "
                , idSerieDetalle) >= 0 ;
        
        return toReturn ;
    }

    public List<Ejercicio> getAllEjercicio() 
    {
        return getList(Ejercicio.class ,
                " select al from Ejercicio al ");
    }
    
    
    public SerieDetalle getSerieDetalleById(int idSerieDetalle)
    {
        return getPojo(SerieDetalle.class, idSerieDetalle);
    }

    public List<Serie> getListSeriePorAlumno(Alumno alumno) 
    {
        if(alumno == null || alumno.getId() == null)
            return null ;
        
            return getList(Serie.class ,
                " select al from Serie al"
                    + " where al.alumno = ?1", alumno);
            
            
    }
    
    

}
