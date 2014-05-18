package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.PlanDePago;
import py.com.academia.beans.enums.EstadoCuota;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(CuotaPlanDePago.class)
public class CuotaPlanDePago_ { 

    public static volatile SingularAttribute<CuotaPlanDePago, Integer> id;
    public static volatile SingularAttribute<CuotaPlanDePago, PlanDePago> planDePago;
    public static volatile SingularAttribute<CuotaPlanDePago, EstadoCuota> estadoCuota;
    public static volatile SingularAttribute<CuotaPlanDePago, Double> valor;
    public static volatile SingularAttribute<CuotaPlanDePago, Date> fechaVencimiento;
    public static volatile SingularAttribute<CuotaPlanDePago, Date> fechaPago;

}