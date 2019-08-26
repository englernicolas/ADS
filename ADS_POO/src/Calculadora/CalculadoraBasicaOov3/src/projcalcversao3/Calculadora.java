package projcalcversao3;

public class Calculadora {
    static Operacoes oper = new Operacoes();
    public static void main(String[] args) {
        while (true){
            int num1 = EntradaSaida.infonum1();
            int num2 = EntradaSaida.infonum2();
            int acao = EntradaSaida.infop();
            String msg = oper.calcular(num1, num2, acao);
            EntradaSaida.mostraResultado(msg);
        }
    }
}
