package br.com.bancodigital.banco;

import br.com.bancodigital.models.Cliente;
import br.com.bancodigital.models.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Conta buscarConta(int numero) {
       for(Conta c : contas){
           if(c.getNumero() == numero){
               return c;
           }
       }
        return null;
    }

    public Cliente buscarCliente(String nome) {
        for(Cliente cliente : clientes){
           if(nome.equals(cliente.getNome())){
               return cliente;
           }
        }
        return null;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    public void removerConta(Conta conta) {
        contas.remove(conta);
        System.out.println("Conta removida com sucesso!");
    }


}
