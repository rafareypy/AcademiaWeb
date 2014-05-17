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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import py.com.academia.beans.enums.DiaSemana;

/**
 *
 * @author rafael
 */
@Entity
public class Serie implements Serializable {
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
    
    
    @NotNull(message = "Pais esta vacio !!! ")        
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(referencedColumnName="id",columnDefinition="integer")
    @Valid
    @XmlElement
    private Alumno alumno ;
    
 
    @NotNull(message = "Por Favor agregue una fecha Inicio !!! ")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date fechaInicio ;    
    
    
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DiaSemana diaSemana = DiaSemana.LUNES ;
    
    
    
    
 
    @NotNull(message = "Por Favor agregue una fecha Final!!! ")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date fechaFin ;    
    
    @OneToMany(cascade= CascadeType.MERGE, mappedBy="serie")
    private List<SerieDetalle> serieDetalles = new LinkedList<SerieDetalle>();
    

    public void addSerie(SerieDetalle serieDetalle)
    {
        serieDetalle.setSerie(this);
        getSerieDetalles().add(serieDetalle);
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
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Genero[ id=" + getId() + " ]";
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the serieDetalles
     */
    public List<SerieDetalle> getSerieDetalles() {
        return serieDetalles;
    }

    /**
     * @param serieDetalles the serieDetalles to set
     */
    public void setSerieDetalles(List<SerieDetalle> serieDetalles) {
        this.serieDetalles = serieDetalles;
    }

    /**
     * @return the diaSemana
     */
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    /**
     * @param diaSemana the diaSemana to set
     */
    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

  
    
}
