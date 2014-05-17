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
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Genero;
import py.com.academia.ejb.BasicSessionBean;
import py.com.academia.face.Codifica;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AlumnoSession extends BasicSessionBean
{

    
    public Alumno getAlumnoById(int idAlumno)
    {
        return getPojo(Alumno.class, idAlumno);
    }
    
    public List<Alumno> getAllAlumnos()
    {
        return getList(Alumno.class ,
                " select al from Alumno al ");
    }
    
    public List<Alumno> getAlumnosByName( String name )
    {        
        return getList(Alumno.class, 
                "select al from Alumno al where al.nombre like ?1", "%" +  name + "%" );        
    }
    
    public List<Alumno> getLastUsers() {
        return getLimitedList(Alumno.class, 
                "select al from Alumno "
                + " al order by al.id desc",
                10);
    }    


    public Alumno saveAlumno(Alumno us)
    {    
        us.setFechaMatricula(new Date());
        us.setFechaNascimiento(new Date());
        
        getEm().persist(us);        
        return us ;        
    }
    
    public Alumno setAlumno( Alumno us )
    {
        us.setFechaMatricula(new Date());
        us.setFechaNascimiento(new Date());
        
        getEm().merge(us);
        return us ;
    }
    
    public void removeAlumno(Alumno us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeAlumno(int idAlumno)
    {
        
        
        execute("DELETE FROM Evaluacion evaluacion "
                + " WHERE evaluacion.alumno.id  = ?1 ", idAlumno);
        
        boolean toReturn =  execute(
                " DELETE FROM Alumno us WHERE us.id  = ?1 "
                , idAlumno) >= 0 ;
        
        return toReturn ;
    }
    //-------------------------- Ususario --------------------------
 
public Alumno isUserOK(String login, String password)
{
    
     
    
    login = Codifica.md5(login);
    
    password = Codifica.md5(password);
    
        try 
        {
            return getPojo(Alumno.class, 
                    " select us from Alumno us where "
                    + " us.login = ?1 "
                    + " and us.password = ?2 ", login, password);
        }
        catch (Exception e) 
        {
            return null;
        }        
}
    
    // -------------------------Estado Civil -----------------------
    
    public EstadoCivil getEstadoCivilId(int idEstadoCivil)
    {
        return getPojo(EstadoCivil.class, idEstadoCivil);
    }
    
    public List<EstadoCivil> getAllEstadoCivil()
    {
        return getList(EstadoCivil.class ,
                " select al from EstadoCivil al ");
    }
    
    


    public EstadoCivil saveEstadoCivil(EstadoCivil us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public EstadoCivil setEstadoCivil( EstadoCivil us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeEstadoCivil(EstadoCivil us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeEstadoCivil(int idEstadoCivil)
    {
        
        
        
        boolean toReturn =  execute(
                " DELETE FROM EstadoCivil us WHERE us.id  = ?1 "
                , idEstadoCivil) >= 0 ;
        
        return toReturn ;
    }
    
        
    
//--------------------------------- Genero ------------------------------------
    
    public Genero getGeneroId(int idGenero)
    {
        return getPojo(Genero.class, idGenero);
    }
    
    public List<Genero> getAllGenero()
    {
        return getList(Genero.class ,
                " select al from Genero al ");
    }
    
    


    public Genero saveGenero(Genero us)
    {    
        getEm().persist(us);        
        return us ;        
    }
    
    public Genero setGenero( Genero us )
    {
        getEm().merge(us);
        return us ;
    }
    
    public void removeGenero(Genero us)
    {        
        us = getEm().merge(us);
        getEm().remove(us);
    }
    
    public boolean removeGenero(int idGenero)
    {
        
        
        
        boolean toReturn =  execute(
                " DELETE FROM Genero us WHERE us.id  = ?1 "
                , idGenero) >= 0 ;
        
        return toReturn ;
    }

    public EstadoCivil getEstadoCivilByDescripcion(String name) 
    {
        return getPojo(EstadoCivil.class,
                "select cat from EstadoCivil cat "
                + " where  cat.descripcion = ?1"
                ,name);       
    }

    public Genero getGeneroByDescripcion(String name)
    {
        return getPojo(Genero.class,
                "select cat from Genero cat "
                + " where  cat.descripcion = ?1"
                ,name);       
    }

    public Alumno getAlumnoByName(String nombre)
    {
        return getPojo(Alumno.class,
                "select cat from Alumno cat "
                + " where  cat.nombre = ?1"
                ,nombre);           
    }
    
    
    
    
  

}
