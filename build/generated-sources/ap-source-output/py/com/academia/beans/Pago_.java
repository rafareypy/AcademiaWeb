package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Integer> id;
    public static volatile SingularAttribute<Pago, Double> descuento;
    public static volatile SingularAttribute<Pago, Double> valorPagado;
    public static volatile SingularAttribute<Pago, String> descripcion;
    public static volatile SingularAttribute<Pago, Date> fechaPago;

}