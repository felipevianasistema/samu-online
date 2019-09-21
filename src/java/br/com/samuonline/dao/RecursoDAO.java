package br.com.samuonline.dao;

import br.com.samuonline.modelo.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecursoDAO extends MainDAO implements DataAccessObject {

    private Connection con;

    public RecursoDAO(Connection connection) {
        this.con = connection;
    }

    @Override
    public void adicionar(Object obj) throws Exception {

    }

    @Override
    public void excluir(Object obj) throws Exception {

    }

    @Override
    public void atualizar(Object obj) throws Exception {

    }

    /**
     * Consulta/resgata todos os recursos cadastrados.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Recurso> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Recurso> lista = new ArrayList<Recurso>();
        String sql = "Select * from Recurso order by nome asc";

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Recurso objRecurso = new Recurso();
                objRecurso.setIdRecurso(rs.getInt("idRecurso"));
                objRecurso.setNome(rs.getString("nome"));
                objRecurso.setDescricao(rs.getString("descricao"));
                lista.add(objRecurso);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    /**
     * Retorna todos os recursos relacionado ao perfil do usuário autenticado no
     * sistema.
     *
     * @return
     */
    public List<Recurso> resgatarRecursosPerfil(int idPerfil) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Recurso> listaRecurso = new ArrayList<Recurso>();
        String sql = "Select rec.* from Perfil_Recurso pr "
                + "inner join Recurso rec on (rec.idRecurso = pr.Recurso_idRecurso) "
                + "where pr.perfil_idPerfil = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPerfil);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Recurso objRecurso = new Recurso();
                objRecurso.setIdRecurso(rs.getInt("idRecurso"));
                objRecurso.setNome(rs.getString("nome"));
                objRecurso.setDescricao(rs.getString("descricao"));
                listaRecurso.add(objRecurso);
            }
            return listaRecurso;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }

    }

    @Override
    public List<?> filtrarPesquisa(Object obj) throws Exception {
        return null;

    }
}
