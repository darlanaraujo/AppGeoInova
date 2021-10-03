package com.darlanbonfim.appgeoinova;

import java.util.Arrays;
import java.util.List;

public class Pessoas {

    private String nome;
    private String nascimento;
    private String email;
    private char sexo;

    public Arrays pessoas;

    public String getNome(String s) {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento(String s) {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail(String s) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo(String s) {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

}
