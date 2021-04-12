package modelDominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;
    
    private int codUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private Cidade cidadeUsuario;
    private Estado estadoUsuario;
    private String ruaUsuario;
    private String bairroUsuario;
    private String complementoUsuario;
    private int numeroUsuario;

    public Usuario(int codUsuario, String emailUsuario, Cidade cidadeUsuario, Estado estadoUsuario, String ruaUsuario, String bairroUsuario, String complementoUsuario, int numeroUsuario) {
        this.codUsuario = codUsuario;
        this.emailUsuario = emailUsuario;
        this.cidadeUsuario = cidadeUsuario;
        this.estadoUsuario = estadoUsuario;
        this.ruaUsuario = ruaUsuario;
        this.bairroUsuario = bairroUsuario;
        this.complementoUsuario = complementoUsuario;
        this.numeroUsuario = numeroUsuario;
    }

    public Usuario(int codUsuario, String emailUsuario, String senhaUsuario) {
        this.codUsuario = codUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuario(String emailUsuario, String senhaUsuario) {
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public Usuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Usuario(int codUsuario, String emailUsuario) {
        this.codUsuario = codUsuario;
        this.emailUsuario = emailUsuario;
    }
    
    // Não entendi o porque mas só funciona se isso existir ????
    public Usuario() {}

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Cidade getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(Cidade cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }

    public Estado getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Estado estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getRuaUsuario() {
        return ruaUsuario;
    }

    public void setRuaUsuario(String ruaUsuario) {
        this.ruaUsuario = ruaUsuario;
    }

    public String getBairroUsuario() {
        return bairroUsuario;
    }

    public void setBairroUsuario(String bairroUsuario) {
        this.bairroUsuario = bairroUsuario;
    }

    public String getComplementoUsuario() {
        return complementoUsuario;
    }

    public void setComplementoUsuario(String complementoUsuario) {
        this.complementoUsuario = complementoUsuario;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }
    
    public String getNumeroUsuarioToString() {
        return Integer.toString(numeroUsuario);
    }
    
    //Referência https://receitasdecodigo.com.br/java/validar-email-em-java
    public static boolean validaEmail(String email) {
        boolean emailValido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }
        }
        
        return emailValido;
    }
    
}
