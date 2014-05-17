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
import py.com.academia.beans.Alumno;
import py.com.academia.session.AlumnoSession;



@FacesConverter(value="tipoProductoConverter")
public class TipoProductoConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            AlumnoSession bean = getFacadeWithJNDI(AlumnoSession.class);
            Alumno cat = bean.getAlumnoByName(value);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           Alumno cat = (Alumno)value;
           return cat.getNombre();
        }
        return null;
    }

}
