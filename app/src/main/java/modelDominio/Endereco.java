package modelDominio;

import java.io.Serializable;

public class Endereco implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codEndereco;
    private String cidadeEndereco;
    private String estadoEndereco;
    private String ruaEndereco;
    private String bairroEndereco;
    private String complementoEndereco;

    public Endereco(int codEndereco, String cidadeEndereco, String estadoEndereco, String ruaEndereco, String bairroEndereco, String complementoEndereco) {
        this.codEndereco = codEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.estadoEndereco = estadoEndereco;
        this.ruaEndereco = ruaEndereco;
        this.bairroEndereco = bairroEndereco;
        this.complementoEndereco = complementoEndereco;
    }

    public Endereco(String cidadeEndereco, String estadoEndereco, String ruaEndereco, String bairroEndereco, String complementoEndereco) {
        this.cidadeEndereco = cidadeEndereco;
        this.estadoEndereco = estadoEndereco;
        this.ruaEndereco = ruaEndereco;
        this.bairroEndereco = bairroEndereco;
        this.complementoEndereco = complementoEndereco;
    }

    public Endereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getEstadoEndereco() {
        return estadoEndereco;
    }

    public void setEstadoEndereco(String estadoEndereco) {
        this.estadoEndereco = estadoEndereco;
    }

    public String getRuaEndereco() {
        return ruaEndereco;
    }

    public void setRuaEndereco(String ruaEndereco) {
        this.ruaEndereco = ruaEndereco;
    }

    public String getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(String bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    @Override
    public String toString() {
        return "Endereco{" + "codEndereco=" + codEndereco + ", cidadeEndereco=" + cidadeEndereco + ", estadoEndereco=" + estadoEndereco + ", ruaEndereco=" + ruaEndereco + ", bairroEndereco=" + bairroEndereco + ", complementoEndereco=" + complementoEndereco + '}';
    }

}
