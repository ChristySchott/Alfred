package modelDominio;

import java.io.Serializable;

public class Avaliacao implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codAvaliacao;
    private String descricaoAvaliacao;
    private int notaAvaliacao;
    private int codCliente;
    private int codEmpresa;

    public Avaliacao(int codAvaliacao, String descricaoAvaliacao, int notaAvaliacao, int codCliente, int codEmpresa) {
        this.codAvaliacao = codAvaliacao;
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.codCliente = codCliente;
        this.codEmpresa = codEmpresa;
    }

    public Avaliacao(String descricaoAvaliacao, int notaAvaliacao, int codCliente, int codEmpresa) {
        this.descricaoAvaliacao = descricaoAvaliacao;
        this.notaAvaliacao = notaAvaliacao;
        this.codCliente = codCliente;
        this.codEmpresa = codEmpresa;
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

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "codAvaliacao=" + codAvaliacao + ", descricaoAvaliacao=" + descricaoAvaliacao + ", notaAvaliacao=" + notaAvaliacao + ", codCliente=" + codCliente + ", codEmpresa=" + codEmpresa + '}';
    }
    
    
}
