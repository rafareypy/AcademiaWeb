package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.Producto;
import py.com.academia.beans.Venta;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(ItemVenta.class)
public class ItemVenta_ { 

    public static volatile SingularAttribute<ItemVenta, Integer> id;
    public static volatile SingularAttribute<ItemVenta, Producto> producto;
    public static volatile SingularAttribute<ItemVenta, Integer> qnt;
    public static volatile SingularAttribute<ItemVenta, Double> valorVenta;
    public static volatile SingularAttribute<ItemVenta, Venta> venta;

}