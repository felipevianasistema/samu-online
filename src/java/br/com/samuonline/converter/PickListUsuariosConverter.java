package br.com.samuonline.converter;

import br.com.samuonline.modelo.UsuarioFuncionario;
import java.util.Iterator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "pickListUsuariosConverter")
public class PickListUsuariosConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Object ret = null;
        if (arg1 instanceof PickList) {
            Object dualList = ((PickList) arg1).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Iterator iterator = dl.getSource().iterator(); iterator
                    .hasNext();) {
                Object o = iterator.next();
                String id = (new StringBuilder()).append(
                        ((UsuarioFuncionario) o).getIdLoginFuncionario()).toString();
                if (arg2.equals(id)) {
                    ret = o;
                    break;
                }
            }

            if (ret == null) {
                for (Iterator iterator1 = dl.getTarget().iterator(); iterator1
                        .hasNext();) {
                    Object o = iterator1.next();
                    String id = (new StringBuilder()).append(
                            ((UsuarioFuncionario) o).getIdLoginFuncionario()).toString();
                    if (arg2.equals(id)) {
                        ret = o;
                        break;
                    }
                }

            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String str = "";
        if (arg2 instanceof UsuarioFuncionario) {
            str = String.valueOf(((UsuarioFuncionario) arg2).getIdLoginFuncionario());
        }
        return str;
    }

}
