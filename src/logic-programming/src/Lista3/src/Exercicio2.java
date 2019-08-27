import javax.swing.JOptionPane;

public class Exercicio2 {
    public static String multiploDe3 (int num1, int num2){
        String msgMultiplo3 = null;

        if (num1 % 3 == 0 && num2 % 3 == 0){
            msgMultiplo3 = num1 + " é múltiplo de 3!\n" + num2 + " é múltiplo de 3!";
            return msgMultiplo3;
        }else if (num1 % 3 != 0 && num2 % 3 == 0){
            msgMultiplo3 = num1 + " não é múltiplo de 3!\n" + num2 + " é múltiplo de 3!";
            return msgMultiplo3;
        }else if (num1 % 3 == 0 && num2 % 3 != 0){
            msgMultiplo3 = num1 + " é múltiplo de 3!\n" + num2 + " não é múltiplo de 3!";
            return msgMultiplo3;
        }else{
            msgMultiplo3 = num1 + "não é múltiplo de 3!\n" + num2 + " não é múltiplo de 3!";
            return msgMultiplo3;
        }
    }

    public static String parImpar(int num1, int num2){
        String msgParImpar = null;

        if (num1 % 2 == 0 && num2 % 2 == 0){
            msgParImpar = num1 + " é par!\n" + num2 + " é par!";
            return msgParImpar;
        }else if (num1 % 2 != 0 && num2 % 2 == 0){
            msgParImpar = num1 + " é ímpar!\n" + num2 + " é par!";
            return msgParImpar;
        }else if (num1 % 2 == 0 && num2 % 2 != 0){
            msgParImpar = num1 + " é par!\n" + num2 + " é ímpar!";
            return msgParImpar;
        }else{
            msgParImpar = num1 + " é ímpar!\n" + num2 + " é ímpar!";
            return msgParImpar;
        }
    }

    public static String mediaMaior7 (int num1, int num2){
        float media = (num1 + num2)/2;
        String msgMaior7 = null;

        if (media >= 7){
            msgMaior7 = "A média (" + media + ") entre " + num1 + " e " + num2 + " é maior ou igual a 7!";
            return msgMaior7;
        }else{
            msgMaior7 = "A média (" + media + ") entre " + num1 + " e " + num2 + " é menor que 7!";
            return msgMaior7;
        }

    }

    public static void main(String[] args) {
        boolean msgInvalida = false;

        int num1 = Integer.parseInt(JOptionPane.showInputDialog("Qual o primeiro número?"));
        int num2 = Integer.parseInt(JOptionPane.showInputDialog("Qual o segundo número?"));
        do{
            String menu = JOptionPane.showInputDialog("1  Verificar se os números são múltiplos de 3 \n2  Verificar se os dois números lidos são pares \n3  Verificar se a média dos dois números é maior ou igual a 7 \n4  Sair");
            switch (menu){
                case "1":
                    JOptionPane.showMessageDialog(null, multiploDe3(num1, num2), "Múltiplo de 3?", JOptionPane.INFORMATION_MESSAGE);
                    msgInvalida = false;
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, parImpar(num1, num2), "Par ou ímpar?", JOptionPane.INFORMATION_MESSAGE);
                    msgInvalida = false;
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, mediaMaior7(num1, num2), "Média maior que 7?", JOptionPane.INFORMATION_MESSAGE);
                    msgInvalida = false;
                    break;
                case "4":
                    msgInvalida = false;
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Você digitou um número inválido, tente novamente!", "Número inválido", JOptionPane.ERROR_MESSAGE);
                    msgInvalida = true;
            }
        }while (msgInvalida == true);
    }
}
