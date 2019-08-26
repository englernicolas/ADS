import javax.swing.JOptionPane;

public class Exercicio5 {
    public static void main(String[] args) {
        int qtdHabA = 80000, qtdHabB = 200000;
        int anos = 0;
        double crescA = (qtdHabA * 0.03), crescB = (qtdHabB * 0.015);

        while (qtdHabA <= qtdHabB){
            anos++;

            qtdHabA += crescA;
            crescA = (qtdHabA * 0.03);
            qtdHabB += crescB;
            crescB = (qtdHabB * 0.015);
        }

        JOptionPane.showMessageDialog(null, "O país A (" + qtdHabA + ") ultrapassou a população do país B (" + qtdHabB + ") em " + anos + " anos", "", JOptionPane.INFORMATION_MESSAGE);
    }
}
