package br.com.samuonline.modelo;

import java.io.Serializable;

public class PerfilRecurso implements Serializable {

    private int idPerfilRecurso;
    private int PerfilIdPerfil;
    private int RecursoIdRecurso;

    public int getIdPerfilRecurso() {
        return idPerfilRecurso;
    }

    public void setIdPerfilRecurso(int idPerfilRecurso) {
        this.idPerfilRecurso = idPerfilRecurso;
    }

    public int getPerfilIdPerfil() {
        return PerfilIdPerfil;
    }

    public void setPerfilIdPerfil(int PerfilIdPerfil) {
        this.PerfilIdPerfil = PerfilIdPerfil;
    }

    public int getRecursoIdRecurso() {
        return RecursoIdRecurso;
    }

    public void setRecursoIdRecurso(int RecursoIdRecurso) {
        this.RecursoIdRecurso = RecursoIdRecurso;
    }
}
