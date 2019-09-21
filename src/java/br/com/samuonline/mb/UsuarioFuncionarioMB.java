package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.UsuarioFuncionarioDAO;
import br.com.samuonline.dao.PerfilDAO;
import br.com.samuonline.modelo.UsuarioFuncionario;
import br.com.samuonline.modelo.Perfil;
import br.com.samuonline.util.Util;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@javax.faces.bean.ManagedBean
@ViewScoped
public class UsuarioFuncionarioMB extends MainMB implements ManagedBean {

    private UsuarioFuncionario objUsuarioFuncionario = new UsuarioFuncionario();
    private List<SelectItem> itensViatura = new ArrayList<SelectItem>();
    private List<SelectItem> itensPerfil = new ArrayList<SelectItem>();
    private List<UsuarioFuncionario> objListaFuncionarios = new ArrayList<UsuarioFuncionario>();

    public UsuarioFuncionarioMB() {

        if (!FacesContext.getCurrentInstance().isPostback()) {
            visibilidadeMain(0);
            consultar();
        }
    }

    /**
     * Cadastra um novo usuário/funcionário na base de dados.
     *
     * @return
     */
    @Override
    public void adicionar() {

        if (!validar()) {
            return;
        }

        try {
            UsuarioFuncionarioDAO objLoginFuncionarioDAO = new UsuarioFuncionarioDAO(getConnection());
            objLoginFuncionarioDAO.adicionar(getObjUsuarioFuncionario());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            setObjUsuarioFuncionario(new UsuarioFuncionario());
        } catch (Exception ex) {
            if (ex.getMessage().contains(Constante.UN_USUARIO)) {
                addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_USUARIO_JA_CADASTRADO);
                return;
            }
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void excluir() {
    }

    /**
     * Atualiza as informações do usuário específico.
     *
     * @return
     */
    @Override
    public void atualizar() {

        if (!validar()) {
            return;
        }

        try {
            UsuarioFuncionarioDAO objLoginFuncionarioDAO = new UsuarioFuncionarioDAO(getConnection());
            objLoginFuncionarioDAO.atualizar(getObjUsuarioFuncionario());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_ALTERAR_SUCESSO);
        } catch (Exception ex) {
            if (ex.getMessage().contains(Constante.UN_USUARIO)) {
                addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_USUARIO_JA_CADASTRADO);
                return;
            }
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_ALTERAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    /**
     * Consulta todos os usuário, viaturas e perfis cadastrados no sistema.
     *
     * @return
     */
    @Override
    public void consultar() {

        //limpa a lista de perfis e viaturas.
        setItensPerfil(new ArrayList<SelectItem>());
        setItensViatura(new ArrayList<SelectItem>());

        try {
            //carrega a combobox de perfil.
            PerfilDAO objPerfilDAO = new PerfilDAO(getConnection());
            List<Perfil> listaPerfil = objPerfilDAO.consultar();
            for (Perfil perfil : listaPerfil) {
                getItensPerfil().add(new SelectItem(perfil.getIdPerfil(), perfil.getNome()));
            }

            //carrega todo os usuário cadastrados.
            UsuarioFuncionarioDAO objLoginFuncionarioDAO = new UsuarioFuncionarioDAO(getConnection());
            this.objListaFuncionarios = objLoginFuncionarioDAO.consultar();

        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Filtra/pesquisa o(s) usuário que deverão ser exibidos na grid.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            if (getObjUsuarioFuncionario().getNome() == null || getObjUsuarioFuncionario().getNome().trim().equals("")) {
                addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_PREENCHER_CAMPO);
                return;
            }

            UsuarioFuncionarioDAO objLoginFuncionarioDAO = new UsuarioFuncionarioDAO(getConnection());
            this.objListaFuncionarios = objLoginFuncionarioDAO.filtrarPesquisa(getObjUsuarioFuncionario());
            setObjUsuarioFuncionario(new UsuarioFuncionario());
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
        } finally {
            closeConnection();
        }
    }

    /**
     * Exibe o formulário para o usuário efetuar um novo cadastro.
     *
     * @return
     */
    @Override
    public void novo() {
        setObjUsuarioFuncionario(new UsuarioFuncionario());
        visibilidadeMain(1);
    }

    /**
     * Exibe o formulário preenchido com as informações do usuário. Remove o
     * inicio e o fim da chamada do skype para que apenas seja exibido a placa
     * da viatura para o usuário.
     *
     * @return
     */
    @Override
    public void editar() {
        //remove a chamada do skype.
        getObjUsuarioFuncionario().setSkype(getObjUsuarioFuncionario().getSkype().replace("skype:", ""));
        getObjUsuarioFuncionario().setSkype(getObjUsuarioFuncionario().getSkype().replace("?call", ""));
        //não exibe o campo da senha ao clicar em editar.
        getObjUsuarioFuncionario().setSenha("");
        visibilidadeMain(2);
    }

    /**
     * Exibe a tela de consulta para o usuário.
     *
     * @return
     */
    @Override
    public void voltar() {
        consultar();
        setObjUsuarioFuncionario(new UsuarioFuncionario());
        visibilidadeMain(0);
    }

    /**
     * Valida algumas informações preenchidas no formulário.
     */
    @Override
    public boolean validar() {

        if (!Util.validarCPF(getObjUsuarioFuncionario().getCpf())) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_CPF_INVALIDO);
            return false;
        }
        return true;
    }

    /**
     * Altera a senha do usuário selecionado.
     */
    public void redefinirSenha() {

        //verifica se os dois campos foram preenchidos.
        if (getObjUsuarioFuncionario().getSenha() == null
                || getObjUsuarioFuncionario().getSenha().isEmpty()
                || getObjUsuarioFuncionario().getRepetirSenha() == null
                || getObjUsuarioFuncionario().getRepetirSenha().isEmpty()) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_PREENCHER_CAMPO);
            return;
        }

        //verifica se as duas senhas informadas são iguais.
        if (!getObjUsuarioFuncionario().getSenha().equals(objUsuarioFuncionario.getRepetirSenha())) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_REPETIR_SENHA_FALHA);
            return;
        }

        try {
            UsuarioFuncionarioDAO objUsuarioDAO = new UsuarioFuncionarioDAO(getConnection());
            objUsuarioDAO.redefinirSenha(getObjUsuarioFuncionario());
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_ALTERAR_SUCESSO);
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_ALTERAR_FALHA);
        } finally {
            closeConnection();
        }
    }

    public UsuarioFuncionario getObjUsuarioFuncionario() {
        return objUsuarioFuncionario;
    }

    public void setObjUsuarioFuncionario(UsuarioFuncionario objUsuarioFuncionario) {
        this.objUsuarioFuncionario = objUsuarioFuncionario;
    }

    public List<SelectItem> getItensViatura() {
        return itensViatura;
    }

    public void setItensViatura(List<SelectItem> itensViatura) {
        this.itensViatura = itensViatura;
    }

    public List<SelectItem> getItensPerfil() {
        return itensPerfil;
    }

    public void setItensPerfil(List<SelectItem> itensPerfil) {
        this.itensPerfil = itensPerfil;
    }

    public List<UsuarioFuncionario> getObjListaFuncionarios() {
        return objListaFuncionarios;
    }

    public void setObjListaFuncionarios(List<UsuarioFuncionario> objListaFuncionarios) {
        this.objListaFuncionarios = objListaFuncionarios;
    }
}
