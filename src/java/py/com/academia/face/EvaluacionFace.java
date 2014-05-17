package py.com.academia.face;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.Evaluacion;
import py.com.academia.beans.Objetivo;
import py.com.academia.session.EvaluacionSession ;





@Named
@SessionScoped  //@RequestScoped verificar
public class EvaluacionFace extends BaseCDIBean<Evaluacion> {

    private static final long serialVersionUID = 7L;
    @Inject
    private EvaluacionSession bean;
    

    private List<Evaluacion> list;

    public EvaluacionFace() 
    {
        setSelectedBean(new Evaluacion());
    }

    public String doEditEvaluacion()
    {
        return "/admin/evaluacion/edit.faces";
    }

    public String doFinishEditEvaluacion() 
    {
        bean.setEvaluacion(getSelectedBean());
        return doListEvaluacion();
    }
    public String doRemoveEvaluacion() 
    {
        bean.removeEvaluacion(getSelectedBean());
        return doListEvaluacion();
    }

    public String doCreateEvaluacion() {
        setSelectedBean(new Evaluacion());
        return "/admin/evaluacion/add.faces";
    }

    public String doFinishCreateEvaluacion() {
        bean.saveEvaluacion(getSelectedBean());
        return doListEvaluacion();
    }

    public String doListEvaluacion() {
        list = bean.getAllEvaluaciones();
        return "/admin/evaluacion/list.faces";
    }
    
    public String doBuscarEvavuacionProAlumno()
    {
        return "/admin/evaluacion/buscar_evaluaciones_por_alumno.faces";
    }
    
    public String doListEvaluacionPorAlumno() {
        list = bean.getAllEvaluacionesPorAlumno(getSelectedBean().getAlumno());
        return "/admin/evaluacion/list.faces";
    }
    
    public List<Evaluacion> getListEvaluacion() 
    {
        return bean.getAllEvaluaciones();
    }

    public List<Objetivo> getListObjetivos() 
    {
        return bean.getAllObjetivos();
    }

    public List<Alumno> getListAlumnos() 
    {
        return bean.getAllAlumnos();
    }
    
    public List<Evaluacion> getList() {
        return list;
    }

    public void setList(List<Evaluacion> list) {
        this.list = list;
    }
    

    
    
    
}
