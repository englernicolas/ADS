import javax.swing.JOptionPane;

public class Exercicio4 {

	public static void main(String[] args) {
		float num1 = 0;
		float num2 = 0;
		float resultado = 0;
		int continuar = 0;
		String operacao = null;
		num1 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o primeiro número?"));
		do {
			operacao = JOptionPane.showInputDialog(
					"Qual é a operação?\n1 para + (adição)\n2 para - (subtração)\n3 para * (multiplicação)\n4 para / (divisão)");
			num2 = Integer.parseInt(JOptionPane.showInputDialog("Qual é o segundo número?"));
			switch (operacao) {
			case "1":
				resultado = num1 + num2;
				operacao = "+";
				break;
			case "2":
				resultado = num1 - num2;
				operacao = "-";
				break;
			case "3":
				resultado = num1 * num2;
				operacao = "*";
				break;
			case "4":
				if (num2 == 0) {
					JOptionPane.showMessageDialog(null, "O segundo número foi igual a 0", "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					resultado = num1 / num2;
				}
				operacao = "/";
				break;
			}
			JOptionPane.showMessageDialog(null, "O resultado de " + num1 + operacao + num2 + "=" + resultado, "",
					JOptionPane.INFORMATION_MESSAGE);
			num1 = resultado;
			continuar = JOptionPane.showConfirmDialog(null, "Você deseja continuar as operações?");
			if (continuar == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "O primeiro número é o resultado da última operação realizada");
			}
		} while (continuar != JOptionPane.NO_OPTION);

	}

}
