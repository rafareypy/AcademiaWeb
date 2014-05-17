package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.EstadoCivil;
import py.com.academia.session.AlumnoSession;





@Named
@SessionScoped  //@RequestScoped verificar
public class EstadoCivilFace extends BaseCDIBean<EstadoCivil> {

    private static final long serialVersionUID = 7L;
    @Inject
    private AlumnoSession bean;
    
    
    String erro = "" ;

    private List<EstadoCivil> list;

    public EstadoCivilFace() 
    {
        setSelectedBean(new EstadoCivil());
    }

    public String doEditEstadoCivil()
    {
        return "/admin/estado_civil/edit.faces";
    }

    public String doFinishEditEstadoCivil() 
    {
        bean.setEstadoCivil(getSelectedBean());
        return doListEstadoCivil();
    }
    public String doRemoveEstadoCivil() 
    {
        try {
            bean.removeEstadoCivil(getSelectedBean());
        } catch (Exception e) {
            erro = "No Fue possible Eliminar este Estado Civil \n Causa Erro:\n"
                    + "Possiblemente algun alumno esta usando este estado Civil !!";
        }
        
        
        return doListEstadoCivil();
    }

    public String doCreateEstadoCivil() {
        setSelectedBean(new EstadoCivil());
        return "/admin/estado_civil/add.faces";
    }

    public String doFinishCreateEstadoCivil() {
        bean.saveEstadoCivil(getSelectedBean());
        return doListEstadoCivil();
    }

    public String doListEstadoCivil() {
        list = bean.getAllEstadoCivil();
        
        if("".equals(erro)){
            
        }else{
            FacesContext.getCurrentInstance().
                addMessage("form:msj", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                ""+erro, null));
            erro = "" ;
        }
        
        return "/admin/estado_civil/list.faces";
    }

    
    public String doErro(String msj)
    {
        
        FacesContext.getCurrentInstance().
                addMessage("form:msj", 
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                ""+msj, null));

        
        return "/admin/estado_civil/erro.faces";    
    }
    
    public List<EstadoCivil> getListEstadoCivil() 
    {
        return bean.getAllEstadoCivil();
    }
    
    
    public List<EstadoCivil> getList() {
        return list;
    }

    public void setList(List<EstadoCivil> list) {
        this.list = list;
    }
    

    
    
    
}
