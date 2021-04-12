/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author be_ha
 */
public class Cidade implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private int codCidade;
    private String nomeCidade;
    private int codEstado;

    public Cidade(int codCidade, String nomeCidade) {
        this.codCidade = codCidade;
        this.nomeCidade = nomeCidade;
    }

    public Cidade(int codCidade, String nomeCidade, int codEstado) {
        this.codCidade = codCidade;
        this.nomeCidade = nomeCidade;
        this.codEstado = codEstado;
    }

    public Cidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }
    
    
}
