import javax.swing.JOptionPane;
public class Exercicio4 {

	public static void main(String[] args) {
		int tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		while (tamVetor < 1){
			JOptionPane.showMessageDialog(null, "Voce digitou um n�mero inv�lido","",JOptionPane.ERROR_MESSAGE);
			tamVetor = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do vetor?"));
		}
		int[] vetor = new int[tamVetor]; 
		int i = 0; 
		int qtdNumNeg = 0;
		String msgNegativos = "";
		String msgFinal = "";
		for (i=0; i<(tamVetor); i++){
			vetor[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual o "+(i+1)+"� valor do vetor?"));

			if (vetor[i] < 0){ 
				msgNegativos += (vetor[i]+"\n"); 
				qtdNumNeg++;
			}
		}
		if (qtdNumNeg < 1){
			msgFinal = ("N�o h� n�meros negativos no vetor");
		}else{
			msgFinal = ("Os n�meros negativos do vetor s�o:\n"+msgNegativos);
		}
		JOptionPane.showMessageDialog(null,msgFinal,"Negativos?",JOptionPane.INFORMATION_MESSAGE);
	}

}
