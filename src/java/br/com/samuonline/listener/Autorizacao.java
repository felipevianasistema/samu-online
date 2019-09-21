package br.com.samuonline.listener;

import br.com.samuonline.constantes.Constante;
import br.com.samuonline.mb.MainMB;
import br.com.samuonline.modelo.Recurso;
import br.com.samuonline.modelo.UsuarioFuncionario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class Autorizacao extends MainMB implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        boolean isLoginPage = (currentPage.lastIndexOf("autenticacao.xhtml") > -1);
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute("autenticacao");

        if (!isLoginPage && currentUser == null) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "autenticacaoPage");
            //exibe a mensagem para o usuário.
            addMessage(Constante.TIPO_MENSAGEM_WARN, Constante.MSG_SESSAO_EXPIRADA);
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    /**
     * Indica em qual ciclo de vida do JSF irá ser executado.
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    /**
     * Verifica se o usuário tentou acessar uma página que não possui permissão.
     *
     * @param obj
     * @param currentPage
     * @return
     */
    private boolean verificarPermissaoPagina(Object obj, String currentPage) throws Exception {

        try {

            if (currentPage.equals("//autenticacao.xhtml")
                    || currentPage.equals("/index.xhtml")) {
                return true;
            }

            UsuarioFuncionario objUsuario = new UsuarioFuncionario();
            objUsuario = (UsuarioFuncionario) obj;

            for (Recurso rec : objUsuario.getObjListarecursos()) {
                //usuário possui permissão para acessar a página.
                if (rec.getNome().trim().equals(currentPage.trim())) {

                    return true;
                }
            }
            return false;

        } catch (Exception ex) {
            throw ex;
        }
    }
}
