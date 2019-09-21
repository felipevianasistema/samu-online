package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.PerfilDAO;
import br.com.samuonline.dao.RecursoDAO;
import br.com.samuonline.datamodel.RecursoDataModel;
import br.com.samuonline.modelo.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@javax.faces.bean.ManagedBean
@ViewScoped
public class PerfilMB extends MainMB implements ManagedBean {

    private Perfil objPerfil = new Perfil();
    private List<Perfil> objListaPerfil = new ArrayList<Perfil>();
    private RecursoDataModel objRecursoDataModel;

    public PerfilMB() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            consultar();
            visibilidadeMain(0);
        }
    }

    /**
     * Adiciona/cadastra um novo perfil e os recursos na base de dados.
     *
     * @return
     */
    @Override
    public void adicionar() {

        try {
            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            beginTransaction();
            objPerfilDAO.adicionar(getObjPerfil());
            commitTransaction();
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            setObjPerfil(new Perfil());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
            rollBackTransaction();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void excluir() {
    }

    /**
     * Atualiza as informações do perfil.
     *
     * @return
     */
    @Override
    public void atualizar() {

        try {

            //inicio da transação.
            beginTransaction();
            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            //atualiza as informações do perifl.
            objPerfilDAO.atualizar(getObjPerfil());
            //atualiza os recursos do perfil.
            objPerfilDAO.atualizarPerfilRecurso(getObjPerfil());
            //confirma a transação.
            commitTransaction();
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_ALTERAR_SUCESSO);
        } catch (Exception ex) {
            ex.printStackTrace();
            //cancela a transação.
            rollBackTransaction();
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_ALTERAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    /**
     * Consulta todos os perfis cadastrados na base de dados.
     *
     * @return
     */
    @Override
    public void consultar() {

        try {
            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            this.objListaPerfil = objPerfilDAO.consultar();

            //resgata todos os recursos cadastrados no sistema.
            RecursoDAO objRecursoDAO = new RecursoDAO(getConnection());
            this.objRecursoDataModel = new RecursoDataModel(objRecursoDAO.consultar());
        } catch (Exception ex) {
            setObjListaPerfil(new ArrayList<Perfil>());
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Filtra/pesquisa as informações exibidas na grid.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            if (getObjPerfil().getNome() == null || getObjPerfil().getNome().trim().equals("")) {
                addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_PREENCHER_CAMPO);
                return;
            }

            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            this.objListaPerfil = objPerfilDAO.filtrarPesquisa(getObjPerfil());
            setObjPerfil(new Perfil());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Exibe o formulário para um novo cadastro de perfil.
     *
     * @return
     */
    @Override
    public void novo() {
        setObjPerfil(new Perfil());
        visibilidadeMain(1);
    }

    /**
     * Exibe o formulário preenchido com as informações do perfil selecionado.
     */
    @Override
    public void editar() {

        try {
            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            getObjPerfil().setArrayRecursos(objPerfilDAO.resgatarPerfilRecurso(getObjPerfil()));
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_EDITAR_FALHA);
        } finally {
            closeConnection();
        }

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
        setObjPerfil(new Perfil());
        visibilidadeMain(0);
    }

    @Override
    public boolean validar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public List<Perfil> getObjListaPerfil() {
        return objListaPerfil;
    }

    public void setObjListaPerfil(List<Perfil> objListaPerfil) {
        this.objListaPerfil = objListaPerfil;
    }

    public RecursoDataModel getObjRecursoDataModel() {
        return objRecursoDataModel;
    }

    public void setObjRecursoDataModel(RecursoDataModel objRecursoDataModel) {
        this.objRecursoDataModel = objRecursoDataModel;
    }
}
