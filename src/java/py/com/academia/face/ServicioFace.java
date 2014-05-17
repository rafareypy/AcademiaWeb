package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Servicio;
import py.com.academia.session.ServicioSession;




@Named
@SessionScoped  //@RequestScoped verificar
public class ServicioFace extends BaseCDIBean<Servicio> {

    private static final long serialVersionUID = 7L;
    @Inject
    private ServicioSession bean;
    

    private List<Servicio> list;

    public ServicioFace() 
    {
        setSelectedBean(new Servicio());
    }

    public String doEditServicio()
    {
        return "/admin/servicio/edit.faces";
    }

    public String doFinishEditServicio() 
    {
        bean.setServicio(getSelectedBean());
        return doListServicio();
    }
    public String doRemoveServicio() 
    {
        bean.removeServicio(getSelectedBean());
        return doListServicio();
    }

    public String doCreateServicio() {
        setSelectedBean(new Servicio());
        return "/admin/servicio/add.faces";
    }

    public String doFinishCreateServicio() {
        bean.saveServicio(getSelectedBean());
        return doListServicio();
    }

    public String doListServicio() {
        list = bean.getAllServicios();
        return "/admin/servicio/list.faces";
    }

    public List<Servicio> getListServicio() 
    {
        return bean.getAllServicios();
    }
    
    
    public List<Servicio> getList() {
        return list;
    }

    public void setList(List<Servicio> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
