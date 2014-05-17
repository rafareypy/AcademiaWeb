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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author rafael
 */
@Entity
public class PlanDePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    

    @NotNull(message = "Tines que ingressar un Dia Del Mes para vencimiento de La cuota !!!")
    @Column(nullable=false)
    @Min(1)
    @Max(0x1c)
    private Integer  diaDoMesVenc ;   
    
    
    
    @Valid
    @NotNull
    @JoinColumn(referencedColumnName="id")
    @ManyToOne(optional=false,cascade =  CascadeType.MERGE)    
//    @XmlElement
    private Alumno alumno ;    
    
    
    
    @Column(length = 60)
    private String  descripcion ;   
    
    
//    @NotNull(message = "Tines que ingressar un valor para el Plan !!!")
//    @Column(nullable=false)
//    @XmlElement
//    @Min(0)
//    private Double  soma ;   
    
//    @Column(nullable=true)
//    @XmlElement
//    @Min(0)
//    private Double  descuento ;   
    
    @NotNull(message = "Tines que ingressar un valor final para el Plan !!!")
    @Column(nullable=false)
//    @XmlElement
    @Min(0)
    private Double  valorFinal ;   
    
    
    @OneToMany(cascade= CascadeType.PERSIST, mappedBy="planDePago")
    private List<PlanDePagoItem> PlanDePagoItens = new LinkedList<PlanDePagoItem>();
    
    public void addPlanDePagoItem(PlanDePagoItem planDePagoItem)
    {
        planDePagoItem.setPlanDePago(this);
        getPlanDePagoItens().add(planDePagoItem);
    }
    
    
    @OneToMany(cascade= CascadeType.PERSIST, mappedBy="planDePago")
    private List<CuotaPlanDePago> cuotasPlanPago = new LinkedList<CuotaPlanDePago>();

    
    
    public void addCuota(CuotaPlanDePago cuotaPlanPago)
    {
        cuotaPlanPago.setPlanDePago(this);
        getCuotasPlanPago().add(cuotaPlanPago);
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
        if (!(object instanceof PlanDePago)) {
            return false;
        }
        PlanDePago other = (PlanDePago) object;
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
     * @return the PlanDePagoItens
     */
    public List<PlanDePagoItem> getPlanDePagoItens() {
        return PlanDePagoItens;
    }

    /**
     * @param PlanDePagoItens the PlanDePagoItens to set
     */
    public void setPlanDePagoItens(List<PlanDePagoItem> PlanDePagoItens) {
        this.PlanDePagoItens = PlanDePagoItens;
    }

    /**
     * @return the cuotasPlanPago
     */
    public List<CuotaPlanDePago> getCuotasPlanPago() {
        return cuotasPlanPago;
    }

    /**
     * @param cuotasPlanPago the cuotasPlanPago to set
     */
    public void setCuotasPlanPago(List<CuotaPlanDePago> cuotasPlanPago) {
        this.cuotasPlanPago = cuotasPlanPago;
    }

    /**
     * @return the valorFinal
     */
    public Double getValorFinal() {
        return valorFinal;
    }

    /**
     * @param valorFinal the valorFinal to set
     */
    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    /**
     * @return the diaDoMesVenc
     */
    public Integer getDiaDoMesVenc() {
        return diaDoMesVenc;
    }

    /**
     * @param diaDoMesVenc the diaDoMesVenc to set
     */
    public void setDiaDoMesVenc(Integer diaDoMesVenc) {
        this.diaDoMesVenc = diaDoMesVenc;
    }

  
}
