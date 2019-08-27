package oop.src.Calculadora.CalculadoraBasicaOov7.src.projcalcversao7;

public class Divisao extends Operacao {
    public Divisao() {
        m_numero1 = 0;
        m_numero2 = 0;
    }
    public String calcular() {
        int dResultado = 0;
        String msg = "";
        if (m_numero2 != 0) {
            dResultado = m_numero1 / m_numero2;
            msg = "A divisão é: " + dResultado;
        } else {
            dResultado = 999999999;
            msg = "Erro de divisão por zero";
        }
        return msg;
    }
}
