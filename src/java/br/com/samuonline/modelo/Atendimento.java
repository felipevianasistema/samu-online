package br.com.samuonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Atendimento implements Serializable {

    private int idAtendimento;
    private long codigoAtendimento;
    private FichaAph objFichaAph = new FichaAph();
    private Solicitante objSolicitante = new Solicitante();
    private Paciente objPaciente = new Paciente();
    private byte statusAtendimento;
    private Date dataAtendimento;
    private String motivo;
    private char tipoLigacao; //S-Simples/E-Emergencial
    private Viatura objViaturas = new Viatura();
    private List<Paciente> objListaPaciente = new ArrayList<Paciente>();
    private List<Viatura> objListaViatura = new ArrayList<Viatura>();
    private List<UsuarioFuncionario> objListUsuarioFuncionario = new ArrayList<UsuarioFuncionario>();

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public long getCodigoAtendimento() {
        return codigoAtendimento;
    }

    public void setCodigoAtendimento(long codigoAtendimento) {
        this.codigoAtendimento = codigoAtendimento;
    }

    public FichaAph getObjFichaAph() {
        return objFichaAph;
    }

    public void setObjFichaAph(FichaAph objFichaAph) {
        this.objFichaAph = objFichaAph;
    }

    public Solicitante getObjSolicitante() {
        return objSolicitante;
    }

    public void setObjSolicitante(Solicitante objSolicitante) {
        this.objSolicitante = objSolicitante;
    }

    public Paciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(Paciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    public byte getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(byte statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public char getTipoLigacao() {
        return tipoLigacao;
    }

    public void setTipoLigacao(char tipoLigacao) {
        this.tipoLigacao = tipoLigacao;
    }

    public Viatura getObjViaturas() {
        return objViaturas;
    }

    public void setObjViaturas(Viatura objViaturas) {
        this.objViaturas = objViaturas;
    }

    public List<Paciente> getObjListaPaciente() {
        return objListaPaciente;
    }

    public void setObjListaPaciente(List<Paciente> objListaPaciente) {
        this.objListaPaciente = objListaPaciente;
    }

    public List<Viatura> getObjListaViatura() {
        return objListaViatura;
    }

    public void setObjListaViatura(List<Viatura> objListaViatura) {
        this.objListaViatura = objListaViatura;
    }

    public List<UsuarioFuncionario> getObjListUsuarioFuncionario() {
        return objListUsuarioFuncionario;
    }

    public void setObjListUsuarioFuncionario(List<UsuarioFuncionario> objListUsuarioFuncionario) {
        this.objListUsuarioFuncionario = objListUsuarioFuncionario;
    }
}
