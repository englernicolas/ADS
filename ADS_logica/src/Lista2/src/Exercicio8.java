import java.util.Random;

import javax.swing.JOptionPane;

public class Exercicio8 {

	public static void main(String[] args) {

		int[][] matriz = new int[10][10];
		Random numAleatorio = new Random();
		int valorMenor = 0, valorMaior = 0;
		String posicaoMenor = "", posicaoMaior = "";
		String imprimeMatriz = "";

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				matriz[i][j] = numAleatorio.nextInt(151);

				if (i == 0) {
					valorMenor = matriz[i][j];
					valorMaior = matriz[i][j];
				}
				if (matriz[i][j] >= valorMaior) {
					posicaoMaior = "[" + i + "," + j + "]";
					valorMaior = matriz[i][j];
				}
				if (matriz[i][j] <= valorMenor) {
					posicaoMenor = "[" + i + "," + j + "]";
					valorMenor = matriz[i][j];
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				imprimeMatriz += (matriz[i][j] + " | "); 
			}
			imprimeMatriz += "\n"; 
		}

		JOptionPane.showMessageDialog(null, imprimeMatriz, "matriz de tamanho 10x10", JOptionPane.INFORMATION_MESSAGE);

		JOptionPane.showMessageDialog(null,
				"A posição do maior número é " + posicaoMaior + "\nA posição do menor número é " + posicaoMenor,
				"Considere a matriz de tamanho 10x10", JOptionPane.INFORMATION_MESSAGE);
	}
}
