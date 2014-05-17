package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.beans.Pais;
import py.com.academia.session.PaisSession;




@Named
@SessionScoped  //@RequestScoped verificar
public class PaisFace extends BaseCDIBean<Pais> {

    private static final long serialVersionUID = 7L;
    @Inject
    private PaisSession bean;
    private List<Pais> list;

    public PaisFace() 
    {
        setSelectedBean(new Pais());
    }

    public String doEditPais()
    {
        return "/admin/pais/edit.faces";
    }

    public String doFinishEditPais() 
    {
        bean.setPais(getSelectedBean());
        return doListAparatoPais();
    }
    public String doRemovePais() 
    {
        bean.removePais(getSelectedBean());
        return doListAparatoPais();
    }

    public String doCreatePais() {
        setSelectedBean(new Pais());
        return "/admin/pais/add.faces";
    }

    public String doFinishCreatePais() {
        bean.savePais(getSelectedBean());
        return doListAparatoPais();
    }

    public String doListAparatoPais() {
        list = bean.getAllPais();
        return "/admin/pais/list.faces";
    }

    public List<Pais> getListPais() 
    {
        return bean.getAllPais();
    }
    
    
    public List<Pais> getList() {
        return list;
    }

    public void setList(List<Pais> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
