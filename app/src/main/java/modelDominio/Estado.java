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
public class Estado implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private int codEstado;
    private String nomeEstado;
    private String siglaEstado;

    public Estado(int codEstado) {
        this.codEstado = codEstado;
    }

    public Estado(int codEstado, String nomeEstado, String siglaEstado) {
        this.codEstado = codEstado;
        this.nomeEstado = nomeEstado;
        this.siglaEstado = siglaEstado;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    // Sobrescrevemos este método para mostrar apenas o nome do estado no componente Spinner, do Android.
    @Override
    public String toString() {
        return nomeEstado;
    }
}
