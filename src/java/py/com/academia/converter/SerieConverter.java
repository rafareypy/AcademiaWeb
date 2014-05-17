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
import py.com.academia.beans.Serie;
import py.com.academia.session.SerieSession;


@FacesConverter(value="serieConverter")
public class SerieConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            SerieSession bean = getFacadeWithJNDI(SerieSession.class);
            Serie cat = bean.getSerieById(Integer.valueOf(value));
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Serie cat = (Serie)value;
           return String.valueOf(cat.getId()) ;
        }
        return null;
    }

}
