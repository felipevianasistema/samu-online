package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.AtendimentoDAO;
import br.com.samuonline.dao.PacienteDAO;
import br.com.samuonline.dao.SolicitanteDAO;
import br.com.samuonline.dao.UsuarioFuncionarioDAO;
import br.com.samuonline.dao.ViaturaDAO;
import br.com.samuonline.modelo.Atendimento;
import br.com.samuonline.modelo.Paciente;
import br.com.samuonline.modelo.UsuarioFuncionario;
import br.com.samuonline.modelo.Viatura;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DualListModel;

@javax.faces.bean.ManagedBean
@ViewScoped
public class AtendimentoMB extends MainMB implements ManagedBean {

    private Atendimento objAtendimento = new Atendimento();
    private Atendimento objConsultaAtendimento = new Atendimento();
    private HtmlPanelGroup renderPainelAtendimento = new HtmlPanelGroup();
    private HtmlPanelGroup renderPainelSolicitante = new HtmlPanelGroup();
    private HtmlPanelGroup renderPainelPaciente = new HtmlPanelGroup();
    private HtmlPanelGroup renderPainelViaturaMedico = new HtmlPanelGroup();
    private HtmlPanelGroup renderPainelConsultar = new HtmlPanelGroup();
    //PickList.
    private DualListModel<Viatura> dualListViaturas;
    private List<Viatura> viaturasSource = new ArrayList<Viatura>();
    private List<Viatura> viaturasTarget = new ArrayList<Viatura>();
    private DualListModel<UsuarioFuncionario> dualListMedicos;
    private List<UsuarioFuncionario> medicosSource = new ArrayList<UsuarioFuncionario>();
    private List<UsuarioFuncionario> medicosTarget = new ArrayList<UsuarioFuncionario>();
    private List<Atendimento> objListaAtendimento = new ArrayList<Atendimento>();
    private byte tabSelecionada = 0;
    private String sessao;
    /*
     * 1 - Registro do atendimento
     * 2 - Registra o solicitante
     * 3 - Registra o(s) paciente(s)
     * 4 - Registrar viatura(s) e médico(s).
     */
    private byte etapaAtendimento = 0;

    public AtendimentoMB() {

        if (!FacesContext.getCurrentInstance().isPostback()) {
            //ao clicar na lista de últmos chamados/atendimentos cadastrados, o usuário é redirecionado para esta página exibindo as informações do atendimento selecionado
            FacesContext fc = FacesContext.getCurrentInstance();
            sessao = String.valueOf(fc.getExternalContext().getSessionMap().get("cod_atendimento"));
            //verifica se retornou null da sessão
            if (!sessao.equals("null")) {
                this.objConsultaAtendimento.setCodigoAtendimento(Long.parseLong(sessao));
            }
            if (getObjConsultaAtendimento().getCodigoAtendimento() > 0) {
                filtrarPesquisa();
            } else {
                getRenderPainelConsultar().setRendered(false);
            }
        }
    }

    /**
     * Este método é executado quando a página é exibida, resolveu o problema de
     * quando efetuava um cadastro de atendimento, caso precisasse efetuar um
     * novo cadastro, o pickList não esvaziava. Quando o menu de atendimento é
     * clicado, a página da um reflash.
     *
     * @return
     */
    public String inicio() {
        //se o usuário abriu e página e não veio pelo clique dos últimos chamados, deve ocultar.
        if(sessao.equals("null")){
            getRenderPainelConsultar().setRendered(false);
        }
        this.setEtapaAtendimento((byte) 1);
        consultar();

        return null;
    }

    /**
     * 1 - Registro do atendimento 2 - Registra o solicitante 3 - Registra o(s)
     * paciente(s) 4 - Registrar viatura(s) e médico(s).
     *
     * @param mode
     */
    public void visibilidade(int mode) {

        if (mode == 1) {
            this.getRenderPainelAtendimento().setRendered(true);
            this.getRenderPainelSolicitante().setRendered(false);
            this.getRenderPainelPaciente().setRendered(false);
            this.getRenderPainelViaturaMedico().setRendered(false);
        } else if (mode == 2) {
            this.getRenderPainelAtendimento().setRendered(false);
            this.getRenderPainelSolicitante().setRendered(true);
            this.getRenderPainelPaciente().setRendered(false);
            this.getRenderPainelViaturaMedico().setRendered(false);
        } else if (mode == 3) {
            this.getRenderPainelAtendimento().setRendered(false);
            this.getRenderPainelSolicitante().setRendered(false);
            this.getRenderPainelPaciente().setRendered(true);
            this.getRenderPainelViaturaMedico().setRendered(false);
        } else if (mode == 4) {
            this.getRenderPainelAtendimento().setRendered(false);
            this.getRenderPainelSolicitante().setRendered(false);
            this.getRenderPainelPaciente().setRendered(false);
            this.getRenderPainelViaturaMedico().setRendered(true);
        }
    }

    /**
     * Exibe a tela de cadastro do solicitante.
     */
    public void registrarSolicitante() {
        setEtapaAtendimento((byte) 2);
        visibilidade(2);
        this.tabSelecionada = 1;
    }

    /**
     * Exite a tela de cadastro atendimento.
     */
    public void registrarAtendimento() {
        setEtapaAtendimento((byte) 1);
        visibilidade(1);
    }

    /**
     * Exibe a tela de cadastro do paciente.
     */
    public void registrarPaciente() {
        setEtapaAtendimento((byte) 3);
        visibilidade(3);
        this.tabSelecionada = 1;
    }

    /**
     * Exibe a tela de cadastro das viaturas que irão participar do atendimento.
     */
    public void registrarViaturas() {
        //valida se existe algum paciente cadastrado.
        if (getObjAtendimento().getObjListaPaciente().size() == 0) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_ADICIONAR_PACIENTE);
            return;
        }
        setEtapaAtendimento((byte) 4);
        this.tabSelecionada = 1;
        visibilidade(4);
    }

    /**
     * Adiciona todos os dados do atendimento.
     */
    @Override
    public void adicionar() {

        try {
            SolicitanteDAO objSolicitanteDAO = new SolicitanteDAO(getConnection());
            AtendimentoDAO objAtendimentoDAO = new AtendimentoDAO(getConnection());
            PacienteDAO objPacienteDAO = new PacienteDAO(getConnection());

            beginTransaction();

            //adiciona os dados do solicitante
            objSolicitanteDAO.adicionar(getObjAtendimento().getObjSolicitante());
            //adiciona os dados do atendimento
            objAtendimentoDAO.adicionar(getObjAtendimento());

            //adiciona os dados do(s) paciente(s)
            for (Paciente objPaciente : getObjAtendimento().getObjListaPaciente()) {
                //resgata o id do atendimento para preencher a tabela de pacientes
                objPaciente.setAtendimentoIdAtendimento(getObjAtendimento().getIdAtendimento());
                objPacienteDAO.adicionar(objPaciente);
            }

            //adiciona os dados das viaturas que irão participar do atendimento
            for (Viatura objViatura : getDualListViaturas().getTarget()) {
                objAtendimentoDAO.adicionarViaturaAtendimento(getObjAtendimento().getIdAtendimento(), objViatura);
            }

            //adiciona os dados do(s) médico(s) que irão participar do atendimento.
            for (UsuarioFuncionario objUsuarioFuncionario : getDualListMedicos().getTarget()) {
                objAtendimentoDAO.adicionarFuncionariosAtendimento(getObjAtendimento().getIdAtendimento(), objUsuarioFuncionario);
            }

            //adiciona os do funiconário (atendente) que registrou o atendimento.
            objAtendimentoDAO.adicionarFuncionariosAtendimento(getObjAtendimento().getIdAtendimento(), getUsuarioSessao());

            commitTransaction();
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CODIGO_ATENCIMENTO_SUCESSO + getObjAtendimento().getCodigoAtendimento());
            //limpa todos os campos
            cancelar();
        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
            rollBackTransaction();
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
     * Preenche a PickList das viaturas e dos médicos.
     */
    @Override
    public void consultar() {

        visibilidade(1);

        try {

            //preenche a pickList das viaturas.
            ViaturaDAO objViaturaDAO = new ViaturaDAO(getConnection());
            List<Viatura> listaViatura = objViaturaDAO.consultar();
            setViaturasSource(new ArrayList<Viatura>());
            for (Viatura objViatura : listaViatura) {
                getViaturasSource().add(objViatura);
            }

            //preenche o pickList dos médicos que irão participar do atendimento.
            UsuarioFuncionarioDAO objUsuariosDAO = new UsuarioFuncionarioDAO(getConnection());
            List<UsuarioFuncionario> listaUsuarios = objUsuariosDAO.carregarMedicos();
            setMedicosSource(new ArrayList<UsuarioFuncionario>());
            for (UsuarioFuncionario objUsuario : listaUsuarios) {
                getMedicosSource().add(objUsuario);
            }

            setDualListViaturas(new DualListModel<Viatura>(getViaturasSource(), getViaturasTarget()));
            setDualListMedicos(new DualListModel<UsuarioFuncionario>(getMedicosSource(), getMedicosTarget()));

        } catch (Exception ex) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CONSULTA_ERRO);
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * Cancela/limpa os dados do wizar.
     */
    public void cancelar() {
        setObjAtendimento(new Atendimento());
        consultar();
        setEtapaAtendimento((byte) 1);
        visibilidade(1);
    }

    /**
     * Preenche a lista contendo todos os pacientes cadastrados no formulário.
     */
    public void adicionarPacientes() {
        getObjAtendimento().getObjListaPaciente().add(getObjAtendimento().getObjPaciente());
        getObjAtendimento().setObjPaciente(new Paciente());
        this.setEtapaAtendimento((byte) 3);
        visibilidade(3);
    }

    @Override
    public void novo() {

    }

    @Override
    public void editar() {

    }

    /**
     * Exibe a tela de cadastro do solicitante.
     */
    @Override
    public void voltar() {
        this.setEtapaAtendimento((byte) 2);
        visibilidade(2);
    }

    @Override
    public boolean validar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Não valida os campos ao clicar nas abas.
     *
     * @param event
     */
    public void onTabChange(TabChangeEvent event) {
        FacesContext.getCurrentInstance().renderResponse();
    }

    /**
     * Consulta um atendimento cadastrado.
     */
    @Override
    public void filtrarPesquisa() {

        if (objConsultaAtendimento.getCodigoAtendimento() == 0) {
            addMessage(Constante.TIPO_MENSAGEM_ERROR, Constante.MSG_PREENCHER_CAMPO);
            return;
        }

        try {
            AtendimentoDAO objAtendimentoDAO = new AtendimentoDAO(getConnection());
            this.objListaAtendimento = objAtendimentoDAO.filtrarPesquisa(getObjConsultaAtendimento());

            if (getObjListaAtendimento().isEmpty()) {
                addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_DADOS_NAO_LOCALIZADO);
                getRenderPainelConsultar().setRendered(false);
            } else {
                System.out.println("-->>>OK");
                setObjConsultaAtendimento(getObjListaAtendimento().get(0));
                getRenderPainelConsultar().setRendered(true);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
        } finally {
            closeConnection();
            this.tabSelecionada = 0;
        }
    }

    public Atendimento getObjAtendimento() {
        return objAtendimento;
    }

    public void setObjAtendimento(Atendimento objAtendimento) {
        this.objAtendimento = objAtendimento;
    }

    public byte getEtapaAtendimento() {
        return etapaAtendimento;
    }

    public void setEtapaAtendimento(byte etapaAtendimento) {
        this.etapaAtendimento = etapaAtendimento;
    }

    public HtmlPanelGroup getRenderPainelAtendimento() {
        return renderPainelAtendimento;
    }

    public void setRenderPainelAtendimento(HtmlPanelGroup renderPainelAtendimento) {
        this.renderPainelAtendimento = renderPainelAtendimento;
    }

    public HtmlPanelGroup getRenderPainelSolicitante() {
        return renderPainelSolicitante;
    }

    public void setRenderPainelSolicitante(HtmlPanelGroup renderPainelSolicitante) {
        this.renderPainelSolicitante = renderPainelSolicitante;
    }

    public HtmlPanelGroup getRenderPainelPaciente() {
        return renderPainelPaciente;
    }

    public void setRenderPainelPaciente(HtmlPanelGroup renderPainelPaciente) {
        this.renderPainelPaciente = renderPainelPaciente;
    }

    public HtmlPanelGroup getRenderPainelViaturaMedico() {
        return renderPainelViaturaMedico;
    }

    public void setRenderPainelViaturaMedico(HtmlPanelGroup renderPainelViaturaMedico) {
        this.renderPainelViaturaMedico = renderPainelViaturaMedico;
    }

    public DualListModel<Viatura> getDualListViaturas() {
        return dualListViaturas;
    }

    public void setDualListViaturas(DualListModel<Viatura> dualListViaturas) {
        this.dualListViaturas = dualListViaturas;
    }

    public List<Viatura> getViaturasSource() {
        return viaturasSource;
    }

    public void setViaturasSource(List<Viatura> viaturasSource) {
        this.viaturasSource = viaturasSource;
    }

    public List<Viatura> getViaturasTarget() {
        return viaturasTarget;
    }

    public void setViaturasTarget(List<Viatura> viaturasTarget) {
        this.viaturasTarget = viaturasTarget;
    }

    public DualListModel<UsuarioFuncionario> getDualListMedicos() {
        return dualListMedicos;
    }

    public void setDualListMedicos(DualListModel<UsuarioFuncionario> dualListMedicos) {
        this.dualListMedicos = dualListMedicos;
    }

    public List<UsuarioFuncionario> getMedicosSource() {
        return medicosSource;
    }

    public void setMedicosSource(List<UsuarioFuncionario> medicosSource) {
        this.medicosSource = medicosSource;
    }

    public List<UsuarioFuncionario> getMedicosTarget() {
        return medicosTarget;
    }

    public void setMedicosTarget(List<UsuarioFuncionario> medicosTarget) {
        this.medicosTarget = medicosTarget;
    }

    public List<Atendimento> getObjListaAtendimento() {
        return objListaAtendimento;
    }

    public void setObjListaAtendimento(List<Atendimento> objListaAtendimento) {
        this.objListaAtendimento = objListaAtendimento;
    }

    public Atendimento getObjConsultaAtendimento() {
        return objConsultaAtendimento;
    }

    public void setObjConsultaAtendimento(Atendimento objConsultaAtendimento) {
        this.objConsultaAtendimento = objConsultaAtendimento;
    }

    public HtmlPanelGroup getRenderPainelConsultar() {
        return renderPainelConsultar;
    }

    public void setRenderPainelConsultar(HtmlPanelGroup renderPainelConsultar) {
        this.renderPainelConsultar = renderPainelConsultar;
    }

    public byte getTabSelecionada() {
        return tabSelecionada;
    }

    public void setTabSelecionada(byte tabSelecionada) {
        this.tabSelecionada = tabSelecionada;
    }

}
