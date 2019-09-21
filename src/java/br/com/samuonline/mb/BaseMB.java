package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.BaseDAO;
import br.com.samuonline.modelo.Base;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@javax.faces.bean.ManagedBean
@ViewScoped
public class BaseMB extends MainMB implements ManagedBean {

    private Base objBase = new Base();
    private List<Base> listaBase = new ArrayList<Base>();

    public BaseMB() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            consultar();
            visibilidadeMain(0);
        }
    }

    /**
     * Adiciona/cadastra uma nova base.
     */
    @Override
    public void adicionar() {

        try {
            BaseDAO objBaseDAO = new BaseDAO(getConnection());
            objBaseDAO.adicionar(getObjBase());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            setObjBase(new Base());
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

    /**
     * Atualiza as informações de uma base.
     */
    @Override
    public void atualizar() {

        try {
            BaseDAO objBaseDAO = new BaseDAO(getConnection());
            objBaseDAO.atualizar(getObjBase());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_ALTERAR_SUCESSO);
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_ALTERAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    /**
     * Retorna todas as bases cadastradas.
     */
    @Override
    public void consultar() {

        try {
            BaseDAO objBaseDAO = new BaseDAO(getConnection());
            this.listaBase = objBaseDAO.consultar();
        } catch (Exception ex) {
            setListaBase(new ArrayList<Base>());
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Exibe a tela de cadastro para o usuário.
     */
    @Override
    public void novo() {
        setObjBase(new Base());
        visibilidadeMain(1);
    }

    /**
     * Exibe a tela de edição para o usuário.
     */
    @Override
    public void editar() {
        visibilidadeMain(2);
    }

    /**
     * Exibe a tela de consulta.
     */
    @Override
    public void voltar() {
        visibilidadeMain(0);
        consultar();
        setObjBase(new Base());
    }

    @Override
    public boolean validar() {
        return false;
    }

    /**
     * Filtro de pesquisa. Retorna as bases localizadas no banco de dados.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            BaseDAO objBaseDAO = new BaseDAO(getConnection());
            this.listaBase = objBaseDAO.filtrarPesquisa(getObjBase());
        } catch (Exception ex) {
            ex.printStackTrace();
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
        } finally {
            closeConnection();
        }

    }

    public Base getObjBase() {
        return objBase;
    }

    public void setObjBase(Base objBase) {
        this.objBase = objBase;
    }

    public List<Base> getListaBase() {
        return listaBase;
    }

    public void setListaBase(List<Base> listaBase) {
        this.listaBase = listaBase;
    }

}
