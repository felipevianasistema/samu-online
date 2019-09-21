package br.com.samuonline.dao;

import br.com.samuonline.modelo.FichaAph;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class FichaAphDAO extends MainDAO implements DataAccessObject<FichaAph> {

    private Connection con;

    public FichaAphDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Cadastra a ficha APH para o atendimento consultado.
     *
     * @param obj
     * @throws Exception
     */
    @Override
    public void adicionar(FichaAph obj) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Insert into Ficha_Aph (hora_transmissao, hora_chegada_local,"
                + " hora_saida_local, hora_chegada_hospital, hora_ligacao,"
                + " data_hora_registro_ficha, produtos_utilizados)"
                + " values (?,?,?,?,?,?,?) returning idFicha_aph";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getHoraTransmissao());
            stmt.setString(2, obj.getHoraChegadaLocal());
            stmt.setString(3, obj.getHoraSaidaLocal());
            stmt.setString(4, obj.getHoraChegadaHospital());
            stmt.setString(5, obj.getHoraLigacao());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));// registra a data/hora atual do sistema.
            stmt.setString(7, obj.getProdutosUtilizado());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                obj.setIdFichaAph(rs.getInt("idFicha_aph"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    @Override
    public void excluir(FichaAph obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(FichaAph obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FichaAph> consultar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FichaAph> filtrarPesquisa(FichaAph obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
