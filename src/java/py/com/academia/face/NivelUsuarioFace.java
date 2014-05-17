package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.NivelUsuario;
import py.com.academia.beans.Usuario;
import py.com.academia.session.UsuarioSession;


@Named
@SessionScoped  //@RequestScoped verificar
public class NivelUsuarioFace extends BaseCDIBean<NivelUsuario> {

    private static final long serialVersionUID = 7L;
    @Inject
    private UsuarioSession bean;
    private List<NivelUsuario> list;

    public NivelUsuarioFace() 
    {
        setSelectedBean(new NivelUsuario());
    }

    public String doEditNivelUsuario()
    {
        return "/admin/nivelusuario/edit.faces";
    }

    public String doFinishEditNivelUsuario() 
    {
        bean.setNivelUsuario(getSelectedBean());
        return doListNivelUsuario();
    }
    public String doRemoveNivelUsuario() 
    {
        bean.removeNivelUsuario(getSelectedBean());
        return doListNivelUsuario();
    }

    public String doCreateNivelUsuario() {
        setSelectedBean(new NivelUsuario());
        return "/admin/nivelusuario/add.faces";
    }

    public String doFinishCreateNivelUsuario() {
        bean.saveNivelUsuario(getSelectedBean());
        return doListNivelUsuario();
    }

    public String doListNivelUsuario() {
        list = bean.getAllNivelUsuarios();
        return "/admin/nivelusuario/list.faces";
    }

    public List<NivelUsuario> getAllNivelUsuarios() 
    {
        return bean.getAllNivelUsuarios();
    }
    
    
    public List<NivelUsuario> getList() {
        return list;
    }

    public void setList(List<NivelUsuario> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
