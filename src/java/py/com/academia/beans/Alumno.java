/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Alumno implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	
        @NotNull(message = "Por Favor agregue duna fecha!!! ")
        @Temporal(javax.persistence.TemporalType.DATE)
        @Column(nullable=false)
	private Date 		fechaMatricula ;
    

//        @NotNull(message = "Descripcion esta vacio !!! ")        
//        @ManyToOne(cascade={CascadeType.ALL})
//        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
////        @Valid
//        @XmlElement
//	private Ciudad ciudad ;

        
        
//        @XmlElement
        @NotNull(message = "Cacmpo Nombre esta Nulo!!!")
        @NotEmpty(message = "Campo Nombre esta Vacio !!!")
        @Column(nullable=false,unique=true)
        private String  nombre ; 

        @NotNull(message = "Campo Login esta Nulo!!!")
        @NotEmpty(message = "Campo Login esta Vacio !!!")
        @Column(nullable=false,unique=true)
        private String  login ;         
        
        @NotNull(message = "Cacmpo Password esta Nulo!!!")
        @NotEmpty(message = "Campo Passwor esta Vacio !!!")
        @Column(nullable=false,unique=true)
        private String  password ;         
        
       @NotNull(message = "Campo Email  esta Nulo!!!")
        @NotEmpty(message = "Campo Celular  esta Vacio !!!")
        @Column(nullable=false)
        @Email(message = "Por Favor ingresse un email valido")
//        @XmlElement
        private String  email ; 
       
     @NotNull(message = "Cacmpo Direccion esta Nulo!!!")
        @NotEmpty(message = "Campo Direccion esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElement
        private String  Direccion ;   
      

        @NotNull(message = "Cacmpo Barrio esta Nulo!!!")
        @NotEmpty(message = "Campo Barrio esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElement
        private String  Barrio ;    
        
        @NotNull(message = "Cacmpo Cedula esta Nulo!!!")
        @NotEmpty(message = "Campo Cedula esta Vacio !!!")
        @Column(nullable=false, unique = true)
//        @XmlElement
        private String  cedula ;          
                
        
        
        
        @NotNull(message = "Cacmpo Apellido esta Nulo!!!")
        @NotEmpty(message = "Campo Apellido esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElement
        private String  apelido ;   
        
        
        @NotNull(message = "Por Favor agregue duna fecha De Nacimiento !!! ")
        @Temporal(javax.persistence.TemporalType.DATE)
        @Column(nullable=false)
	private Date 		fechaNascimiento ;   
        
      @NotNull(message = "Cacmpo Linea Baja esta Nulo!!!")
        @NotEmpty(message = "Campo Linea Baja esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElementC
        private String  lineaBaja ; 
        
        
        @NotNull(message = "Cacmpo Celular  esta Nulo!!!")
        @NotEmpty(message = "Campo Celular  esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElement
        private String  celular ;   
        
        @NotNull(message = "Campo NombreCasoEemergencia  esta Nulo!!!")
        @NotEmpty(message = "Campo NombreCasoEemergencia  esta Vacio !!!")
        @Column(nullable=false)        
//        @XmlElement
        private String  nombreCasoEemergencia ; 

                
        @NotNull(message = "Campo parentesco  esta Nulo!!!")
        @NotEmpty(message = "Campo parentesco  esta Vacio !!!")
        @Column(nullable=false)        
//        @XmlElement
        private String  parentesco ;     
        

        @NotNull(message = "Campo telefono Parente  esta Nulo!!!")
        @NotEmpty(message = "Campo telefono Parente  esta Vacio !!!")
        @Column(nullable=false)
//        @XmlElement
        private String  telefonoParente ; 
        

        @NotNull(message = "Campo celuar Parente  esta Nulo!!!")
        @NotEmpty(message = "Campo celular Parente  esta Vacio !!!")
        @Column(nullable=false)      
//        @XmlElement
        private String  celularParente ;         
        
        @NotNull(message = "EstadoCivil esta vacio !!! ")        
        @ManyToOne(cascade={CascadeType.MERGE})
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
        @Valid
//        @XmlElement
	private EstadoCivil estadoCivil ;   
        
        @NotNull(message = "Genero esta vacio !!! ")            
        @ManyToOne(cascade={CascadeType.MERGE})
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
        @Valid()
//        @XmlElement
	private Genero genero ;  
   
        
    @OneToMany(cascade= CascadeType.MERGE)
    private List<Servicio> servicios = new LinkedList<Servicio>();        
    
    public void addServicio(Servicio servicio)
    {
        getServicios().add(servicio);
    }
        
    @OneToMany(cascade= CascadeType.MERGE)
    private List<Limitacion> limitaciones = new LinkedList<Limitacion>();
        
    public void addLimitacion(Limitacion limitacion1)
    {
        
        getLimitaciones().add(limitacion1);
    }        
    
    @OneToMany(cascade= CascadeType.MERGE, mappedBy="alumno")
    private List<Evaluacion> evaluaciones = new LinkedList<Evaluacion>();    
        
    public void addEvaluacion(Evaluacion evaluacion)
    {
        evaluacion.setAlumno(this);
        getEvaluaciones().add(evaluacion);
    }
    
    
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
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        if(apelido!=null)
            return apelido;
        return "py.com.academia.beans.Alumno[ id=" + getId() + " ]";
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
     * @return the fechaNascimiento
     */
    public Date getFechaNascimiento() {
        return fechaNascimiento;
    }

    /**
     * @param fechaNascimiento the fechaNascimiento to set
     */
    public void setFechaNascimiento(Date fechaNascimiento) {
        this.fechaNascimiento = fechaNascimiento;
    }

    /**
     * @return the fechaMatricula
     */
    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    /**
     * @param fechaMatricula the fechaMatricula to set
     */
    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
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
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the Barrio
     */
    public String getBarrio() {
        return Barrio;
    }

    /**
     * @param Barrio the Barrio to set
     */
    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
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
     * @return the lineaBaja
     */
    public String getLineaBaja() {
        return lineaBaja;
    }

    /**
     * @param lineaBaja the lineaBaja to set
     */
    public void setLineaBaja(String lineaBaja) {
        this.lineaBaja = lineaBaja;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the nombreCasoEemergencia
     */
    public String getNombreCasoEemergencia() {
        return nombreCasoEemergencia;
    }

    /**
     * @param nombreCasoEemergencia the nombreCasoEemergencia to set
     */
    public void setNombreCasoEemergencia(String nombreCasoEemergencia) {
        this.nombreCasoEemergencia = nombreCasoEemergencia;
    }

    /**
     * @return the parentesco
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * @param parentesco the parentesco to set
     */
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    /**
     * @return the telefonoParente
     */
    public String getTelefonoParente() {
        return telefonoParente;
    }

    /**
     * @param telefonoParente the telefonoParente to set
     */
    public void setTelefonoParente(String telefonoParente) {
        this.telefonoParente = telefonoParente;
    }

    /**
     * @return the celularParente
     */
    public String getCelularParente() {
        return celularParente;
    }

    /**
     * @param celularParente the celularParente to set
     */
    public void setCelularParente(String celularParente) {
        this.celularParente = celularParente;
    }

    /**
     * @return the genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * @return the estadoCivil
     */
    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

   

    /**
     * @return the limitaciones
     */
    public List<Limitacion> getLimitaciones() {
        return limitaciones;
    }

    /**
     * @param limitaciones the limitaciones to set
     */
    public void setLimitaciones(List<Limitacion> limitaciones) {
        this.limitaciones = limitaciones;
    }

    /**
     * @return the servicios
     */
    public List<Servicio> getServicios() {
        return servicios;
    }

    /**
     * @param servicios the servicios to set
     */
    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the evaluaciones
     */
    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    /**
     * @param evaluaciones the evaluaciones to set
     */
    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
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

  
   

  
}
