package contabancaria;

import javax.swing.*;

public class InterfaceGrafica {
    Conta conta = new Conta();

    public void solicitarInformacoesUsuario() {
        String nomeTitular = JOptionPane.showInputDialog("Insira o nome do titular");

        String[] listaTiposDeContas = {"Conta Corrente", "Conta Poupança"};
        JComboBox listaDeContas = new JComboBox(listaTiposDeContas);
        Object[] mensagemTipoConta = {"Selecione o tipo de conta:", listaDeContas};
        JOptionPane.showMessageDialog(null, mensagemTipoConta, "Banco", JOptionPane.QUESTION_MESSAGE);

        String tipoConta;

        if (listaDeContas.getSelectedIndex() == 0) {
            tipoConta = listaTiposDeContas[0];
        } else {
            tipoConta = listaTiposDeContas[1];
        }
        conta.definirAtributosConta(nomeTitular, tipoConta);
    }

    public void exibirMenu() {
        while (true) {
            String[] opcoesMenu = {"Depositar", "Sacar", "Dados da conta", "Extrato completo", "Extrato depósitos", "Extrato saques", "Sair"};
            JComboBox listaDeOpcoes = new JComboBox(opcoesMenu);
            Object[] mensagemSelecionarOpcaoMenu = {"Selecione o que deseja fazer:", listaDeOpcoes};
            JOptionPane.showMessageDialog(null, mensagemSelecionarOpcaoMenu, "Banco", JOptionPane.QUESTION_MESSAGE);

            switch (listaDeOpcoes.getSelectedIndex()) {
                case 0:
                    solicitarInformacoesDeposito();
                    break;
                case 1:
                    solicitarInformacoesExtrato();
                    break;
                case 2:
                    exibirDadosDaConta();
                    break;
                case 3:
                    exibirExtratoCompleto();
                    break;
                case 4:
                    exibirExtratoDeDepositos();
                    break;
                case 5:
                    exibirExtratoDeSaques();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    private void solicitarInformacoesDeposito() {
        float valorDepositado = Float.parseFloat(JOptionPane.showInputDialog("Insira o valor em R$ a ser depositado (Mínimo R$ 2.00)"));
        while (valorDepositado < 2) {
            JOptionPane.showMessageDialog(null, "O valor depositado não pode ser menor que R$ 2.00", "Erro", JOptionPane.ERROR_MESSAGE);
            valorDepositado = Float.parseFloat(JOptionPane.showInputDialog("Insira o valor em R$ a ser depositado (Mínimo R$ 2.00)"));
        }
        conta.depositar(valorDepositado);
    }

    private void solicitarInformacoesExtrato() {
        float valorSacado = Float.parseFloat(JOptionPane.showInputDialog("Saldo atual: R$ " + conta.getSaldo() + "\nInsira o valor em R$ a ser sacado"));
        while (valorSacado < 2) {
            JOptionPane.showMessageDialog(null, "O valor sacado não pode ser menor que R$ 2.00", "Erro", JOptionPane.ERROR_MESSAGE);
            valorSacado = Float.parseFloat(JOptionPane.showInputDialog("Saldo atual: R$ " + conta.getSaldo() + "\nInsira o valor em R$ a ser sacado"));
        }
        while (conta.getSaldo() - valorSacado < -1000) {
            JOptionPane.showMessageDialog(null, "Sua conta não pode ficar em menos de R$ -1000.00\n Saldo atual: R$ " + conta.getSaldo(), "Erro", JOptionPane.ERROR_MESSAGE);
            valorSacado = Float.parseFloat(JOptionPane.showInputDialog("Saldo atual: R$ " + conta.getSaldo() + "\nInsira o valor em R$ a ser sacado"));
        }
        conta.sacar(valorSacado);
    }

    private void exibirDadosDaConta() {
        String dados;
        dados = "Nome do Titular: " + conta.getNomeTitular() + "\nTipo de Conta: " + conta.getTipoConta() + "\nSaldo: R$ " + conta.getSaldo();
        JOptionPane.showMessageDialog(null, dados, "Dados da Conta", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirExtratoCompleto() {
        String movimentacoes = "";
        if (conta.getListaDeMovimentacoes().size() == 0) {
            movimentacoes = "Você ainda não possui movimentações em sua conta";
        } else {
            for (Movimentacao movimentacao : conta.getListaDeMovimentacoes()) {
                if (movimentacao.getTipo() == 2) {
                    movimentacoes += movimentacao.getData() + " - Depósito de R$ " + movimentacao.getValor() + "\n";
                } else {
                    movimentacoes += movimentacao.getData() + " - Saque de R$ " + movimentacao.getValor() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, movimentacoes, "Extrato Completo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirExtratoDeDepositos() {
        String depositos = "";
        if (conta.getListaDeDepositos().size() == 0) {
            depositos = "Você ainda não efetuou nenhum Deposito";
        } else {
            for (Movimentacao movimentacao : conta.getListaDeMovimentacoes()) {
                if (movimentacao.getTipo() == 2) {
                    depositos += movimentacao.getData() + " - Depósito de R$ " + movimentacao.getValor() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, depositos, "Extrato de Depósitos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exibirExtratoDeSaques() {
        String saques = "";
        if (conta.getListadeSaques().size() == 0) {
            saques = "Você ainda não efetuou nenhum saque";
        } else {
            for (Movimentacao movimentacao : conta.getListaDeMovimentacoes()) {
                if (movimentacao.getTipo() == 1) {
                    saques += movimentacao.getData() + " - Saque de R$ " + movimentacao.getValor() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, saques, "Extrato de Saques", JOptionPane.INFORMATION_MESSAGE);
    }
}
