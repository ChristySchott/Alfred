package com.example.alfred.modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PratoPedido implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codPratoPedido;
    private int quantidadePratoPedido;
    private double valorUnidadePratoPedido;

    public PratoPedido(int codPratoPedido, int quantidadePratoPedido, double valorUnidadePratoPedido) {
        this.codPratoPedido = codPratoPedido;
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public PratoPedido(int quantidadePratoPedido, double valorUnidadePratoPedido) {
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public int getCodPratoPedido() {
        return codPratoPedido;
    }

    public void setCodPratoPedido(int codPratoPedido) {
        this.codPratoPedido = codPratoPedido;
    }

    public int getQuantidadePratoPedido() {
        return quantidadePratoPedido;
    }

    public void setQuantidadePratoPedido(int quantidadePratoPedido) {
        this.quantidadePratoPedido = quantidadePratoPedido;
    }

    public double getValorUnidadePratoPedido() {
        return valorUnidadePratoPedido;
    }

    public void setValorUnidadePratoPedido(double valorUnidadePratoPedido) {
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public String getValorUnidadePratoPedidoString(){
        String pattern = "###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valorUnidadePratoPedido);
    }

    @Override
    public String toString() {
        return "PratoPedido{" + "codPratoPedido=" + codPratoPedido + ", quantidadePratoPedido=" + quantidadePratoPedido + ", valorUnidadePratoPedido=" + valorUnidadePratoPedido + '}';
    }

}