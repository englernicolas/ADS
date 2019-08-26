import javax.swing.JOptionPane;

public class Exercicio9 {

	public static void main(String[] args) {
		int qtdAparece = 0, qtdMaior = 0, qtdMenor = 0;
		String igualX = "", maiorX = "", menorX = "";

		int valorX = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de X?"));
		while (valorX < 0) {
			JOptionPane.showMessageDialog(null, "O número não pode ser menor que 1", "Número inválido",
					JOptionPane.ERROR_MESSAGE);
			valorX = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de X?"));
		}

		int tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		while (tamVetor < 1) {
			JOptionPane.showMessageDialog(null, "O número não pode ser menor que 1", "Número inválido",
					JOptionPane.ERROR_MESSAGE);
			tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		}

		int[] vetor = new int[tamVetor];

		for (int i = 0; i < tamVetor; i++) {
			vetor[i] = Integer
					.parseInt(JOptionPane.showInputDialog("Qual o valor da " + (i + 1) + "ª posição do vetor?"));
			while (vetor[i] < 1) {
				JOptionPane.showMessageDialog(null, "O número não pode ser menor que 1", "Número inválido",
						JOptionPane.ERROR_MESSAGE);
				vetor[i] = Integer
						.parseInt(JOptionPane.showInputDialog("Qual o valor da " + (i + 1) + "ª posição do vetor?"));
			}
			if (vetor[i] == valorX) {
				qtdAparece++;
				igualX += "O número da posição " + (i + 1) + " tem o mesmo valor que x\n";
			} else if (vetor[i] > valorX) {
				qtdMaior++;
				maiorX += "O número da posição " + (i + 1) + " é maior que x\n";
			} else {
				qtdMenor++;
				menorX += "O número da posição " + (i + 1) + " é menor que x\n";
			}

		}
		JOptionPane.showMessageDialog(null,
				"Foi encontrado " + qtdAparece + " número(s) com o mesmo valor que x\n" + igualX + "\nFoi encontrado "
						+ qtdMaior + " número(s) maior(es) que x\n" + maiorX + "\nFoi encontrado " + qtdMenor
						+ " número(s) menor(es) que x\n" + menorX);
	}
}
