package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Genero;
import py.com.academia.session.AlumnoSession;




@Named
@SessionScoped  //@RequestScoped verificar
public  class AlumnoFace extends BaseCDIBean<Alumno> {

    
    
    private static final long serialVersionUID = 7L;
    @Inject
    private AlumnoSession bean;
    
    @Inject
    private Codifica codifica ;

    private List<Alumno> list;

    public AlumnoFace() 
    {
        setSelectedBean(new Alumno());
    }

    public String doEditAlumno()
    {
        return "/admin/alumno/edit.faces";
    }

    public String doFinishEditAlumno() 
    {
        bean.setAlumno( obtnerAlunoCodificadoSenha(getSelectedBean()));
        return doListAlumno();
    }
    public String doRemoveAlumno() 
    {
        bean.removeAlumno(getSelectedBean());
        return doListAlumno();
    }

    public String doCreateAlumno() {
        setSelectedBean(new Alumno());
        return "/admin/alumno/add.faces";
    }

    public String doFinishCreateAlumno() {
        bean.saveAlumno(obtnerAlunoCodificadoSenha(getSelectedBean()));
        return doListAlumno();
    }

    public String doListAlumno() {
        list = bean.getAllAlumnos();
        return "/admin/alumno/list.faces";
    }

    public List<Alumno> getListAlumno() 
    {
        return bean.getAllAlumnos();                
    }
    
    public List<EstadoCivil> getAllEstadoCivil() 
    {
        return bean.getAllEstadoCivil();
    }

    public List<Genero> getAllGenero() 
    {
        return bean.getAllGenero();
    }
    
    
    public List<Alumno> getList() {
        return list;
    }

    public void setList(List<Alumno> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------

    private Alumno obtnerAlunoCodificadoSenha(Alumno alumno)
    {
    
        if(alumno.getLogin() != null && alumno.getPassword() != null)
        {
            alumno.setLogin(getCodifica().md5(alumno.getLogin()));
            alumno.setPassword(getCodifica().md5(alumno.getPassword()));
        }
        return alumno ;
        
    }

    /**
     * @return the codifica
     */
    public Codifica getCodifica() {
        return codifica;
    }

    /**
     * @param codifica the codifica to set
     */
    public void setCodifica(Codifica codifica) {
        this.codifica = codifica;
    }
    
    
    
    
    
}
