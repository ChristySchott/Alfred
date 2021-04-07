package com.example.alfred.modelDominio;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codCliente;
    private String nomeCliente;
    private String sobrenomeCliente;
    private Date dataNascimentoCliente;
    private int areaCliente;
    private int telefoneCliente;
    private byte[] imageCliente;

    public Cliente(int codCliente, String nomeCliente, String sobrenomeCliente, Date dataNascimentoCliente, int areaCliente, int telefoneCliente, byte[] imageCliente) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.areaCliente = areaCliente;
        this.telefoneCliente = telefoneCliente;
        this.imageCliente = imageCliente;
    }

    public Cliente(String nomeCliente, String sobrenomeCliente, Date dataNascimentoCliente, int areaCliente, int telefoneCliente, byte[] imageCliente) {
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.areaCliente = areaCliente;
        this.telefoneCliente = telefoneCliente;
        this.imageCliente = imageCliente;
    }

    public Cliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public Date getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(Date dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public int getAreaCliente() {
        return areaCliente;
    }

    public void setAreaCliente(int areaCliente) {
        this.areaCliente = areaCliente;
    }

    public int getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(int telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public byte[] getImageCliente() {
        return imageCliente;
    }

    public void setImageCliente(byte[] imageCliente) {
        this.imageCliente = imageCliente;
    }

    public String getDataNascimentoClienteString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataNascimentoCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" + "codCliente=" + codCliente + ", nomeCliente=" + nomeCliente + ", sobrenomeCliente=" + sobrenomeCliente + ", dataNascimentoCliente=" + dataNascimentoCliente + ", areaCliente=" + areaCliente + ", telefoneCliente=" + telefoneCliente + ", imageCliente=" + imageCliente + '}';
    }

}
