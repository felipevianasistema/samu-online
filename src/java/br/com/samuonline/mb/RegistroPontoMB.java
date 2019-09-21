package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.BaseDAO;
import br.com.samuonline.dao.RegistroPontoDAO;
import br.com.samuonline.dao.ViaturaDAO;
import br.com.samuonline.modelo.Base;
import br.com.samuonline.modelo.RegistroPonto;
import br.com.samuonline.modelo.Viatura;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

@javax.faces.bean.ManagedBean
@ViewScoped
public class RegistroPontoMB extends MainMB implements ManagedBean {

    private RegistroPonto objRegistroPonto = new RegistroPonto();
    private List<RegistroPonto> listaRegistroPonto = new ArrayList<RegistroPonto>();
    private List<SelectItem> itensViatura = new ArrayList<SelectItem>();
    private List<SelectItem> itensBase = new ArrayList<SelectItem>();

    public RegistroPontoMB() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            visibilidadeMain(0);
            consultar();
        }
    }

    /**
     * Cadastra/adiciona um ponto.
     */
    @Override
    public void adicionar() {

        try {
            RegistroPontoDAO objRegistroPontoDAO = new RegistroPontoDAO(getConnection());

            objRegistroPontoDAO.adicionar(getObjRegistroPonto());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            setObjRegistroPonto(new RegistroPonto());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void excluir() {

    }

    @Override
    public void atualizar() {

    }

    /**
     * Resgata todos os pontos do usuário autenticado no sistema.
     */
    @Override
    public void consultar() {

        setItensBase(new ArrayList<SelectItem>());
        setItensViatura(new ArrayList<SelectItem>());

        try {
            //carrega a combobox com as bases.
            BaseDAO objBaseDAO = new BaseDAO(getConnection());
            List<Base> listaBase = objBaseDAO.consultar();
            
            for (Base objBase : listaBase) {
                getItensBase().add(new SelectItem(objBase.getIdBase(), objBase.getCodigo() + " - " + objBase.getNome()));
            }

            //carrega os pontos.
            RegistroPontoDAO objRegistroPontoDAO = new RegistroPontoDAO(getConnection());
            this.listaRegistroPonto = objRegistroPontoDAO.consultar();

        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Ocuta/Exibe a combobox da viatura e da base.
     *
     * @param evt
     */
    public void selecionarLocal(ValueChangeEvent evt) {
    }

    /**
     * Exibe a tela de cadastro para o usuário.
     */
    @Override
    public void novo() {
        visibilidadeMain(1);
        setObjRegistroPonto(new RegistroPonto());
    }

    @Override
    public void editar() {

    }

    /**
     * Exibe a tela de consulta para o usuário.
     */
    @Override
    public void voltar() {
        visibilidadeMain(0);
        consultar();
        setObjRegistroPonto(new RegistroPonto());
    }

    @Override
    public boolean validar() {
        return false;

    }

    /**
     * Filtra um ponto de acordo com o mês selecionado. Obs: retorna apenas o
     * registro do usuário autenticado no sistema.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            RegistroPontoDAO objRegistroPontoDAO = new RegistroPontoDAO(getConnection());
            this.listaRegistroPonto = objRegistroPontoDAO.filtrarPesquisa(getObjRegistroPonto());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    public RegistroPonto getObjRegistroPonto() {
        return objRegistroPonto;
    }

    public void setObjRegistroPonto(RegistroPonto objRegistroPonto) {
        this.objRegistroPonto = objRegistroPonto;
    }

    public List<RegistroPonto> getListaRegistroPonto() {
        return listaRegistroPonto;
    }

    public void setListaRegistroPonto(List<RegistroPonto> listaRegistroPonto) {
        this.listaRegistroPonto = listaRegistroPonto;
    }

    public List<SelectItem> getItensViatura() {
        return itensViatura;
    }

    public void setItensViatura(List<SelectItem> itensViatura) {
        this.itensViatura = itensViatura;
    }

    public List<SelectItem> getItensBase() {
        return itensBase;
    }

    public void setItensBase(List<SelectItem> itensBase) {
        this.itensBase = itensBase;
    }
}
