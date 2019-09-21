package br.com.samuonline.mb;

import br.com.samuonline.conexao.ConFactory;
import br.com.samuonline.constantes.Constante;
import br.com.samuonline.modelo.Recurso;
import br.com.samuonline.modelo.UsuarioFuncionario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

public class MainMB implements Serializable {

    private Connection con;
    private boolean rendBtnNovo;
    private boolean rendBtnAdicionar;
    private boolean rendBtnVoltar;
    private boolean rendBtnLimpar;
    private boolean rendBtnAtualizar;
    private boolean rendFiltro;
    private boolean rendGrid;
    private boolean rendFormulario;

    /**
     * Finaliza a sessão do usuário.
     */
    public String encerrarSessao() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().remove("autenticacao");
        //exibe mensagem para o usuário, após finalizar a sessão.
        addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_SESSAO_ENCERRADA_SUCESSO);
        //redireciona o usuário para tela de autenticação.
        return "autenticacao";
    }

    /**
     * Retorna as informações da sessão do usuário.
     *
     * @return
     */
    public UsuarioFuncionario getUsuarioSessao() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return (UsuarioFuncionario) fc.getExternalContext().getSessionMap().get("autenticacao");
    }
    
    

    /**
     * Exibe mensagens nas páginas xhtml.
     *
     * @param faces
     * @param msg
     */
    public void addMessage(FacesMessage.Severity faces, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(faces, msg, null));
    }

    /**
     * Controla a visibilidadeMain dos componentes das páginas. 0 - Exibe a tela de
 Consulta. 1 - Exibe a tela de cadastro. 2 - Exibe a tela de atualização.
     *
     * @param tipo
     */
    public void visibilidadeMain(int tipo) {

        if (tipo == 0) {
            this.rendBtnNovo = true;
            this.rendBtnAdicionar = false;
            this.rendBtnVoltar = false;
            this.rendBtnLimpar = false;
            this.rendBtnAtualizar = false;
            this.rendFiltro = true;
            this.rendGrid = true;
            this.rendFormulario = false;
        } else if (tipo == 1) {
            this.rendBtnNovo = false;
            this.rendBtnAdicionar = true;
            this.rendBtnVoltar = true;
            this.rendBtnLimpar = true;
            this.rendBtnAtualizar = false;
            this.rendFiltro = false;
            this.rendGrid = false;
            this.rendFormulario = true;
        } else if (tipo == 2) {
            this.rendBtnNovo = false;
            this.rendBtnAdicionar = false;
            this.rendBtnVoltar = true;
            this.rendBtnLimpar = false;
            this.rendBtnAtualizar = true;
            this.rendFiltro = false;
            this.rendGrid = false;
            this.rendFormulario = true;
        }
    }

    /**
     * Inicia conexão com a base de dados.
     *
     * @return Conenction
     * @throws Exception
     */
    public Connection getConnection() throws Exception {

        try {
            if (con == null) {
                con = ConFactory.getConnection();
                return con;
            } else {
                return con;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Finaliza a conexão com a base de dados.
     */
    public void closeConnection() {

        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (Exception ex) {
        }
    }

    /**
     * Aguarda a confirmação ou cancelamento da transação. Está sendo utilizado
     * apenas para açoes que implicam em mais de uma consulta na base de dados.
     *
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        try {
            if (con != null && con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * confirma a transação.
     *
     * @throws SQLException
     */
    public void commitTransaction() throws SQLException {
        try {
            if (con != null && !con.getAutoCommit()) {
                con.commit();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * cancela uma transação.
     *
     * @throws SQLException
     */
    public void rollBackTransaction() {
        try {
            if (con != null && !con.getAutoCommit()) {
                con.rollback();
            }
        } catch (SQLException ex) {
        }
    }

    /**
     * Verificar se o usuarioSessao possui permissao para visualizar o
     * componente da pagina.
     */
    public void hasPermissaoComponente(ComponentSystemEvent event) {
        List<Recurso> listaRecursos = getUsuarioSessao().getObjListarecursos();
        boolean resposta = Boolean.FALSE;
        for (Recurso recurso : listaRecursos) {
            if (recurso.getNome().equals(event.getComponent().getId())) {
                resposta = Boolean.TRUE;
                break;
            }
        }
        event.getComponent().setRendered(resposta);
    }

    public boolean isRendBtnNovo() {
        return rendBtnNovo;
    }

    public void setRendBtnNovo(boolean rendBtnNovo) {
        this.rendBtnNovo = rendBtnNovo;
    }

    public boolean isRendBtnAdicionar() {
        return rendBtnAdicionar;
    }

    public void setRendBtnAdicionar(boolean rendBtnAdicionar) {
        this.rendBtnAdicionar = rendBtnAdicionar;
    }

    public boolean isRendBtnVoltar() {
        return rendBtnVoltar;
    }

    public void setRendBtnVoltar(boolean rendBtnVoltar) {
        this.rendBtnVoltar = rendBtnVoltar;
    }

    public boolean isRendBtnLimpar() {
        return rendBtnLimpar;
    }

    public void setRendBtnLimpar(boolean rendBtnLimpar) {
        this.rendBtnLimpar = rendBtnLimpar;
    }

    public boolean isRendBtnAtualizar() {
        return rendBtnAtualizar;
    }

    public void setRendBtnAtualizar(boolean rendBtnAtualizar) {
        this.rendBtnAtualizar = rendBtnAtualizar;
    }

    public boolean isRendFiltro() {
        return rendFiltro;
    }

    public void setRendFiltro(boolean rendFiltro) {
        this.rendFiltro = rendFiltro;
    }

    public boolean isRendGrid() {
        return rendGrid;
    }

    public void setRendGrid(boolean rendGrid) {
        this.rendGrid = rendGrid;
    }

    public boolean isRendFormulario() {
        return rendFormulario;
    }

    public void setRendFormulario(boolean rendFormulario) {
        this.rendFormulario = rendFormulario;
    }
}
