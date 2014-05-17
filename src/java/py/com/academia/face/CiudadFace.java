package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Ciudad;
import py.com.academia.beans.Pais;
import py.com.academia.session.CiudadSession;




@Named
@SessionScoped  //@RequestScoped verificar
public class CiudadFace extends BaseCDIBean<Ciudad> {

    private static final long serialVersionUID = 7L;
    @Inject
    private CiudadSession bean;
    

    private List<Ciudad> list;

    public CiudadFace() 
    {
        setSelectedBean(new Ciudad());
    }

    public String doEditCiudad()
    {
        return "/admin/ciudad/edit.faces";
    }

    public String doFinishEditCiudad() 
    {
        bean.setCiudad(getSelectedBean());
        return doListCiduad();
    }
    public String doRemoveCiudad() 
    {
        bean.removeCiudad(getSelectedBean());
        return doListCiduad();
    }

    public String doCreateCiudad() {
        setSelectedBean(new Ciudad());
        return "/admin/ciudad/add.faces";
    }

    public String doFinishCreateCiudad() {
        bean.saveCiudad(getSelectedBean());
        return doListCiduad();
    }

    public String doListCiduad() {
        list = bean.getAllCiudad();
        return "/admin/ciudad/list.faces";
    }

    public List<Ciudad> getListCiudad() 
    {
        return bean.getAllCiudad();
    }
    
    
    public List<Pais> getListPais() 
    {
        return bean.getAllPais();
    }
    
    
    public List<Ciudad> getList() {
        return list;
    }

    public void setList(List<Ciudad> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
