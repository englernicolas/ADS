import javax.swing.JOptionPane;

public class Exercicio3 {
    public static String resultado(int gA, int gB, String tA, String tB){
        String msgFinal = null;

        if (gA > gB){
            msgFinal = "O time A (" + tA + ") ganhou do time B (" + tB + ") por " + gA + " x " + gB;
            return msgFinal;
        }else if (gA < gB) {
            msgFinal = "O time B (" + tB + ") ganhou do time A (" + tA + ") por " + gB + " x " + gA;
            return msgFinal;
        }else{
            msgFinal = "O jogo terminou empatado, time A (" + tA + ") " + gA + " x " + gB + " time B (" + tB + ")";
            return msgFinal;
        }

    }

    public static void main(String[] args) {
        String timeA = JOptionPane.showInputDialog("Qual o nome do time A?");

        String timeB = JOptionPane.showInputDialog("Qual o nome do time B?");

        int golsA = Integer.parseInt(JOptionPane.showInputDialog("Quantos gols o time A (" + timeA + ") fez?"));
        while (golsA < 0){
            JOptionPane.showMessageDialog(null, "A quantidade de gols não pode ser menor que 0", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            golsA = Integer.parseInt(JOptionPane.showInputDialog("Quantos gols o time A (" + timeA + ") fez?"));
        }

        int golsB = Integer.parseInt(JOptionPane.showInputDialog("Quantos gols o time B (" + timeB + ") fez?"));
        while (golsB < 0){
            JOptionPane.showMessageDialog(null, "A quantidade de gols não pode ser menor que 0", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            golsB = Integer.parseInt(JOptionPane.showInputDialog("Quantos gols o time B l(" + timeB + ") fez?"));
        }

        JOptionPane.showMessageDialog(null, resultado(golsA, golsB, timeA, timeB), "Resultado da partida", JOptionPane.INFORMATION_MESSAGE);
    }
}
