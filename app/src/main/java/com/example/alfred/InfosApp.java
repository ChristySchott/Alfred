package com.example.alfred;

import android.app.Application;

import com.example.alfred.modelDominio.Prato;
import com.example.alfred.modelDominio.Empresa;

import java.util.ArrayList;

public class InfosApp extends Application {

    private ArrayList<Empresa> empresaList;
    private ArrayList<Prato> pratoList;

    @Override
    public void onCreate() {
        super.onCreate();
        empresaList = new ArrayList<>();
    }

    public ArrayList<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(ArrayList<Empresa> empresaList){
        this.empresaList = empresaList;
    }

    public ArrayList<Prato> getPratoList() {
        return pratoList;
    }

    public void setPratoList(ArrayList<Prato> pratoList) {
        this.pratoList = pratoList;
    }
}
