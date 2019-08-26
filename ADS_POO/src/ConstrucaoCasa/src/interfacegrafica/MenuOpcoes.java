package interfacegrafica;

import classesobjetoscasa.Casa;
import classesobjetoscasa.Janela;
import classesobjetoscasa.Porta;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;


public class MenuOpcoes {

    public void exibirMenu() {

        Casa casa = new Casa();
        while (true) {
            int acao = Integer.parseInt(JOptionPane
                    .showInputDialog("Escolha uma opção : \n"
                            + "1 - Construir Casa \n" + "2 - Pintar Casa\n"
                            + "3 - Mover Porta(s)\n" + "4 - Mover Janela(s)\n"
                            + "5 - Informações da Casa\n"
                            + "6 - Sair"));
            switch(acao) {
                case 1:
                    dadosConstCasa(casa);
                    break;
                case 2:
                    pintarCasa(casa);
                    break;
                case 3:
                    moverPorta(casa);
                    break;
                case 4:
                    moverJanela(casa);
                    break;
                case 5:
                    mostrarInformacoes(casa);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    private void dadosConstCasa(Casa casa){
        String descricao = JOptionPane.showInputDialog("Descrição da casa: ");
        String cor = JOptionPane.showInputDialog("Informe a cor da casa: ");
        int quantidadePorta = Integer.parseInt(JOptionPane.showInputDialog("Informe quantas portas: "));
        List<Porta> listaDePortas = new ArrayList();
        for (int i = 0; i < quantidadePorta; i++){
            listaDePortas.add(solicitarPortas(i));
        }

        int quantidadeJanela = Integer.parseInt(JOptionPane.showInputDialog("Informe quantas Janelas: "));
        List<Janela> listaDeJanelas = new ArrayList();
        for (int i = 0; i < quantidadeJanela; i++) {
            listaDeJanelas.add(solicitarJanelas(i));
        }

        casa.construirCasa(descricao,cor,listaDeJanelas,listaDePortas);
    }

    private void pintarCasa(Casa casa){
        String cor = JOptionPane.showInputDialog("Digite a cor da casa: ");
        casa.pintarCasa(cor);
    }

    private void moverPorta(Casa casa){
        if (casa.getListaDePortas().size() == 0){
            JOptionPane.showMessageDialog(null, "A casa não tem Porta(s)!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A casa tem Porta(s)!", "OK", JOptionPane.INFORMATION_MESSAGE);
            List list = new ArrayList();

            for (Porta porta : casa.getListaDePortas()){ // não entendi muito bem esse for
                list.add(porta.getIdentificacao());
            }
            JComboBox field1 = new JComboBox(list.toArray());
            String[] listStatus = {"Aberta", "Fechada"};
            JComboBox field2 = new JComboBox(listStatus);

            Object[] message = {"Porta: ", field1, "Status: ", field2};
            int option = JOptionPane.showConfirmDialog(null, message, "Porta", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                int status;
                if (field2.getSelectedIndex() == 0) {
                    status = 1;
                } else {
                    status = 2;
                }
                casa.movimentarPorta(field1.getSelectedIndex(), status);
            }
        }
    }

    private void moverJanela(Casa casa){
        if (casa.getListaDeJanelas().size() == 0){
            JOptionPane.showMessageDialog(null, "A casa não tem Janela(s)!", "Erro", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "A casa tem Janela(s)!", "OK", JOptionPane.INFORMATION_MESSAGE);
            List list = new ArrayList();

            for (Janela janela : casa.getListaDeJanelas()){ // não entendi muito bem esse for
                list.add(janela.getIdentificacao());
            }
            JComboBox field1 = new JComboBox(list.toArray());
            String[] listStatus = {"Aberta", "Fechada"};
            JComboBox field2 = new JComboBox(listStatus);

            Object[] message = {"Janela: ", field1, "Status: ", field2};
            int option = JOptionPane.showConfirmDialog(null, message, "Janela", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                int status;
                if (field2.getSelectedIndex() == 1) {
                    status = 1;
                } else {
                    status = 2;
                }
                casa.movimentarJanela(field1.getSelectedIndex(), status);
            }
        }
    }

    private void mostrarInformacoes(Casa casa){
        String janelas = "";
        String portas = "";
        String cor = casa.getCor() + "\n";
        String descricao = casa.getDescricao() + "\n";
        if (casa.getListaDeJanelas().size() == 0) {
            janelas = "A casa não possuí nenhuma Janela \n";
        } else {
            for (Janela janela : casa.getListaDeJanelas()) {
                janelas += "Descrição: " + janela.getIdentificacao() + "\n";
                String situacao = "";
                if (janela.getStatus() == 2){
                    situacao = "aberta\n";
                }else{
                    situacao = "fechada\n";
                }
                janelas += "Situação: " + situacao + "\n";
            }
        }
        if (casa.getListaDePortas().size() == 0) {
            portas = "A casa não possuí nenhuma Porta \n";
        } else{
            for (Porta porta : casa.getListaDePortas()) {
                portas += "Descrição: " + porta.getIdentificacao() + "\n";
                String situacao = "";
                if (porta.getStatus() == 2){
                    situacao = "aberta\n";
                }else{
                    situacao = "fechada\n";
                }
                portas += "Situação: " + situacao + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, "A descrição da casa é:\n" + descricao + "\nA cor da casa é:\n" + cor + "\nJanelas\n" + janelas + "\nPortas\n" + portas, "Casa", JOptionPane.INFORMATION_MESSAGE);
    }

    private Porta solicitarPortas(int i){
        Porta porta = new Porta();
        i = i + 1;
        String identificacao = JOptionPane.showInputDialog("Digite a descrição da porta " + (i) + ": ");
        int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o status da porta '" + identificacao + "'\n 1 - Para aberta \n 2 - Para fechada:"));

        porta.setIdentificacao(identificacao);

        if (status == 1){
            porta.setStatus(1);
        } else {
            porta.setStatus(2);
        }

        return porta;
    }

    private Janela solicitarJanelas(int i){
        Janela janela = new Janela();
        i = i + 1;
        String identificacao = JOptionPane.showInputDialog("Digite a descrição da janela " + (i) + ": ");
        int status = Integer.parseInt(JOptionPane.showInputDialog("Digite o status da janela '" + identificacao + "'\n 1 - Para aberta \n 2 - Para fechada:"));

        janela.setIdentificacao(identificacao);

        if (status == 1){
            janela.setStatus(1);
        } else {
            janela.setStatus(2);
        }

        return janela;
    }
}