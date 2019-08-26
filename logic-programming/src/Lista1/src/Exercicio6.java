import javax.swing.JOptionPane;

public class Exercicio6 {

	public static void main(String[] args) {
		float[][] matriz = new float[6][4];
		float[] somaMensal = new float[6];
		int meses = 0;
		int semanas = 0;
		float somaTotal = 0;
		String msgMes = "";
		for (meses = 0; meses < 6; meses++) {
			for (semanas = 0; semanas < 4; semanas++) {
				matriz[meses][semanas] = Integer.parseInt(JOptionPane.showInputDialog(
						"Informe o valor das vendas da " + (semanas + 1) + "ª semana do " + (meses + 1) + "º mês"));
				somaMensal[meses] += matriz[meses][semanas];
				somaTotal += matriz[meses][semanas];
			}
			msgMes += "O total de vendas do " + (meses + 1) + "º mês foi de R$" + somaMensal[meses] + "\n";
		}
		JOptionPane.showMessageDialog(null,
				"Total de vendas do semestre:\nR$" + somaTotal + "\n\nTotal de vendas dos meses:\n" + msgMes, "Vendas",
				JOptionPane.INFORMATION_MESSAGE);

	}

}
