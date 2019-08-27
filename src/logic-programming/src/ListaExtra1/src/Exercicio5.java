import javax.swing.JOptionPane;

public class Exercicio5 {
    public static String resultado(int d, int j, int bN){
        String msgFinal = null;
        if (d > j && d > bN){
            msgFinal = "Daniel foi o vencedor com " + d + " votos";
            return msgFinal;
        }else if (j > d && j > bN){
            msgFinal = "Jean foi o vencedor com " + j + " votos";
            return msgFinal;
        }else if (j == d && j > bN && d > bN){
            msgFinal = "A votação foi cancelada, pois terminou em empate";
            return msgFinal;
        }else{
            msgFinal = "A votação foi cancelada, pois Daniel e Jean tiveram menos votos que votos em branco ou nulos";
            return msgFinal;
        }
    }

    public static void main(String[] args) {
        int continuar = 0;
        int qtdDaniel = 0, qtdJean = 0, qtdBranco = 0, qtdNulo = 0;

        int votantes = JOptionPane.showConfirmDialog(null, "Existem votantes?");

        if (votantes == JOptionPane.YES_OPTION){
            do{
                int voto = Integer.parseInt(JOptionPane.showInputDialog("URNA ELETRÔNICA DO JEC\n  15 - Daniel\n  75 - Jean\n  0 - Em branco\n  Outros valores - Nulo"));
                switch (voto){
                    case 15:
                        JOptionPane.showMessageDialog(null,"Você votou no Daniel!", "Votação", JOptionPane.INFORMATION_MESSAGE);
                        qtdDaniel++;
                        break;
                    case 75:
                        JOptionPane.showMessageDialog(null,"Você votou no Jean!", "Votação", JOptionPane.INFORMATION_MESSAGE);
                        qtdJean++;
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null,"Você votou em branco!", "Votação", JOptionPane.INFORMATION_MESSAGE);
                        qtdBranco++;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Seu voto foi anulado!", "Votação", JOptionPane.INFORMATION_MESSAGE);
                        qtdNulo++;
                        break;
                }
                continuar = JOptionPane.showConfirmDialog(null, "Existe mais alguem para votar?");
            }while (continuar == JOptionPane.YES_OPTION);
            JOptionPane.showMessageDialog(null, resultado(qtdDaniel, qtdJean, (qtdBranco + qtdNulo)), "Votações encerradas!", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Tabela com os resultados\n  Daniel - " + (qtdDaniel) + " votos\n  Jean - " + (qtdJean) + " votos\n  Em branco - " + (qtdBranco) + " votos\n  Nulos - " + (qtdNulo) + " votos", "Votações encerradas!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Não houveram votantes", "Votação cancelada!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
