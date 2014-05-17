package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.Pais;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile SingularAttribute<Ciudad, Integer> id;
    public static volatile SingularAttribute<Ciudad, Pais> pais;
    public static volatile SingularAttribute<Ciudad, String> descripcion;

}