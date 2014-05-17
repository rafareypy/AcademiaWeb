package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.CuotaPlanDePago;
import py.com.academia.beans.PlanDePagoItem;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(PlanDePago.class)
public class PlanDePago_ { 

    public static volatile SingularAttribute<PlanDePago, Integer> id;
    public static volatile SingularAttribute<PlanDePago, Integer> diaDoMesVenc;
    public static volatile ListAttribute<PlanDePago, CuotaPlanDePago> cuotasPlanPago;
    public static volatile SingularAttribute<PlanDePago, Double> valorFinal;
    public static volatile SingularAttribute<PlanDePago, String> descripcion;
    public static volatile ListAttribute<PlanDePago, PlanDePagoItem> PlanDePagoItens;
    public static volatile SingularAttribute<PlanDePago, Alumno> alumno;

}