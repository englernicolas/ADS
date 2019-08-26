import javax.swing.JOptionPane;

public class Exercicio7 {

	public static void main(String[] args) {
		String lanche = "";
		String bebida = "";
		boolean lancheInvalido = false;
		boolean bebidaInvalida = false;
		int qtdLanches = 0;
		int qtdBebidas = 0;
		double precoBebida = 0;
		float precoLanche = 0;
		int comprarLanche = 0;
		int comprarBebida = 0;
		
		comprarLanche = JOptionPane.showConfirmDialog(null, "Voce deseja comprar algum lanche?");
		do {
			if (comprarLanche == JOptionPane.YES_OPTION) {
				lanche = JOptionPane.showInputDialog(
						"Qual lanche voce deseja?\n 1 - Fatia de pizza (R$5,00)\n 2 - Coxinha (R$3,00)");
				switch (lanche) {
				case "1":
					lancheInvalido = false;
					lanche = "Pizza(s)";
					qtdLanches = Integer
							.parseInt(JOptionPane.showInputDialog("Quantas fatias de pizza voce deseja comprar?"));
					while (qtdLanches < 0) {
						JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
								JOptionPane.ERROR_MESSAGE);
						qtdLanches = Integer
								.parseInt(JOptionPane.showInputDialog("Quantas fatias de pizza voce deseja comprar?"));
					}
					precoLanche = 5 * qtdLanches;
					break;
				case "2":
					lancheInvalido = false;
					lanche = "Coxinha(s)";
					qtdLanches = Integer.parseInt(JOptionPane.showInputDialog("Quantas coxinhas voce deseja comprar?"));
					while (qtdLanches < 0) {
						JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
								JOptionPane.ERROR_MESSAGE);
						qtdLanches = Integer
								.parseInt(JOptionPane.showInputDialog("Quantas coxinhas voce deseja comprar?"));
					}
					precoLanche = 3 * qtdLanches;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
							JOptionPane.ERROR_MESSAGE);
					lancheInvalido = true;
				}
			}
		} while (lancheInvalido == true);

		comprarBebida = JOptionPane.showConfirmDialog(null, "Voce deseja comprar alguma bebida?");
		do {
			if (comprarBebida == JOptionPane.YES_OPTION) {
				bebida = JOptionPane
						.showInputDialog("Qual bebida voce deseja?\n 1 - Refrigerante (R$4,00)\n 2 - Água (R$2,50)");
				switch (bebida) {
				case "1":
					bebidaInvalida = false;
					bebida = "Refrigerante(s)";
					qtdBebidas = Integer
							.parseInt(JOptionPane.showInputDialog("Quantos refrigerantes voce deseja comprar?"));
					while (qtdBebidas < 0) {
						JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
								JOptionPane.ERROR_MESSAGE);
						qtdBebidas = Integer
								.parseInt(JOptionPane.showInputDialog("Quantos refrigerantes voce deseja comprar?"));
					}
					precoBebida = 4 * qtdBebidas;
					break;
				case "2":
					bebidaInvalida = false;
					bebida = "Água(s)";
					qtdBebidas = Integer.parseInt(JOptionPane.showInputDialog("Quantas águas voce deseja comprar?"));
					while (qtdBebidas < 0) {
						JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
								JOptionPane.ERROR_MESSAGE);
						qtdBebidas = Integer
								.parseInt(JOptionPane.showInputDialog("Quantas águas voce deseja comprar?"));
					}
					precoBebida = 2.5 * qtdBebidas;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Voce digitou uma opção inválida", "",
							JOptionPane.ERROR_MESSAGE);
					bebidaInvalida = true;
				}
			}
		} while (bebidaInvalida == true);

		double precoFinal = precoBebida + precoLanche;
		if (comprarLanche != JOptionPane.YES_OPTION && comprarBebida != JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Voce não quis comprar nada", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (comprarLanche != JOptionPane.YES_OPTION && comprarBebida == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null,
						"A compra ficou em um total de R$" + precoFinal + "\n -" + qtdBebidas + " " + bebida, "",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (comprarLanche == JOptionPane.YES_OPTION && comprarBebida != JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null,
							"A compra ficou em um total de R$" + precoFinal + "\n -" + qtdLanches + " " + lanche, "",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane
							.showMessageDialog(
									null, "A compra ficou em um total de R$" + precoFinal + "\n -" + qtdLanches + " "
											+ lanche + "\n -" + qtdBebidas + " " + bebida,
									"", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
	}
}