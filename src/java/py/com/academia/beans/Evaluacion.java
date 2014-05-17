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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author rafael
 */
@Entity
public class Evaluacion implements Serializable {
    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
        @NotNull(message = "Por Favor agregue una fecha!!! ")
        @Temporal(javax.persistence.TemporalType.DATE)
        @Column(nullable=false)
	private Date 		fecha ;
    

        @NotNull(message = "Alumno esta vacio !!! ")        
        @ManyToOne(cascade={CascadeType.MERGE})
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
        @Valid
//        @XmlElement
	private Alumno alumno ;

        
           
//        @XmlElement
        @NotNull(message = "Campo Peso esta Nulo!!!")        
        @Column(nullable=false)
        private Double peso ;    
        
//        @XmlElement
        @NotNull(message = "Campo Talha esta Nulo!!!")       
        @Column(nullable=false)
        private Double talha ;     //medida 
                
        
//        @XmlElement
        @NotNull(message = "Campo IMC esta Nulo!!!")      
        @Column(nullable=false)
        private Double imc ;    
        
//        @XmlElement
        @NotNull(message = "Campo Brazo Izquiedo esta Nulo!!!")        
        @Column(nullable=false)
        private Double bazoIzquierdo ;             
        
//        @XmlElement
        @NotNull(message = "Campo Brazo Derecho esta Nulo!!!")
        @Column(nullable=false)
        private Double bazoDerecho ;             
        
                
//        @XmlElement
        @NotNull(message = "Campo Pierna Ezquierda esta Nulo!!!")
        @Column(nullable=false)
        private Double piernaIzquierda ;             

                
//        @XmlElement
        @NotNull(message = "Campo Pierna Derecha esta Nulo!!!")
        @Column(nullable=false)
        private Double piernaDerecha ;             
                

                
//        @XmlElement
        @NotNull(message = "Campo Pantorrilla Ezquierda esta Nulo!!!")
        @Column(nullable=false)
        private Double pantorillaEzquierda ;             

                        
        
//        @XmlElement
        @NotNull(message = "Campo Pantorrilla Derecha esta Nulo!!!")
        @Column(nullable=false)
        private Double pantorillaDerecha ;             
                
//        @XmlElement
        @NotNull(message = "Campo Pantorrilla Derecha esta Nulo!!!")
        @Column(nullable=false)
        private Double circunferenciaAbdominal ;             
                
//        @XmlElement
        @NotNull(message = "Campo Toractica esta Nulo!!!")
        @Column(nullable=false)
        private Double circunferenciaToracica ;                             
                
        

        @NotNull(message = "Campo Objetivo esta vacio !!! ")        
        @ManyToOne(cascade={CascadeType.MERGE})
        @JoinColumn(referencedColumnName="id",columnDefinition="integer")
        @Valid
//        @XmlElement
	private Objetivo objetivo ;
        
        
        
    

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
        if (!(object instanceof Evaluacion)) {
            return false;
        }
        Evaluacion other = (Evaluacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.com.academia.beans.Evaluaciones[ id=" + id + " ]";
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the talha
     */
    public Double getTalha() {
        return talha;
    }

    /**
     * @param talha the talha to set
     */
    public void setTalha(Double talha) {
        this.talha = talha;
    }

    /**
     * @return the imc
     */
    public Double getImc() {
        return imc;
    }

    /**
     * @param imc the imc to set
     */
    public void setImc(Double imc) {
        this.imc = imc;
    }

    /**
     * @return the bazoIzquierdo
     */
    public Double getBazoIzquierdo() {
        return bazoIzquierdo;
    }

    /**
     * @param bazoIzquierdo the bazoIzquierdo to set
     */
    public void setBazoIzquierdo(Double bazoIzquierdo) {
        this.bazoIzquierdo = bazoIzquierdo;
    }

    /**
     * @return the bazoDerecho
     */
    public Double getBazoDerecho() {
        return bazoDerecho;
    }

    /**
     * @param bazoDerecho the bazoDerecho to set
     */
    public void setBazoDerecho(Double bazoDerecho) {
        this.bazoDerecho = bazoDerecho;
    }

    /**
     * @return the piernaIzquierda
     */
    public Double getPiernaIzquierda() {
        return piernaIzquierda;
    }

    /**
     * @param piernaIzquierda the piernaIzquierda to set
     */
    public void setPiernaIzquierda(Double piernaIzquierda) {
        this.piernaIzquierda = piernaIzquierda;
    }

    /**
     * @return the piernaDerecha
     */
    public Double getPiernaDerecha() {
        return piernaDerecha;
    }

    /**
     * @param piernaDerecha the piernaDerecha to set
     */
    public void setPiernaDerecha(Double piernaDerecha) {
        this.piernaDerecha = piernaDerecha;
    }

    /**
     * @return the pantorillaEzquierda
     */
    public Double getPantorillaEzquierda() {
        return pantorillaEzquierda;
    }

    /**
     * @param pantorillaEzquierda the pantorillaEzquierda to set
     */
    public void setPantorillaEzquierda(Double pantorillaEzquierda) {
        this.pantorillaEzquierda = pantorillaEzquierda;
    }

    /**
     * @return the pantorillaDerecha
     */
    public Double getPantorillaDerecha() {
        return pantorillaDerecha;
    }

    /**
     * @param pantorillaDerecha the pantorillaDerecha to set
     */
    public void setPantorillaDerecha(Double pantorillaDerecha) {
        this.pantorillaDerecha = pantorillaDerecha;
    }

    /**
     * @return the circunferenciaAbdominal
     */
    public Double getCircunferenciaAbdominal() {
        return circunferenciaAbdominal;
    }

    /**
     * @param circunferenciaAbdominal the circunferenciaAbdominal to set
     */
    public void setCircunferenciaAbdominal(Double circunferenciaAbdominal) {
        this.circunferenciaAbdominal = circunferenciaAbdominal;
    }

    /**
     * @return the circunferenciaToracica
     */
    public Double getCircunferenciaToracica() {
        return circunferenciaToracica;
    }

    /**
     * @param circunferenciaToracica the circunferenciaToracica to set
     */
    public void setCircunferenciaToracica(Double circunferenciaToracica) {
        this.circunferenciaToracica = circunferenciaToracica;
    }

    /**
     * @return the objetivo
     */
    public Objetivo getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }
    
}
