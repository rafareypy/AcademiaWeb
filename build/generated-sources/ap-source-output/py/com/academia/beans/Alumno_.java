package py.com.academia.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.com.academia.beans.EstadoCivil;
import py.com.academia.beans.Evaluacion;
import py.com.academia.beans.Genero;
import py.com.academia.beans.Limitacion;
import py.com.academia.beans.Servicio;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-05-17T22:20:38")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, String> parentesco;
    public static volatile SingularAttribute<Alumno, Date> fechaNascimiento;
    public static volatile SingularAttribute<Alumno, String> telefonoParente;
    public static volatile SingularAttribute<Alumno, String> nombreCasoEemergencia;
    public static volatile SingularAttribute<Alumno, EstadoCivil> estadoCivil;
    public static volatile ListAttribute<Alumno, Limitacion> limitaciones;
    public static volatile ListAttribute<Alumno, Evaluacion> evaluaciones;
    public static volatile SingularAttribute<Alumno, String> lineaBaja;
    public static volatile SingularAttribute<Alumno, String> password;
    public static volatile SingularAttribute<Alumno, String> Barrio;
    public static volatile SingularAttribute<Alumno, String> Direccion;
    public static volatile SingularAttribute<Alumno, String> nombre;
    public static volatile SingularAttribute<Alumno, Integer> id;
    public static volatile ListAttribute<Alumno, Servicio> servicios;
    public static volatile SingularAttribute<Alumno, Genero> genero;
    public static volatile SingularAttribute<Alumno, String> email;
    public static volatile SingularAttribute<Alumno, Date> fechaMatricula;
    public static volatile SingularAttribute<Alumno, String> celularParente;
    public static volatile SingularAttribute<Alumno, String> login;
    public static volatile SingularAttribute<Alumno, String> cedula;
    public static volatile SingularAttribute<Alumno, String> apelido;
    public static volatile SingularAttribute<Alumno, String> celular;

}