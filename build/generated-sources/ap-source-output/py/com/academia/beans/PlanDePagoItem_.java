package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.PlanDePago;
import py.com.academia.beans.Servicio;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(PlanDePagoItem.class)
public class PlanDePagoItem_ { 

    public static volatile SingularAttribute<PlanDePagoItem, Integer> id;
    public static volatile SingularAttribute<PlanDePagoItem, Servicio> servicio;
    public static volatile SingularAttribute<PlanDePagoItem, PlanDePago> planDePago;

}