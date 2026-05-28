package br.com.bancodigital.models;

import br.com.bancodigital.interfaces.IConta;

public abstract class Conta implements IConta {
    private static final int AGENCIA = 1;
    private static int NUMERO = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta (Cliente cliente) {
        this.cliente = cliente;
        this.agencia = AGENCIA;
        this.numero = NUMERO++;
    }

    @Override
    public void sacar(double valor) {
        if(valor <= 0) {
            System.out.println("Valor inválido");
            return;
        }

        if(valor > saldo) {
            System.out.println("Saldo insuficiente");
            return;
        }
        this.saldo -= valor;
        System.out.println("Saque realizado com sucesso");
        System.out.println("Saldo atual: " + this.saldo);
    }

    @Override
    public void depositar(double valor) {
       if (valor <= 0) {
           System.out.println("Valor invalido");
           return;
       }
       this.saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);

        System.out.println("Transferencia realizada com sucesso!");

    }

    public int getAgencia() {
        return agencia;
    }
    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInformacoes() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
}
