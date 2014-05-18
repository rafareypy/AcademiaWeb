package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.Objetivo;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(Evaluacion.class)
public class Evaluacion_ { 

    public static volatile SingularAttribute<Evaluacion, Double> bazoIzquierdo;
    public static volatile SingularAttribute<Evaluacion, Double> circunferenciaToracica;
    public static volatile SingularAttribute<Evaluacion, Date> fecha;
    public static volatile SingularAttribute<Evaluacion, Objetivo> objetivo;
    public static volatile SingularAttribute<Evaluacion, Double> bazoDerecho;
    public static volatile SingularAttribute<Evaluacion, Double> piernaDerecha;
    public static volatile SingularAttribute<Evaluacion, Double> pantorillaEzquierda;
    public static volatile SingularAttribute<Evaluacion, Alumno> alumno;
    public static volatile SingularAttribute<Evaluacion, Double> circunferenciaAbdominal;
    public static volatile SingularAttribute<Evaluacion, Integer> id;
    public static volatile SingularAttribute<Evaluacion, Double> peso;
    public static volatile SingularAttribute<Evaluacion, Double> piernaIzquierda;
    public static volatile SingularAttribute<Evaluacion, Double> imc;
    public static volatile SingularAttribute<Evaluacion, Double> talha;
    public static volatile SingularAttribute<Evaluacion, Double> pantorillaDerecha;

}