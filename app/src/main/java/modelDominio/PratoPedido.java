package modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;

public class PratoPedido implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private Prato prato;
    private int quantidadePratoPedido;
    private double valorUnidadePratoPedido;

    public PratoPedido(int codPratoPedido, int quantidadePratoPedido, double valorUnidadePratoPedido) {
        Prato prato = new Prato(codPratoPedido);
        this.prato = prato;
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public PratoPedido(int quantidadePratoPedido, double valorUnidadePratoPedido) {
        this.quantidadePratoPedido = quantidadePratoPedido;
        this.valorUnidadePratoPedido = valorUnidadePratoPedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
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
        return "PratoPedido{" + "prato=" + prato + ", quantidadePratoPedido=" + quantidadePratoPedido + ", valorUnidadePratoPedido=" + valorUnidadePratoPedido + '}';
    }

}
