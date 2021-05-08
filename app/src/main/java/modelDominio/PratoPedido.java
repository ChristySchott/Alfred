package modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PratoPedido implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codPratoPedido;
    private String nomePrato;
    private int quantidadePratoPedido;
    private double valorUnidadePratoPedido;
    private int codPedido;

    public PratoPedido(int codPratoPedido, int quantidadePratoPedido, double valorUnidadePratoPedido, String nomePrato) {
        this.codPratoPedido = codPratoPedido;
        this.nomePrato = nomePrato;
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public PratoPedido(int quantidadePratoPedido, double valorUnidadePratoPedido) {
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public PratoPedido(int quantidadePratoPedido, double valorUnidadePratoPedido, String nomePrato) {
        this.nomePrato = nomePrato;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
        this.quantidadePratoPedido = quantidadePratoPedido;
    }

    public PratoPedido(int codPratoPedido, int quantidadePratoPedido, int codPedido, String nomePrato) {
        this.codPratoPedido = codPratoPedido;
        this.nomePrato = nomePrato;
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.codPedido = codPedido;
    }

    public PratoPedido(int codPratoPedido) {
        this.codPratoPedido = codPratoPedido;
    }

    public int getCodPratoPedido() {
        return codPratoPedido;
    }

    public void setCodPratoPedido(int codPratoPedido) {
        this.codPratoPedido = codPratoPedido;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setPrato(String nomePrato) {
        this.nomePrato = nomePrato;
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

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    @Override
    public String toString() {
        return "PratoPedido{" + "prato=" + nomePrato + ", quantidadePratoPedido=" + quantidadePratoPedido + ", valorUnidadePratoPedido=" + valorUnidadePratoPedido + '}';
    }

}
