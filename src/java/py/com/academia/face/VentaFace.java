package py.com.academia.face;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Venta;
import py.com.academia.beans.ItemVenta;
import py.com.academia.beans.Producto;
import py.com.academia.beans.enums.StatusVentaType;
import py.com.academia.beans.enums.TipoProducto;
import py.com.academia.execao.ExceptionFormatoFechaString;
import py.com.academia.session.ProductoSession;
import py.com.academia.session.VentaSession;
import py.com.academia.util.Util;




@Named
@SessionScoped  //@RequestScoped verificar
public class VentaFace extends BaseCDIBean<Venta> {

    private static final long serialVersionUID = 7L;
    @Inject
    private VentaSession bean;

    @Inject
    private ProductoSession productoSession;
    
    private String fechaInicioString ;
    
    private String fechaFinString ;
    
    private ItemVenta itemVenta = new ItemVenta();

    private List<ItemVenta> listItemVentaTemporario = new LinkedList<ItemVenta>();
    
    private List<Venta> listVenta;
    
    private String somaTotalVentaFormatado = "" ;
    
    private Double somaTotalVenta = 0.0 ;
    
    
    public VentaFace() 
    {
        inicio();
    }


    public String doCreateVenta() {
        setSelectedBean(new Venta());
        return "/admin/venta/add.faces";
    }

    public String doFinishCreateVenta() {
        getBean().saveVenta(getSelectedBean());
        return doVentaRopas();
    }

    
    public String doConsultarVentas()
    {
        return "/admin/venta/buscar_venta.faces";
    }
    
    public String doBuscarVentasPorFechas()
    {
        setListVenta(new LinkedList<Venta>());
        
        try 
        {   
            setListVenta(getBean().getVentasPorFechaYStatusVentaType(
                    Util.passaFechaStringToCalendar(getFechaInicioString())
                    , Util.passaFechaStringToCalendar(getFechaFinString())
                    , StatusVentaType.ACTIVA));
                    
        }
        catch (ExceptionFormatoFechaString ex) 
        {
            Logger.getLogger(VentaFace.class.getName()).log(Level.SEVERE, null, ex);

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro.", "Formato de Fechas Passado es Invalido \n"
                    + " Formato Correcto dd/MM/yyyy ");
            FacesContext.getCurrentInstance().addMessage(null, fm);           

            return doConsultarVentas();
        }
        
        return doListaVentas();                
    }

    public List<Venta> getListVentas() 
    {
        return getBean().getAllVentas();
    }

    
    public List<Producto> getListProductosRopas() 
    {
        return getProductoSession().getAllProductosPorTipoProducto(TipoProducto.ROPAS);
    }

    
    public List<Producto> getListProductosCOMESTIBLE() 
    {
        return getProductoSession().getAllProductosPorTipoProducto(TipoProducto.COMESTIBLE);
    }
    
    
    public String doAdicionarItem()
    {

       adicionarItem();
        return doVentaCOMESTIBLE();
        
    }
    

    
    public String doAdicionarItemROPA()
    {

        adicionarItem();
        return doVentaRopas();
        
    }
    
    private void adicionarItem()
    {
    
        Integer idVenta = null ;
        
        verificarSiHayVenta();
                                
            getItemVenta().setValorVenta(
                            getItemVenta().getProducto().getValorVenta()
                            * getItemVenta().getQnt());            
            getSelectedBean().addItem(getItemVenta());
            getSelectedBean().setFechaVenta(new Date());
            validarVenta(getSelectedBean());
        
        
        if(getSelectedBean().getId() == null)        
            idVenta =  getBean().saveVenta(getSelectedBean()).getId();                    
        else
            idVenta =  getBean().setVenta(getSelectedBean()).getId();            

        
        setSelectedBean(getBean().getVentaById(idVenta));
        setItemVenta(new ItemVenta());
        setListItemVentaTemporario(getSelectedBean().getItensVenta());

        
    }
    
    
    private String concluirProcesso(StatusVentaType statusVentaType)
    {
        if(getSelectedBean() != null && getSelectedBean().getId() != null)
        {
            Venta venta = getSelectedBean();
            venta.setStatusVentaType(statusVentaType);
            getBean().setVenta(venta);
            setSelectedBean(new Venta());
            setItemVenta(new ItemVenta());
            setListItemVentaTemporario(new LinkedList<ItemVenta>());
            setListVenta(new LinkedList<Venta>());
        }        
        return doCreateVenta();                
    }
    
    public String doConcluirVentaEnProcesso()
    {
        return concluirProcesso(StatusVentaType.ACTIVA);
    }
    
    public String doCancelarVentaEnProcesso()
    {
        return concluirProcesso(StatusVentaType.ANULADA);
        
    }
        
    
    
    public String doRemoveItem()
    {
        
       if(Boolean.FALSE.equals(getBean().removerItemVenta(getItemVenta()))) 
       {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro.", "No Fue Possible Eliminar el Item.");
            FacesContext.getCurrentInstance().addMessage(null, fm);           
       }else{
           setSelectedBean(getBean().getVentaById(getSelectedBean().getId()));
           setListItemVentaTemporario(getSelectedBean().getItensVenta());
       }
        
       return doVentaRopas();
    }
    
    /**
     * @return the bean
     */
    public VentaSession getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(VentaSession bean) {
        this.bean = bean;
    }

    /**
     * @return the itemVenta
     */
    public ItemVenta getItemVenta() {
        return itemVenta;
    }

    /**
     * @param itemVenta the itemVenta to set
     */
    public void setItemVenta(ItemVenta itemVenta) {
        this.itemVenta = itemVenta;
    }

    

    /**
     * @return the listVenta
     */
    public List<Venta> getListVenta() {
        return listVenta;
    }

    /**
     * @param listVenta the listVenta to set
     */
    public void setListVenta(List<Venta> listVenta) {
        this.listVenta = listVenta;
        calcularTotalVenta(listVenta);
    }

    public String doVentaRopas()
    {
        return "/admin/venta/vender_ropa.faces";        
    }

    public String doVentaCOMESTIBLE()
    {
        return "/admin/venta/vender_comida.faces";        
    }
    
    


    /**
     * @return the productoSession
     */
    public ProductoSession getProductoSession() {
        return productoSession;
    }

    /**
     * @param productoSession the productoSession to set
     */
    public void setProductoSession(ProductoSession productoSession) {
        this.productoSession = productoSession;
    }

    /**
     * @return the listItemVentaTemporario
     */
    public List<ItemVenta> getListItemVentaTemporario() {
        return listItemVentaTemporario;
    }

    /**
     * @param listItemVentaTemporario the listItemVentaTemporario to set
     */
    public void setListItemVentaTemporario(List<ItemVenta> listItemVentaTemporario) {
        this.listItemVentaTemporario = listItemVentaTemporario;
    }

    private void validarVenta(Venta venta) 
    {
    
        if(venta.getFechaVenta() == null ){
            enviaMsjErro("falta data");            
        }
        
    }

    private void enviaMsjErro(String msj) 
    {
        for (int i = 0; i < 30; i++) {
            System.out.println(msj);
        }
    }

    private void verificarSiHayVenta() 
    {
        
        if(getSelectedBean() != null && getSelectedBean().getId() != null)
        {
            enviaMsjErro("Objeto SelectBean no esta nulo Statous "+ getSelectedBean().getStatusVentaType());
            
            
            if( StatusVentaType.PROCESSO.equals(
                    getSelectedBean().getStatusVentaType())  )
            {
                enviaMsjErro("inserimos lista Temporaria");    
                setListItemVentaTemporario(getSelectedBean().getItensVenta());
            }
            else
            {
                enviaMsjErro("Inserimos nuevos objetos por que la factura en cuestion ya esta finalizado");
                insereNuevosObjetos();
            }
            
        }
        else
        {
            insereNuevosObjetos();
        }
        
        
    }

    private void inicio() 
    {
            verificarSiHayVenta();
    }

    private void insereNuevosObjetos() 
    {
            setSelectedBean(new Venta());            
            setListItemVentaTemporario(new LinkedList<ItemVenta>());    
    }

    private void msj(String msj) 
    {
        System.out.println(msj);
    }

    private String doListaVentas() 
    {        
        return "/admin/venta/list.faces";       
    }

    /**
     * @return the fechaInicioString
     */
    public String getFechaInicioString() {
        return fechaInicioString;
    }

    /**
     * @param fechaInicioString the fechaInicioString to set
     */
    public void setFechaInicioString(String fechaInicioString) {
        this.fechaInicioString = fechaInicioString;
    }

    /**
     * @return the fechaFinString
     */
    public String getFechaFinString() {
        return fechaFinString;
    }

    /**
     * @param fechaFinString the fechaFinString to set
     */
    public void setFechaFinString(String fechaFinString) {
        this.fechaFinString = fechaFinString;
    }

    private void calcularTotalVenta(List<Venta> listVenta)
    {
        
        Double vlr =  0.0 ;
        if(listVenta != null)
        {
            if(! listVenta.isEmpty())
            {
                for (Venta venta : listVenta) 
                {
                    try {
                         vlr = getSomaTotalVenta() ;
                    } catch (Exception e) {
                    }
                    setSomaTotalVenta(vlr + venta.getTotal());
                }
            }
        }
    }

    /**
     * @return the somaTotalVenta
     */
    public Double getSomaTotalVenta() {
        return somaTotalVenta;
    }

    /**
     * @param somaTotalVenta the somaTotalVenta to set
     */
    public void setSomaTotalVenta(Double somaTotalVenta) {
        this.somaTotalVenta = somaTotalVenta;
    }

    public String getSomaTotalVentaFormatado() {

        somaTotalVentaFormatado = "G$ ";
        
        if(somaTotalVenta != null)
        {
            try {
                somaTotalVentaFormatado += getSomaTotalVenta().toString() ;
            } catch (Exception e) 
            {
                System.out.println("getSomaTotalVentaFormatado \n "
                        + "Erro al Passar valores soma total ");
            }            
        }
        
        return somaTotalVentaFormatado;
    }

    /**
     * @param somaTotalVentaFormatado the somaTotalVentaFormatado to set
     */
    public void setSomaTotalVentaFormatado(String somaTotalVentaFormatado) {
        this.somaTotalVentaFormatado = somaTotalVentaFormatado;
    }


   
    
    
    

    
    
    
    
    
}
