import javax.swing.JOptionPane;

public class Exercicio5 { 

	public static void main(String[] args) {
		int[] numeros = new int[10];
		int num = 0;
		int i = 0;
		String msgFinal = "";
		boolean achou = false;
		for (i = 0; i < 10; i++) {
			numeros[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o n�mero da posi��o " + (i + 1)));
		}

		num = Integer.parseInt(JOptionPane.showInputDialog("Insira um n�mero inteiro"));

		for (i = 0; i < 10; i++) {
			if (numeros[i] == num) {
				msgFinal +=  num+" foi encontrado na posi��o " + (i + 1) + "\n";
				achou = true;
			}
		}
		if (achou == false) {
			msgFinal += "O n�mero n�o foi encontrado no vetor";
		}
		JOptionPane.showMessageDialog(null, msgFinal, "Foi encontrado?",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
