/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.academia.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import py.com.academia.ManualCDILookup;
import py.com.academia.beans.NivelUsuario;
import py.com.academia.beans.Pais;
import py.com.academia.session.PaisSession;


@FacesConverter(value="paisConverter")
public class PaisConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            PaisSession bean = getFacadeWithJNDI(PaisSession.class);
            Pais cat = bean.getPaisByDescripcion(value);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Pais cat = (Pais)value;
           return cat.getDescripcion();
        }
        return null;
    }

}
