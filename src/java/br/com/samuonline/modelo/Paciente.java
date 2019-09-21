package br.com.samuonline.modelo;

import java.io.Serializable;
import java.util.Date;

public class Paciente implements Serializable {

    private int idPaciente;
    private String nome;
    private Date dataNascimento;
    private char sexo; //M-Masculino/F-Feminino
    private int atendimentoIdAtendimento;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idPaciente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Paciente other = (Paciente) obj;
        if (this.idPaciente != other.idPaciente) {
            return false;
        }
        return true;
    }
    
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getAtendimentoIdAtendimento() {
        return atendimentoIdAtendimento;
    }

    public void setAtendimentoIdAtendimento(int atendimentoIdAtendimento) {
        this.atendimentoIdAtendimento = atendimentoIdAtendimento;
    }
}
