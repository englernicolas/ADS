import javax.swing.JOptionPane;

public class Exercicio4 {

	public static void main(String[] args) {
		float num1 = 0;
		float num2 = 0;
		float resultado = 0;
		int continuar = 0;
		String operacao = null;
		num1 = Integer.parseInt(JOptionPane.showInputDialog("Qual � o primeiro n�mero?"));
		do {
			operacao = JOptionPane.showInputDialog(
					"Qual � a opera��o?\n1 para + (adi��o)\n2 para - (subtra��o)\n3 para * (multiplica��o)\n4 para / (divis�o)");
			num2 = Integer.parseInt(JOptionPane.showInputDialog("Qual � o segundo n�mero?"));
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
					JOptionPane.showMessageDialog(null, "O segundo n�mero foi igual a 0", "",
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
			continuar = JOptionPane.showConfirmDialog(null, "Voc� deseja continuar as opera��es?");
			if (continuar == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "O primeiro n�mero � o resultado da �ltima opera��o realizada");
			}
		} while (continuar != JOptionPane.NO_OPTION);

	}

}
