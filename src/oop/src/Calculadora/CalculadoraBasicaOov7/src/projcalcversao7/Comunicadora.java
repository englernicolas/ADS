package projcalcversao7;

public class Comunicadora {
    public void executar(){
        while (true) {
            int num1 = EntradaSaida.infonum1();
            int num2 = EntradaSaida.infonum2();
            int acao = EntradaSaida.infop();
            Controladora controle = new Controladora();
            String msg = controle.calcular(num1, num2, acao);
            EntradaSaida.mostraResultado(msg);
        }
    }
}
