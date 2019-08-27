package oop.src.ContaBancaria.src.contabancaria;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String nomeTitular;
    private String tipoConta;
    private float saldo;
    private List<Movimentacao> listaDeMovimentacoes=new ArrayList<Movimentacao>();
    private List<Movimentacao> listaDeDepositos = new ArrayList<Movimentacao>();
    private List<Movimentacao> listaDeSaques = new ArrayList<Movimentacao>();

    public void definirAtributosConta(String nomeTitular, String tipoConta){
        this.nomeTitular = nomeTitular;
        this.tipoConta = tipoConta;
    }

    public void depositar(float valorDepositado) {
        setSaldo(valorDepositado);
        Movimentacao movimentacao = new Movimentacao(2, valorDepositado);
        Movimentacao movimentacaoDepositos = new Movimentacao(valorDepositado);
        listaDeMovimentacoes.add(movimentacao);
        listaDeDepositos.add(movimentacaoDepositos);

    }public void sacar(float valorSacado) {
        setSaldo(-valorSacado);
        Movimentacao movimentacao = new Movimentacao(1, valorSacado);
        Movimentacao movimentacaoSaques = new Movimentacao(valorSacado);
        listaDeMovimentacoes.add(movimentacao);
        listaDeSaques.add(movimentacaoSaques);
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo + getSaldo();
    }

    public float getSaldo() {
        return saldo;
    }

    public List<Movimentacao> getListaDeMovimentacoes() {
        return listaDeMovimentacoes;
    }

    public List<Movimentacao> getListaDeDepositos() {
        return listaDeDepositos;
    }

    public List<Movimentacao> getListadeSaques() {
        return listaDeSaques;
    }
}
