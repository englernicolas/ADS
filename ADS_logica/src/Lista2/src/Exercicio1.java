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
		num1 = Integer.parseInt(JOptionPane.showInputDialog("Qual o primeiro n�mero?"));
		num2 = Integer.parseInt(JOptionPane.showInputDialog("Qual o segundo n�mero?"));
		do {
			menu = JOptionPane.showInputDialog(
					"1 � Verificar se os n�meros s�o m�ltiplos de 3 \n2 � Verificar se os dois n�meros lidos s�o pares \n3 � Verificar se a m�dia dos dois n�meros � maior ou igual a 7 \n4 � Sair");
			switch (menu) {
			case "1":
				if (num1 % 3 == 0) {
					num1Multiplo3 = num1 + " � um n�mero multiplo de 3";
				} else {
					num1Multiplo3 = num1 + " n�o � um n�mero multiplo de 3";
				}
				if (num2 % 3 == 0) {
					num2Multiplo3 = num2 + " � um n�mero multiplo de 3";
				} else {
					num2Multiplo3 = num2 + " n�o � um n�mero multiplo de 3";
				}
				JOptionPane.showMessageDialog(null, num1Multiplo3 + "\n" + num2Multiplo3, "Multiplo de 3?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "2":
				if (num1 % 2 == 0) {
					msgParImpar1 = num1 + " � um n�mero par";
				} else {
					msgParImpar1 = num1 + " � um n�mero �mpar";
				}
				if (num2 % 2 == 0) {
					msgParImpar2 = num2 + " � um n�mero par";
				} else {
					msgParImpar2 = num2 + " � um n�mero �mpar";
				}
				JOptionPane.showMessageDialog(null, msgParImpar1 + "\n" + msgParImpar2, "Par ou �mpar?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "3":
				media = (num1 + num2) / 2;
				if (media >= 7) {
					msgMaior7 = "A m�dia(" + media + ") dos dois n�meros � maior ou igual a 7";
				} else {
					msgMaior7 = "A m�dia(" + media + ") dos dois n�meros � menor que 7";
				}
				JOptionPane.showMessageDialog(null, msgMaior7, "A m�dia � maior que 7?",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "4":
				break;
			default:
				JOptionPane.showMessageDialog(null, "Voc� inseriu um n�mero inv�lido", "N�mero inv�lido", JOptionPane.ERROR_MESSAGE);
				mensagemInvalida = true; 
			}

		} while (mensagemInvalida == true);
	}

}
