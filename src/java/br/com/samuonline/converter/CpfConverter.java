package br.com.samuonline.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Felipe
 */
public class CpfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (context == null || component == null) {
            throw new NullPointerException();
        }

        if (value != null && !value.trim().equals("")) {
            value = value.replaceAll("\\D", "");
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (context == null || component == null) {
            throw new NullPointerException();
        }

        if (value == null) {
            return "";
        }

        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(value.toString());

        if (matcher.matches()) {
            value = matcher.replaceAll("$1.$2.$3-$4");
        }

        return value.toString();
    }
}
