package br.com.samuonline.dao;

import br.com.samuonline.modelo.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PacienteDAO extends MainDAO implements DataAccessObject<Paciente> {

    private Connection con;

    public PacienteDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Adiciona os dados do paciente.
     *
     * @param objPaciente
     * @throws Exception
     */
    @Override
    public void adicionar(Paciente objPaciente) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into Paciente (Atendimento_idAtendimento, nome,"
                + " data_nascimento, sexo) values (?,?,?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objPaciente.getAtendimentoIdAtendimento());
            stmt.setString(2, objPaciente.getNome());
            stmt.setDate(3, new java.sql.Date(objPaciente.getDataNascimento().getTime()));
            stmt.setString(4, String.valueOf(objPaciente.getSexo()));
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public void excluir(Paciente objPaciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Paciente objPaciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> consultar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paciente> filtrarPesquisa(Paciente objPaciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
