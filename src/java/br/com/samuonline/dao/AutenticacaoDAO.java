package br.com.samuonline.dao;

import br.com.samuonline.modelo.UsuarioFuncionario;
import br.com.samuonline.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutenticacaoDAO extends MainDAO {

    private Connection con;

    public AutenticacaoDAO(Connection connection) {
        this.con = connection;
    }

    public UsuarioFuncionario autenticar(UsuarioFuncionario objLoginFuncionario) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select lf.*, perf.nome as nome_perfil from Login_Funcionario lf "
                + "inner join Perfil perf on (perf.idPerfil = lf.perfil_idPerfil) "
                + "where (usuario = ? and senha = ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, objLoginFuncionario.getUsuario());
            stmt.setString(2, Util.getMD5(objLoginFuncionario.getSenha()));
            rs = stmt.executeQuery();

            if (rs.next()) {
                objLoginFuncionario.setIdLoginFuncionario(rs.getInt("idLogin_Funcionario"));
                objLoginFuncionario.setPerfilIdPerfil(rs.getInt("perfil_idPerfil"));
                objLoginFuncionario.getObjPerfil().setNome(rs.getString("nome_perfil"));
                objLoginFuncionario.setNome(rs.getString("nome"));
                objLoginFuncionario.setSenha("");
                objLoginFuncionario.setCpf(rs.getString("cpf"));
                objLoginFuncionario.setSituacao(rs.getByte("situacao"));
                objLoginFuncionario.setSkype(rs.getString("skype"));
                objLoginFuncionario.setEmail(rs.getString("email"));

                return objLoginFuncionario;
            } else {
                return null;
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }
}
