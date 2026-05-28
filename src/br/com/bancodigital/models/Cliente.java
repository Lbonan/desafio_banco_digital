package br.com.bancodigital.models;

public class Cliente {
    private String nome;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
