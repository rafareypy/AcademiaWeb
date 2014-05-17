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
import py.com.academia.beans.AparatoMusculacion;
import py.com.academia.session.AparatoMusculacionSession;


@FacesConverter(value="aparatoMusuclacionConverter")
public class AparatoMusculacionConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String descripcion) {
        if (descripcion != null) 
        {
            AparatoMusculacionSession bean = getFacadeWithJNDI(AparatoMusculacionSession.class);
            AparatoMusculacion cat = bean.getAparatoMusculacionByDescripcion(descripcion);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           AparatoMusculacion cat = (AparatoMusculacion)value;
           return cat.getDescripcion();
        }
        return null;
    }

}
