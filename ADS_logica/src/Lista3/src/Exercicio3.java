import javax.swing.JOptionPane;

public class Exercicio3 {
    public static String desconto5 (float valorCompra){
        double desconto = valorCompra - (valorCompra * 0.05);
        String msgFinal = "A sua compra (R$ " + valorCompra + ") recebeu um desconto de 5%, pois ultrapassou os R$ 100, e ficou em um total de R$ " + desconto;
        return msgFinal;
    }

    public static String desconto10 (float valorCompra){
        double desconto = valorCompra - (valorCompra * 0.1);
        String msgFinal = "A sua compra (R$ " + valorCompra + ") recebeu um desconto de 10%, pois ultrapassou os R$ 200, e ficou em um total de R$ " + desconto;
        return msgFinal;
    }

    public static String desconto15 (float valorCompra){
        double desconto = valorCompra - (valorCompra * 0.15);
        String msgFinal = "A sua compra (R$ " + valorCompra + ") recebeu um desconto de 15%, pois ultrapassou os R$ 500, e ficou em um total de R$ " + desconto;
        return msgFinal;
    }

    public static void main(String[] args) {
        float valorCompra = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor da compra?"));
        while (valorCompra < 1){
            JOptionPane.showMessageDialog(null, "O valor de sua compra não pode ser menor que R$ 1,00", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            valorCompra = Integer.parseInt(JOptionPane.showInputDialog("Qual o valor da compra?"));
        }

        if (valorCompra < 100){
            JOptionPane.showMessageDialog(null, "A sua compra ficou em um total de R$ " + valorCompra, "Sem desconto", JOptionPane.INFORMATION_MESSAGE);
        }else if (valorCompra >= 100 && valorCompra < 200){
            JOptionPane.showMessageDialog(null, desconto5(valorCompra), "Desconto de 5%", JOptionPane.INFORMATION_MESSAGE);
        }else if (valorCompra >= 200 && valorCompra < 500){
            JOptionPane.showMessageDialog(null, desconto10(valorCompra), "Desconto de 10%", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, desconto15(valorCompra), "Desconto de 15%", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
