import javax.swing.JOptionPane;
public class Exercicio3 {

	public static void main(String[] args) {
		int qtdAlunos = 0;
		int i = 0;
		int cont = 0;
		float notas = 0;
		float media = 0;
		float medias = 0;
		float mediaTurma = 0; 
		float nota = 0;
		String msgFinal = "";
		
		qtdAlunos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de alunos?"));
		while (qtdAlunos < 1){
			JOptionPane.showMessageDialog(null, "Voce digitou um número inválido","Quantidade inválida",JOptionPane.ERROR_MESSAGE);
			qtdAlunos = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de alunos?"));
		}
		for (cont=0; cont<qtdAlunos; cont++){
			for (i=0; i<3; i++){
				nota = Integer.parseInt(JOptionPane.showInputDialog("Qual a "+(i+1)+"ª nota do "+(cont+1)+"º aluno?"));
				while (nota < 0) {
					JOptionPane.showMessageDialog(null, "Voce digitou uma nota inválida","Nota inválida",JOptionPane.ERROR_MESSAGE);
					nota = Integer.parseInt(JOptionPane.showInputDialog("Qual a "+(i+1)+"ª nota do "+(cont+1)+"º aluno?"));
				}
				notas += nota;
				media = notas/3; 
			}
		msgFinal += ("A média do "+(cont+1)+"º aluno é de "+media+"\n");
		medias += media;
		nota = 0;
		notas = 0; 
		media = 0;
		}
		mediaTurma = medias/qtdAlunos;
		
		JOptionPane.showMessageDialog(null,"A média da turma é de "+mediaTurma+"\n"+msgFinal,"Médias",JOptionPane.INFORMATION_MESSAGE);
	}

}
