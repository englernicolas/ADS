package projcalcversao6;

import javax.swing.*;

public class Controladora {
    public  String calcular(int n1, int n2, int op) {
        String mens = "";
        switch (op) {
            case 1:
                Soma soma = new Soma();
                soma.ajustaValores(n1, n2);
                mens = soma.calcular();
                break;
            case 2:
                Subtracao sub = new Subtracao();
                sub.ajustaValores(n1, n2);
                mens = sub.calcular();
                break;
            case 3:
                Multiplicacao multi = new Multiplicacao();
                multi.ajustaValores(n1, n2);
                mens = multi.calcular();
                break;
            case 4:
                Divisao divi = new Divisao();
                divi.ajustaValores(n1, n2);
                mens = divi.calcular();
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
