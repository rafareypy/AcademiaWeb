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
import py.com.academia.session.UsuarioSession;


@FacesConverter(value="nivelConverter")
public class NivelConverter extends ManualCDILookup implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) 
        {
            UsuarioSession bean = getFacadeWithJNDI(UsuarioSession.class);
            NivelUsuario cat = bean.getNivelUsuarioByDescripcion(value);
            return cat;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) 
        {
           NivelUsuario cat = (NivelUsuario)value;
           return cat.getDescripcion();
        }
        return null;
    }

}
