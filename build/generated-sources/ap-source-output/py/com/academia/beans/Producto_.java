package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.ItemVenta;
import py.com.academia.beans.enums.TipoProducto;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Integer> id;
    public static volatile SingularAttribute<Producto, String> spec;
    public static volatile SingularAttribute<Producto, TipoProducto> tipoProducto;
    public static volatile ListAttribute<Producto, ItemVenta> itensVenta;
    public static volatile SingularAttribute<Producto, String> name;
    public static volatile SingularAttribute<Producto, Double> valorVenta;
    public static volatile SingularAttribute<Producto, Double> cost;

}