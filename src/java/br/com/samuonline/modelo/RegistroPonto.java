package br.com.samuonline.modelo;

import java.sql.Timestamp;

public class RegistroPonto {

    private int idFuncionarioViatura;
    private Base objBase = new Base();
    private UsuarioFuncionario objUsuarioFuncionario = new UsuarioFuncionario();
    private Timestamp dataHoraRegistro;
    private char entradaSaida = 'E';
    private String dataFiltro;

    public int getIdFuncionarioViatura() {
        return idFuncionarioViatura;
    }

    public void setIdFuncionarioViatura(int idFuncionarioViatura) {
        this.idFuncionarioViatura = idFuncionarioViatura;
    }

    public Base getObjBase() {
        return objBase;
    }

    public void setObjBase(Base objBase) {
        this.objBase = objBase;
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

    public String getDataFiltro() {
        return dataFiltro;
    }

    public void setDataFiltro(String dataFiltro) {
        this.dataFiltro = dataFiltro;
    }
}
