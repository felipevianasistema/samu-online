package br.com.samuonline.dao;

import br.com.samuonline.modelo.UsuarioFuncionario;
import br.com.samuonline.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFuncionarioDAO extends MainDAO implements DataAccessObject<UsuarioFuncionario> {

    private Connection con;

    public UsuarioFuncionarioDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Cadastra um novo usuário/funiconário na base de dados.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void adicionar(UsuarioFuncionario objLoginFuncionario) throws Exception {

        PreparedStatement stmt = null;
        String sql = "insert into Login_Funcionario (viatura_idViatura, perfil_idPerfil,"
                + " nome, usuario, senha, cpf, situacao, skype, email)"
                + " values (?,?,?,?,?,?,?,?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objLoginFuncionario.getViaturaIdViatura());
            stmt.setInt(2, objLoginFuncionario.getPerfilIdPerfil());
            stmt.setString(3, objLoginFuncionario.getNome().toUpperCase());
            stmt.setString(4, objLoginFuncionario.getUsuario());
            stmt.setString(5, Util.getMD5(objLoginFuncionario.getSenha()));
            stmt.setString(6, objLoginFuncionario.getCpf().replaceAll("\\D", ""));
            stmt.setByte(7, objLoginFuncionario.getSituacao());
            stmt.setString(8, "skype:" + objLoginFuncionario.getSkype().replaceAll("-", "") + "?call");
            stmt.setString(9, objLoginFuncionario.getEmail());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public void excluir(UsuarioFuncionario obj) throws Exception {
    }

    /**
     * Atualiza as informações de um usuário específico.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void atualizar(UsuarioFuncionario objLoginFuncionario) throws Exception {

        PreparedStatement stmt = null;
        String sql = "update Login_Funcionario set perfil_idPerfil = ?,"
                + " nome = ?, usuario = ?, cpf = ?, situacao = ?, skype = ?, email = ? "
                + "where idLogin_funcionario = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objLoginFuncionario.getPerfilIdPerfil());
            stmt.setString(2, objLoginFuncionario.getNome().toUpperCase());
            stmt.setString(3, objLoginFuncionario.getUsuario());
            //stmt.setString(5, Util.getMD5(objLoginFuncionario.getSenha()));
            stmt.setString(4, objLoginFuncionario.getCpf().replaceAll("\\D", ""));
            stmt.setByte(5, objLoginFuncionario.getSituacao());
            stmt.setString(6, "skype:" + objLoginFuncionario.getSkype().replaceAll("-", "") + "?call");
            stmt.setString(7, objLoginFuncionario.getEmail());
            stmt.setInt(8, objLoginFuncionario.getIdLoginFuncionario());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    /**
     * Consulta todos os usuário cadastrados na base de dados.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UsuarioFuncionario> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UsuarioFuncionario> lista = new ArrayList<UsuarioFuncionario>();
        String sql = "select lf.*, pe.nome as perfil_nome from login_funcionario lf "
                + "inner join Perfil pe on (pe.idPerfil = lf.perfil_idPerfil) order by nome asc";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                UsuarioFuncionario objLoginFuncionario = new UsuarioFuncionario();
                objLoginFuncionario.setIdLoginFuncionario(rs.getInt("idLogin_funcionario"));
                objLoginFuncionario.setPerfilIdPerfil(rs.getInt("perfil_idPerfil"));
                objLoginFuncionario.setNome(rs.getString("nome"));
                objLoginFuncionario.setUsuario(rs.getString("usuario"));
                objLoginFuncionario.setSenha("");
                objLoginFuncionario.setCpf(rs.getString("cpf"));
                objLoginFuncionario.setSituacao(rs.getByte("situacao"));
                objLoginFuncionario.setSkype(rs.getString("skype"));
                objLoginFuncionario.setEmail(rs.getString("email"));
                objLoginFuncionario.getObjPerfil().setNome(rs.getString("perfil_nome"));
                lista.add(objLoginFuncionario);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    /**
     * Filtra/pesquisa o(s) usuário que deverão ser exibidos na grid.
     */
    @Override
    public List<UsuarioFuncionario> filtrarPesquisa(UsuarioFuncionario objLoginFuncionario) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<UsuarioFuncionario> lista = new ArrayList<UsuarioFuncionario>();
        String sql = "select lf.*, pe.nome as perfil_nome from login_funcionario lf "
                + "inner join Perfil pe on (pe.idPerfil = lf.perfil_idPerfil) "
                + "where lf.nome like ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, '%' + objLoginFuncionario.getNome().toUpperCase() + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                UsuarioFuncionario objLoginFunc = new UsuarioFuncionario();
                objLoginFunc.setIdLoginFuncionario(rs.getInt("idLogin_funcionario"));
                objLoginFunc.setPerfilIdPerfil(rs.getInt("perfil_idPerfil"));
                objLoginFunc.setNome(rs.getString("nome"));
                objLoginFunc.setUsuario(rs.getString("usuario"));
                objLoginFuncionario.setSenha("");
                objLoginFunc.setCpf(rs.getString("cpf"));
                objLoginFunc.setSituacao(rs.getByte("situacao"));
                objLoginFunc.setSkype(rs.getString("skype"));
                objLoginFunc.setEmail(rs.getString("email"));
                objLoginFunc.getObjPerfil().setNome(rs.getString("perfil_nome"));
                lista.add(objLoginFunc);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }

    }

    /**
     *
     * Atualiza a senha do usuário.
     *
     * @param objUsuario
     */
    public void redefinirSenha(UsuarioFuncionario objUsuario) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Update Login_Funcionario set senha = ? where idLogin_Funcionario = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, Util.getMD5(objUsuario.getSenha()));
            stmt.setInt(2, objUsuario.getIdLoginFuncionario());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    /**
     * Retorna todos os funcionários que possui o perfil médico.
     *
     * @return
     * @throws Exception
     */
    public List<UsuarioFuncionario> carregarMedicos() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select lf.nome, lf.idLogin_funcionario from Login_Funcionario lf"
                + " inner join Perfil perf on (perf.idPerfil = lf.Perfil_idPerfil)"
                + " Where perf.idPerfil = ?";
        List<UsuarioFuncionario> lista = new ArrayList<UsuarioFuncionario>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, 2);// médicos.
            rs = stmt.executeQuery();

            while (rs.next()) {
                UsuarioFuncionario objLoginFunc = new UsuarioFuncionario();
                objLoginFunc.setIdLoginFuncionario(rs.getInt("idLogin_funcionario"));
                objLoginFunc.setNome(rs.getString("nome"));
                lista.add(objLoginFunc);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return lista;
    }
}
