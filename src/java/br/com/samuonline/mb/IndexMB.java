package br.com.samuonline.mb;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;

@javax.faces.bean.ManagedBean
@ViewScoped
public class IndexMB extends MainMB {

    private List<String> objListaImagens = new ArrayList<String>();

    public IndexMB() {
        //preenche a lista com todas as imagens a serem exibida no slide da página inicial.
        for (byte i = 1; i <= 12; i++) {
            getObjListaImagens().add("img/galeria/" + i + ".jpg");
        }
    }

    public List<String> getObjListaImagens() {
        return objListaImagens;
    }

    public void setObjListaImagens(List<String> objListaImagens) {
        this.objListaImagens = objListaImagens;
    }
}
