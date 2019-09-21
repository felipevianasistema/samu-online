/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.samuonline.datamodel;

import br.com.samuonline.modelo.Recurso;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Felipe
 */
public class RecursoDataModel extends ListDataModel<Recurso> implements SelectableDataModel<Recurso> {

    public RecursoDataModel() {
    }

    public RecursoDataModel(List<Recurso> data) {
        super(data);
    }

    @Override
    public Recurso getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<Recurso> cars = (List<Recurso>) getWrappedData();

        for (Recurso recurso : cars) {
            if (String.valueOf(recurso.getIdRecurso()).equals(rowKey)) {
                return recurso;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(Recurso recurso) {
        return recurso.getIdRecurso();
    }
}
