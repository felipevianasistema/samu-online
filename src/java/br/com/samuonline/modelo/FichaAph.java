package br.com.samuonline.modelo;

import java.io.Serializable;
import java.sql.Timestamp;


public class FichaAph implements Serializable {

    private int idAtendimento; // utilizado no registro da ficha aph.
    private int idFichaAph;
    private String horaTransmissao;
    private String horaChegadaLocal;
    private String horaSaidaLocal;
    private String horaChegadaHospital;
    private String horaLigacao;
    private Timestamp dataHoraRegistroFicha;
    private String produtosUtilizado;

    public int getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(int idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public int getIdFichaAph() {
        return idFichaAph;
    }

    public void setIdFichaAph(int idFichaAph) {
        this.idFichaAph = idFichaAph;
    }

    public String getHoraTransmissao() {
        return horaTransmissao;
    }

    public void setHoraTransmissao(String horaTransmissao) {
        this.horaTransmissao = horaTransmissao;
    }

    public String getHoraChegadaLocal() {
        return horaChegadaLocal;
    }

    public void setHoraChegadaLocal(String horaChegadaLocal) {
        this.horaChegadaLocal = horaChegadaLocal;
    }

    public String getHoraSaidaLocal() {
        return horaSaidaLocal;
    }

    public void setHoraSaidaLocal(String horaSaidaLocal) {
        this.horaSaidaLocal = horaSaidaLocal;
    }

    public String getHoraChegadaHospital() {
        return horaChegadaHospital;
    }

    public void setHoraChegadaHospital(String horaChegadaHospital) {
        this.horaChegadaHospital = horaChegadaHospital;
    }

    public String getHoraLigacao() {
        return horaLigacao;
    }

    public void setHoraLigacao(String horaLigacao) {
        this.horaLigacao = horaLigacao;
    }

    

    public String getProdutosUtilizado() {
        return produtosUtilizado;
    }

    public void setProdutosUtilizado(String produtosUtilizado) {
        this.produtosUtilizado = produtosUtilizado;
    }

    public Timestamp getDataHoraRegistroFicha() {
        return dataHoraRegistroFicha;
    }

    public void setDataHoraRegistroFicha(Timestamp dataHoraRegistroFicha) {
        this.dataHoraRegistroFicha = dataHoraRegistroFicha;
    }

    
}
