package br.com.samuonline.dao;

import br.com.samuonline.modelo.RegistroPonto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RegistroPontoDAO extends MainDAO implements DataAccessObject<RegistroPonto> {

    private Connection con;

    public RegistroPontoDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Cadastra o registro de ponto do funcionário.
     *
     * @param objRegistroPonto
     * @throws Exception
     */
    @Override
    public void adicionar(RegistroPonto objRegistroPonto) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into Funcionario_Viatura_Base (base_idBase, login_funcionario_idLogin_funcionario,"
                + " data_hora_registro, entrada_saida) "
                + "values (?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objRegistroPonto.getObjBase().getIdBase());
            stmt.setInt(2, getUsuarioSessao().getIdLoginFuncionario());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setString(4, String.valueOf(objRegistroPonto.getEntradaSaida()));
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public void excluir(RegistroPonto objRegistroPonto) throws Exception {

    }

    @Override
    public void atualizar(RegistroPonto objRegistroPonto) throws Exception {

    }

    @Override
    public List<RegistroPonto> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegistroPonto> listaRegistroPonto = new ArrayList<RegistroPonto>();
        String sql = "Select fvb.*, b.nome, lf.nome as nomeFuncionario from Funcionario_Viatura_Base fvb"
                + " inner join Base b on (b.idBase = fvb.Base_idBase)"
                + " inner join Login_Funcionario lf on (lf.idLogin_Funcionario = fvb.login_funcionario_idLogin_funcionario)"
                + " where login_funcionario_idLogin_funcionario = ? order by fvb.data_hora_registro asc";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, getUsuarioSessao().getIdLoginFuncionario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                RegistroPonto objRegistroPonto = new RegistroPonto();
                objRegistroPonto.setIdFuncionarioViatura(rs.getInt("idFuncionario_Viatura_Base"));
                objRegistroPonto.getObjBase().setNome(rs.getString("nome").toUpperCase());
                objRegistroPonto.getObjUsuarioFuncionario().setNome(rs.getString("nomeFuncionario"));
                objRegistroPonto.setDataHoraRegistro(rs.getTimestamp("data_hora_registro"));
                objRegistroPonto.setEntradaSaida(rs.getString("entrada_saida").charAt(0));
                listaRegistroPonto.add(objRegistroPonto);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return listaRegistroPonto;
    }

    @Override
    public List<RegistroPonto> filtrarPesquisa(RegistroPonto objRegistroPonto) throws Exception {

        List<RegistroPonto> listaRegistroPonto = new ArrayList<RegistroPonto>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select fvb.*, b.nome, lf.nome as nomeFuncionario from Funcionario_Viatura_Base fvb"
                + " inner join Base b on (b.idBase = fvb.Base_idBase)"
                + " inner join Login_Funcionario lf on (lf.idLogin_Funcionario = fvb.login_funcionario_idLogin_funcionario)"
                + " where login_funcionario_idLogin_funcionario = ? and to_char(fvb.data_hora_registro, 'MM') = ?"
                + " order by fvb.data_hora_registro asc";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, getUsuarioSessao().getIdLoginFuncionario());
            stmt.setString(2, objRegistroPonto.getDataFiltro());
            rs = stmt.executeQuery();

            while (rs.next()) {
                RegistroPonto objRegistro = new RegistroPonto();
                objRegistro.setIdFuncionarioViatura(rs.getInt("idFuncionario_Viatura_Base"));
                objRegistro.getObjBase().setNome(rs.getString("nome").toUpperCase());
                objRegistro.getObjUsuarioFuncionario().setNome(rs.getString("nomeFuncionario"));
                objRegistro.setDataHoraRegistro(rs.getTimestamp("data_hora_registro"));
                objRegistro.setEntradaSaida(rs.getString("entrada_saida").charAt(0));
                listaRegistroPonto.add(objRegistro);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return listaRegistroPonto;
    }

}
