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
import py.com.academia.beans.Objetivo;
import py.com.academia.session.ObjetivoSession;



@FacesConverter(value="objetivoConverter")
public class ObjetivoConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            ObjetivoSession bean = getFacadeWithJNDI(ObjetivoSession.class);
            Objetivo cat = bean.getObjetivoByDescripcion(value);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Objetivo cat = (Objetivo)value;
           return cat.getDescripcion();
        }
        return null;
    }

}
