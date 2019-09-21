package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.AtendimentoDAO;
import br.com.samuonline.dao.AutenticacaoDAO;
import br.com.samuonline.dao.RecursoDAO;
import br.com.samuonline.modelo.Atendimento;
import br.com.samuonline.modelo.UsuarioFuncionario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@javax.faces.bean.ManagedBean
@SessionScoped
public class AutenticacaoMB extends MainMB {

    private UsuarioFuncionario objLoginFuncionario = new UsuarioFuncionario();
    private Atendimento objAtendimento = new Atendimento();
    private List<String> objListaAtendimento = new ArrayList<String>();

    public AutenticacaoMB() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            listarChamados();
        }
    }

    /**
     * Autenticação do sistema, o usuário efetua login no sistema informando o
     * usuário e a senha. Verifica se a situação do usuário está (true - ativa)
     * ou (false - inativa) Caso esteja inativa, o usuário não poderá efetuar
     * autenticação no sistema, até que o administrador altere a situação do
     * usuário para ativa.
     *
     * @return
     */
    public String autenticar() {

        try {
            AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO(getConnection());
            RecursoDAO recursoDAO = new RecursoDAO(getConnection());
            //verifica se o usuário e senha informado, está cadastro na base de dados.
            setObjLoginFuncionario(objLoginFuncionario = autenticacaoDAO.autenticar(getObjLoginFuncionario()));

            if (getObjLoginFuncionario() == null) {
                //usuário informado, não localizado.
                addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_USUARIO_INEXISTENTE);
                setObjLoginFuncionario(new UsuarioFuncionario());
                return null;
            } else if (getObjLoginFuncionario().getSituacao() == 0) {
                //usuário inativo.
                addMessage(Constante.TIPO_MENSAGEM_WARN, Constante.MSG_USUARIO_INATIVO);
                setObjLoginFuncionario(new UsuarioFuncionario());
                return null;
            } else {//usuário cadastrado no sistema.
                //resgata todos os recursos do usuário/perfil.
                getObjLoginFuncionario().setObjListarecursos(recursoDAO.resgatarRecursosPerfil(getObjLoginFuncionario().getPerfilIdPerfil()));

                //enviar os dados do usuário para sessão do browser.
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().getSessionMap().put("autenticacao", getObjLoginFuncionario());
                //redireciona o usuário para página interna.
                return "index?faces-redirect=true";
            }
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_WARN, Constante.MSG_SISTEMA_INDISPONIVEL);
            setObjLoginFuncionario(new UsuarioFuncionario());
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    /**
     * Ao clicar no link, o usuário é redirecionado para a tela de consultar de
     * contrato.
     */
    public String reirecionarTelaConsultarAtendimento() {

        //envia o código do atendimento/chamado para sessão
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("cod_atendimento", getObjAtendimento().getCodigoAtendimento());

        //redireciona o usuário para tela de consultar contrato
        return "atendimento.xhtml?faces-redirect=true";
    }

    /**
     * Lista os últimos atendimentos abaixo do menu do sistema.
     */
    public void listarChamados() {
        try {
            AtendimentoDAO atendimentoDAO = new AtendimentoDAO(getConnection());
            this.getObjListaAtendimento().clear();
            this.setObjListaAtendimento(atendimentoDAO.consultarUltimosAtendimentos());

            for (; this.objListaAtendimento.size() < 8;) {
                objListaAtendimento.add("-");
            }

        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_CONSULTA_ERRO);
        } finally {
            closeConnection();
        }
    }

    public UsuarioFuncionario getObjLoginFuncionario() {
        return objLoginFuncionario;
    }

    public void setObjLoginFuncionario(UsuarioFuncionario objLoginFuncionario) {
        this.objLoginFuncionario = objLoginFuncionario;
    }

    public Atendimento getObjAtendimento() {
        return objAtendimento;
    }

    public void setObjAtendimento(Atendimento objAtendimento) {
        this.objAtendimento = objAtendimento;
    }

    public List<String> getObjListaAtendimento() {
        return objListaAtendimento;
    }

    public void setObjListaAtendimento(List<String> objListaAtendimento) {
        this.objListaAtendimento = objListaAtendimento;
    }

}
