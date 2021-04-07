package com.example.alfred.modelDominio;

import java.io.Serializable;

public class Empresa implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codEmpresa;
    private String nomeEmpresa;
    private String cnpjEmpresa;
    private Boolean abertoFechadoEmpresa;
    private int codCategoria;
    private String nomeCategoria;
    private int notaAvaliacao;
    private byte[] imagemEmpresa;


    public Empresa(int codEmpresa, String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, int codCategoria, byte[] imagemEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.codCategoria = codCategoria;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, int codCategoria, byte[] imagemEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.codCategoria = codCategoria;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(int codEmpresa, String nomeEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
    }

    public Empresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Boolean getAbertoFechadoEmpresa() {
        return abertoFechadoEmpresa;
    }

    public void setAbertoFechadoEmpresa(Boolean abertoFechadoEmpresa) {
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public byte[] getImagemEmpresa() {
        return imagemEmpresa;
    }

    public void setImagemEmpresa(byte[] imagemEmpresa) {
        this.imagemEmpresa = imagemEmpresa;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    @Override
    public String toString() {
        return "Empresa{" + "codEmpresa=" + codEmpresa + ", nomeEmpresa=" + nomeEmpresa + ", cnpjEmpresa=" + cnpjEmpresa + ", abertoFechadoEmpresa=" + abertoFechadoEmpresa + ", codCategoria=" + codCategoria + ", imagemEmpresa=" + imagemEmpresa + '}';
    }

}

