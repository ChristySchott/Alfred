package com.example.alfred.controller;

import com.example.alfred.modelDominio.Avaliacao;
import com.example.alfred.modelDominio.Categoria;
import com.example.alfred.modelDominio.Endereco;
import com.example.alfred.modelDominio.Prato;
import com.example.alfred.modelDominio.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ConexaoController {
    public Usuario usuario;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int idUnico;

    public ConexaoController(ObjectInputStream in, ObjectOutputStream out, int idUnico) {
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
    }

    public Boolean avaliacaoInserir(Avaliacao avaliacao) {
        String msg = "";
        try {
            out.writeObject("AvaliacaoInserir");
            msg = (String) in.readObject();
            out.writeObject(avaliacao);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean avaliacaoAlterar(Avaliacao avaliacao) {
        String msg = "";
        try {
            out.writeObject("AvaliacaoAlterar");
            msg = (String) in.readObject();
            out.writeObject(avaliacao);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean avaliacaoExcluir(Avaliacao avaliacao) {
        String msg = "";
        try {
            out.writeObject("AvaliacaoExcluir");
            msg = (String) in.readObject();
            out.writeObject(avaliacao);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Avaliacao> avaliacaoLista() {
        String msg;
        try {
            out.writeObject("AvaliacaoLista");
            msg = (String) in.readObject();
            ArrayList<Avaliacao> listaAvaliacao = (ArrayList<Avaliacao>) in.readObject();
            return listaAvaliacao;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /* CATEGORIA */
    public Boolean categoriaInserir(Categoria categoria) {
        String msg = "";
        try {
            out.writeObject("CategoriaInserir");
            msg = (String) in.readObject();
            out.writeObject(categoria);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean categoriaAlterar(Categoria categoria) {
        String msg = "";
        try {
            out.writeObject("CategoriaAlterar");
            msg = (String) in.readObject();
            out.writeObject(categoria);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean categoriaExcluir(Categoria categoria) {
        String msg = "";
        try {
            out.writeObject("CategoriaExcluir");
            msg = (String) in.readObject();
            out.writeObject(categoria);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Categoria> categoriaLista() {
        String msg;
        try {
            out.writeObject("CategoriaLista");
            msg = (String) in.readObject();
            ArrayList<Categoria> listaCategoria = (ArrayList<Categoria>) in.readObject();
            return listaCategoria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ArrayList<Categoria> categoriaListaNome(String nome) {
        String msg;
        try {
            out.writeObject("CategoriaListaNome");
            msg = (String) in.readObject();
            out.writeObject(nome);
            ArrayList<Categoria> listaCategoriaNome = (ArrayList<Categoria>) in.readObject();
            return listaCategoriaNome;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* USUÁRIO */
    public Usuario efetuarLogin(Usuario usr) {
        String msg;
        try {
            out.writeObject("UsuarioEfetuarLogin");
            msg = (String) in.readObject();
            out.writeObject(usr);
            Usuario usrselecionado = (Usuario) in.readObject();
            return usrselecionado;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean usuarioInserir(Usuario usr) {
        String msg = "";
        try {
            out.writeObject("UsuarioInserir");
            msg = (String) in.readObject();
            out.writeObject(usr);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean usuarioExcluir(Usuario usr) {
        String msg = "";
        try {
            out.writeObject("UsuarioExcluir");
            msg = (String) in.readObject();
            out.writeObject(usr);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean usuarioAlterar(Usuario usr) {
        String msg = "";
        try {
            out.writeObject("UsuarioAlterar");
            msg = (String) in.readObject();
            out.writeObject(usr);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Usuario> usuarioLista() {
        String msg;
        try {
            out.writeObject("UsuarioLista");
            msg = (String) in.readObject();
            ArrayList<Usuario> listausr = (ArrayList<Usuario>) in.readObject();
            return listausr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* PRATO */
    public Boolean pratoInserir(Prato prato) {
        String msg = "";
        try {
            out.writeObject("PratoInserir");
            msg = (String) in.readObject();
            out.writeObject(prato);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean pratoAlterar(Prato prato) {
        String msg = "";
        try {
            out.writeObject("PratoAlterar");
            msg = (String) in.readObject();
            out.writeObject(prato);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean pratoExcluir(Prato prato) {
        String msg = "";
        try {
            out.writeObject("PratoExcluir");
            msg = (String) in.readObject();
            out.writeObject(prato);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Prato> pratoLista() {
        String msg;
        try {
            out.writeObject("PratoLista");
            msg = (String) in.readObject();
            ArrayList<Prato> listaPrato = (ArrayList<Prato>) in.readObject();
            return listaPrato;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ArrayList<Prato> pratoListaNome(String nome) {
        String msg;
        try {
            out.writeObject("PratoListaNome");
            msg = (String) in.readObject();
            out.writeObject(nome);
            ArrayList<Prato> listaPratoNome = (ArrayList<Prato>) in.readObject();
            return listaPratoNome;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Prato> pratoListaEmpresa(String nome) {
        String msg;
        try {
            out.writeObject("PratoListaEmpresa");
            msg = (String) in.readObject();
            out.writeObject(nome);
            ArrayList<Prato> listaPratoEmpresa = (ArrayList<Prato>) in.readObject();
            return listaPratoEmpresa;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* ENDEREÇO */
    public Boolean enderecoInserir(Endereco endereco) {
        String msg = "";
        try {
            out.writeObject("EnderecoInserir");
            msg = (String) in.readObject();
            out.writeObject(endereco);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean enderecoAlterar(Endereco endereco) {
        String msg = "";
        try {
            out.writeObject("EnderecoAlterar");
            msg = (String) in.readObject();
            out.writeObject(endereco);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean enderecoExcluir(Endereco endereco) {
        String msg = "";
        try {
            out.writeObject("EnderecoExcluir");
            msg = (String) in.readObject();
            out.writeObject(endereco);
            msg = (String) in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /* FIM */
    public void fim() {
        String msg;
        try {
            out.writeObject("fim");
            in.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
