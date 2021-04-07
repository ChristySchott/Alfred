package com.example.alfred.modelDominio;

import java.io.Serializable;

public class UsuarioComum extends Usuario implements Serializable {
    private static final long serialVersionUID = 123456789L;

    public UsuarioComum(int codUsuario, String emailUsuario, String senhaUsuario) {
        super(codUsuario, emailUsuario, senhaUsuario);
    }

    public UsuarioComum(String emailUsuario, String senhaUsuario) {
        super(emailUsuario, senhaUsuario);
    }

    @Override
    public String toString() {
        return "UsuarioComum{" + '}';
    }

}
