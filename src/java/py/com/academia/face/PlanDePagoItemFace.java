package py.com.academia.face;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Alumno;
import py.com.academia.beans.CuotaPlanDePago;
import py.com.academia.beans.PlanDePago;
import py.com.academia.beans.PlanDePagoItem;
import py.com.academia.beans.Serie;
import py.com.academia.beans.SerieDetalle;
import py.com.academia.beans.Servicio;
import py.com.academia.beans.enums.EstadoCuota;
import py.com.academia.execao.ExceptionEliminarObjeto;
import py.com.academia.execao.ExceptionFormatoFechaString;
import py.com.academia.session.PlanDePagoSession;
import py.com.academia.util.Util;

@Named
@SessionScoped  //@RequestScoped verificar
public class PlanDePagoItemFace extends BaseCDIBean<PlanDePagoItem> {

    private static final long serialVersionUID = 7L;
    @Inject
    private PlanDePagoSession bean;
    private PlanDePago planDePago;
    private Alumno alumno;
    private CuotaPlanDePago CuotaPlanDePago;
    private List<PlanDePagoItem> list;
    private List<CuotaPlanDePago> listaCuotaPlanDePago;
    private List<PlanDePagoItem> listaPlanDePagoItemTemporal;
    private List<PlanDePago> listaPlanDePago;
    private List<PlanDePago> listaPlanDePagoAlumnoLogado;
    private List<PlanDePagoItem> listaPlanDePagoItemAlumnoLogado;
    private String fechaInicioString ;
    private String fechaFinString ;
    
    
    public PlanDePagoItemFace() {
        setPlanDePago(new PlanDePago());
        setSelectedBean(new PlanDePagoItem());
    }

    public String doEditPlanDePagoItem() 
    {
        return "/admin/plan_de_pago/edit.faces";
    }

    public String doDescontarMensalidade() {

        CuotaPlanDePago cuotaPlanDePago =
                getBean().efetuarPagoCuotaPlanDePago(getCuotaPlanDePago());


        if (EstadoCuota.DESACTIVADO.equals(cuotaPlanDePago.getEstadoCuota())) {
        } else {
            System.out.println("Problemas al Desactivar La Mensalidad !!!");
        }


        return doListCuotasPlanPagoPorAlumno();


    }
    
//    public String doBuscarMensalidadesPorEstadoPorFechas()
//    {
//        setListaCuotaPlanDePago(new LinkedList<CuotaPlanDePago>());
//        
//        Calendar fechaIni = null ;
//        Calendar fechaFin = null ;
//        try 
//        {
//            fechaIni = Util.passaFechaStringToCalendar(getFechaInicioString());
//            fechaFin = Util.passaFechaStringToCalendar(getFechaInicioString());
//    
//            setListaCuotaPlanDePago(getBean().getListaCuotaPlanDePagoPorEstadoYFecha(fechaIni, fechaFin, EstadoCuota.CANCELADO));
//                    
//        }
//        catch (ExceptionFormatoFechaString ex) 
//        {
//            Logger.getLogger(VentaFace.class.getName()).log(Level.SEVERE, null, ex);
//
//            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro.", "Formato de Fechas Passado es Invalido \n"
//                    + " Formato Correcto dd/MM/yyyy ");
//            FacesContext.getCurrentInstance().addMessage(null, fm);           
//
//            return doConsultarCuotasPorFecha();
//        }
//        
//        return doListaCuotasPorFecha();                
//    }
        

    public String doReanudarMensalidade() {
        CuotaPlanDePago cuotaPlanDePago =
                getBean().ReanudarPagoCuotaPlanDePago(getCuotaPlanDePago());

        return doListCuotasPlanPagoPorAlumno();

    }

    public String doFinishEditPlanDePagoItem() {
        getBean().setPlanDePagoItem(getSelectedBean());
        return doListPlanDePagoItem();
    }

    public String doRemovePlanDePagoItem()  
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
        
        
        PlanDePagoItem pagoItem = bean.getPlanDePagoItemByID(idInteger);
        
        Integer idPlanDePago = pagoItem.getPlanDePago().getId();
        try 
        {            
            bean.removePlanDePagoItem(pagoItem);
            
            PlanDePago planDePago = bean.getPlanDePagoById(idPlanDePago);

            setPlanDePago(planDePago);

            setListaPlanDePagoItemTemporal(new ArrayList<PlanDePagoItem>()) ;

            setListaPlanDePagoItemTemporal(getListaPlanDePagoItemPorPlanDePagoID(planDePago));

            setSelectedBean(new PlanDePagoItem());

            
        
        }
        catch (ExceptionEliminarObjeto ex) 
        {
            Logger.getLogger(PlanDePagoItemFace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return doVariosDetallesPlanDePagoItem();
//        try {
//            
//            setPlanDePago(getSelectedBean().getPlanDePago()); 
//            getBean().removePlanDePagoItem(getSelectedBean());
//            
//            setPlanDePago(bean.getPlanDePagoById(getPlanDePago().getId()));
//            
//            return "/admin/plan_de_pago/list.faces";
//
//            
//            
//            
//        } catch (ExceptionEliminarObjeto ex) {
//            Logger.getLogger(PlanDePagoItemFace.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return doListPlanDePagoItem();
    }

    public String doCreatePlanDePagoItem() {
        setSelectedBean(new PlanDePagoItem());
        setPlanDePago(new PlanDePago());
        return "/admin/plan_de_pago/add.faces";
    }

    public String doFinishCreatePlanDePagoItem() {

        getSelectedBean().addPlanDePago(getPlanDePago());

        PlanDePagoItem dePagoItem = getBean().savePlanDePagoItem(getSelectedBean());
        setPlanDePago(dePagoItem.getPlanDePago());

        setListaPlanDePagoItemTemporal(new ArrayList<PlanDePagoItem>());

        setListaPlanDePagoItemTemporal(
                getBean().getListPlanDePagoItemPorPlanDePagoID(getPlanDePago()));

        setSelectedBean(new PlanDePagoItem());

        return doVariosPlanDePagoItem();

    }

    public String doBuscarCuotasPlanPagoPorAlumno() {
        return "/admin/mensalidades/mensalidade_por_alumno.faces";
    }

    public String doListCuotasPlanPagoPorAlumno() 
    {
        setListaCuotaPlanDePago(getBean().getListCuotaPlanDePagoPendientesPorAlumnoID(
                 getAlumno()));

        return "/admin/mensalidades/varios.faces";
    }

    
    
    public String doListCuotasPlanPago() {

        setListaCuotaPlanDePago(getBean().getListCuotaPlanDePago());

        return "/admin/mensalidades/list.faces";
    }

    private List<PlanDePagoItem> getListaPlanDePagoItemPorPlanDePagoID(PlanDePago dePago) {
        return getBean().getListPlanDePagoItemPorPlanDePagoID(dePago);
    }

    public String doListPlanDePagoItem() {
        setList(getBean().getAllPlanDePagoItem());
        return "/admin/plan_de_pago/list.faces";
    }

    private String doVariosPlanDePagoItem() {
        return "/admin/plan_de_pago/varios.faces";
    }

    public String doListPlanDePago() {
        setListaPlanDePago(getBean().getAllPlanDePagos());
        return "/admin/plan_de_pago/list.faces";
    }

    //---------------------Plan De Pago Alumno Logado --------------------------
    public String doBuscarPlanesPagoPorAlumnoLogado() 
    {
        if (getAlumno() == null) 
            return doFaltaLogin();
        
        setListaPlanDePagoAlumnoLogado(null);
        setListaPlanDePagoAlumnoLogado(getBean().getPlanDePagosPorAlumno(getAlumno()));
        
        return doVariosPlanDePagoPorAlumnoLogado();
        
    }

    private String doVariosPlanDePagoPorAlumnoLogado() 
    {
        return "list_plan_de_pago_por_alumno.faces";        
    }

    
    
   public String  doBuscarDetallesDePlanDePagoAlumnoLogado()
   {
   
       setListaPlanDePagoItemAlumnoLogado(getPlanDePago().getPlanDePagoItens());
       return doListaPlanDePagoItemTemporalAlumnoLogado();
       
   }
    
   
   
   public String doVariosDetallesPlanDePagoItem()
   {       
       return "varios_detalles_plan_pago_item.faces";       
   }
   
   
   
    public String doFinishCreatePlanDePagoItemAlumnoLogado() {

        getPlanDePago().setAlumno(getAlumno());
        getSelectedBean().addPlanDePago(getPlanDePago());

        PlanDePagoItem dePagoItem = getBean().savePlanDePagoItem(getSelectedBean());
        setPlanDePago(dePagoItem.getPlanDePago());

        setListaPlanDePagoItemAlumnoLogado(new ArrayList<PlanDePagoItem>());

        setListaPlanDePagoItemAlumnoLogado(
                getBean().getListPlanDePagoItemPorPlanDePagoID(getPlanDePago()));

        setSelectedBean(new PlanDePagoItem());

        return doListaPlanDePagoItemTemporalAlumnoLogado();

    }
   
    
    public String doListCuotasPlanPagoPorAlumnoLogado() 
    {
        
        if(getAlumno() == null )
            return doFaltaLogin();
        
        setListaCuotaPlanDePago(getBean().getListCuotaPlanDePagoPendientesPorAlumnoID(
                 getAlumno()));

        return "varios_mesalidades_por_alumno_logado.faces";
    }

   
   public String doCreatePlanDePagoItemAlumnoLogado()
   {
        setSelectedBean(new PlanDePagoItem());
        setPlanDePago(new PlanDePago());
        return "add_plan_de_pago_alumno_logado.faces";
   }
   
   public String doBuscarCuotasPlanPagoPorFecha()
   {
       setListaCuotaPlanDePago(new ArrayList<CuotaPlanDePago>());

        try 
        {            
            setListaCuotaPlanDePago(getBean().getListaCuotaPlanDePagoPorEstadoYFecha
                                (
                                Util.passaFechaStringToCalendar(getFechaInicioString())        
                                , Util.passaFechaStringToCalendar(getFechaFinString()),
                                EstadoCuota.CANCELADO));
            
        }
        catch (ExceptionFormatoFechaString ex) 
        {
            Logger.getLogger(VentaFace.class.getName()).log(Level.SEVERE, null, ex);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro.", "Formato de Fechas Passado es Invalido \n"
                    + " Formato Correcto dd/MM/yyyy ");
            FacesContext.getCurrentInstance().addMessage(null, fm);           

            return doBuscarCuotasPlanPagoPorFecha();
                        
        }
       
       return  doListarCuotasPlanPagoPorFecha();
       
   }
   
    private String doListaPlanDePagoItemTemporalAlumnoLogado()
    {    
        return "list_plan_de_pago_item_por_alumno.faces";
    }

    private String doListarCuotasPlanPagoPorFecha() 
    {
        return "/admin/mensalidades/list_consulta_cuota_por_fecha.faces";
    }
   
   
   
    
    public List<PlanDePago> getListPlanDePago() {
        return getBean().getAllPlanDePagos();
    }

    public List<Servicio> getListServicios() {
        return getBean().getAllServicios();
    }

    public List<PlanDePagoItem> getList() {
        return list;
    }

    public void setList(List<PlanDePagoItem> list) {
        this.list = list;
    }

    /**
     * @return the planDePago
     */
    public PlanDePago getPlanDePago() {
        return planDePago;
    }

    /**
     * @param planDePago the planDePago to set
     */
    public void setPlanDePago(PlanDePago planDePago) {
        this.planDePago = planDePago;
    }

    /**
     * @return the listaPlanDePago
     */
    public List<PlanDePago> getListaPlanDePago() {
        return listaPlanDePago;
    }

    /**
     * @param listaPlanDePago the listaPlanDePago to set
     */
    public void setListaPlanDePago(List<PlanDePago> listaPlanDePago) {
        this.listaPlanDePago = listaPlanDePago;
    }

    /**
     * @return the listaPlanDePagoItemTemporal
     */
    public List<PlanDePagoItem> getListaPlanDePagoItemTemporal() {
        return listaPlanDePagoItemTemporal;
    }

    /**
     * @param listaPlanDePagoItemTemporal the listaPlanDePagoItemTemporal to set
     */
    public void setListaPlanDePagoItemTemporal(List<PlanDePagoItem> listaPlanDePagoItemTemporal) {
        this.listaPlanDePagoItemTemporal = listaPlanDePagoItemTemporal;
    }

    /**
     * @return the listaCuotaPlanDePago
     */
    public List<CuotaPlanDePago> getListaCuotaPlanDePago() {
        return listaCuotaPlanDePago;
    }

    /**
     * @param listaCuotaPlanDePago the listaCuotaPlanDePago to set
     */
    public void setListaCuotaPlanDePago(List<CuotaPlanDePago> listaCuotaPlanDePago) {
        this.listaCuotaPlanDePago = listaCuotaPlanDePago;
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
     * @return the bean
     */
    public PlanDePagoSession getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(PlanDePagoSession bean) {
        this.bean = bean;
    }

    /**
     * @return the CuotaPlanDePago
     */
    public CuotaPlanDePago getCuotaPlanDePago() {
        return CuotaPlanDePago;
    }

    /**
     * @param CuotaPlanDePago the CuotaPlanDePago to set
     */
    public void setCuotaPlanDePago(CuotaPlanDePago CuotaPlanDePago) {
        this.CuotaPlanDePago = CuotaPlanDePago;
    }

    private String doFaltaLogin() {
        return "falta_login.faces";
    }

    /**
     * @return the listaPlanDePagoAlumnoLogado
     */
    public List<PlanDePago> getListaPlanDePagoAlumnoLogado() {
        return listaPlanDePagoAlumnoLogado;
    }

    /**
     * @param listaPlanDePagoAlumnoLogado the listaPlanDePagoAlumnoLogado to set
     */
    public void setListaPlanDePagoAlumnoLogado(List<PlanDePago> listaPlanDePagoAlumnoLogado) {
        this.listaPlanDePagoAlumnoLogado = listaPlanDePagoAlumnoLogado;
    }

    /**
     * @return the listaPlanDePagoItemAlumnoLogado
     */
    public List<PlanDePagoItem> getListaPlanDePagoItemAlumnoLogado() {
        return listaPlanDePagoItemAlumnoLogado;
    }

    /**
     * @param listaPlanDePagoItemAlumnoLogado the listaPlanDePagoItemAlumnoLogado to set
     */
    public void setListaPlanDePagoItemAlumnoLogado(List<PlanDePagoItem> listaPlanDePagoItemAlumnoLogado) {
        this.listaPlanDePagoItemAlumnoLogado = listaPlanDePagoItemAlumnoLogado;
    }

    /**
     * @return the fechaInicioString
     */
    public String getFechaInicioString() {
        return fechaInicioString;
    }

  
    public void setFechaInicioString(String fechaInicioString) {
        this.fechaInicioString = fechaInicioString;
    }


    public String getFechaFinString() {
        return fechaFinString;
    }

    public void setFechaFinString(String fechaFinString) {
        this.fechaFinString = fechaFinString;
    }

    public String doConsultarCuotasPorFecha() 
    {       
        return "/admin/mensalidades/buscar_mensalidades.faces";
    }

    public String doListaCuotasPorFecha()
    {
        return "/admin/mensalidades/list_consulta_cuota_por_fecha.faces";
    }

  


  
}
