package com.example.android.justjava;

/**
 * Created by Hellow on 12/09/2016.
 */
public class Produto {
    public String chave;
    public String nome;
    public Boolean status;


    public Produto() {
    }

    public Produto(String chave,String nome, Boolean status) {
        this.chave = chave;
        this.nome = nome;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }
}
