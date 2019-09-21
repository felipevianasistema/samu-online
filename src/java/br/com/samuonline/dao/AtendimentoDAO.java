package br.com.samuonline.dao;

import br.com.samuonline.modelo.Atendimento;
import br.com.samuonline.modelo.Paciente;
import br.com.samuonline.modelo.UsuarioFuncionario;
import br.com.samuonline.modelo.Viatura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO extends MainDAO implements DataAccessObject<Atendimento> {

    private Connection con;

    public AtendimentoDAO(Connection connection) {
        this.con = connection;
    }

    /**
     * Adiciona os dados da tabela de atendimento e retorna o id da linha
     * cadastrada.
     *
     * @param objAtendimento
     * @throws Exception
     */
    @Override
    public void adicionar(Atendimento objAtendimento) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        long millis = System.currentTimeMillis();

        String sql = "Insert into Atendimento (codigo_atendimento,"
                + " solicitante_idSolicitante, status_atendimento, data_atendimento, motivo,"
                + " tipo_ligacao) values (?,?,?,?,?,?) RETURNING idAtendimento, codigo_atendimento";

        try {
            stmt = con.prepareStatement(sql);
            //registra id do usuário + subString do millisegundos + ano atual.
            stmt.setLong(1, Long.parseLong(getUsuarioSessao().getIdLoginFuncionario() + String.valueOf(millis).substring(10, String.valueOf(millis).length()) + sdf.format(new java.util.Date())));
            stmt.setInt(2, objAtendimento.getObjSolicitante().getIdSolicitante());
            stmt.setByte(3, objAtendimento.getStatusAtendimento());
            stmt.setDate(4, new Date(new java.util.Date().getTime()));
            stmt.setString(5, objAtendimento.getMotivo());
            stmt.setString(6, String.valueOf(objAtendimento.getTipoLigacao()));
            rs = stmt.executeQuery();

            if (rs.next()) {
                //retorna o id da linha cadastrada
                objAtendimento.setIdAtendimento(rs.getInt(1));
                objAtendimento.setCodigoAtendimento(rs.getLong(2));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
    }

    @Override
    public void excluir(Atendimento objAtendimento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Relaciona a ficha aph ao atendimento e altera o status do atendimento.
     *
     * @param objAtendimento
     * @throws Exception
     */
    @Override
    public void atualizar(Atendimento objAtendimento) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Update Atendimento set ficha_aph_idFicha_aph = ?,"
                + " status_atendimento = ? where idAtendimento = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objAtendimento.getObjFichaAph().getIdFichaAph());
            stmt.setByte(2, objAtendimento.getStatusAtendimento());
            stmt.setInt(3, objAtendimento.getIdAtendimento());
            stmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    @Override
    public List<Atendimento> consultar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Consulta um atendimento cadastrado.
     *
     * @param objAtendimento
     * @return
     * @throws Exception
     */
    @Override
    public List<Atendimento> filtrarPesquisa(Atendimento objAtendimento) throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Atendimento> listaAtendimento = new ArrayList<Atendimento>();
        String sql = "Select atend.*, viat.idViatura, viat.codigo, viat.placa, viat.modelo, lf.idLogin_Funcionario, lf.nome as nome_usuario, lf.cpf, lf.email,"
                + " ficha_a.*, solic.*, paci.nome as nome_paciente, paci.data_nascimento,"
                + " paci.sexo, paci.idPaciente,perf.nome as nome_perfil from Atendimento atend"
                + " inner join Atendimento_Viatura atend_v on (atend_v.Atendimento_idAtendimento = atend.idAtendimento)"
                + " inner join Viatura viat on (viat.idViatura = atend_v.Viatura_idViatura)"
                + " left join Ficha_Aph ficha_a on (ficha_a.idFicha_Aph = atend.Ficha_Aph_idFicha_Aph)"
                + " inner join Solicitante solic on (solic.idSolicitante = atend.Solicitante_idSolicitante)"
                + " inner join Paciente paci on (paci.Atendimento_idAtendimento = atend.idAtendimento)"
                + " inner join Login_Funcionario_Atendimento lfa on (lfa.Atendimento_idAtendimento = atend.idAtendimento)"
                + " inner join Login_Funcionario lf on (lf.idLogin_Funcionario = lfa.Login_Funcionario_idLogin_Funcionario)"
                + " inner join Perfil perf on (perf.idPerfil = lf.Perfil_idPerfil)"
                + " where atend.codigo_atendimento = ? and perf.idPerfil = 2";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, objAtendimento.getCodigoAtendimento());
            rs = stmt.executeQuery();

            Atendimento objAtend = new Atendimento();
            int cont = 0;

            while (rs.next()) {
                //os dados do atendimento sempre retorna os mesmo, então eu só preciso adicionar uma vez.
                if (cont == 0) {
                    //atendimento
                    objAtend.setIdAtendimento(rs.getInt("idatendimento"));
                    objAtend.setCodigoAtendimento(rs.getLong("codigo_atendimento"));
                    objAtend.setStatusAtendimento(rs.getByte("status_atendimento"));
                    objAtend.setDataAtendimento(rs.getDate("data_atendimento"));
                    objAtend.setMotivo(rs.getString("motivo"));
                    objAtend.setTipoLigacao(rs.getString("tipo_ligacao").charAt(0));

                    //solicitante
                    objAtend.getObjSolicitante().setTelefone(rs.getString("telefone"));
                    objAtend.getObjSolicitante().setNome(rs.getString("nome"));
                    objAtend.getObjSolicitante().setCidade(rs.getString("cidade"));
                    objAtend.getObjSolicitante().setBairro(rs.getString("bairro"));
                    objAtend.getObjSolicitante().setEndereco(rs.getString("endereco"));
                    objAtend.getObjSolicitante().setPontoReferencia(rs.getString("ponto_referencia"));
                    //ficha aph
                    objAtend.getObjFichaAph().setIdFichaAph(rs.getInt("idFicha_aph"));
                    objAtend.getObjFichaAph().setHoraTransmissao(rs.getString("hora_transmissao"));
                    objAtend.getObjFichaAph().setHoraChegadaLocal(rs.getString("hora_chegada_local"));
                    objAtend.getObjFichaAph().setHoraSaidaLocal(rs.getString("hora_saida_local"));
                    objAtend.getObjFichaAph().setHoraChegadaHospital(rs.getString("hora_chegada_hospital"));
                    objAtend.getObjFichaAph().setHoraLigacao(rs.getString("hora_ligacao"));
                    objAtend.getObjFichaAph().setDataHoraRegistroFicha(rs.getTimestamp("data_hora_registro_ficha"));
                    objAtend.getObjFichaAph().setProdutosUtilizado(rs.getString("produtos_utilizados"));

                    cont++;
                }
                //viaturas.
                Viatura objViatura = new Viatura();
                objViatura.setIdViatura(rs.getInt("idViatura"));
                objViatura.setPlaca(rs.getString("placa"));
                objViatura.setModelo(rs.getString("modelo"));

                if (!objAtend.getObjListaViatura().contains(objViatura)) {
                    objAtend.getObjListaViatura().add(objViatura);
                }
                //login funcionário.
                UsuarioFuncionario objUsuarioFuncionario = new UsuarioFuncionario();
                objUsuarioFuncionario.setIdLoginFuncionario(rs.getInt("idLogin_Funcionario"));
                objUsuarioFuncionario.setNome(rs.getString("nome_usuario"));
                objUsuarioFuncionario.setCpf(rs.getString("cpf"));
                objUsuarioFuncionario.setEmail(rs.getString("email"));
                //perfil.
                objUsuarioFuncionario.getObjPerfil().setNome(rs.getString("nome_perfil"));
                if (!objAtend.getObjListUsuarioFuncionario().contains(objUsuarioFuncionario)) {
                    objAtend.getObjListUsuarioFuncionario().add(objUsuarioFuncionario);
                }
                //paciente.
                Paciente objPaciente = new Paciente();
                objPaciente.setIdPaciente(rs.getInt("idPaciente"));
                objPaciente.setNome(rs.getString("nome_paciente"));
                objPaciente.setDataNascimento(rs.getDate("data_nascimento"));
                objPaciente.setSexo(rs.getString("sexo").charAt(0));

                //verifica se o paciente já foi adicionado a lista.
                if (!objAtend.getObjListaPaciente().contains(objPaciente)) {
                    objAtend.getObjListaPaciente().add(objPaciente);
                }
            }

            //verifica se houve algum atendimento localizado
            if (objAtend.getIdAtendimento() > 0) {
                //atendimento localizado.
                listaAtendimento.add(objAtend);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return listaAtendimento;
    }

    public List<String> consultarUltimosAtendimentos() throws Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select codigo_atendimento from Atendimento LIMIT 8";
        List<String> objLista = new ArrayList<String>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                objLista.add(String.valueOf(rs.getInt("codigo_atendimento")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, rs);
        }
        return objLista;
    }

    /**
     * Adiciona as viaturas que participaram do atendimento.
     *
     * @param idAtendimento
     * @param objViatura
     * @throws Exception
     */
    public void adicionarViaturaAtendimento(int idAtendimento, Viatura objViatura) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into Atendimento_Viatura (atendimento_idAtendimento, viatura_idViatura) values (?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAtendimento);
            stmt.setInt(2, objViatura.getIdViatura());
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

    /**
     *
     * Filtro de pesquisa, retorna o contrato cadastrado de acordo com o códgo
     * informado.
     *
     * @param objAtendimento
     * @return
     */
    public Atendimento pesquisarAtendimentoSimples(Atendimento objAtendimento) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "Select idAtendimento, status_atendimento, ficha_aph_idFicha_aph from Atendimento"
                + " where codigo_atendimento = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, objAtendimento.getCodigoAtendimento());
            rs = stmt.executeQuery();

            if (rs.next()) {
                objAtendimento.setIdAtendimento(rs.getInt("idAtendimento"));
                objAtendimento.setStatusAtendimento(rs.getByte("status_atendimento"));
                objAtendimento.getObjFichaAph().setIdFichaAph(rs.getInt("ficha_aph_idFicha_aph"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePreparedResult(stmt, rs);
        }
        return objAtendimento;
    }

    /**
     * Adiciona todos os funcionários que irão participar do atendimento.
     *
     * @param idAtendimento
     * @param objUsuarioFuncionario
     * @throws Exception
     */
    public void adicionarFuncionariosAtendimento(int idAtendimento, UsuarioFuncionario objUsuarioFuncionario) throws Exception {

        PreparedStatement stmt = null;
        String sql = "Insert into LOGIN_FUNCIONARIO_ATENDIMENTO"
                + "(LOGIN_FUNCIONARIO_IDLOGIN_FUNCIONARIO, ATENDIMENTO_IDATENDIMENTO)"
                + " values (?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, objUsuarioFuncionario.getIdLoginFuncionario());
            stmt.setInt(2, idAtendimento);
            stmt.execute();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closePreparedResult(stmt, null);
        }
    }

}
