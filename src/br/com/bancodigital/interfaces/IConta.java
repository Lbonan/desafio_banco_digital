package br.com.bancodigital.interfaces;

import br.com.bancodigital.models.Conta;

import java.math.BigDecimal;

public interface IConta {
    void sacar(BigDecimal valor);
    void depositar(BigDecimal valor);
    void transferir(BigDecimal valor, Conta destino);
    void imprimirExtrato();
}
