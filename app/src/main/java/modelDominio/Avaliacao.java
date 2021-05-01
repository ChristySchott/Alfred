package modelDominio;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codAvaliacao;
    private String descricaoAvaliacao;
    private int notaAvaliacao;
    private Cliente cliente;
    private Empresa empresa;

    public Avaliacao(int codAvaliacao, String descricaoAvaliacao, int notaAvaliacao, Cliente cliente, Empresa empresa) {
        this.codAvaliacao = codAvaliacao;
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.cliente = cliente;
        this.empresa = empresa;
    }

    public Avaliacao(int codAvaliacao, String descricaoAvaliacao, int notaAvaliacao, Cliente cliente) {
        this.codAvaliacao = codAvaliacao;
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.cliente = cliente;
    }

    public Avaliacao(String descricaoAvaliacao, int notaAvaliacao, Cliente cliente, Empresa empresa) {
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.cliente = cliente;
        this.empresa = empresa;
    }

    public Avaliacao(int codAvaliacao, int notaAvaliacao) {
        this.codAvaliacao = codAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
    }

    public Avaliacao(int codAvaliacao) {
        this.codAvaliacao = codAvaliacao;
    }

    public int getCodAvaliacao() {
        return codAvaliacao;
    }

    public void setCodAvaliacao(int codAvaliacao) {
        this.codAvaliacao = codAvaliacao;
    }

    public String getDescricaoAvaliacao() {
        return descricaoAvaliacao;
    }

    public void setDescricaoAvaliacao(String descricaoAvaliacao) {
        this.descricaoAvaliacao = descricaoAvaliacao;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
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


}
