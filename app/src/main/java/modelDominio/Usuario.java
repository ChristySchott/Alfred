package modelDominio;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private int codUsuario;
    private String emailUsuario;
    private String senhaUsuario;

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


    public static boolean validaEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codUsuario=" + codUsuario + ", emailUsuario=" + emailUsuario + ", senhaUsuario=" + senhaUsuario + '}';
    }

}
