package br.com.samuonline.dao;

import br.com.samuonline.modelo.Viatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViaturaDAO extends MainDAO implements DataAccessObject<Viatura> {

    private Connection con;

    public ViaturaDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Cadastra uma nova viatura na base de dados do sistema.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void adicionar(Viatura objViatura) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into Viatura (codigo, placa, modelo, skype, tipo_viatura, base_idBase) values (?,?,?,?,?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objViatura.getCodigo());
            stmt.setString(2, objViatura.getPlaca());
            stmt.setString(3, objViatura.getModelo().toUpperCase());
            stmt.setString(4, "skype:" + objViatura.getSkype().replaceAll("-", "") + "?call");
            stmt.setString(5, objViatura.getTipoViatura());
            stmt.setInt(6, objViatura.getObjBase().getIdBase());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public void excluir(Viatura objViatura) throws Exception {
    }

    /**
     * Atualiza as informações da viatura selecionada.
     *
     * @param objViatura
     * @throws Exception
     */
    @Override
    public void atualizar(Viatura objViatura) throws Exception {

        PreparedStatement stmt = null;
        String sql = "UPDATE viatura SET codigo=?, modelo=?, skype=?, tipo_viatura=?, placa=? WHERE idViatura = ?;";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objViatura.getCodigo());
            stmt.setString(2, objViatura.getModelo());
            stmt.setString(3, "skype:" + objViatura.getSkype() + "?call");
            stmt.setString(4, objViatura.getTipoViatura());
            stmt.setString(5, objViatura.getPlaca());
            stmt.setInt(6, objViatura.getIdViatura());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    /**
     * Consulta todas as viaturas cadastradas na base de dados.
     *
     * @return List<Viatura>
     * @throws Exception
     */
    @Override
    public List<Viatura> consultar() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select vt.*, bs.nome as nome_base, bs.idBase, bs.codigo as cod_base from Viatura vt "
                + "inner join Base bs on (bs.idBase = vt.base_idBase)"
                + " order by vt.codigo asc";
        List<Viatura> lista = new ArrayList<Viatura>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Viatura objViatura = new Viatura();
                objViatura.setIdViatura(rs.getInt("idviatura"));
                objViatura.setCodigo(rs.getInt("codigo"));
                objViatura.setPlaca(rs.getString("placa"));
                objViatura.setModelo(rs.getString("modelo"));
                objViatura.setSkype(rs.getString("skype"));
                objViatura.setTipoViatura(rs.getString("tipo_viatura"));
                objViatura.getObjBase().setCodigo(rs.getInt("cod_base"));
                objViatura.getObjBase().setIdBase(rs.getInt("idBase"));
                objViatura.getObjBase().setNome(rs.getString("nome_base").toUpperCase());
                lista.add(objViatura);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
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
    public List<Viatura> filtrarPesquisa(Viatura objViatura) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Viatura> lista = new ArrayList<Viatura>();
        String sql = "Select v.*, b.nome as nome_base, b.idBase, b.codigo as codigo_base from Viatura v"
                + " inner join Base b on (v.Base_idBase = b.idBase)"
                + " where v.codigo = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objViatura.getCodigo());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Viatura objViat = new Viatura();
                objViat.setIdViatura(rs.getInt("idviatura"));
                objViat.setCodigo(rs.getInt("codigo"));
                objViat.setPlaca(rs.getString("placa"));
                objViat.setModelo(rs.getString("modelo").toUpperCase());
                objViat.setSkype(rs.getString("skype"));
                objViat.setTipoViatura(rs.getString("tipo_viatura"));
                objViat.getObjBase().setIdBase(rs.getInt("idBase"));
                objViat.getObjBase().setNome(rs.getString("nome_base"));
                objViat.getObjBase().setCodigo(rs.getInt("codigo_base"));
                lista.add(objViat);
            }
            return lista;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }
}
