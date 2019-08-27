package oop.src.Calculadora.CalculadoraBasicaOov4.src.projcalcversao4;

import javax.swing.JOptionPane;

public class Calculadora {
    public static void main(String[] args) {
        while (true) {
            int num1 = EntradaSaida.infonum1();
            int num2 = EntradaSaida.infonum2();
            int acao = EntradaSaida.infop();
            String msg = calcular(num1, num2, acao);
            EntradaSaida.mostraResultado(msg);
        }
    }

    private static String calcular(int n1, int n2, int op) {
        String mens = "";
        switch (op) {
            case 1:
                Soma soma = new Soma();
                mens = soma.calcular(n1, n2);
                break;
            case 2:
                Subtracao sub = new Subtracao();
                mens = sub.calcular(n1, n2);
                break;
            case 3:
                Multiplicacao multi = new Multiplicacao();
                mens = multi.calcular(n1, n2);
                break;
            case 4:
                Divisao divi = new Divisao();
                mens = divi.calcular(n1, n2);
            case 5:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção Inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
        return mens;
    }
}
