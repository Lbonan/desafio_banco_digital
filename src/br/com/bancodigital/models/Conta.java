package br.com.bancodigital.models;

import br.com.bancodigital.interfaces.IConta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
    private static final int AGENCIA = 1;
    private static int NUMERO = 1;
    protected List<String> historico = new ArrayList<>();

    protected int agencia;
    protected int numero;
    private double saldo;
    protected Cliente cliente;

    public Conta (Cliente cliente) {
        this.cliente = cliente;
        this.agencia = AGENCIA;
        this.numero = NUMERO++;
    }

    private boolean validaValor(double valor){
        if(valor <= 0){
            System.out.println("Valor inválido");
            return false;
        }
        return true;
    }

    @Override
    public void sacar(double valor) {
        if(!validaValor(valor)){
            return;
        };
        if(valor > saldo) {
            System.out.println("Saldo insuficiente!");
            return;
        }
        this.saldo -= valor;
        System.out.println("Saque realizado com sucesso");
        historico.add("[" + LocalDateTime.now() + "]" +"Saque de R$ " + valor);
        System.out.println("Saldo atual: " + this.saldo);
    }

    @Override
    public void depositar(double valor) {
        if(!validaValor(valor)) {
            return;
        }
       this.saldo += valor;
       historico.add("[" + LocalDateTime.now() + "]" +"Depósito de R$ " + valor);
       System.out.println("Depósito realizado com sucesso!");
    }

    @Override
    public void transferir(double valor, Conta destino) {
        if(!validaValor(valor)) {
            return;
        }
        if(valor > saldo) {
            System.out.println("Saldo insuficiente!");
            return;
        }
        this.saldo -= valor;
        destino.depositar(valor);

        historico.add("Transferência enviada de R$ " + valor);
        destino.historico.add("[" + LocalDateTime.now() + "]" +"Transferência recebida de R$ " + valor);

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

    public void exibirHistorico(){
        System.out.println("===== HISTORICO =====");
        for(String hs: historico){
            System.out.println(hs);
        }
    }

    public void imprimirInformacoes() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }


}
