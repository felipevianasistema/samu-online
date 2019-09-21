package br.com.samuonline.modelo;

import java.io.Serializable;

public class LoginFuncionarioAtendimento implements Serializable {

    private int idLoginFuncionarioAtendimento;
    private LoginFuncionarioAtendimento objLoginFuncionario = new LoginFuncionarioAtendimento();
    private Atendimento objAtendimento = new Atendimento();

    public int getIdLoginFuncionarioAtendimento() {
        return idLoginFuncionarioAtendimento;
    }

    public void setIdLoginFuncionarioAtendimento(int idLoginFuncionarioAtendimento) {
        this.idLoginFuncionarioAtendimento = idLoginFuncionarioAtendimento;
    }

    public LoginFuncionarioAtendimento getObjLoginFuncionario() {
        return objLoginFuncionario;
    }

    public void setObjLoginFuncionario(LoginFuncionarioAtendimento objLoginFuncionario) {
        this.objLoginFuncionario = objLoginFuncionario;
    }

    public Atendimento getObjAtendimento() {
        return objAtendimento;
    }

    public void setObjAtendimento(Atendimento objAtendimento) {
        this.objAtendimento = objAtendimento;
    }

}
