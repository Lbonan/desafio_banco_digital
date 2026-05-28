package br.com.bancodigital.interfaces;

import br.com.bancodigital.models.Conta;

public interface IConta {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, Conta destino);
    void imprimirExtrato();
}
