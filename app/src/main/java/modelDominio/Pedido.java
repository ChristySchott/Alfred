package modelDominio;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codPedido;
    private int statusPedido; // 0 - Em análise 1 - Aprovado 2 - Negado
    private String observacaoPedido;
    private int formaPagamentoPedido; // 0 - Dinheiro 1 - Cartão 
    private Cliente cliente;
    private Empresa empresa;
    private List<PratoPedido> pratosPedido;

    public Pedido(int codPedido, int statusPedido, String observacaoPedido, int formaPagamentoPedido, int codCliente, Empresa emp) {
        this.codPedido = codPedido;
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        Cliente cl = new Cliente(codCliente);
        this.cliente = cl;
        this.empresa = emp;
    }

    public Pedido(int statusPedido, String observacaoPedido, int formaPagamentoPedido, int codCliente, Empresa emp) {
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        Cliente cl = new Cliente(codCliente);
        this.cliente = cl;
        this.empresa = emp;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(int statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getObservacaoPedido() {
        return observacaoPedido;
    }

    public void setObservacaoPedido(String observacaoPedido) {
        this.observacaoPedido = observacaoPedido;
    }

    public int getFormaPagamentoPedido() {
        return formaPagamentoPedido;
    }

    public void setFormaPagamentoPedido(int formaPagamentoPedido) {
        this.formaPagamentoPedido = formaPagamentoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<PratoPedido> getPratosPedido() {
        return pratosPedido;
    }

    public void setPratosPedido(List<PratoPedido> pratosPedido) {
        this.pratosPedido = pratosPedido;
    }
    
    @Override
    public String toString() {
        return "Pedido{" + "codPedido=" + codPedido + ", statusPedido=" + statusPedido + ", observacaoPedido=" + observacaoPedido + ", formaPagamentoPedido=" + formaPagamentoPedido + ", cliente=" + cliente + ", empresa=" + empresa + '}';
    }
    
    
}
