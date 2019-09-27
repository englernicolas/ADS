import javax.swing.JOptionPane;

public class Exercicio2 {

	public static void main(String[] args) {
		int i = 0;
		int contPares = 0;
		int contImpares = 0;
		int[] pares = new int[21];
		int[] impares = new int[20];
		String msgPares = "";
		String msgImpares = "";
		for (i = 10; i < 51; i++) {
			if (i % 2 == 0) {
				pares[contPares] = i;
				if (pares[contPares] != 50) {
					msgPares += (pares[contPares] + ", ");
				} else {
					msgPares += (pares[contPares] + ".");
				}
				contPares++;
			} else {
				impares[contImpares] = i;
				if (impares[contImpares] != 49) {
					msgImpares += (impares[contImpares] + ", ");
				} else {
					msgImpares += (impares[contImpares] + ".");
				}
				contImpares++;
			}
		}
		JOptionPane
				.showMessageDialog(null,
						"Os n�meros pares entre 10 e 50 s�o:\n" + msgPares
								+ "\n\nOs n�meros �mpares entre 10 e 50 s�o:\n" + msgImpares,
						"Pares e �mpares de 10 a 50", JOptionPane.INFORMATION_MESSAGE);
	}

}
