package br.com.samuonline.dao;

import br.com.samuonline.modelo.Solicitante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SolicitanteDAO extends MainDAO implements DataAccessObject<Solicitante> {

    private Connection con;

    public SolicitanteDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Adiciona os dados do solicitante e retorna o id da linha afetada para
     * registrar na tabela de antedimento.
     *
     * @param objSolicitante
     * @throws Exception
     */
    @Override
    public void adicionar(Solicitante objSolicitante) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Insert into Solicitante (telefone, nome, cidade, bairro,"
                + " endereco, ponto_referencia) values (?,?,?,?,?,?) "
                + "RETURNING idSolicitante";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, objSolicitante.getTelefone());
            stmt.setString(2, objSolicitante.getNome());
            stmt.setString(3, objSolicitante.getCidade());
            stmt.setString(4, objSolicitante.getBairro());
            stmt.setString(5, objSolicitante.getEndereco());
            stmt.setString(6, objSolicitante.getPontoReferencia());
            rs = stmt.executeQuery();

            if (rs.next()) {
                //retorna o id do solicitante cadastrado.
                objSolicitante.setIdSolicitante(rs.getInt(1));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    @Override
    public void excluir(Solicitante objSolicitante) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Solicitante objSolicitante) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Solicitante> consultar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Solicitante> filtrarPesquisa(Solicitante objSolicitante) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
