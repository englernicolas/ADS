import javax.swing.JOptionPane;

public class Exercicio3 {

	public static void main(String[] args) {
		int i = 0;
		int numero = 0;
		int qtdPar = 0;
		int qtdImpar = 0;
		int somaPar = 0;
		int somaImpar = 0;

		for (i = 1; i <= 10; i++) {
			numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o " + i + "� n�mero"));
			if (numero % 2 == 0) {
				qtdPar++;
				somaPar += numero;
			} else {
				qtdImpar++;
				somaImpar += numero;
			}
		}

		JOptionPane.showMessageDialog(null,
				"Foi encontrado " + qtdPar + " n�mero(s) par(es), a soma deles � " + somaPar + "\nFoi encontrado "
						+ qtdImpar + " n�mero(s) �mpar(es), a soma deles � " + somaImpar,
				"Resultado", JOptionPane.INFORMATION_MESSAGE);
	}

}
