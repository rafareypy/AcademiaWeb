package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Ejercicio;
import py.com.academia.session.EjercicioSession;





@Named
@SessionScoped  //@RequestScoped verificar
public class EjercicioFace extends BaseCDIBean<Ejercicio> {

    private static final long serialVersionUID = 7L;
    @Inject
    private EjercicioSession bean;
    

    private List<Ejercicio> list;

    public EjercicioFace() 
    {
        setSelectedBean(new Ejercicio());
    }

    public String doEditEjercicio()
    {
        return "/admin/ejercicio/edit.faces";
    }

    public String doFinishEditEjercicio() 
    {
        bean.setEjercicio(getSelectedBean());
        return doListEjercicio();
    }
    public String doRemoveEjercicio() 
    {
        bean.removeEjercicio(getSelectedBean());
        return doListEjercicio();
    }

    public String doCreateEjercicio() {
        setSelectedBean(new Ejercicio());
        return "/admin/ejercicio/add.faces";
    }

    public String doFinishCreateEjercicio() {
        bean.saveEjercicio(getSelectedBean());
        return doListEjercicio();
    }

    public String doListEjercicio() {
        list = bean.getAllEjercicios();
        return "/admin/ejercicio/list.faces";
    }

    public List<Ejercicio> getListEjercicio() 
    {
        return bean.getAllEjercicios();
    }
    
    
    public List<Ejercicio> getList() {
        return list;
    }

    public void setList(List<Ejercicio> list) {
        this.list = list;
    }
    

    
    
    
}
