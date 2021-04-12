package modelDominio;

import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private int codCategoria;
    private String nomeCategoria;
    private byte[] imagemCategoria;

    public Categoria(int codCategoria, String nomeCategoria, byte[] imagemCategoria) {
        this.codCategoria = codCategoria;
        this.nomeCategoria = nomeCategoria;
        this.imagemCategoria = imagemCategoria;
    }
    
    public Categoria(int codCategoria, String nomeCategoria) {
        this.codCategoria = codCategoria;
        this.nomeCategoria = nomeCategoria;
    }
    
    public Categoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public byte[] getImagemCategoria() {
        return imagemCategoria;
    }

    public void setImagemCategoria(byte[] imagemCategoria) {
        this.imagemCategoria = imagemCategoria;
    }
    
    @Override
    public String toString() {
        return "Categoria{" + "codCategoria=" + codCategoria + ", nomeCategoria=" + nomeCategoria + '}';
    }
    
}
