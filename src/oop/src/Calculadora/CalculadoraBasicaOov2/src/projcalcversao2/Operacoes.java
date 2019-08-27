package oop.src.Calculadora.CalculadoraBasicaOov2.src.projcalcversao2;

import javax.swing.JOptionPane;

public class Operacoes {
    public String calcular(int n1, int n2, int op){
        String msg = "";
        double dResultado = 0;

        switch (op) {
            case 1:
                dResultado = n1 + n2;
                msg = "A soma é: ";
                break;
            case 2:
                dResultado = n1 - n2;
                msg = "A subtração é: ";
                break;
            case 3:
                dResultado = n1 * n2;
                msg = "A multiplicação é: ";
                break;
            case 4:
                if (n2 != 0) {
                    dResultado = n1 / n2;
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
        return (msg + "" + dResultado);
    }
}
