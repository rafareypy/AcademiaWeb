package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.session.AparatoMusculacionSession;



@Named
@SessionScoped  //@RequestScoped verificar
public class AparatoMusculacionFace extends BaseCDIBean<AparatoMusculacion> {

    private static final long serialVersionUID = 7L;
    @Inject
    private AparatoMusculacionSession bean;
    private List<AparatoMusculacion> list;

    public AparatoMusculacionFace() 
    {
        setSelectedBean(new AparatoMusculacion());
    }

    public String doEditAparatoMusculacion()
    {
        return "/admin/aparato_musculacion/edit.faces";
    }

    public String doFinishEditAparatoMusculacion() 
    {
        bean.setAparatoMusculacion(getSelectedBean());
        return doListAparatoMusculacion();
    }
    public String doRemoveAparatoMusculacion() 
    {
        bean.removeAparatoMusculacion(getSelectedBean());
        return doListAparatoMusculacion();
    }

    public String doCreateAparatoMusculacion() {
        setSelectedBean(new AparatoMusculacion());
        return "/admin/aparato_musculacion/add.faces";
    }

    public String doFinishCreateAparatoMusculacion() {
        bean.saveAparatoMusculacion(getSelectedBean());
        return doListAparatoMusculacion();
    }

    public String doListAparatoMusculacion() {
        list = bean.getAllAparatoMusculacion();
        return "/admin/aparato_musculacion/list.faces";
    }

    public List<AparatoMusculacion> getListAparatoMusculacion() 
    {
        return bean.getAllAparatoMusculacion();
    }
    
    
    public List<AparatoMusculacion> getList() {
        return list;
    }

    public void setList(List<AparatoMusculacion> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
