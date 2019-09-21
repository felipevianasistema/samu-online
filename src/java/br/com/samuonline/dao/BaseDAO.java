package br.com.samuonline.dao;

import br.com.samuonline.modelo.Base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO extends MainDAO implements DataAccessObject<Base> {

    private Connection con;

    public BaseDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Cadastra/adiciona uma nova base.
     *
     * @param objBase
     * @throws Exception
     */
    @Override
    public void adicionar(Base objBase) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into Base (codigo, nome, descricao) values (?, ?, ?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objBase.getCodigo());
            stmt.setString(2, objBase.getNome());
            stmt.setString(3, objBase.getDescricao());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }

    }

    @Override
    public void excluir(Base objBase) throws Exception {

    }

    /**
     * Atualiza as informações de uma base.
     *
     * @param objBase
     * @throws Exception
     */
    @Override
    public void atualizar(Base objBase) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Update Base set codigo = ?, nome = ?, descricao = ? where idBase = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objBase.getCodigo());
            stmt.setString(2, objBase.getNome());
            stmt.setString(3, objBase.getDescricao());
            stmt.setInt(4, objBase.getIdBase());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }

    }

    /**
     * Resgata todas as bases cadastradas.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Base> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Base> listaBase = new ArrayList<Base>();
        String sql = "Select * from Base";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Base objBase = new Base();
                objBase.setIdBase(rs.getInt("idBase"));
                objBase.setCodigo(rs.getInt("codigo"));
                objBase.setNome(rs.getString("nome").toUpperCase());
                objBase.setDescricao(rs.getString("descricao").toUpperCase());
                listaBase.add(objBase);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return listaBase;
    }

    /**
     * Filtra as bases, de acordo com o parâmetro informado.
     *
     * @param objBase
     * @return
     * @throws Exception
     */
    @Override
    public List<Base> filtrarPesquisa(Base objBase) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Base> listaBase = new ArrayList<Base>();
        String sql = "Select * from Base where codigo = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objBase.getCodigo());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Base objBaseResult = new Base();
                objBaseResult.setIdBase(rs.getInt("idBase"));
                objBaseResult.setCodigo(rs.getInt("codigo"));
                objBaseResult.setNome(rs.getString("nome").toUpperCase());
                objBaseResult.setDescricao(rs.getString("descricao").toUpperCase());
                listaBase.add(objBaseResult);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return listaBase;
    }
}
