package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.SerieDetalle;
import py.com.academia.beans.enums.DiaSemana;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-15T20:44:37")
@StaticMetamodel(Serie.class)
public class Serie_ { 

    public static volatile SingularAttribute<Serie, Integer> id;
    public static volatile ListAttribute<Serie, SerieDetalle> serieDetalles;
    public static volatile SingularAttribute<Serie, DiaSemana> diaSemana;
    public static volatile SingularAttribute<Serie, Date> fechaFin;
    public static volatile SingularAttribute<Serie, Alumno> alumno;
    public static volatile SingularAttribute<Serie, Date> fechaInicio;

}