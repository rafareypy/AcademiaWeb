/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.face;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Named
@SessionScoped
public class AdminLoginFace extends BaseCDIBean {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "O campo login não pode ser nulo")
    private String login;
    @NotNull(message = "O campo senha não pode ser nulo")
    @Min(value=5,message="The password need higher than five letters")
    private String password;
    private boolean adminUserLogged = false;

    public String doLogin()
    {
        if (getLogin().equalsIgnoreCase("rafa") && getPassword().equals("1234")) 
        {
            adminUserLogged = true;
        }
        else 
        {
            adminUserLogged = true;

        }
        
        if (isAdminUserLogged()) 
        {
            return "main.faces";

        }
        else 
        {            
            return "index.faces";
        }
        
    }

    
    public String doLogout()
    {
        adminUserLogged = false;
        return "/index.faces";
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminUserLogged() {
        return adminUserLogged;
    }
}
