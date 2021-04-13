package com.example.alfred.controller;

import com.example.alfred.InformacoesApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Avaliacao;
import modelDominio.Categoria;
import modelDominio.Cliente;
import modelDominio.Empresa;
import modelDominio.Endereco;
import modelDominio.Prato;
import modelDominio.Usuario;

public class ConexaoController {
    public Usuario usuario;
    InformacoesApp informacoesApp;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int idUnico;

    public ConexaoController(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    public ConexaoController(ObjectInputStream in, ObjectOutputStream out, int idUnico) {
        this.in = in;
        this.out = out;
        this.idUnico = idUnico;
    }

    public void Conectar() {
        try {
            System.out.println("Conectando no servidor...");
            informacoesApp.socket = new Socket("10.0.2.2", 12345);
            informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
            informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Boolean avaliacaoInserir(Avaliacao avaliacao) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("AvaliacaoInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(avaliacao);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean avaliacaoAlterar(Avaliacao avaliacao) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("AvaliacaoAlterar");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(avaliacao);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean avaliacaoExcluir(Avaliacao avaliacao) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("AvaliacaoExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(avaliacao);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Avaliacao> avaliacaoLista() {
        String msg;
        try {
            informacoesApp.out.writeObject("AvaliacaoLista");
            msg = (String) informacoesApp.in.readObject();
            ArrayList<Avaliacao> listaAvaliacao = (ArrayList<Avaliacao>) informacoesApp.in.readObject();
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
            informacoesApp.out.writeObject("CategoriaInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(categoria);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean categoriaAlterar(Categoria categoria) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("CategoriaAlterar");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(categoria);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean categoriaExcluir(Categoria categoria) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("CategoriaExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(categoria);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Categoria> categoriaLista() {
        String msg;
        try {
            informacoesApp.out.writeObject("CategoriaLista");
            msg = (String) informacoesApp.in.readObject();
            ArrayList<Categoria> listaCategoria = (ArrayList<Categoria>) informacoesApp.in.readObject();
            return listaCategoria;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ArrayList<Categoria> categoriaListaNome(String nome) {
        String msg;
        try {
            informacoesApp.out.writeObject("CategoriaListaNome");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(nome);
            ArrayList<Categoria> listaCategoriaNome = (ArrayList<Categoria>) informacoesApp.in.readObject();
            return listaCategoriaNome;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /* EMPRESA */
    public ArrayList<Empresa> empresasAbertasLista() {
        ArrayList<Empresa> listaEmpresas;
        try {
            informacoesApp.out.writeObject("EmpresaAbertaLista");
            listaEmpresas = (ArrayList<Empresa>) informacoesApp.in.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaEmpresas = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaEmpresas = null;
        }
        return listaEmpresas;
    }

    public ArrayList<Empresa> empresasFechadasLista() {
        ArrayList<Empresa> listaEmpresas;
        try {
            informacoesApp.out.writeObject("EmpresaFechadaLista");
            listaEmpresas = (ArrayList<Empresa>) informacoesApp.in.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaEmpresas = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaEmpresas = null;
        }
        return listaEmpresas;
    }

    /* USUÁRIO */
    public Cliente efetuarLogin(Cliente cl) {
        String msg;
        Cliente clienteSelecionado = null;
        try {
            informacoesApp.out.writeObject("ClienteEfetuarLogin");
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(cl);
                clienteSelecionado = (Cliente) informacoesApp.in.readObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            clienteSelecionado = null;
        }

        return clienteSelecionado;
    }


    public Boolean clienteInserir(Cliente cl) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("ClienteInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(cl);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Usuario buscarUsuario(Usuario usr) {
        String msg;
        Usuario usrselecionado = null;
        try {
            informacoesApp.out.writeObject("BuscarUsuario");
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(usr);
                msg = (String) informacoesApp.in.readObject();
                usrselecionado = (Usuario) informacoesApp.in.readObject();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
        }

        return usrselecionado;
    }

    public Boolean usuarioInserir(Usuario usr) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("UsuarioInserir");
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(usr);
                return true;
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean usuarioAlterar(Usuario usr) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("UsuarioAlterar");
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(usr);
                return true;
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Usuario> usuarioLista() {
        String msg;
        try {
            informacoesApp.out.writeObject("UsuarioLista");
            msg = (String) informacoesApp.in.readObject();
            ArrayList<Usuario> listausr = (ArrayList<Usuario>) informacoesApp.in.readObject();
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
            informacoesApp.out.writeObject("PratoInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(prato);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean pratoAlterar(Prato prato) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PratoAlterar");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(prato);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean pratoExcluir(Prato prato) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PratoExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(prato);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Prato> pratoLista() {
        String msg;
        try {
            informacoesApp.out.writeObject("PratoLista");
            msg = (String) informacoesApp.in.readObject();
            ArrayList<Prato> listaPrato = (ArrayList<Prato>) informacoesApp.in.readObject();
            return listaPrato;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ArrayList<Prato> pratoListaNome(String nome) {
        String msg;
        try {
            informacoesApp.out.writeObject("PratoListaNome");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(nome);
            ArrayList<Prato> listaPratoNome = (ArrayList<Prato>) informacoesApp.in.readObject();
            return listaPratoNome;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Prato> pratoListaEmpresa(String nome) {
        String msg;
        try {
            informacoesApp.out.writeObject("PratoListaEmpresa");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(nome);
            ArrayList<Prato> listaPratoEmpresa = (ArrayList<Prato>) informacoesApp.in.readObject();
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
            informacoesApp.out.writeObject("EnderecoInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(endereco);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean enderecoAlterar(Endereco endereco) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("EnderecoAlterar");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(endereco);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean enderecoExcluir(Endereco endereco) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("EnderecoExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(endereco);
            msg = (String) informacoesApp.in.readObject();
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
            informacoesApp.out.writeObject("fim");
            in.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
