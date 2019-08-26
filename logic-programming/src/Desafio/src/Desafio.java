import javax.swing.JOptionPane;

public class Desafio {
    // método para selecionar se o usuário vai usar o "X" ou "O"
    private static String jogSelec(){
        int jogadorInt = Integer.parseInt(JOptionPane.showInputDialog("Você deseja jogar com X ou com O?\n  1 - para X\n  2 - para O"));
        while (jogadorInt < 1 || jogadorInt > 2){
            JOptionPane.showMessageDialog(null,"Vocẽ digitou uma opção inválida, digite novamente","Opção inválida", JOptionPane.ERROR_MESSAGE);
            jogadorInt = Integer.parseInt(JOptionPane.showInputDialog("Você deseja jogar com X ou com O?\n  1 - para X\n  2 - para O"));
        }

        String jogador = "";
        if (jogadorInt == 1){
            jogador = "X";
            return jogador;
        }else{
            jogador = "O";
            return jogador;
        }
    }

    // método para inverter o jogador ao fim de cada jogada
    private static String inverterJog (String jogSelec){
        if (jogSelec.equals("X")){
            jogSelec = "O";
            return jogSelec;
        } else{
            jogSelec = "X";
            return jogSelec;
        }
    }

    // método para imprimir o "tabuleiro"
    private static String tabuleiro(String matriz[][]){
        String msg = "";
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (j < 2){
                    msg += " " + matriz[i][j] + "  |  ";
                }else{
                    msg += " " + matriz[i][j];
                }
            }
            if (i < 2) {
                msg += "\n----+----+----\n";
            }
        }
        return msg;
    }

    // método para verificar as possiveis vitórias
    private static boolean verificarVitorias(String matriz[][]){
        boolean vitoria = false;
        // verificador vitória linha
        for (int i = 0; i < 3; i++) {
            boolean errou = false;
            for (int j = 1; j < 3; j++) {
                if (matriz[i][j] != matriz[i][j - 1]) {
                    errou = true;
                    break;
                }
            }
            if (!errou){
                vitoria = true;
            }
        }

        // verificador vitória coluna
        for (int i = 0; i < 3; i++) {
            boolean errou = false;
            for (int j = 1; j < 3; j++) {
                if (matriz[j][i] != matriz[j - 1][i]) {
                    errou = true;
                    break;
                }
            }
            if (!errou){
                vitoria = true;
            }
        }

        // verificador vitória diagonal esquerda-direita
        boolean errou = false;
        for (int i = 1; i < 3; i++) {
            if (matriz[i][i] != matriz[i - 1][i - 1]) {
                errou = true;
            }
        }
        if (!errou) {
            vitoria = true;
        }

        //verifica vitória diagonal direita-esquerda
        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]){
            vitoria = true;
        }
        return vitoria;
    }

    // método para verificar se deu velha
    private static boolean verificarVelha(int jogada, boolean vitoria){
        boolean velha = false;
        if (jogada == 9 && !vitoria){
            velha = true;
        }
        return velha;
    }

    private static boolean verificarRepetido(int posicao, String[][]matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j].equals(Integer.toString(posicao))) {
                    return false;
                }
            }
        }
        return true;
    }

    // roda o jogo
    public static void main(String[] args) {
        int continuar = 0;
        // repete até o jogador não querer jogar de novo
        do {
            String[][] matriz = new String[3][3];

            // distribui os números de 1 a 9 na matriz
            int distribuidor = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    distribuidor++;
                    matriz[i][j] = Integer.toString(distribuidor);
                }
            }

            String jogador = jogSelec();
            String imprimirTabuleiro = "";
            String msgVitoria = "O '" + jogador + "' ganhou!";
            boolean repetido = false, velha = true, vitoria = false;
            int jogada = 0;

            JOptionPane.showMessageDialog(null, "Vocẽ escolheu jogar com o '" + jogador + "'", "Jogador escolhido", JOptionPane.INFORMATION_MESSAGE);

            // repete até o jogo terminar (em vitória ou em velha)
            do {
                distribuidor = 0; // zera o distribuidor de números (para sempre ficar entre 1 e 9)
                jogada++; // adiciona uma jogada
                imprimirTabuleiro = tabuleiro(matriz);
                int posicao = Integer.parseInt(JOptionPane.showInputDialog("Essa é a " + jogada + "ª jogada\n" + imprimirTabuleiro + "\nQual posição o '" + jogador + "' vai?")); // pergunta em qual posição o jogador quer jogar

                // validações das posições
                while (posicao < 1 || posicao > 9) { // não pode ser menor que 1 e maior que 9
                    JOptionPane.showMessageDialog(null, "Vocẽ digitou uma opção inválida, digite novamente", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    posicao = Integer.parseInt(JOptionPane.showInputDialog("Essa é a " + jogada + "ª jogada\n" + imprimirTabuleiro + "\nQual posição o '" + jogador + "' vai?"));
                }

                repetido = verificarRepetido(posicao, matriz);

                while (repetido) { // não pode ser um espaço que já foi jogado
                    JOptionPane.showMessageDialog(null, "Vocẽ digitou uma opção inválida, digite novamente", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    posicao = Integer.parseInt(JOptionPane.showInputDialog("Essa é a " + jogada + "ª jogada\n" + imprimirTabuleiro + "\nQual posição o '" + jogador + "' vai?"));
                    repetido = verificarRepetido(posicao, matriz);

                }

                // atribui a posição jogada na matriz (coloca o "X" ou "O" na matriz)
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (matriz[i][j].equals(Integer.toString(posicao))) {
                            matriz[i][j] = jogador;
                        }
                    }
                }

                // verifica possível vítoria ou velha
                vitoria = verificarVitorias(matriz);
                velha = verificarVelha(jogada, vitoria);

                imprimirTabuleiro = ""; // serve para resetar o tabuleiro (para não ficar tabuleiros enfileirados)
                jogador = inverterJog(jogador); // inverte a posição do jogador
            } while (!vitoria && !velha);

            // imprime as mensagens finais (de velha ou de vitória)
            if (velha) {
                JOptionPane.showMessageDialog(null, "O jogo terminou em velha!", "Velha", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, msgVitoria, "Vitória", JOptionPane.INFORMATION_MESSAGE);
            }

            continuar = JOptionPane.showConfirmDialog(null, "Você deseja jogar de novo?"); // pergunta se o usuário quer jogar de novo
            if (continuar == JOptionPane.YES_OPTION) {
                vitoria = false;
                velha = false;
                imprimirTabuleiro = "";
            }
        }while (continuar == JOptionPane.YES_OPTION);
    }

}
