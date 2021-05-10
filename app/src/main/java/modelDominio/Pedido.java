package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codPedido;
    private int statusPedido; // 0 - Em análise 1 - Aprovado 2 - Negado
    private String observacaoPedido;
    private int formaPagamentoPedido; // 0 - Dinheiro 1 - Cartão
    private Cliente cliente;
    private Empresa empresa;
    private double valorTotal;
    private ArrayList<PratoPedido> listaPratosPedido;

    public Pedido(int codPedido, int statusPedido, String observacaoPedido, int formaPagamentoPedido, Cliente cliente, Empresa empresa) {
        this.codPedido = codPedido;
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        this.cliente = cliente;
        this.empresa = empresa;
    }

    public Pedido(int codPedido, int statusPedido, String observacaoPedido, int formaPagamentoPedido, Cliente cliente, double valorTotal) {
        this.codPedido = codPedido;
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Pedido(int codPedido, int statusPedido, String observacaoPedido, int formaPagamentoPedido, int codCliente, int codEmpresa) {
        this.codPedido = codPedido;
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        Cliente cliente = new Cliente(codCliente);
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public Pedido(int codPedido, int statusPedido, String observacaoPedido, int formaPagamentoPedido) {
        this.codPedido = codPedido;
        this.statusPedido = statusPedido;
        this.observacaoPedido = observacaoPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
    }

//    public Pedido (int codCliente, int codEmpresa) {
//        Cliente meuCliente = new Cliente(codCliente);
//        this.cliente = meuCliente;
//        Empresa minhaEmpresa = new Empresa(codEmpresa);
//        this.empresa = minhaEmpresa;
//    }

    public Pedido(int codPedido, Empresa empresa, ArrayList<PratoPedido> listaPratosPedido) {
        this.codPedido = codPedido;
        this.empresa = empresa;
        this.listaPratosPedido = listaPratosPedido;
    }

    public Pedido (Cliente cliente, Empresa empresa) {
        this.cliente = cliente;
        this.empresa = empresa;
    }

    public ArrayList<PratoPedido> getListaPratosPedido() {
        return listaPratosPedido;
    }

    public void setListaPratosPedido(ArrayList<PratoPedido> listaPratosPedido) {
        this.listaPratosPedido = listaPratosPedido;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" + "codPedido=" + codPedido + ", statusPedido=" + statusPedido + ", observacaoPedido=" + observacaoPedido + ", formaPagamentoPedido=" + formaPagamentoPedido + ", cliente=" + cliente + ", empresa=" + empresa + '}';
    }


}
