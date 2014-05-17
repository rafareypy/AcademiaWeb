package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Genero;
import py.com.academia.session.AlumnoSession ;





@Named
@SessionScoped  //@RequestScoped verificar
public class GeneroFace extends BaseCDIBean<Genero> {

    private static final long serialVersionUID = 7L;
    @Inject
    private AlumnoSession bean;
    

    private List<Genero> list;

    public GeneroFace() 
    {
        setSelectedBean(new Genero());
    }

    public String doEditGenero()
    {
        return "/admin/genero/edit.faces";
    }

    public String doFinishEditGenero() 
    {
        bean.setGenero(getSelectedBean());
        return doListGenero();
    }
    public String doRemoveGenero() 
    {
        bean.removeGenero(getSelectedBean());
        return doListGenero();
    }

    public String doCreateGenero() {
        setSelectedBean(new Genero());
        return "/admin/genero/add.faces";
    }

    public String doFinishCreateGenero() {
        bean.saveGenero(getSelectedBean());
        return doListGenero();
    }

    public String doListGenero() {
        list = bean.getAllGenero();                
        return "/admin/genero/list.faces";
    }

    public List<Genero> getListGenero() 
    {
        return bean.getAllGenero();
    }
    
    
    public List<Genero> getList() {
        return list;
    }

    public void setList(List<Genero> list) {
        this.list = list;
    }
    

    
    
    
}
