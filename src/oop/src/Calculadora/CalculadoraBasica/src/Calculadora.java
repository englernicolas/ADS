package oop.src.Calculadora.CalculadoraBasica.src;

import javax.swing.JOptionPane;

public class Calculadora {
    public static void main(String[] args) {
        while (true) {
            String msg = "";
            double dResultado = 0;
            int num1 = EntradaSaida.infonum1();
            int num2 = EntradaSaida.infonum2();
            int acao = EntradaSaida.infop();
            switch (acao) {
                case 1:
                    dResultado = num1 + num2;
                    msg = "A soma é: ";
                    break;
                case 2:
                    dResultado = num1 - num2;
                    msg = "A subtração é: ";
                    break;
                case 3:
                    dResultado = num1 * num2;
                    msg = "A multiplicação é: ";
                    break;
                case 4:
                    if (num2 != 0) {
                        dResultado = num1 / num2;
                        msg = "A divisão é: ";
                    } else {
                        dResultado = 999999999.999999999;
                        msg = "Erro de divisão por zero";
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            EntradaSaida.mostraResultado(msg + dResultado);
        }
    }

}
