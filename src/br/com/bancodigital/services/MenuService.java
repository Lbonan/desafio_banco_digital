package br.com.bancodigital.services;

import br.com.bancodigital.banco.Banco;
import br.com.bancodigital.exceptions.CpfInvalidoException;
import br.com.bancodigital.models.Cliente;
import br.com.bancodigital.models.Conta;
import br.com.bancodigital.models.ContaCorrente;
import br.com.bancodigital.models.ContaPoupanca;

import java.util.Scanner;



public class MenuService {
   private final Banco banco = new Banco();
   private final Scanner sc = new Scanner(System.in);

    public void iniciar(){
        boolean executando = true;

        while(executando){
            exibirMenu();
            int opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 1 -> iniciarCadastro();
                case 2 -> criarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> transferir();
                case 6 -> mostrarHistorico();
                case 7 -> imprimirExtrato();
                case 8 -> excluirConta();
                case 9 -> executando = false;
            }
        }


    }

    public void exibirMenu(){
        System.out.println("===== BANCO DIGITAL =====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Criar Conta");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Transferir");
        System.out.println("6 - Histórico");
        System.out.println("7 - Extrato");
        System.out.println("8 - Excluir conta");
        System.out.println("9 - Sair");
    }

    public void iniciarCadastro() {
        System.out.println("Nome do Cliente: ");
        String nome = sc.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        System.out.println("CPF do Cliente: ");
        String cpf = sc.nextLine();
        try{
            cliente.setCpf(cpf);
            banco.adicionarCliente(cliente);
            System.out.println("Cadastro realizado com sucesso!");
        } catch(CpfInvalidoException e){
            System.out.println(e.getMessage());
        }
    }

    public void criarConta(){
        System.out.println("Nome do cliente:");
        String nome = sc.nextLine();

        Cliente cliente = banco.buscarCliente(nome);
        if(cliente == null){
            System.out.println("Cliente não encontrado");
            return;
        }
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupanca");
        int tipoDaConta = sc.nextInt();
        sc.nextLine();
        Conta conta;

        if(tipoDaConta == 1){
            conta = new ContaCorrente(cliente);
        } else if (tipoDaConta == 2) {
            conta = new ContaPoupanca(cliente);
        }else {
            System.out.println("Tipo inválido");
            return;
        }
        banco.adicionarConta(conta);
        System.out.println("Conta criada com sucesso!");
        System.out.println("Numero da Conta: " + conta.getNumero());

    }

    public void depositar(){
        System.out.println("Informe o numero da Conta: ");
        int numero = sc.nextInt();
        sc.nextLine();
        Conta conta = banco.buscarConta(numero);

        if(conta == null){
            System.out.println("Conta inexistente");
            return;
        }

        System.out.println("Valor do deposito: ");
        double valor = sc.nextDouble();
        conta.depositar(valor);
    }
    public void sacar(){
        System.out.println("Informe o numero da Conta: ");
        int numero = sc.nextInt();
        sc.nextLine();
        Conta conta = banco.buscarConta(numero);

        if(conta == null){
            System.out.println("Conta inexistente");
            return;
        }
        System.out.println("Valor da saque: ");
        double valor = sc.nextDouble();
        conta.sacar(valor);
    }
    public void transferir(){
        System.out.println("Informe o numero da Conta: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Conta contaOrigem = banco.buscarConta(numero);


        if(contaOrigem == null){
            System.out.println("Conta origem não encontrada!");
            return;
        }

        System.out.println("Informe o numero Conta: ");
        int numeroConta = sc.nextInt();

        Conta contaDestino = banco.buscarConta(numeroConta);
        if(contaDestino == null){
            System.out.println("Conta inexistente");
            return;

        }
        System.out.println("Valor da transferencia: :");
        double valor = sc.nextDouble();

        contaOrigem.transferir(valor, contaDestino);

    }
    public void mostrarHistorico(){
       System.out.println("Informe o numero da Conta: ");
       int numero = sc.nextInt();
       sc.nextLine();
        Conta conta = banco.buscarConta(numero);

        if(conta == null){
            System.out.println("Conta inexistente");
        }

        assert conta != null;
        conta.exibirHistorico();
    }
    public void imprimirExtrato(){
        System.out.println("Informe o numero da Conta: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Conta conta = banco.buscarConta(numero);

        if(conta == null){
            System.out.println("Conta inexistente");
            return;
        }
        conta.imprimirInformacoes();
    }
    public void excluirConta(){
        System.out.println("Informe o numero da Conta: ");
        int numero = sc.nextInt();
        banco.removerConta(numero);
    }
}
