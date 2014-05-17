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
public class UsuarioFace extends BaseCDIBean<Usuario> {

    private static final long serialVersionUID = 7L;
    @Inject
    private UsuarioSession bean;
    private List<Usuario> list;

    public UsuarioFace() 
    {
        setSelectedBean(new Usuario());
    }

    public String doEditUsuario()
    {
        return "/admin/usuario/edit.faces";
    }

    public String doFinishEditUsuario() 
    {
        bean.setUsuario(getSelectedBean());
        return doListUsuarios();
    }
    public String doRemoveUsuario() 
    {
        bean.removeUsuario(getSelectedBean());
        return doListUsuarios();
    }

    public String doCreateUsuario() {
        setSelectedBean(new Usuario());
        return "/admin/usuario/add.faces";
    }

    public String doFinishCreateUsuario() {
        bean.saveUsuario(getSelectedBean());
        return doListUsuarios();
    }

    public String doListUsuarios() {
        list = bean.getAllUsuarios();        
        return "/admin/usuario/list.faces";
    }

    public List<NivelUsuario> getListNivelUsuario() 
    {
        return bean.getAllNivelUsuarios();
    }
    
    
    public List<Usuario> getList() {
        return list;
    }

    public void setList(List<Usuario> list) {
        this.list = list;
    }
    
    
//    -------------------------- Nivel de Usuario ------------------------------
    
    
    
    
    
}
