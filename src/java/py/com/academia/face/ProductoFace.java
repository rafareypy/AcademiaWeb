package py.com.academia.face;

import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.com.academia.beans.Producto;
import py.com.academia.beans.enums.TipoProducto;
import py.com.academia.session.ProductoSession;




@Named
@SessionScoped  //@RequestScoped verificar
public class ProductoFace extends BaseCDIBean<Producto> {

    private static final long serialVersionUID = 7L;
    @Inject
    private ProductoSession bean;
    

    private List<Producto> list;
    
    
    private List<TipoProducto> listaTipoProducto = new LinkedList<TipoProducto>();
    

    public ProductoFace() 
    {        
        inicio();
    }

    public String doEditProducto()
    {
        return "/admin/producto/edit.faces";
    }

    public String doFinishEditProducto() 
    {
        getBean().setProducto(getSelectedBean());
        return doListProducto();
    }
    public String doRemoveProducto() 
    {
        getBean().removeProducto(getSelectedBean());
        return doListProducto();
    }

    public String doCreateProducto() {
        setSelectedBean(new Producto());
        return "/admin/producto/add.faces";
    }

    public String doFinishCreateProducto() {
        getBean().saveProducto(getSelectedBean());
        return doListProducto();
    }

    public String doListProducto() {
        setList(getBean().getAllProductos());
        return "/admin/producto/list.faces";
    }

    public List<Producto> getListProductos() 
    {
        return getBean().getAllProductos();
    }

    /**
     * @return the bean
     */
    public ProductoSession getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(ProductoSession bean) {
        this.bean = bean;
    }

    /**
     * @return the list
     */
    public List<Producto> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Producto> list) {
        this.list = list;
    }

    
    /**
     * @return the listaTipoProducto
     */
    public List<TipoProducto> getListaTipoProducto() {
        return listaTipoProducto;
    }

    /**
     * @param listaTipoProducto the listaTipoProducto to set
     */
    public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
        this.listaTipoProducto = listaTipoProducto;
    }
    

    private void inicio() 
    {
            getListaTipoProducto().add(TipoProducto.COMESTIBLE);
            getListaTipoProducto().add(TipoProducto.ROPAS);        
        setSelectedBean(new Producto());
//        setListCategoria(bean.getAllCategorias());
    }
    
    
    
    
    
    
}
