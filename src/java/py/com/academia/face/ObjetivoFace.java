package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Objetivo;
import py.com.academia.session.ObjetivoSession;





@Named
@SessionScoped  //@RequestScoped verificar
public class ObjetivoFace extends BaseCDIBean<Objetivo> {

    private static final long serialVersionUID = 7L;
    @Inject
    private ObjetivoSession bean;
    

    private List<Objetivo> list;

    public ObjetivoFace() 
    {
        setSelectedBean(new Objetivo());
    }

    public String doEditObjetivo()
    {
        return "/admin/objetivo/edit.faces";
    }

    public String doFinishEditObjetivo() 
    {
        bean.setObjetivo(getSelectedBean());
        return doListObjetivo();
    }
    public String doRemoveObjetivo() 
    {
        bean.removeObjetivo(getSelectedBean());
        return doListObjetivo();
    }

    public String doCreateObjetivo() {
        setSelectedBean(new Objetivo());
        return "/admin/objetivo/add.faces";
    }

    public String doFinishCreateObjetivo() {
        try 
        {
            bean.saveObjetivo(getSelectedBean());
        }
        catch (Exception e) 
        {
            return doErro();
        }
        
        return doListObjetivo();
    }

    public String doListObjetivo() {
        list = bean.getAllObjetivos();
        return "/admin/objetivo/list.faces";
    }

    public List<Objetivo> getListEjercicio() 
    {
        return bean.getAllObjetivos();
    }
    
    
    public List<Objetivo> getList() {
        return list;
    }

    public void setList(List<Objetivo> list) {
        this.list = list;
    }

    private String doErro() 
    {
        return  doListObjetivo();
    }
    

    
    
    
}
