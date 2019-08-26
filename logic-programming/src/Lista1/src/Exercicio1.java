import javax.swing.JOptionPane;

public class Exercicio1 {

	public static void main(String[] args) {
		float valorTotal = 0;
		float valorParcelaMensal = 0;
		int qtdParcelas = 0;

		valorTotal = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor total de sua compra?"));
		while (valorTotal < 1) {
			JOptionPane.showMessageDialog(null, "O número não pode ser menor que 1", "Número inválido",
					JOptionPane.ERROR_MESSAGE);
			valorTotal = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor total de sua compra?"));
		}
		
		qtdParcelas = Integer
				.parseInt(JOptionPane.showInputDialog("Qual a quantidades de parcelas que você deseja fazer?"));
		while (qtdParcelas < 1) {
			JOptionPane.showMessageDialog(null, "O número não pode ser menor que 1", "Número inválido",
					JOptionPane.ERROR_MESSAGE);
			qtdParcelas = Integer
					.parseInt(JOptionPane.showInputDialog("Qual a quantidades de parcelas que você deseja fazer?"));
		}
		
		valorParcelaMensal = valorTotal / qtdParcelas;

		JOptionPane.showMessageDialog(null,
				"Sua compra foi de R$" + valorTotal + ", você optou por parcelar em " + qtdParcelas
						+ " vezes. Sua parcela mensal é de R$" + valorParcelaMensal,
				"Resultado", JOptionPane.INFORMATION_MESSAGE);
	}

}
