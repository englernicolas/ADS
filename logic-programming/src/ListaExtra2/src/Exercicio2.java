import javax.swing.JOptionPane;

public class Exercicio2 {
    public static void main(String[] args) {
        String msgFinal = "";

        int num = Integer.parseInt(JOptionPane.showInputDialog("Qual o número?"));
        int inc = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do incremento?"));
        while (inc > num || inc < 1){
            inc = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor do incremento?"));
        }
        for (int i = 0; i < num + 1; i += inc){
            if (i < (num)){
                msgFinal += i + ", ";
            }else{
                msgFinal += i + ".";
            }
        }

        JOptionPane.showMessageDialog(null, msgFinal, "Números de 0 à " + num , JOptionPane.INFORMATION_MESSAGE);
    }
}
