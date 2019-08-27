package oop.src.Calculadora.CalculadoraBasicaOov7.src.projcalcversao7;

public abstract class Operacao implements Ioperacao{
    protected int m_numero1;
    protected int m_numero2;
    public void ajustaValores(int numero1, int numero2){
        m_numero1 = numero1;
        m_numero2 = numero2;
    }

    abstract public String calcular();
}
