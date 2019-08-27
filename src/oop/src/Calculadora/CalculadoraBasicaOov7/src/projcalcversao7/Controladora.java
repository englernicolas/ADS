package oop.src.Calculadora.CalculadoraBasicaOov7.src.projcalcversao7;

import javax.swing.*;

public class Controladora {
    public  String calcular(int n1, int n2, int op) {
        String mens = "";
        Ioperacao oper = null;
        switch (op) {
            case 1:
                Soma soma = new Soma();
                oper = soma;
                break;
            case 2:
                Subtracao sub = new Subtracao();
                oper = sub;
                break;
            case 3:
                Multiplicacao multi = new Multiplicacao();
                oper = multi;
                break;
            case 4:
                Divisao divi = new Divisao();
                oper = divi;
            case 5:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção Inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }

        if (oper != null){
            oper.ajustaValores(n1,n2);
            mens = oper.calcular();
        }
        return mens;
    }
}
