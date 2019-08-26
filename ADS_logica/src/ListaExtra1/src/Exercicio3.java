import javax.swing.*;

public class Exercicio3 {
    public static double mediaAritmetica(int somaNotas){
        double media = somaNotas / 4;
        return media;
    }

    public static String conceitos(double media){
        String msgFinal = null;
        if (media >= 9){
            msgFinal = "De acordo com sua média (" + media + "), o conceito do aluno é A";
            return msgFinal;
        }else if (media >= 8 && media < 9){
            msgFinal = "De acordo com sua média (" + media + "), o conceito do aluno é B";
            return msgFinal;
        }else if (media >= 7 && media < 8){
            msgFinal = "De acordo com sua média (" + media + "), o conceito do aluno é C";
            return msgFinal;
        }else{
            msgFinal = "De acordo com sua média (" + media + "), o conceito do aluno é D";
            return msgFinal;
        }
    }

    public static void main(String[] args) {
        int somaNotas = 0;

        for (int i = 0; i < 4; i++){
            int notas = Integer.parseInt(JOptionPane.showInputDialog("Qual a " + (i + 1) + "ª nota do aluno?"));
            while (notas < 0 || notas > 10){
                JOptionPane.showMessageDialog(null, "Você digitou uma nota inválida, digite novamente", "Nota inválida", JOptionPane.ERROR_MESSAGE);
                notas = Integer.parseInt(JOptionPane.showInputDialog("Qual a " + (i + 1) + "ª nota do aluno?"));
            }
            somaNotas += notas;
        }

        JOptionPane.showMessageDialog(null, conceitos(mediaAritmetica(somaNotas)), "Conceito do aluno", JOptionPane.INFORMATION_MESSAGE);
    }
}
