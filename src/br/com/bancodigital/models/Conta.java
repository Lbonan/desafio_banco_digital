package br.com.bancodigital.models;

import br.com.bancodigital.interfaces.IConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
    private static final int AGENCIA = 1;
    private static int NUMERO = 1;
    protected List<String> historico = new ArrayList<>();

    protected int agencia;
    protected int numero;
    private BigDecimal saldo =  BigDecimal.ZERO;
    protected Cliente cliente;

    public Conta (Cliente cliente) {
        this.cliente = cliente;
        this.agencia = AGENCIA;
        this.numero = NUMERO++;
    }

    private boolean validaValor(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) == 0){
            System.out.println("Valor inválido");
            return false;
        }
        return true;
    }

    @Override
    public void sacar(BigDecimal valor) {
        if(!validaValor(valor)){
            return;
        };
        if(valor.compareTo(saldo) > 1) {
            System.out.println("Saldo insuficiente!");
            return;
        }
        this.saldo =  this.saldo.subtract(valor);
        System.out.println("Saque realizado com sucesso");
        historico.add("[" + LocalDateTime.now() + "]" +"Saque de R$ " + valor);
        System.out.println("Saldo atual: " + this.saldo);
    }

    @Override
    public void depositar(BigDecimal valor) {
        if(!validaValor(valor)) {
            return;
        }
       this.saldo = this.saldo.add(valor);
       historico.add("[" + LocalDateTime.now() + "]" +"Depósito de R$ " + valor);
       System.out.println("Depósito realizado com sucesso!");
    }

    @Override
    public void transferir(BigDecimal valor, Conta destino) {
        if(!validaValor(valor)) {
            return;
        }
        if(valor.compareTo(saldo) == 1) {
            System.out.println("Saldo insuficiente!");
            return;
        }
        this.saldo =  this.saldo.subtract(valor);
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

    public BigDecimal getSaldo() {
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
