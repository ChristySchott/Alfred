package com.example.alfred.controller;

import android.util.Log;

import com.example.alfred.InformacoesApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Avaliacao;
import modelDominio.Categoria;
import modelDominio.Cidade;
import modelDominio.Cliente;
import modelDominio.Empresa;
import modelDominio.Estado;
import modelDominio.Pedido;
import modelDominio.Prato;
import modelDominio.PratoPedido;
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
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }

    public ArrayList<Categoria> categoriaLista() {
        ArrayList<Categoria> listaCategoria;
        try {
            informacoesApp.out.writeObject("CategoriaLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                listaCategoria = (ArrayList<Categoria>) informacoesApp.in.readObject();
            } else {
                listaCategoria = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listaCategoria = null;
        }
        return listaCategoria;
    }

    /* PEDIDO */
    public Boolean pedidoInserir(Pedido pedido) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PedidoInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(pedido);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }

    public Boolean pedidoAlterar(Pedido pedido) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PedidoAlterar");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(pedido);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }


    public Boolean pedidoExcluir(int codPedido) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PedidoExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(codPedido);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }

    public ArrayList<Pedido> pedidoAnaliseLista(int codCliente) {
        ArrayList<Pedido> listaPedidoAnalise;
        try {
            informacoesApp.out.writeObject("PedidoAnaliseLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(codCliente);
                listaPedidoAnalise = (ArrayList<Pedido>) informacoesApp.in.readObject();
            } else {
                listaPedidoAnalise = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaPedidoAnalise = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaPedidoAnalise = null;
        }
        return listaPedidoAnalise;
    }

    public ArrayList<Pedido> pedidoAprovadoLista(int codCliente) {
        ArrayList<Pedido> listaPedidoAnalise;
        try {
            informacoesApp.out.writeObject("PedidoAprovadoLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(codCliente);
                listaPedidoAnalise = (ArrayList<Pedido>) informacoesApp.in.readObject();
            } else {
                listaPedidoAnalise = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaPedidoAnalise = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaPedidoAnalise = null;
        }
        return listaPedidoAnalise;
    }

    public ArrayList<Pedido> pedidoReprovadoLista(int codCliente) {
        ArrayList<Pedido> listaPedidoAnalise;
        try {
            informacoesApp.out.writeObject("PedidoReprovadoLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(codCliente);
                listaPedidoAnalise = (ArrayList<Pedido>) informacoesApp.in.readObject();
            } else {
                listaPedidoAnalise = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaPedidoAnalise = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaPedidoAnalise = null;
        }
        return listaPedidoAnalise;
    }

    /* PRATO PEDIDO */
    public Boolean pratoPedidoInserir(PratoPedido pratoPedido) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PratoPedidoInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(pratoPedido);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }

    public Boolean pratoPedidoExcluir(PratoPedido pratoPedido) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("PratoPedidoExcluir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(pratoPedido);
            msg = (String) informacoesApp.in.readObject();
            return msg.equals("ok");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            return  false;
        }
    }

    public int getCodPedido() {
        int codPedido;
        try {
            informacoesApp.out.writeObject("CodigoPedido");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                codPedido = (int) informacoesApp.in.readObject();
            } else {
                codPedido = 0;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            codPedido = 0;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            codPedido = 0;
        }
        return codPedido;
    }

    public ArrayList<PratoPedido> pratoPedidoCarrinhoLista(int codCategoria) {
        ArrayList<PratoPedido> listaPratoPedido;
        try {
            informacoesApp.out.writeObject("PratoPedidoCarrinhoLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(codCategoria);
                listaPratoPedido = (ArrayList<PratoPedido>) informacoesApp.in.readObject();
            } else {
                listaPratoPedido = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaPratoPedido = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaPratoPedido = null;
        }
        return listaPratoPedido;
    }

    public ArrayList<PratoPedido> listaPratosPedido(int codPedido) {
        String msg;
        try {
            out.writeObject("ListaPratosPedido");
            msg = (String) in.readObject();

            if (msg.equals("ok")) {
                out.writeObject(codPedido);
                return (ArrayList<PratoPedido>) in.readObject();
            } else {
                throw new Exception("Erro ao buscar lista de PratosPedido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* EMPRESA */
    public ArrayList<Empresa> empresasLista(String nome, String codCategoria) {
        ArrayList<Empresa> listaEmpresas;
        try {
            informacoesApp.out.writeObject("EmpresaLista");
            String msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(nome);
                informacoesApp.out.writeObject(codCategoria);
                listaEmpresas = (ArrayList<Empresa>) informacoesApp.in.readObject();
            } else {
                listaEmpresas = null;
            }
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

    public Boolean recuperarSenha(String emailUsuario) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("RecuperarSenha");
            msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(emailUsuario);
                msg = (String) informacoesApp.in.readObject();
                return msg.equals("ok");
            } else {
                throw new Exception("Erro ao recuperar senha");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
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

    public Boolean clienteAlterar(Cliente cl) {
        String msg = "";
        try {
            informacoesApp.out.writeObject("ClienteAlterar");
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(cl);
                return informacoesApp.in.readObject().equals("ok");
            }

            return false;
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
                informacoesApp.in.readObject();
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
                return informacoesApp.in.readObject().equals("ok");
            }

            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Prato> pratoListaEmpresa(int codEmpresa) {
        String msg;
        ArrayList<Prato> listaPrato;
        try {
            informacoesApp.out.writeObject("PratoListaEmpresa");
            msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(codEmpresa);
                listaPrato = (ArrayList<Prato>) informacoesApp.in.readObject();
            } else {
                listaPrato = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            listaPrato = null;
        }

        return listaPrato;
    }


    /* CIDADE - ESTADO */
    public ArrayList<Estado> listaEstados() {
        String msg;
        ArrayList<Estado> listaEstados;
        try {
            informacoesApp.out.writeObject("ListaEstados");
            msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                listaEstados = (ArrayList<Estado>) informacoesApp.in.readObject();
            } else {
                listaEstados = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaEstados = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaEstados = null;
        }
        return listaEstados;
    }

    public ArrayList<Cidade> listaCidadesEstado(Estado estado) {
        String msg;
        ArrayList<Cidade> listaCidades;
        try {
            informacoesApp.out.writeObject("ListaCidadesEstado");
            msg = (String) informacoesApp.in.readObject();
            if (msg.equals("ok")) {
                informacoesApp.out.writeObject(estado);
                listaCidades = (ArrayList<Cidade>) informacoesApp.in.readObject();
            } else {
                listaCidades = null;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            listaCidades = null;
        } catch (ClassNotFoundException classe) {
            classe.printStackTrace();
            listaCidades = null;
        }
        return listaCidades;
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
