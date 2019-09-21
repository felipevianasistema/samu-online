package br.com.samuonline.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class FuncionarioViatura implements Serializable {

    private int idFuncionarioViatura;
    private Viatura objViatura = new Viatura();
    private UsuarioFuncionario objUsuarioFuncionario = new UsuarioFuncionario();
    private Timestamp dataHoraRegistro;
    private char entradaSaida; //E - Entrada/S - Saída
    private Base objBase = new Base();

    public int getIdFuncionarioViatura() {
        return idFuncionarioViatura;
    }

    public void setIdFuncionarioViatura(int idFuncionarioViatura) {
        this.idFuncionarioViatura = idFuncionarioViatura;
    }

    public Viatura getObjViatura() {
        return objViatura;
    }

    public void setObjViatura(Viatura objViatura) {
        this.objViatura = objViatura;
    }

    public UsuarioFuncionario getObjUsuarioFuncionario() {
        return objUsuarioFuncionario;
    }

    public void setObjUsuarioFuncionario(UsuarioFuncionario objUsuarioFuncionario) {
        this.objUsuarioFuncionario = objUsuarioFuncionario;
    }

    public Timestamp getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Timestamp dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public char getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(char entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public Base getObjBase() {
        return objBase;
    }

    public void setObjBase(Base objBase) {
        this.objBase = objBase;
    }

}
