package br.com.bancodigital.models;

public class ContaCorrente extends Conta{
    public ContaCorrente(Cliente cliente) {
        super(cliente);

    }


    @Override
    public void imprimirExtrato() {
        System.out.println("======= EXTRATO CONTA CORRENTE =======");
        imprimirInformacoes();
        System.out.println("======================================");
    }
}
