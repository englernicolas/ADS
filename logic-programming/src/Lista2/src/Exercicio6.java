import javax.swing.JOptionPane;
public class Exercicio6 {

	public static void main(String[] args) {
		int ano = Integer.parseInt(JOptionPane.showInputDialog("Qual o ano?"));
		String msgFinal = "";
		while (ano<1){
			JOptionPane.showMessageDialog(null, "Você informou um ano inválido","",JOptionPane.ERROR_MESSAGE);
			ano = Integer.parseInt(JOptionPane.showInputDialog("Qual o ano?"));
		}
		if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)){
			msgFinal = ("O ano "+ano+" é bissexto");
		}else{
			msgFinal = ("O ano "+ano+" não é bissexto");
		}
		JOptionPane.showMessageDialog(null, msgFinal,"É um ano bissexto?",JOptionPane.INFORMATION_MESSAGE);
	}

}
