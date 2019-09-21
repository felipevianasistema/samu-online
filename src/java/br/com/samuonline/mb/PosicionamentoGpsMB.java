package br.com.samuonline.mb;

import br.com.samuonline.modelo.PosicionamentoGps;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@javax.faces.bean.ManagedBean
@ViewScoped
public class PosicionamentoGpsMB extends MainMB {

    private static List<PosicionamentoGps> objListaPosicaoTemp = new ArrayList<PosicionamentoGps>();
    private List<PosicionamentoGps> objListaPosicao = new ArrayList<PosicionamentoGps>();
    private PosicionamentoGps objPosicionamento = new PosicionamentoGps();
    private String posicionamento;

    public PosicionamentoGpsMB() {
    }

    /**
     * Adiciona a posição das viaturas que estão acessando.
     */
    public void adicionarPosicao() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

        getObjPosicionamento().setIp(request.getRemoteAddr());
        getObjPosicionamento().setPosicionamento(getPosicionamento());
        
        for (int i = 0; i < getObjListaPosicaoTemp().size(); i++) {
            if (getObjListaPosicaoTemp().get(i).getIp().equals(getObjPosicionamento().getIp())) {
                getObjListaPosicaoTemp().remove(i);
            }
        }
        objListaPosicao.clear();
        getObjListaPosicaoTemp().add(objPosicionamento);
        for (PosicionamentoGps obj : getObjListaPosicaoTemp()) {
            getObjListaPosicao().add(obj);
        }
    }

    public static List<PosicionamentoGps> getObjListaPosicaoTemp() {
        return objListaPosicaoTemp;
    }

    public static void setObjListaPosicaoTemp(List<PosicionamentoGps> aObjListaPosicao) {
        objListaPosicaoTemp = aObjListaPosicao;
    }

    public PosicionamentoGps getObjPosicionamento() {
        return objPosicionamento;
    }

    public void setObjPosicionamento(PosicionamentoGps objPosicionamento) {
        this.objPosicionamento = objPosicionamento;
    }

    public List<PosicionamentoGps> getObjListaPosicao() {
        return objListaPosicao;
    }

    public void setObjListaPosicao(List<PosicionamentoGps> objListaPosicao) {
        this.objListaPosicao = objListaPosicao;
    }

    public String getPosicionamento() {
        return posicionamento;
    }

    public void setPosicionamento(String posicionamento) {
        this.posicionamento = posicionamento;
    }
}
