package projcalcversao7;

public class Multiplicacao extends Operacao {
    public Multiplicacao() {
        m_numero1 = 0;
        m_numero2 = 0;
    }
    public String calcular() {
        int dResultado = 0;
        String msg = "";
        dResultado = m_numero1 * m_numero2;
        msg = "A multiplicação é: " + dResultado;
        return msg;
    }
}
