package py.com.academia.face;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.beans.Ejercicio;
import py.com.academia.beans.Serie;
import py.com.academia.beans.SerieDetalle;
import py.com.academia.beans.enums.DiaSemana;
import py.com.academia.session.SerieSession;


@Named
@SessionScoped  //@RequestScoped verificar
public class SerieDetalleFace extends BaseCDIBean<SerieDetalle> {

    private static final long serialVersionUID = 7L;
    @Inject
    private SerieSession bean;

    private Alumno alumno ;
    
    private List<Serie> listSeriePorAlumno;
    
    private Integer itemId ;
   
    private Serie serie ;
    
    private String q  = "" ;
    
    private UIInput textUI;

    private List<SerieDetalle> list;
    
    private List<SerieDetalle> listTemporal;

    private List<Serie> listSerie;
    
    private List<Serie> listSerieTemporal;
    
    private List<DiaSemana> listaDiasSemana = new LinkedList<DiaSemana>();
    
    
    public SerieDetalleFace() 
    {       
        setSerie(new Serie());
        setSelectedBean(new SerieDetalle());
        inicio();
    }

    public String doEditSerieDetalle()
    {
        return "/admin/serie_detalle/edit.faces";
    }

    public String doFinishEditSerieDetalle() 
    {
        bean.setSerieDetalle(getSelectedBean());
        return doListSerieDetalle();
    }
    public String doRemoveSerieDetalle() 
    {
       
        String id = (String) FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("id");

        
        System.out.println("idPassado "  + id);
        
        Integer idInteger = Integer.valueOf(id);
        if(bean == null){
            System.out.println("bean esta nulo");
        }
        if(idInteger == null){
            System.out.println("Id Esta nulo");
        }
        
        
        SerieDetalle sd = bean.getSerieDetalleById(idInteger);
        
        Integer idSerie = sd.getSerie().getId();
        
        bean.removeSerieDetalle(sd);
        
        Serie serie = bean.getSerieById(idSerie);
        
        setSerie(serie);
                
        setListTemporal(new ArrayList<SerieDetalle>()) ;
                
        setListTemporal(getListaSeriesPorSerieID(getSerie()));
        
        setSelectedBean(new SerieDetalle());
        
        return doVariosSerieDetalle();        
    }
    
    public String posicao()
    {
        
     
        
        if( listTemporal == null )
        {
            System.out.println(" ojo Lista Temporal se Nula");            
        }
        
        if(listTemporal != null  )
        {
            System.out.println("ojo Lista Temporal no es nula  e tamanho = " + listTemporal.size() );            
        }
        
        if(listTemporal != null )
        {
            if(!listTemporal.isEmpty())
            {
                System.out.println("ojoESta vacia !!!");
                q = listTemporal.get(0).getNroSeries();
            }
        }
        
        return q;
        
    }
    
    
    public String doVariosSerieDetallePorID() 
    {               
        String id = (String) FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("id");
        
        setSerie(bean.getSerieById(Integer.valueOf(id)));
                
        setListTemporal(new ArrayList<SerieDetalle>()) ;
                
        setListTemporal(getListaSeriesPorSerieID(getSerie()));
        
        setSelectedBean(new SerieDetalle());
        
        return doVariosSerieDetalle();                
    }
    
    
    public String doVariosSerieDetalle() 
    {               
        return "/admin/serie_detalle/varios.faces";
    }

    
    
    public String doCreateSerieDetalle() {
        setSelectedBean(new SerieDetalle());
        setSerie(new Serie());
        return "/admin/serie_detalle/add.faces";
    }

    public String doFinishCreateSerieDetalle() {
        getSelectedBean().setSerie(getSerie());
        getSelectedBean().getSerie().setFechaFin(new Date());
        getSelectedBean().getSerie().setFechaInicio(new Date());
        
        SerieDetalle sd = bean.saveSerieDetalle(getSelectedBean());
        
        setSerie(sd.getSerie());
                
        setListTemporal(new ArrayList<SerieDetalle>()) ;
                
        setListTemporal(getListaSeriesPorSerieID(getSerie()));
        
        setQ(posicao());
        
        setSelectedBean(new SerieDetalle());
        
        return doVariosSerieDetalle();
    }

    public String doListSerieDetalle() {
        list = bean.getAllSerieDetalle();
        return "/admin/serie_detalle/list.faces";
    }

    
    //--------------------Serie por Alumno Logado ------------------------------
    
    public String doVariosSerieDetallePorIDEAlumnoLogado() 
    {               
        String id = (String) FacesContext.getCurrentInstance().getExternalContext()
            .getRequestParameterMap().get("id");
        
        setSerie(bean.getSerieById(Integer.valueOf(id)));
                
        setListTemporal(new ArrayList<SerieDetalle>()) ;
                
        setListTemporal(getListaSeriesPorSerieID(getSerie()));
        
        setSelectedBean(new SerieDetalle());
        
        return doVariosSerieDetallePorAlumnoLogado();   
        
    }    
    
    
    
    public List<Alumno> getAllAlumnos() 
    {
        return bean.getAllAlumnos();
    }            

    public List<Serie> getAllSeries() 
    {
        return bean.getAllSeries();
    }            
    
    
    public List<AparatoMusculacion> getAllAparatoMusculacion() 
    {
        return bean.getAllAparatoMusculacion();
    }                
    
    public List<Ejercicio> getAllEjercicios() 
    {
        return bean.getAllEjercicio();
    }                
    
    
    public List<SerieDetalle> getListSerieDetalle() 
    {
        return bean.getAllSerieDetalle();
    }
    
    public List<Serie> getListSerie() 
    {
        return bean.getAllSeries();
    }
        
    
    
    public List<SerieDetalle> getList() {
        return list;
    }

    public void setList(List<SerieDetalle> list) {
        this.list = list;
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    /**
     * @return the listTemporal
     */
    public List<SerieDetalle> getListTemporal() {
        return listTemporal;
    }

    /**
     * @param listTemporal the listTemporal to set
     */
    public void setListTemporal(List<SerieDetalle> listTemporal) {
        this.listTemporal = listTemporal;
    }

    /**
     * @return the q
     */
    public String getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(String q) {
        this.q = q;
    }

    private List<SerieDetalle> getListaSeriesPorSerieID(Serie serie) 
    {
        return bean.getListSerieDetallePorSerieId(serie);
    }

    /**
     * @return the textUI
     */
    public UIInput getTextUI() {
        return textUI;
    }

    /**
     * @param textUI the textUI to set
     */
    public void setTextUI(UIInput textUI) {
        this.textUI = textUI;
    }

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    

    
    public String doListSeriePorAlumno() 
    {
        
        Alumno alumno = null ;
        try 
        {
            alumno = getSerie().getAlumno();
            setAlumno(alumno);
        }
        catch (NullPointerException e) {            }
        
       
        setListSerie(null);
        
        setListSerie(bean.getListSeriePorAlumno(getAlumno()));
        
        return "/admin/serie/list.faces";
    }    
    
    
    public String doRemoveSerie() 
    {
        bean.removeSerie(getSerie());
        return doListSerie();
    }

    
     public String doCreateSerie() 
     {
         setSerie(new Serie());
        return "/admin/serie/add.faces";
    }
     
    public String doFinishCreateSerie() 
    {
        
        bean.saveSerie(getSerie());
        return doListSerie();
    }     
    
    public String doListSerie()
    {        
        return "/admin/serie/list.faces";
    }    
    
    
    public String doBuscaSeriePorAlumno() 
    {
        setListSeriePorAlumno(null);              
        return "/admin/serie/buscar_serie_por_alumno.faces";
    }    
    
    
    //-------------------------Serie -------------------------------------------
    
    
    
    public String doBuscaSeriePorAlumnoLogado() 
    {

        setListSeriePorAlumno(null);
        
        setListSeriePorAlumno(bean.getListSeriePorAlumno(getAlumno()));
        
        if(getAlumno() == null )
        {
            return doFaltaLogin();
        }
        
        return "listaSerie.faces";
    }    
    
    private String doVariosSerieDetallePorAlumnoLogado() 
    {
        return "detalle_serie_detalle_por_alumno.faces";    
    }

    

    
    
    
    
    public void setListSerie(List<Serie> listSerie) {
        this.listSerie = listSerie;
    }

    
    public List<Serie> getListSerieTemporal() {
        return listSerieTemporal;
    }

    public void setListSerieTemporal(List<Serie> listSerieTemporal) {
        this.listSerieTemporal = listSerieTemporal;
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the listSeriePorAlumno
     */
    public List<Serie> getListSeriePorAlumno() {
        return listSeriePorAlumno;
    }

    /**
     * @param listSeriePorAlumno the listSeriePorAlumno to set
     */
    public void setListSeriePorAlumno(List<Serie> listSeriePorAlumno) {
        this.listSeriePorAlumno = listSeriePorAlumno;
    }

    private String doFaltaLogin() 
    {
        return "falta_login.faces";
    }

    /**
     * @return the listaDiasSemana
     */
    public List<DiaSemana> getListaDiasSemana() {
        return listaDiasSemana;
    }

    private void inicio() 
    {
        getListaDiasSemana().add(DiaSemana.LUNES);
        getListaDiasSemana().add(DiaSemana.MARTES);
        getListaDiasSemana().add(DiaSemana.MIERCOLES);
        getListaDiasSemana().add(DiaSemana.JUEVES);
        getListaDiasSemana().add(DiaSemana.VIERNES);
        getListaDiasSemana().add(DiaSemana.SABADO);
        getListaDiasSemana().add(DiaSemana.DOMINGO);
        
    }

    
    
    
}
