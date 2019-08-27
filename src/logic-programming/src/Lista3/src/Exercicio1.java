import javax.swing.JOptionPane;

public class Exercicio1 {
    public static float aritmetica(float somaNotas){
        float media = somaNotas/3;
        return media;
    }

    public static float ponderada(float n1, float n2, float n3, float p1, float p2, float p3){
        float media = ((n1*p1) + (n2*p2) + (n3*p3))/ (p1+p2+p3);
        return media;
    }

    public static String aprovacao(float media){
        String msgAprovacao = "";
        if (media >= 7){
            msgAprovacao = "O aluno foi aprovado com a média "+(media);
            return msgAprovacao;
        }else{
            msgAprovacao = "O aluno foi reprovado pois não antingiu a média (7), sua média foi "+(media);
            return msgAprovacao;
        }
    }

    public static void main(String[] args) {
        float[] notas = new float[3];
        float somaNotas = 0;
        float media = 0;

        for (int i = 0; i < 3; i++) {
            notas[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual a " + (i + 1) + "ª nota do aluno?"));
            while (notas[i] < 0 || notas[i] > 10){
                JOptionPane.showMessageDialog(null, "Você digitou um número inválido, tente novamente", "Número inválido", JOptionPane.ERROR_MESSAGE);
                notas[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual a " + (i + 1) + "ª nota do aluno?"));
            }
            somaNotas += notas[i];
        }
        int mediaEscolhida = Integer.parseInt(JOptionPane.showInputDialog("Você quer deseja usar média pondera ou média aritmética?\nDigite 1 para média aritmética\nDigite 2 para média ponderada"));
        while (mediaEscolhida < 1 || mediaEscolhida > 2) {
            JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida, tente novamente", "Opção inválida", JOptionPane.ERROR_MESSAGE);
            mediaEscolhida = Integer.parseInt(JOptionPane.showInputDialog("Você quer deseja usar média pondera ou média aritmética?\nDigite 1 para média aritmética\nDigite 2 para média ponderada"));
        }
        switch (mediaEscolhida) {
            case 1:
                JOptionPane.showMessageDialog(null, "A média do aluno foi de:\n"+aritmetica(somaNotas), "Média aritmética", JOptionPane.INFORMATION_MESSAGE);
                media = (aritmetica(somaNotas));
                JOptionPane.showMessageDialog(null, aprovacao(media), "Aprovado ou reprovado?", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                float[] pesos = new float[3];
                for (int i = 0; i < 3; i++) {
                    pesos[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o peso da " + (i + 1) + "ª nota ("+(notas[i])+")?"));
                    while (pesos[i] < 1){
                        JOptionPane.showMessageDialog(null, "Você digitou um número inválido, tente novamente", "Número inválido", JOptionPane.ERROR_MESSAGE);
                        pesos[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o peso da " + (i + 1) + "ª nota ("+(notas[i])+")?"));
                    }
                }
                JOptionPane.showMessageDialog(null, "A média do aluno foi de:\n"+ponderada(notas[0], notas[1], notas[2], pesos[0], pesos[1], pesos[2]), "Média ponderada", JOptionPane.INFORMATION_MESSAGE);
                media = ponderada(notas[0], notas[1], notas[2], pesos[0], pesos[1], pesos[2]);
                JOptionPane.showMessageDialog(null, aprovacao(media), "Aprovado ou reprovado?", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}
