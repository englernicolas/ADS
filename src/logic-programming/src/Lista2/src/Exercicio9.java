import javax.swing.JOptionPane;

public class Exercicio9 {

	public static void main(String[] args) {
		int qtdAparece = 0, qtdMaior = 0, qtdMenor = 0;
		String igualX = "", maiorX = "", menorX = "";

		int valorX = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de X?"));
		while (valorX < 0) {
			JOptionPane.showMessageDialog(null, "O n�mero n�o pode ser menor que 1", "N�mero inv�lido",
					JOptionPane.ERROR_MESSAGE);
			valorX = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de X?"));
		}

		int tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		while (tamVetor < 1) {
			JOptionPane.showMessageDialog(null, "O n�mero n�o pode ser menor que 1", "N�mero inv�lido",
					JOptionPane.ERROR_MESSAGE);
			tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		}

		int[] vetor = new int[tamVetor];

		for (int i = 0; i < tamVetor; i++) {
			vetor[i] = Integer
					.parseInt(JOptionPane.showInputDialog("Qual o valor da " + (i + 1) + "� posi��o do vetor?"));
			while (vetor[i] < 1) {
				JOptionPane.showMessageDialog(null, "O n�mero n�o pode ser menor que 1", "N�mero inv�lido",
						JOptionPane.ERROR_MESSAGE);
				vetor[i] = Integer
						.parseInt(JOptionPane.showInputDialog("Qual o valor da " + (i + 1) + "� posi��o do vetor?"));
			}
			if (vetor[i] == valorX) {
				qtdAparece++;
				igualX += "O n�mero da posi��o " + (i + 1) + " tem o mesmo valor que x\n";
			} else if (vetor[i] > valorX) {
				qtdMaior++;
				maiorX += "O n�mero da posi��o " + (i + 1) + " � maior que x\n";
			} else {
				qtdMenor++;
				menorX += "O n�mero da posi��o " + (i + 1) + " � menor que x\n";
			}

		}
		JOptionPane.showMessageDialog(null,
				"Foi encontrado " + qtdAparece + " n�mero(s) com o mesmo valor que x\n" + igualX + "\nFoi encontrado "
						+ qtdMaior + " n�mero(s) maior(es) que x\n" + maiorX + "\nFoi encontrado " + qtdMenor
						+ " n�mero(s) menor(es) que x\n" + menorX);
	}
}
