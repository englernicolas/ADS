import javax.swing.JOptionPane;

public class Exercicio7 {
    public static void main(String[] args) {
        String tipoLivroEscolhido = "";
        String autorEscolhido = "";
        String tamEscolhido = "";
        String livroEscolhido = "";

        int tipoLivro = Integer.parseInt(JOptionPane.showInputDialog("Qual tipo de livro você gosta de ler?\n  1 - Autoajuda\n  2 - Romance\n  3 - Suspense"));
        while (tipoLivro < 1 || tipoLivro > 3){
            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
            tipoLivro = Integer.parseInt(JOptionPane.showInputDialog("Qual tipo de livro você gosta de ler?\n  1 - Autoajuda\n  2 - Romance\n  3 - Suspense"));
        }
        switch (tipoLivro){
            case 1:
                tipoLivroEscolhido = "Autoajuda";
                int autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de Autoajuda você deseja ler?\n  1 - Augusto Cury\n  2 - Tony Robbins"));
                while (autor < 1 || autor > 2){
                    JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                    autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de Autoajuda você deseja ler?\n  1 - Augusto Cury\n  2 - Tony Robbins"));
                }
                switch (autor){
                    case 1:
                        autorEscolhido = "Augusto Cury";
                        int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "O código da inteligência";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "O vendedor de sonhos";
                                break;
                        }
                        break;
                    case 2:
                        autorEscolhido = "Tony Robbins";
                        tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "Poder sem limites";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "Desperte seu gigante interior";
                                break;
                        }
                        break;
                }
                break;
            case 2:
                tipoLivroEscolhido = "Romance";
                autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de você deseja ler?\n  1 - Danielle Steel\n  2 - Sophie Kinsella"));
                while (autor < 1 || autor > 2){
                    JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                    autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de você deseja ler?\n  1 - Danielle Steel\n  2 - Sophie Kinsella"));
                }
                switch (autor){
                    case 1:
                        autorEscolhido = "Danielle Steel";
                        int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "O Brilho da Estrela";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "First Sight (Primeira Vista)";
                                break;
                        }
                        break;
                    case 2:
                        autorEscolhido = "Sophie Kinsella";
                        tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "Fiquei com seu número";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "Wedding Night";
                                break;
                        }
                        break;
                }
                break;
            case 3:
                tipoLivroEscolhido = "Suspense";
                autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de você deseja ler?\n  1 - Dan Brown\n  2 - Stephen King"));
                while (autor < 1 || autor > 2){
                    JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                    autor = Integer.parseInt(JOptionPane.showInputDialog("Qual autor de você deseja ler?\n  1 - Dan Brown\n  2 - Stephen King"));
                }
                switch (autor){
                    case 1:
                        autorEscolhido = "Dan Brown";
                        int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "Inferno";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "Impacto profundo";
                                break;
                        }
                        break;
                    case 2:
                        autorEscolhido = "Stephen King";
                        tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        while (tamanho < 1 || tamanho > 2){
                            JOptionPane.showMessageDialog(null, "Você informou uma opção inválida, tente novamente", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                            tamanho = Integer.parseInt(JOptionPane.showInputDialog("Qual o tamanho do livro você deseja ler?\n  1 - Grande\n  2 - Médio"));
                        }
                        switch (tamanho){
                            case 1:
                                tamEscolhido = "Grande";
                                livroEscolhido = "It A Coisa";
                                break;
                            case 2:
                                tamEscolhido = "Médio";
                                livroEscolhido = "A espera de um milagre";
                                break;
                        }
                        break;
                }
                break;
        }

        JOptionPane.showMessageDialog(null, "O livro indicado é '" + livroEscolhido + "', do(a) autor(a) " + autorEscolhido + ", de tamanho " + tamEscolhido + ", do gênero " + tipoLivroEscolhido, "Livro indicado", JOptionPane.INFORMATION_MESSAGE);
    }
}

