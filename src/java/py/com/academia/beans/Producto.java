/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.academia.beans;

import com.sun.xml.bind.CycleRecoverable;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import py.com.academia.beans.enums.TipoProducto;

/**
 *
 * @author rafael
 */
@Entity
@Table
public class Producto implements Serializable, CycleRecoverable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Basic(optional = false)
//    @XmlElement
    private Integer id;
    @NotNull(message = "Por favor cargue un Nombre para el Producto")
    @NotEmpty
    @Column(unique = true, nullable = false)
//    @XmlElement
    private String name;
    @NotNull(message = "Por favor cargue un Costo para el Producto")
    @Column(nullable = false)
//    @XmlElement
    private Double cost;

    
    
    @Column(nullable = false)
    @NotNull(message = "Por favor cargue una especificacion para el Producto")
    @NotEmpty
//    @XmlElement
    private String spec;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
//    @XmlTransient
    private List<ItemVenta> itensVenta;

    
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoProducto tipoProducto = TipoProducto.ROPAS ;
    
    @Column(nullable = false)
    @NotNull(message = "Por favor cargue un Precio de Venta para el Producto")   
//    @XmlElement
    private Double valorVenta ;
    
   

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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName()+ " [" + getCost() + "]" ;        
    }

    @Override
    public Object onCycleDetected(Context cntxt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }


    /**
     * @return the spec
     */
    public String getSpec() {
        return spec;
    }

    /**
     * @param spec the spec to set
     */
    public void setSpec(String spec) {
        this.spec = spec;
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

 

    

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the valorVenta
     */
    public Double getValorVenta() {
        return valorVenta;
    }

    /**
     * @param valorVenta the valorVenta to set
     */
    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }
}
