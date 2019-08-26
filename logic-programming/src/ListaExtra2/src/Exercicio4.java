import javax.swing.*;

public class Exercicio4 {
    public static double mediaIdades(int somaIdades, int qtdFunc){
        double media = somaIdades / qtdFunc;
        return media;
    }

    public static String resultado(double media){
        String msgFinal = null;
        if (media <= 25){
            msgFinal = "A equipe tem a média das idades (" + media + "), portanto é considerada uma equipe jovem";
            return msgFinal;
        }else if (media > 25 && media <= 60){
            msgFinal = "A equipe tem a média das idades (" + media + "), portanto é considerada uma equipe adulta";
            return msgFinal;
        }else{
            msgFinal = "A equipe tem a média das idades (" + media + "), portanto é considerada uma equipe idosa";
            return msgFinal;
        }
    }

    public static void main(String[] args) {
        int encerrar = 0;
        int qtdFuncionarios = 0;
        int somaIdades = 0;

        do{
            qtdFuncionarios++;
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade do funcionário?"));
            while (idade < 1){
                JOptionPane.showMessageDialog(null,"A idade não pode ser menor que 1, digite novamente", "Valor inválido", JOptionPane.ERROR_MESSAGE);
                idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade do funcionário?"));
            }
            somaIdades += idade;

            encerrar = JOptionPane.showConfirmDialog(null,"Existe mais alguém na equipe?", "", JOptionPane.INFORMATION_MESSAGE);
        }while (encerrar == JOptionPane.YES_OPTION);

        JOptionPane.showMessageDialog(null, resultado(mediaIdades(somaIdades, qtdFuncionarios)), "Equipe jovem, adulta, ou idosa?", JOptionPane.INFORMATION_MESSAGE);
    }
}
