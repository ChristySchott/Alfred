package modelDominio;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private int codCliente;
    private String nomeCliente;
    private String sobrenomeCliente;
    private Date dataNascimentoCliente;
    private int areaCliente;  //Telavez mudar para areaTelefoneCliente
    private int telefoneCliente;
    private byte[] imagemCliente;


    public Cliente(int codCliente, String nomeCliente, String sobrenomeCliente, Date dataNascimentoCliente, int areaCliente, int telefoneCliente, byte[] imagemCliente, int codUsuario, String emailUsuario, Cidade cidadeUsuario, Estado estadoUsuario, String ruaUsuario, String bairroUsuario, String complementoUsuario, int numeroUsuario) {
        super(codUsuario, emailUsuario, cidadeUsuario, estadoUsuario, ruaUsuario, bairroUsuario, complementoUsuario, numeroUsuario);
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.areaCliente = areaCliente;
        this.telefoneCliente = telefoneCliente;
        this.imagemCliente = imagemCliente;
    }

    // TODO - Estamos editando sem imagem, se sobrar tempo editar também a imagemCliente
    public Cliente(int codCliente, String nomeCliente, String sobrenomeCliente, Date dataNascimentoCliente, int areaCliente, int telefoneCliente) {
        this.codCliente = codCliente;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.areaCliente = areaCliente;
        this.telefoneCliente = telefoneCliente;
    }

    public Cliente(String nomeCliente, String sobrenomeCliente, Date dataNascimentoCliente, int areaCliente, int telefoneCliente, byte[] imagemCliente) {
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.areaCliente = areaCliente;
        this.telefoneCliente = telefoneCliente;
        this.imagemCliente = imagemCliente;
    }

    public Cliente(int codCliente, int codUsuario, String emailUsuario, String senhaUsuario) {
        super(codUsuario, emailUsuario, senhaUsuario);
        this.codCliente = codCliente;
    }

    public Cliente(int codCliente, String emailUsuario, String senhaUsuario) {
        super(emailUsuario, senhaUsuario);
        this.codCliente = codCliente;
    }

    public Cliente(String emailUsuario, String senhaUsuario) {
        super(emailUsuario, senhaUsuario);
    }

    public Cliente(int codUsuario) {
        super(codUsuario);
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public Date getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(Date dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public int getAreaCliente() {
        return areaCliente;
    }

    public void setAreaCliente(int areaCliente) {
        this.areaCliente = areaCliente;
    }

    public int getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(int telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public byte[] getImagemCliente() {
        return imagemCliente;
    }

    public void setImagemCliente(byte[] imagemCliente) {
        this.imagemCliente = imagemCliente;
    }

    public String getDataNascimentoClienteString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dataNascimentoCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" + "codCliente=" + codCliente + ", nomeCliente=" + nomeCliente + ", sobrenomeCliente=" + sobrenomeCliente + ", dataNascimentoCliente=" + dataNascimentoCliente + ", areaCliente=" + areaCliente + ", telefoneCliente=" + telefoneCliente + '}' + super.toString();
    }

}
