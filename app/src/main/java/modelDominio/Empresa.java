package modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class Empresa extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private int codEmpresa;
    private String nomeEmpresa;
    private String cnpjEmpresa;
    private Boolean abertoFechadoEmpresa;
    private Categoria categoriaEmpresa;
    private Avaliacao avaliacaoEmpresa;
    private double precoMedioEmpresa;
    private byte[] imagemEmpresa;

    public Empresa(int codEmpresa, String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, Categoria categoriaEmpresa, byte[] imagemEmpresa, int codUsuario, String emailUsuario, Cidade cidadeUsuario, Estado estadoUsuario, String ruaUsuario, String bairroUsuario, String complementoUsuario, int numeroUsuario) {
        super(codUsuario, emailUsuario, cidadeUsuario, estadoUsuario, ruaUsuario, bairroUsuario, complementoUsuario, numeroUsuario);
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(int codEmpresa, String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, Categoria categoriaEmpresa, byte[] imagemEmpresa, int codUsuario, String emailUsuario, String senhaUsuario, Cidade cidadeUsuario, Estado estadoUsuario, String ruaUsuario, String bairroUsuario, String complementoUsuario, int numeroUsuario) {
        super(codUsuario, emailUsuario, senhaUsuario, cidadeUsuario, estadoUsuario, ruaUsuario, bairroUsuario, complementoUsuario, numeroUsuario);
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(int codEmpresa, String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, Categoria categoriaEmpresa, byte[] imagemEmpresa, Avaliacao avaliacao, double precoMedioEmpresa, int codUsuario, String emailUsuario, String senhaUsuario, Cidade cidadeUsuario, Estado estadoUsuario, String ruaUsuario, String bairroUsuario, String complementoUsuario, int numeroUsuario) {
        super(codUsuario, emailUsuario, senhaUsuario, cidadeUsuario, estadoUsuario, ruaUsuario, bairroUsuario, complementoUsuario, numeroUsuario);
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
        this.avaliacaoEmpresa = avaliacao;
        this.precoMedioEmpresa = precoMedioEmpresa;
    }

    // Vamos utilizar esse construtor para listar as empresas no Mobile
    public Empresa(int codEmpresa, String nomeEmpresa, int codCategoria, int codAvaliacao, double precoMedioEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        Categoria categoria = new Categoria(codCategoria);
        this.categoriaEmpresa = categoria;
        Avaliacao avaliacao = new Avaliacao(codAvaliacao);
        this.avaliacaoEmpresa = avaliacao;
        this.precoMedioEmpresa = precoMedioEmpresa;
    }

    public Empresa(
            int codEmpresa,
            String nomeEmpresa,
            String cnpjEmpresa,
            Boolean abertoFechadoEmpresa,
            Categoria categoriaEmpresa,
            byte[] imagemEmpresa,
            int codUsuario,
            String emailUsuario,
            String senhaUsuario
    ) {
        super(codUsuario, emailUsuario, senhaUsuario);
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(int codEmpresa, String nomeEmpresa, String cnpjEmpresa, Boolean abertoFechadoEmpresa, Categoria categoriaEmpresa, byte[] imagemEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
        this.imagemEmpresa = imagemEmpresa;
    }
    public Empresa(int codEmpresa, String nomeEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
    }

    public Empresa(String cnpjEmpresa, String emailUsuario) {
        super(emailUsuario);
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Empresa(String nomeEmpresa, String cnpjEmpresa, int codUsuario) {
        super(codUsuario);
        this.nomeEmpresa = nomeEmpresa;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    // Usamos para apresentar as informações na tela Meus Pedidos (Mobile)
    public Empresa(int codEmpresa, String nomeEmpresa, byte[] imagemEmpresa) {
        this.codEmpresa = codEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.imagemEmpresa = imagemEmpresa;
    }

    public Empresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Boolean getAbertoFechadoEmpresa() {
        return abertoFechadoEmpresa;
    }

    public void setAbertoFechadoEmpresa(Boolean abertoFechadoEmpresa) {
        this.abertoFechadoEmpresa = abertoFechadoEmpresa;
    }

    public Categoria getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(Categoria categoriaEmpresa) {
        this.categoriaEmpresa = categoriaEmpresa;
    }

    public byte[] getImagemEmpresa() {
        return imagemEmpresa;
    }

    public void setImagemEmpresa(byte[] imagemEmpresa) {
        this.imagemEmpresa = imagemEmpresa;
    }

    public Avaliacao getAvaliacaoEmpresa() {
        return avaliacaoEmpresa;
    }

    public void setAvaliacaoEmpresa(Avaliacao avaliacaoEmpresa) {
        this.avaliacaoEmpresa = avaliacaoEmpresa;
    }

    public double getPrecoMedioEmpresa() {
        return precoMedioEmpresa;
    }

    public void setPrecoMedioEmpresa(double precoMedioEmpresa) {
        this.precoMedioEmpresa = precoMedioEmpresa;
    }

    public static boolean validaCnpj(String CNPJ) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int)(CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(CNPJ.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public String getPrecoMedioString(){
        String pattern = "###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(precoMedioEmpresa);
    }

    @Override
    public String toString() {
        return "Empresa{" + "codEmpresa=" + codEmpresa + ", nomeEmpresa=" + nomeEmpresa + ", cnpjEmpresa=" + cnpjEmpresa + ", abertoFechadoEmpresa=" + abertoFechadoEmpresa + ", categoriaEmpresa=" + categoriaEmpresa + '}' + super.toString();
    }
}   
