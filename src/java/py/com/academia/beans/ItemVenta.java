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


@Entity
public class ItemVenta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional=false)
    @JoinColumn(referencedColumnName="id")    
    private Venta venta ;
    
//    @Valid
    @NotNull()
    @ManyToOne(cascade = CascadeType.MERGE, optional=false)
    @JoinColumn(referencedColumnName="id")
    private Producto producto ;
    
    @Column(nullable=false)
    @Min(1)    
    private  int qnt ; //quantos itens
    
    @Column(nullable=false)
    @Min(0)    
    private  Double valorVenta ; //quantos itens
    
    public void addProducto(Producto prod)
    {
        this.setProducto(prod);
        this.setValorVenta(prod.getValorVenta());
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
        if (!(object instanceof ItemVenta)) {
            return false;
        }
        ItemVenta other = (ItemVenta) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.ItemVenta[ id=" + getId() + " ]";
    }

    /**
     * @return the venta
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;        
    }

    /**
     * @return the qnt
     */
    public int getQnt() {
        return qnt;
    }

    /**
     * @param qnt the qnt to set
     */
    public void setQnt(int qnt) {
        this.qnt = qnt;
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
