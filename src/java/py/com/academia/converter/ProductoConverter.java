/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.academia.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import py.com.academia.ManualCDILookup;
import py.com.academia.beans.Producto;
import py.com.academia.execao.ExceptionParametroNulo;
import py.com.academia.session.ProductoSession;


@FacesConverter(value="productoConverter")
public class ProductoConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            ProductoSession bean = getFacadeWithJNDI(ProductoSession.class);
            Producto cat = null;
            try {
                cat = bean.getProductoById(Integer.valueOf(value));
            } catch (ExceptionParametroNulo ex) {
                Logger.getLogger(ProductoConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Producto cat = (Producto)value;
           return String.valueOf(cat.getId()) ;
        }
        return null;
    }

}
