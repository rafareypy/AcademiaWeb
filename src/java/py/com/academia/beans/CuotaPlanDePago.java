/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import py.com.academia.beans.enums.EstadoCuota;


/**
 *
 * @author rafael
 */
@Entity
public class CuotaPlanDePago implements Serializable {
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
    
    @NotNull(message = "Tines que ingressar un valor para el Plan !!!")
    @Column(nullable=false)
//    @XmlElement
    @Min(0)
    private Double  valor ;   
    
    
    @NotNull(message = "Plan de Pago esta vacio !!! ")        
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(referencedColumnName="id",columnDefinition="integer")
    @Valid
//    @XmlElement
    private PlanDePago planDePago ;       
    
    @NotNull(message = "Por Favor agregue duna fecha!!! ")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date 		fechaVencimiento ;
    
       
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=true)
    private Date 		fechaPago ;
    
    @Column(nullable=false)
    @Enumerated(EnumType.ORDINAL)   
    private EstadoCuota estadoCuota = EstadoCuota.PENDIENTE;
    
    public void addPlanDePago(PlanDePago dePago)
    {
        dePago.getCuotasPlanPago().add(this);
        setPlanDePago(planDePago);        
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
        if (!(object instanceof CuotaPlanDePago)) {
            return false;
        }
        CuotaPlanDePago other = (CuotaPlanDePago) object;
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
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
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

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    
    
    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the estadoCuota
     */
    public EstadoCuota getEstadoCuota() {
        return estadoCuota;
    }

    /**
     * @param estadoCuota the estadoCuota to set
     */
    public void setEstadoCuota(EstadoCuota estadoCuota) {
        this.estadoCuota = estadoCuota;
    }

    

 
    
}
