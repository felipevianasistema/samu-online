/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.samuonline.mb;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.dao.AtendimentoDAO;
import br.com.samuonline.dao.FichaAphDAO;
import br.com.samuonline.modelo.Atendimento;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGroup;

/**
 *
 * @author Felipe
 */
@javax.faces.bean.ManagedBean
@ViewScoped
public class FichaAphMB extends MainMB implements ManagedBean {

    private Atendimento objAtendimento = new Atendimento();
    private HtmlPanelGroup renderPanelFicha = new HtmlPanelGroup();

    public FichaAphMB() {
        getRenderPanelFicha().setRendered(false);
    }

    /**
     * Cadastra a ficha APH para o atendimento consultado.
     */
    @Override
    public void adicionar() {

        try {
            FichaAphDAO objFichaDAO = new FichaAphDAO(getConnection());
            beginTransaction();
            objFichaDAO.adicionar(getObjAtendimento().getObjFichaAph());
            AtendimentoDAO atendimentoDAO = new AtendimentoDAO(getConnection());
            atendimentoDAO.atualizar(objAtendimento);
            commitTransaction();
            addMessage(Constante.TIPO_MENSAGEM_INFO, Constante.MSG_CADASTRAR_SUCESSO);
            getRenderPanelFicha().setRendered(false);
            setObjAtendimento(new Atendimento());
        } catch (Exception e) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_CADASTRAR_FALHA);
            rollBackTransaction();
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void novo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void voltar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean validar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Filtro de pesquisa, retorna o contrato cadastrado de acordo com o códgo
     * informado.
     */
    @Override
    public void filtrarPesquisa() {

        try {
            AtendimentoDAO objAtendimentoDAO = new AtendimentoDAO(getConnection());
            this.objAtendimento = objAtendimentoDAO.pesquisarAtendimentoSimples(getObjAtendimento());
        
            if(getObjAtendimento().getObjFichaAph().getIdFichaAph() > 0){
                //ficha já cadastrada.
                getRenderPanelFicha().setRendered(false);
                addMessage(Constante.TIPO_MENSAGEM_WARN, Constante.MSG_FICHA_JA_CADATRADA);
                setObjAtendimento(new Atendimento());
                return;
            }else if (getObjAtendimento().getIdAtendimento() == 0) {
                addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_DADOS_NAO_LOCALIZADO);
                getRenderPanelFicha().setRendered(false);
                return;
            }
            getRenderPanelFicha().setRendered(true);
        } catch (Exception e) {
            addMessage(Constante.TIPO_MENSAGEM_FATAL, Constante.MSG_FILTRO_PESQUISA_ERRO);
            getRenderPanelFicha().setRendered(false);
        } finally {
            closeConnection();
        }
    }

    public Atendimento getObjAtendimento() {
        return objAtendimento;
    }

    public void setObjAtendimento(Atendimento objAtendimento) {
        this.objAtendimento = objAtendimento;
    }

    public HtmlPanelGroup getRenderPanelFicha() {
        return renderPanelFicha;
    }

    public void setRenderPanelFicha(HtmlPanelGroup renderPanelFicha) {
        this.renderPanelFicha = renderPanelFicha;
    }

}
