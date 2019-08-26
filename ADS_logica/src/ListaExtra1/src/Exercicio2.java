import javax.swing.JOptionPane;

public class Exercicio2 {
    public static String primeiroMenor2Ultimos(float n1, float n2, float n3){
        String msgFinal = null;
        if (n1 < (n2 + n3)){
            msgFinal = "O primeiro número do vetor (" + n1 + ") é menor que os dois ultimos números do vetor (" + n2 + ", " + n3 + ")";
            return msgFinal;
        }else{
            msgFinal = "O primeiro número do vetor (" + n1 + ") não é menor que os dois ultimos números do vetor (" + n2 + ", " + n3 + ")";
            return msgFinal;
        }
    }

    public static String primeiroIgualSoma2Ultimos(float n1, float n2, float n3){
        String msgFinal = null;
        if (n1 == (n2 + n3)){
            msgFinal = "O primeiro número do vetor (" + n1 + ") é igual a soma dos dois ultimos números do vetor (" + n2 + ", " + n3 + ")";
            return msgFinal;
        }else{
            msgFinal = "O primeiro número do vetor (" + n1 + ") não é igual a soma dos dois ultimos números do vetor (" + n2 + ", " + n3 + ")";
            return msgFinal;
        }
    }

    public static void main(String[] args) {
        int tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
        while (tamVetor < 3){
            JOptionPane.showMessageDialog(null, "O tamanho do vetor não pode ser menor que 3, escreva novamente", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
        }
        float[] vetor = new float[tamVetor];

        for (int i = 0; i < tamVetor; i++){
            vetor[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor da " + (i + 1) + "ª posição do vetor?"));
        }

        JOptionPane.showMessageDialog(null, primeiroMenor2Ultimos(vetor[0], vetor[(tamVetor - 1)], vetor[(tamVetor - 2)]) + "\n\n" + primeiroIgualSoma2Ultimos(vetor[0], vetor[(tamVetor - 1)], vetor[(tamVetor - 2)]) , "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}
