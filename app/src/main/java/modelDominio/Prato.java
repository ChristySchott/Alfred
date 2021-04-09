package modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Prato implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codPrato;
    private String nomePrato;
    private String descricaoPrato;
    private double valorPrato;
    private Empresa empresa;

    public Prato(int codPrato, String nomePrato, String descricaoPrato, double valorPrato, int codEmpresa) {
        this.codPrato = codPrato;
        this.nomePrato = nomePrato;
        this.descricaoPrato = descricaoPrato;
        this.valorPrato = valorPrato;
        Empresa emp = new Empresa(codEmpresa);
        this.empresa = emp;
    }

    public Prato(int codPrato, String nomePrato, String descricaoPrato, double valorPrato, int codEmpresa, String nomeEmpresa) {
        this.codPrato = codPrato;
        this.nomePrato = nomePrato;
        this.descricaoPrato = descricaoPrato;
        this.valorPrato = valorPrato;
        Empresa emp = new Empresa(codEmpresa, nomeEmpresa);
        this.empresa = emp;
    }

    public Prato(String nomePrato, String descricaoPrato, double valorPrato, int codEmpresa) {
        this.nomePrato = nomePrato;
        this.descricaoPrato = descricaoPrato;
        this.valorPrato = valorPrato;
        Empresa emp = new Empresa(codEmpresa);
        this.empresa = emp;
    }

    public Prato(int codPrato) {
        this.codPrato = codPrato;
    }

    public int getCodPrato() {
        return codPrato;
    }

    public void setCodPrato(int codPrato) {
        this.codPrato = codPrato;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getDescricaoPrato() {
        return descricaoPrato;
    }

    public void setDescricaoPrato(String descricaoPrato) {
        this.descricaoPrato = descricaoPrato;
    }

    public double getValorPrato() {
        return valorPrato;
    }

    public void setValorPrato(double valorPrato) {
        this.valorPrato = valorPrato;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getValorPratoString(){
        String pattern = "###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valorPrato);
    }

    @Override
    public String toString() {
        return "Prato{" + "codPrato=" + codPrato + ", nomePrato=" + nomePrato + ", descricaoPrato=" + descricaoPrato + ", valorPrato=" + valorPrato + ", empresa=" + empresa + '}';
    }

}