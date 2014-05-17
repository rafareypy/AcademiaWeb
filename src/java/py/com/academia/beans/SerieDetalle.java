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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class SerieDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    
    @NotNull
    @Column(nullable=false)
    @XmlElement    
    private String nroSeries;    
    
    @NotNull(message = "Falta agregar un Aparato de Musculacion!!! ")        
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(referencedColumnName="id",columnDefinition="integer")
    @Valid
    @XmlElement
    private AparatoMusculacion aparatoMusculacion ;  
    
    @NotNull(message = "Falta agregar un Ejercicio !!! ")        
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(referencedColumnName="id",columnDefinition="integer")
    @Valid
    @XmlElement
    private Ejercicio ejercicio ;  
        
    
    @Valid
    @NotNull
    @ManyToOne( cascade = CascadeType.MERGE, optional=false)
    @JoinColumn(referencedColumnName="id")    
    private Serie serie ;
        

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
        if (!(object instanceof SerieDetalle)) {
            return false;
        }
        SerieDetalle other = (SerieDetalle) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.SerieDetalle[ id=" + getId() + " ]";
    }

   
    public String getNroSeries() {
        return nroSeries;
    }

    
    public void setNroSeries(String nroSeries) {
        this.nroSeries = nroSeries;
    }

    /**
     * @return the aparatoMusculacion
     */
    public AparatoMusculacion getAparatoMusculacion() {
        return aparatoMusculacion;
    }

    /**
     * @param aparatoMusculacion the aparatoMusculacion to set
     */
    public void setAparatoMusculacion(AparatoMusculacion aparatoMusculacion) {
        this.aparatoMusculacion = aparatoMusculacion;
    }

    /**
     * @return the ejercicio
     */
    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    /**
     * @param ejercicio the ejercicio to set
     */
    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    
}
