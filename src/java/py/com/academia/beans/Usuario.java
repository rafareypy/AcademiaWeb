/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    @NotNull(message = "Cacmpo Login esta Nulo!!!")
    @NotEmpty(message = "Campo Login esta Vacio !!!")
    @Column(length = 10,nullable=false,unique=true)
    private String login ;
    
    
    @NotNull(message = "Campo Pasword esta Nulo!!!")
    @NotEmpty(message = "Campo Pasword esta Vacio !!!")
    @Column(length = 10, nullable=false,unique=true)    
    private String password ;
    
    @NotNull(message = "Campo Nombre esta Nulo!!!")
    @NotEmpty(message = "Campo Nombre esta Vacio !!!")
    @Column(length = 60, nullable=false,unique=true)    
    private String nombre ;
    
    @NotNull(message = "Campo Apelido esta Nulo!!!")
    @NotEmpty(message = "Campo Apelido esta Vacio !!!")
    @Column(length = 60, nullable=false,unique=false)    
    private String apelido ;
    
    
    @NotNull(message = "Campo Apelido esta Nulo!!!")
    @NotEmpty(message = "Campo Apelido esta Vacio !!!")
    @Column(length = 23, unique=false)    
    @Email
    private String email ;
    
        
    @NotNull(message = "Falta Ingressar un Nivel de Usuario !!! ")            
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(referencedColumnName="id",columnDefinition="integer")
    @Valid()
    @XmlElement
    private NivelUsuario nivelUsuario ;      
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Usuario[ id=" + getId() + " ]";
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the nivelUsuario
     */
    public NivelUsuario getNivelUsuario() {
        return nivelUsuario;
    }

    /**
     * @param nivelUsuario the nivelUsuario to set
     */
    public void setNivelUsuario(NivelUsuario nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }
    
}
