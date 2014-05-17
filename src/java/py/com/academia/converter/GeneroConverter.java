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
import py.com.academia.beans.Genero;
import py.com.academia.beans.Pais;
import py.com.academia.session.AlumnoSession;



@FacesConverter(value="generoConverter")
public class GeneroConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            AlumnoSession bean = getFacadeWithJNDI(AlumnoSession.class);
            Genero cat = bean.getGeneroByDescripcion(value);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Genero cat = (Genero)value;
           return cat.getDescripcion();
        }
        return null;
    }

}
