package br.com.samuonline.dao;

import br.com.samuonline.modelo.UsuarioFuncionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.context.FacesContext;

public class MainDAO {

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
     * Finaliza o PreparedStatmente e o ResultSet.
     *
     * @param stmt
     * @param rs
     */
    public void closePreparedResult(PreparedStatement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception ex) {
        }

        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception ex) {
        }
    }
}
