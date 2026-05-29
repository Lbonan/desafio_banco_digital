package br.com.bancodigital.models;

import br.com.bancodigital.exceptions.CpfInvalidoException;

public class Cliente {
    private String nome;
    private String cpf;

    public void validarCpf(String cpf) {
        if ((cpf == null) || !cpf.matches("\\d{11}")){
            throw new CpfInvalidoException("CPF inválido!");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setCpf(String cpf) {
        validarCpf(cpf);
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
