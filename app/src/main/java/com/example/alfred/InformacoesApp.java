package com.example.alfred;

import android.app.Application;

import modelDominio.Cliente;
import modelDominio.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

    public Cliente cliente;

    @Override
    public void onCreate() {
        super.onCreate();
    }

}

