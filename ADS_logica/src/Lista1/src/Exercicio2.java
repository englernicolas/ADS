import javax.swing.JOptionPane;

public class Exercicio2 {

	public static void main(String[] args) { 
		double precoMenosDuzia = 1.30;
		double precoMaisDuzia= 1; 
		double precoFinal = 0;
		String msgPrecoMacas = "R$1,30 at? 12 ma??s \nR$ 1,00 a partir de 12 ma??s";

		JOptionPane.showMessageDialog(null, msgPrecoMacas, "Pre?o das ma??s", JOptionPane.INFORMATION_MESSAGE);

		int qtdMacas = Integer.parseInt(JOptionPane.showInputDialog("Quantas ma??s voc? deseja comprar?"));
		while (qtdMacas < 1) {
			JOptionPane.showMessageDialog(null,
					"O n?mero n?o pode ser menor que 1", "N?mero inv?lido",
					JOptionPane.ERROR_MESSAGE);
			qtdMacas = Integer.parseInt(JOptionPane.showInputDialog("Quantas ma??s voc? deseja comprar?"));
		}

		if (qtdMacas < 12) {
			precoFinal = qtdMacas * precoMenosDuzia;
		} else {
			precoFinal = qtdMacas * precoMaisDuzia;
		}

		JOptionPane.showMessageDialog(null, "Voc? comprou " + qtdMacas + " ma??(s), o pre?o final ? de R$" + precoFinal,
				"Pre?o Final", JOptionPane.INFORMATION_MESSAGE);

	}

}
