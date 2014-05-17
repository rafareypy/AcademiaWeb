/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author rafael
 */
@Entity
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    
    @NotNull(message = "Cacmpo Descripcion de Genero esta Nulo!!!")
    @NotEmpty(message = "Campo Descripcion de Genero esta Vacio !!!")
    @Column(length = 60, nullable = false, unique = true)
    private String  descripcion ;
    
    
    @NotNull(message = "Tines que ingressar un valor para el Servicio !!!")
    @Column(nullable=false)
//    @XmlElement
    @Min(0)
    private Double  valorServicio ;  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Genero[ id=" + id + " ]";
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the valorServicio
     */
    public Double getValorServicio() {
        return valorServicio;
    }

    /**
     * @param valorServicio the valorServicio to set
     */
    public void setValorServicio(Double valorServicio) {
        this.valorServicio = valorServicio;
    }

    /**
     * @return the alumnos
     
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    
    public List<PlanDePago> getPlanDePagos() {
        return planDePagos;
    }

    
    public void setPlanDePagos(List<PlanDePago> planDePagos) {
        this.planDePagos = planDePagos;
    }
    
     */
}
