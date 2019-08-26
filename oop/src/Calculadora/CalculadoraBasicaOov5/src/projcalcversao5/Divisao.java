package projcalcversao5;

public class Divisao {
    public String calcular(int n1, int n2) {
        int dResultado = 0;
        String msg = "";
        if (n2 != 0) {
            dResultado = n1 / n2;
            msg = "A divisão é: " + dResultado;
        } else {
            dResultado = 999999999;
            msg = "Erro de divisão por zero";
        }
        return msg;
    }
}
