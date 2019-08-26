package projcalcversao2;

public class Calculadora {


    public static void main(String[] args) {


        while (true){
            int num1 = EntradaSaida.infonum1();
            int num2 = EntradaSaida.infonum2();
            int acao = EntradaSaida.infop();

            Operacoes oper = new Operacoes();

            String msg = oper.calcular(num1, num2, acao);

            EntradaSaida.mostraResultado(msg);
        }
    }
}
