import javax.swing.JOptionPane;

public class Exercicio1 {

	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		String num2Multiplo3 = null;
		String num1Multiplo3 = null;
		String menu = null;
		String msgParImpar1 = null;
		String msgParImpar2 = null;
		String msgMaior7 = null;
		boolean mensagemInvalida = false; 
		float media = 0;
		num1 = Integer.parseInt(JOptionPane.showInputDialog("Qual o primeiro número?"));
		num2 = Integer.parseInt(JOptionPane.showInputDialog("Qual o segundo número?"));
		do {
			menu = JOptionPane.showInputDialog(
					"1 – Verificar se os números são múltiplos de 3 \n2 – Verificar se os dois números lidos são pares \n3 – Verificar se a média dos dois números é maior ou igual a 7 \n4 – Sair");
			switch (menu) {
			case "1":
				if (num1 % 3 == 0) {
					num1Multiplo3 = num1 + " é um número multiplo de 3";
				} else {
					num1Multiplo3 = num1 + " não é um número multiplo de 3";
				}
				if (num2 % 3 == 0) {
					num2Multiplo3 = num2 + " é um número multiplo de 3";
				} else {
					num2Multiplo3 = num2 + " não é um número multiplo de 3";
				}
				JOptionPane.showMessageDialog(null, num1Multiplo3 + "\n" + num2Multiplo3, "Multiplo de 3?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "2":
				if (num1 % 2 == 0) {
					msgParImpar1 = num1 + " é um número par";
				} else {
					msgParImpar1 = num1 + " é um número ímpar";
				}
				if (num2 % 2 == 0) {
					msgParImpar2 = num2 + " é um número par";
				} else {
					msgParImpar2 = num2 + " é um número ímpar";
				}
				JOptionPane.showMessageDialog(null, msgParImpar1 + "\n" + msgParImpar2, "Par ou Ímpar?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "3":
				media = (num1 + num2) / 2;
				if (media >= 7) {
					msgMaior7 = "A média(" + media + ") dos dois números é maior ou igual a 7";
				} else {
					msgMaior7 = "A média(" + media + ") dos dois números é menor que 7";
				}
				JOptionPane.showMessageDialog(null, msgMaior7, "A média é maior que 7?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "4":
				break;
			default:
				JOptionPane.showMessageDialog(null, "Você inseriu um número inválido", "Número inválido", JOptionPane.ERROR_MESSAGE);
				mensagemInvalida = true; 
			}

		} while (mensagemInvalida == true);
	}

}
