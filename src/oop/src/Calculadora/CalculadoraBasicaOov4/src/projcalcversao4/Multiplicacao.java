package oop.src.Calculadora.CalculadoraBasicaOov4.src.projcalcversao4;

public class Multiplicacao {
    public String calcular(int n1, int n2) {
        int dResultado = 0;
        String msg = "";
        dResultado = n1 * n2;
        msg = "A multiplicação é: " + dResultado;
        return msg;
    }
}
