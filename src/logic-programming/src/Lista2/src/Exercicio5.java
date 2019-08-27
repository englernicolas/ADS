import javax.swing.JOptionPane;

public class Exercicio5 {

	public static void main(String[] args) {
		int tamVetor = Integer.parseInt(JOptionPane
				.showInputDialog("Qual o tamanho do vetor?"));
		int[] vetor = new int[tamVetor];
		int i = 0;
		int posicaoMaior = 0;
		int posicaoMenor = 0;
		int valorMenor = 0, valorMaior = 0;

		for (i = 0; i < (tamVetor); i++) {
			vetor[i] = Integer.parseInt(JOptionPane
					.showInputDialog("Qual o valor do " + (i + 1)
							+ "º número do vetor?"));
			while (vetor[i] < 0) {
				JOptionPane.showMessageDialog(null, "Número inválido", "",
						JOptionPane.ERROR_MESSAGE);
				vetor[i] = Integer.parseInt(JOptionPane
						.showInputDialog("Qual o valor do " + (i + 1)
								+ "º número do vetor?"));
			}
			if (i == 0) {
				valorMenor = vetor[0];
				valorMaior = vetor[0];
			}
			if (vetor[i] >= valorMaior) {
				posicaoMaior = i;
				valorMaior = vetor[i];
			}
			if (vetor[i] <= valorMenor) {
				posicaoMenor = i;
				valorMenor = vetor[i];
			}
		}
		JOptionPane.showMessageDialog(null, "A posição do maior número é "
				+ posicaoMaior + "\nA posição do menor número é "
				+ posicaoMenor, "", JOptionPane.INFORMATION_MESSAGE);
	}

}
