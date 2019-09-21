package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.BaseDAO;
import br.com.samuonline.dao.ViaturaDAO;
import br.com.samuonline.modelo.Base;
import br.com.samuonline.modelo.Viatura;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@javax.faces.bean.ManagedBean
@ViewScoped
public class ViaturaMB extends MainMB implements ManagedBean {

    private Viatura objViatura = new Viatura();
    private List<Viatura> objListaViatura = new ArrayList<Viatura>();
    private List<SelectItem> itensBase = new ArrayList<SelectItem>();

    public ViaturaMB() {

        if (!FacesContext.getCurrentInstance().isPostback()) {
            visibilidadeMain(0);
            consultar();
        }
    }

    /**
     * Cadastra uma nova viatura na base de dados do sistema.
     *
     * @return
     */
    @Override
    public void adicionar() {

        try {
            ViaturaDAO objViaturaDAO = new ViaturaDAO(getConnection());
            objViaturaDAO.adicionar(getObjViatura());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            setObjViatura(new Viatura());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void excluir() {
    }

    /**
     * Atualiza as informações da viatura selecionada.
     *
     * @return
     */
    @Override
    public void atualizar() {

        try {
            ViaturaDAO objViaturaDAO = new ViaturaDAO(getConnection());
            objViaturaDAO.atualizar(getObjViatura());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_ALTERAR_SUCESSO);
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_ALTERAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    /**
     * Consulta todas as viaturas cadastradas na base de dados do sistema.
     *
     * @return
     */
    @Override
    public void consultar() {
        try {
            //carrega as viaturas cadastradas
            ViaturaDAO objViaturaDAO = new ViaturaDAO(getConnection());
            this.objListaViatura = objViaturaDAO.consultar();
            
            BaseDAO baseDAO = new BaseDAO(getConnection());
            List<Base> listaBase = baseDAO.consultar();
            for(Base objBase : listaBase){
                getItensBase().add(new SelectItem(objBase.getIdBase(), objBase.getNome()));
            }
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Filtra/pesquisa as informações da grid.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            if (getObjViatura().getCodigo() <= 0) {
                addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_PREENCHER_CAMPO);
                return;
            }

            ViaturaDAO objViaturaDAO = new ViaturaDAO(getConnection());
            this.objListaViatura = objViaturaDAO.filtrarPesquisa(getObjViatura());
            setObjViatura(new Viatura());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void novo() {
        setObjViatura(new Viatura());
        visibilidadeMain(1);
    }

    /**
     * Exibe a tela de edição/alteração.
     *
     * @return
     */
    @Override
    public void editar() {
        visibilidadeMain(2);
    }

    /**
     * Volta para tela de consulta.
     *
     * @return
     */
    @Override
    public void voltar() {
        consultar();
        setObjViatura(new Viatura());
        visibilidadeMain(0);
    }

    @Override
    public boolean validar() {
        return false;
    }

    public Viatura getObjViatura() {
        return objViatura;
    }

    public void setObjViatura(Viatura objViatura) {
        this.objViatura = objViatura;
    }

    public List<Viatura> getObjListaViatura() {
        return objListaViatura;
    }

    public void setObjListaViatura(List<Viatura> objListaViatura) {
        this.objListaViatura = objListaViatura;
    }

    public List<SelectItem> getItensBase() {
        return itensBase;
    }

    public void setItensBase(List<SelectItem> itensBase) {
        this.itensBase = itensBase;
    }

  
}
