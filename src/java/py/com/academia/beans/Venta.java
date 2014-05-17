/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import py.com.academia.beans.enums.StatusVentaType;

/**
 *
 * @author rafael
 */
@Entity
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull(message = "Falta Cargar Fecha para la Venta !!!")
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable=false)
    private Date fechaVenta = new Date() ;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @NotNull(message = "Falta cargar un status para esta venta")
    private StatusVentaType statusVentaType = StatusVentaType.PROCESSO; 

    @NotNull(message = "FAla un valor total para la venta")
    @Column(nullable=false)   
    @Min(0)    
    private Double total = 0.0;
       

    @OneToMany(cascade= CascadeType.ALL, mappedBy="venta")
    private List<ItemVenta> itensVenta = new LinkedList<ItemVenta>();
    
    
    
    
    public void addItem(ItemVenta itemVenta)
    {
        itemVenta.setVenta(this);
        getItensVenta().add(itemVenta);
        if( getTotal() == null )
        {
            setTotal(itemVenta.getProducto().getValorVenta());
        }
        else
        {
            setTotal(getTotal() +itemVenta.getProducto().getValorVenta() );            
            
        }
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
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Venta[ id=" + getId() + " ]";
    }

    /**
     * @return the fechaVenta
     */
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     * @param fechaVenta the fechaVenta to set
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * @return the statusVentaType
     */
    public StatusVentaType getStatusVentaType() {
        return statusVentaType;
    }

    /**
     * @param statusVentaType the statusVentaType to set
     */
    public void setStatusVentaType(StatusVentaType statusVentaType) {
        this.statusVentaType = statusVentaType;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the itensVenta
     */
    public List<ItemVenta> getItensVenta() {
        return itensVenta;
    }

    /**
     * @param itensVenta the itensVenta to set
     */
    public void setItensVenta(List<ItemVenta> itensVenta) {
        this.itensVenta = itensVenta;
    }

    
    
}
