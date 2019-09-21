package br.com.samuonline.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioFuncionario implements Serializable {

    private int idLoginFuncionario;
    private int viaturaIdViatura = -1;
    private int PerfilIdPerfil = -1;
    private String nome;
    private String usuario;
    private String senha;
    private String repetirSenha;
    private String cpf;
    private byte situacao = (byte) -1; // 1 - ativo / 0 - inativo.
    private String skype;
    private String email;
    private Perfil objPerfil = new Perfil();
    private List<Recurso> objListarecursos = new ArrayList<Recurso>();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idLoginFuncionario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final UsuarioFuncionario other = (UsuarioFuncionario) obj;
        if (this.idLoginFuncionario != other.idLoginFuncionario) {
            return false;
        }
        return true;
    }

    public int getIdLoginFuncionario() {
        return idLoginFuncionario;
    }

    public void setIdLoginFuncionario(int idLoginFuncionario) {
        this.idLoginFuncionario = idLoginFuncionario;
    }

    public int getViaturaIdViatura() {
        return viaturaIdViatura;
    }

    public void setViaturaIdViatura(int viaturaIdViatura) {
        this.viaturaIdViatura = viaturaIdViatura;
    }

    public int getPerfilIdPerfil() {
        return PerfilIdPerfil;
    }

    public void setPerfilIdPerfil(int PerfilIdPerfil) {
        this.PerfilIdPerfil = PerfilIdPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public byte getSituacao() {
        return situacao;
    }

    public void setSituacao(byte situacao) {
        this.situacao = situacao;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }

    public List<Recurso> getObjListarecursos() {
        return objListarecursos;
    }

    public void setObjListarecursos(List<Recurso> objListarecursos) {
        this.objListarecursos = objListarecursos;
    }
}
