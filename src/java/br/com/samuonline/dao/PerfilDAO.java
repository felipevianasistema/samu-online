package br.com.samuonline.dao;

import br.com.samuonline.modelo.Perfil;
import br.com.samuonline.modelo.Recurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO extends MainDAO implements DataAccessObject<Perfil> {

    private Connection con;

    public PerfilDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Consulta todos os perfis cadastrados na base de dados.
     *
     * @return Lista<Perfil>
     * @throws Exception
     */
    @Override
    public List<Perfil> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from Perfil order by nome asc";
        List<Perfil> lista = new ArrayList<Perfil>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Perfil objPerfil = new Perfil();
                objPerfil.setIdPerfil(rs.getInt("idPerfil"));
                objPerfil.setNome(rs.getString("nome").toUpperCase());
                objPerfil.setDescricao(rs.getString("descricao"));
                lista.add(objPerfil);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    /**
     * Cadastra um novo perfil na base de dados.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void adicionar(Perfil objPerfil) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Recurso[] arrayRecursos;

        String sqlPerfil = "Insert into Perfil (nome, descricao) values (?,?) returning idPerfil";
        String sqlRecursos = "Insert into Perfil_Recurso(Perfil_idPerfil, Recurso_idRecurso) values (?,?)";

        try {
            //cadastra o perfil.
            stmt = con.prepareStatement(sqlPerfil);
            stmt.setString(1, objPerfil.getNome().toUpperCase());
            stmt.setString(2, objPerfil.getDescricao());
            rs = stmt.executeQuery();
            if (rs.next()) {
                //resgata o id do perfil cadastrado.
                objPerfil.setIdPerfil(rs.getInt("idPerfil"));
            }
            //esvazia os parâmetros do stmt.
            stmt.clearParameters();

            //cadastra os recursos do perfil.
            stmt = con.prepareStatement(sqlRecursos);
            arrayRecursos = objPerfil.getArrayRecursos();
            for (int i = 0; i < arrayRecursos.length; i++) {
                stmt.setInt(1, objPerfil.getIdPerfil());
                stmt.setInt(2, arrayRecursos[i].getIdRecurso());
                stmt.execute();
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public void excluir(Perfil obj) throws Exception {

    }

    /**
     * Atualiza as informações do perfil.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void atualizar(Perfil objPerfil) throws Exception {

        PreparedStatement stmt = null;
        String sql = "update Perfil set nome = ?, descricao = ? where idPerfil = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, objPerfil.getNome());
            stmt.setString(2, objPerfil.getDescricao());
            stmt.setInt(3, objPerfil.getIdPerfil());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    /**
     * Filtra/pesquisa as informações da grid.
     *
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public List<Perfil> filtrarPesquisa(Perfil objPerfil) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Perfil> lista = new ArrayList<Perfil>();
        String sql = "Select * from Perfil where nome like ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, '%' + objPerfil.getNome().toUpperCase() + '%');
            rs = stmt.executeQuery();

            while (rs.next()) {
                Perfil objPerf = new Perfil();
                objPerf.setIdPerfil(rs.getInt("idPerfil"));
                objPerf.setNome(rs.getString("nome").toUpperCase());
                objPerf.setDescricao(rs.getString("descricao"));
                lista.add(objPerf);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    /**
     * Resgata todos os recursos do perfil selecionado.
     *
     * @param objPerfil
     * @return
     * @throws Exception
     */
    public Recurso[] resgatarPerfilRecurso(Perfil objPerfil) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from Perfil_Recurso where Perfil_idPerfil = ?";
        List<Recurso> listaRecurso = new ArrayList<Recurso>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objPerfil.getIdPerfil());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Recurso objRecurso = new Recurso();
                objRecurso.setIdRecurso(rs.getInt("Recurso_idRecurso"));
                listaRecurso.add(objRecurso);
            }
            return listaRecurso.toArray(new Recurso[listaRecurso.size()]);
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    /**
     * Atualiza todos os recursos do perfil. Remove tudo e cadastra.
     *
     * @param objPerfil
     * @throws Exception
     */
    public void atualizarPerfilRecurso(Perfil objPerfil) throws Exception {

        PreparedStatement stmt = null;
        String sql = "delete from Perfil_Recurso where Perfil_idPerfil = ?";

        try {
            //remove tudo.
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objPerfil.getIdPerfil());
            stmt.execute();
            //remove os parametros.
            stmt.clearParameters();

            //cadastra os recursos do perfil.
            String sql2 = "Insert into Perfil_Recurso (perfil_idPerfil, recurso_idrecurso) values (?,?)";
            stmt = con.prepareStatement(sql2);

            for (Recurso objRecurso : objPerfil.getArrayRecursos()) {
                stmt.setInt(1, objPerfil.getIdPerfil());
                stmt.setInt(2, objRecurso.getIdRecurso());
                stmt.execute();
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }
}
