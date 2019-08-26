import javax.swing.*;

public class Exercicio1 {
    public static void main(String[] args) {
        int[][] matriz = new int[3][4];
        int[][] matrizMod = new int[3][4];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++){
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor de [" + (i + 1) + ", " + (j + 1) + "]?"));
                if (matriz[i][j] < 0){
                    matrizMod[i][j] = 0;
                }else{
                    matrizMod[i][j] = matriz[i][j];
                }
            }
        }



        String imprimirMatriz = "";
        String imprimirMatrizMod = "";

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++){
                imprimirMatriz += "| " + matriz[i][j] + " |";
            }
            imprimirMatriz += "\n";
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 4; j++){
                imprimirMatrizMod += "| " + matrizMod[i][j] + " |";
            }
            imprimirMatrizMod += "\n";
        }


        JOptionPane.showMessageDialog(null, imprimirMatriz, "Matriz 3x4", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, imprimirMatrizMod, "Matriz modificada 3x4", JOptionPane.INFORMATION_MESSAGE);
    }
}
