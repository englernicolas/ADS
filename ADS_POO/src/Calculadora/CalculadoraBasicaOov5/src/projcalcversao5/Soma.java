package projcalcversao5;

public class Soma {
    public String calcular(int n1, int n2) {
        int dResultado = 0;
        String msg = "";
        dResultado = n1 + n2;
        msg = "A soma é: " + dResultado;
        return msg;
    }
}
