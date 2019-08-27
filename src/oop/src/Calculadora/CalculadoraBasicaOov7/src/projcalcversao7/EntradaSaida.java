package oop.src.Calculadora.CalculadoraBasicaOov7.src.projcalcversao7;

import javax.swing.*;

public class EntradaSaida {
    static int infonum1() {
        int n1 = Integer.parseInt(JOptionPane.showInputDialog("Informe o primeiro número : "));
        return n1;
    }

    static int infonum2() {
        int n2 = Integer.parseInt(JOptionPane.showInputDialog("Informe o segundo número : "));
        return n2;
    }

    static int infop() {
        int op = Integer.parseInt(JOptionPane.showInputDialog("Escolha um cálculo básico : \n" + "1 - Soma \n" + "2 - Subtração \n" + "3 - Multiplicação \n" + "4 - Divisão \n" + "5 - Sair do programa \n"));
        return op;
    }

    static void mostraResultado(String recmsgdresult) {
        JOptionPane.showMessageDialog(null, recmsgdresult, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }
}