package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.ItemVenta;
import py.com.academia.beans.enums.StatusVentaType;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> id;
    public static volatile SingularAttribute<Venta, Double> total;
    public static volatile ListAttribute<Venta, ItemVenta> itensVenta;
    public static volatile SingularAttribute<Venta, Date> fechaVenta;
    public static volatile SingularAttribute<Venta, StatusVentaType> statusVentaType;

}