package br.com.samuonline.modelo;

import java.io.Serializable;

public class Viatura implements Serializable {

    private int idViatura;
    private int codigo;
    private String placa;
    private String modelo;
    private String skype;
    private String tipoViatura;
    private Base objBase = new Base();

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idViatura;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Viatura other = (Viatura) obj;
        if (this.idViatura != other.idViatura) {
            return false;
        }
        return true;
    }
    
    

    public int getIdViatura() {
        return idViatura;
    }

    public void setIdViatura(int idViatura) {
        this.idViatura = idViatura;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getTipoViatura() {
        return tipoViatura;
    }

    public void setTipoViatura(String tipoViatura) {
        this.tipoViatura = tipoViatura;
    }

    public Base getObjBase() {
        return objBase;
    }

    public void setObjBase(Base objBase) {
        this.objBase = objBase;
    }
}
