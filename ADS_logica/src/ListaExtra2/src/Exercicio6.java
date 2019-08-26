import javax.swing.JOptionPane;

public class Exercicio6 {

    public static void main(String[] args) {
        int[] vetor = new int[10];
        int qtdNegativos = 0;
        int maiorNum = vetor[0];
        int menorNum = vetor[0];
        String msgNegativo = "";
        String msgMenor = "";
        String msgMaior = "";

        for (int i = 0; i < 10; i++){
            vetor[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o " + (i + 1) + "º valor do vetor?"));

            if (vetor[i] > maiorNum){
                maiorNum = vetor[i];
                msgMaior = "A posição do maior número (" + maiorNum + ") é " + (i + 1) + "\n";
            }

            if (vetor[i] < menorNum){
                menorNum = vetor[i];
                msgMenor = "A posição do menor número (" + menorNum + ") é " + (i + 1) + "\n";
            }

            if (vetor[i] < 0){
                qtdNegativos++;
                msgNegativo = "Existem " + qtdNegativos + " números negativos\nO último número negativo (" + vetor[i] + ") apareceu na posição " + (i + 1) + " do vetor";
            }

        }

        JOptionPane.showMessageDialog(null, msgMaior + msgMenor + msgNegativo, "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}
