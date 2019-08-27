import javax.swing.*;

public class Exercicio4 {
    public static void main(String[] args) {
        int[][] matriz = new int[3][3];
        String msgLinha = "";

        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de [" + (i+1) + ", " + (j+1) + "]?"));
            }
        }

        int linha = Integer.parseInt(JOptionPane.showInputDialog("Qual linha você deseja ver? (1, 2, 3)"));
        while (linha < 1 || linha > 3){
            JOptionPane.showMessageDialog(null, "Você informou uma linha errada, digite novamente", "Linha inválida", JOptionPane.ERROR_MESSAGE);
            linha = Integer.parseInt(JOptionPane.showInputDialog("Qual linha você deseja ver? (1, 2, 3)"));
        }

        for (int j = 0; j < matriz.length; j++){
            if (j < 2){
                msgLinha += matriz[linha - 1][j] + ", ";
            }else{
                msgLinha += matriz[linha - 1][j] + "";
            }
        }

        JOptionPane.showMessageDialog(null, "A o conteúdo da linha infomada é:\n" + "[" + msgLinha + "]", "Linha", JOptionPane.INFORMATION_MESSAGE);
    }
}
