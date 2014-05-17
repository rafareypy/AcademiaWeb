
package py.com.academia.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;



@Entity
public class PlanDePagoItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    
        @NotNull(message = "Servicio esta vacio !!! ")        
        @ManyToOne(cascade={CascadeType.MERGE})
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
        @Valid
//        @XmlElement
	private Servicio servicio ;
        
        
        
        @Valid
//        @XmlElement
        @NotNull(message = "Plan de Pago esta vacio !!! ")        
        @ManyToOne(cascade={CascadeType.MERGE}, optional = false)
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
	private PlanDePago planDePago ;
   
        
        public void addPlanDePago( PlanDePago dePago )
        {
            dePago.addPlanDePagoItem(this);
            setPlanDePago(dePago);
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanDePagoItem)) {
            return false;
        }
        PlanDePagoItem other = (PlanDePagoItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Ciudad[ id=" + id + " ]";
    }

   

   
    public Servicio getServicio() {
        return servicio;
    }

    
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the planDePago
     */
    public PlanDePago getPlanDePago() {
        return planDePago;
    }

    /**
     * @param planDePago the planDePago to set
     */
    public void setPlanDePago(PlanDePago planDePago) {
        this.planDePago = planDePago;
    }
    
}
