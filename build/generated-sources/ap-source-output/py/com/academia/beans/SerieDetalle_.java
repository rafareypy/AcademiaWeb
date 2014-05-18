package py.com.academia.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Serie;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(SerieDetalle.class)
public class SerieDetalle_ { 

    public static volatile SingularAttribute<SerieDetalle, Integer> id;
    public static volatile SingularAttribute<SerieDetalle, Serie> serie;
    public static volatile SingularAttribute<SerieDetalle, AparatoMusculacion> aparatoMusculacion;
    public static volatile SingularAttribute<SerieDetalle, Ejercicio> ejercicio;
    public static volatile SingularAttribute<SerieDetalle, String> nroSeries;

}