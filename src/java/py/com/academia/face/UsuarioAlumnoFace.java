package py.com.academia.face;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.Usuario;
import py.com.academia.session.AlumnoSession;



@Named
@SessionScoped  //@RequestScoped verificar
public class UsuarioAlumnoFace  extends BaseCDIBean<Alumno> 
{

    @Inject
    AlumnoSession alumnoSession ;
    
    private Alumno loggedUser = null;
    private UIInput userUI;    
    private UIInput passwordUI;    
    private String redirectURLOnLogin;

    
    
    
      public void validateLogin(FacesContext context, UIComponent component, Object value)
                                                                throws ValidatorException
   {
        String username = getUserUI().getLocalValue().toString();
        String password = value.toString();
       

        Alumno alumno = alumnoSession.isUserOK(username, password);
        if (alumno == null) 
        {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario nao e valido", "Usuario ou password nao e valido"));
        }
    }

    public String doLogOut() {
        setLoggedUser(null);
        return doCancel();
    }
    
    public String doCancel() {
        return "/index.faces";
    }
    

    public String doFinishLoginSystem() 
    {
        
        loggedUser = alumnoSession.isUserOK(getSelectedBean().getLogin() 
                , getSelectedBean().getPassword());
        String url = getRedirectURLOnLogin();
        if (url != null) 
        {
            setRedirectURLOnLogin(null);
            return url;
        } else {
            return "/index.faces";
        }
    }

   
    public String getRedirectURLOnLogin()
    {
        return redirectURLOnLogin;
    }
    
    
    public void setRedirectURLOnLogin(String redirectURLOnLogin)
    {
        this.redirectURLOnLogin = redirectURLOnLogin;
    }
    
    
    public Alumno getLoggedUser() {
        return loggedUser;
    }

    /**
     * @param loggedUser the loggedUser to set
     */
    public void setLoggedUser(Alumno loggedUser) {
        this.loggedUser = loggedUser;
    }

    
    public boolean isUserLogged() 
    {
        return loggedUser != null;
    }
    
    public String doLoginSystem() {
        setSelectedBean(new Alumno());
        return "/login.faces";
    }

    /**
     * @return the userUI
     */
    public UIInput getUserUI() {
        return userUI;
    }

    /**
     * @param userUI the userUI to set
     */
    public void setUserUI(UIInput userUI) {
        this.userUI = userUI;
    }

    /**
     * @return the passwordUI
     */
    public UIInput getPasswordUI() {
        return passwordUI;
    }

    /**
     * @param passwordUI the passwordUI to set
     */
    public void setPasswordUI(UIInput passwordUI) {
        this.passwordUI = passwordUI;
    }
    

}
