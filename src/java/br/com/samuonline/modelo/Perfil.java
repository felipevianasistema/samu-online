package br.com.samuonline.modelo;

import java.io.Serializable;

public class Perfil implements Serializable {

    private int idPerfil;
    private String nome;
    private String descricao;
    private Recurso[] arrayRecursos;

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Recurso[] getArrayRecursos() {
        return arrayRecursos;
    }

    public void setArrayRecursos(Recurso[] arrayRecursos) {
        this.arrayRecursos = arrayRecursos;
    }
}
