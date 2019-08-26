import javax.swing.JOptionPane;

public class Exercicio1 {
    public static int maiorNum(int n1, int n2, int n3){
        if (n1 >= n2 && n1 >= n3){
            return n1;
        }else if (n2 >= n1 && n2 >= n3){
            return n2;
        }else{
            return n3;
        }

    }

    public static int menorNum(int n1, int n2, int n3){
        if (n1 <= n2 && n1 <= n3){
            return n1;
        }else if (n2 <= n1 && n2 <= n3){
            return n2;
        }else{
            return n3;
        }

    }

    public static String menorXMaior(int menor, int maior){
        int resultado = menor * maior;
        String msgFinal = "O resultado da multiplicação do menor número (" + menor + ") pelo maior número (" + maior + ") é igual a " + resultado;
        return msgFinal;
    }

    public static String maiorDivMenor(int menor, int maior){
        float resultado = (float)maior / menor;
        String msgFinal = "O resultado da divisão do maior número (" + maior + ") pelo menor número (" + menor + ") é igual a " + resultado;
        return msgFinal;
    }

    public static void main(String[] args) {
        int valor1 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o primeiro valor inteiro?"));
            while (valor1 < 1){
                JOptionPane.showMessageDialog(null, "Você informou um valor menor que 1, tente novamente", "Valor inválido", JOptionPane.ERROR_MESSAGE);
                valor1 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o primeiro valor inteiro?"));
            }

        int valor2 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o segundo valor inteiro?"));
        while (valor2 < 1){
            JOptionPane.showMessageDialog(null, "Você informou um valor menor que 1, tente novamente", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            valor2 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o segundo valor inteiro?"));
        }

        int valor3 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o terceiro valor inteiro?"));
        while (valor3 < 1){
            JOptionPane.showMessageDialog(null, "Você informou um valor menor que 1, tente novamente", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            valor3 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o terceiro valor inteiro?"));
        }

        JOptionPane.showMessageDialog(null, menorXMaior(menorNum(valor1,valor2,valor3),maiorNum(valor1,valor2,valor3)) + "\n" + maiorDivMenor(menorNum(valor1,valor2,valor3),maiorNum(valor1,valor2,valor3)), "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}
