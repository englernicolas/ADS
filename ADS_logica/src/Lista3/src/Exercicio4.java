import javax.swing.JOptionPane;

public class Exercicio4 {
    public static String cToF(int temp){
        double tempFinal = ((9 * temp) / 5) + 32;
        String msgFinal = "A conversão de " + temp + " ºC para ºF é de: " + tempFinal + " ºF";
        return msgFinal;
    }

    public static String cToK(int temp){
        double tempFinal = temp + 273.15;
        String msgFinal = "A conversão de " + temp + " ºC para K é de: " + tempFinal + " K";
        return msgFinal;
    }

    public static String fToC(int temp){
        double tempFinal = (5 * (temp - 32)) / 9;
        String msgFinal = "A conversão de " + temp + " ºF para ºC é de: " + tempFinal + " ºC";
        return msgFinal;
    }

    public static String fToK(int temp){
        double tempFinal = (((temp - 32) * 5) / 9) + 273.15;
        String msgFinal = "A conversão de " + temp + " ºF para K é de: " + tempFinal + " K";
        return msgFinal;
    }

    public static String kToC(int temp){
        double tempFinal = temp - 273.15;
        String msgFinal = "A conversão de " + temp + " K para ºC é de: " + tempFinal + " ºC";
        return msgFinal;
    }

    public static String kToF(int temp){
        double tempFinal = (((temp - 273.15) * 9) / 5) + 32;
        String msgFinal = "A conversão de " + temp + " K para ºF é de: " + tempFinal + " ºF";
        return msgFinal;
    }

    public static void main(String[] args) {
        int escalaTerm2 = 0;
        int escalaTerm1 = Integer.parseInt(JOptionPane.showInputDialog("Qual escala termométrica você utiliza?\n 1 - Celsius\n 2 - Fahrenheit\n 3 - Kelvin"));

        while (escalaTerm1 < 1 || escalaTerm1 > 3){
            JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida, tente novamente!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
            escalaTerm1 = Integer.parseInt(JOptionPane.showInputDialog("Qual escala termométrica você utiliza?\n 1 - Celsius\n 2 - Fahrenheit\n 3 - Kelvin"));
        }
        int tempEscolhida = Integer.parseInt(JOptionPane.showInputDialog("Qual a temperatura que você deseja converter?"));
        switch (escalaTerm1){
            case 1:
                escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " ºC para qual escala termométrica?\n 1 - Fahrenheit\n 2 - Kelvin"));
                while (escalaTerm2 < 1 || escalaTerm2 > 2) {
                    JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida, tente novamente!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " ºC para qual escala termométrica?\n 1 - Fahrenheit\n 2 - Kelvin"));
                }

                if (escalaTerm2 == 1){
                    JOptionPane.showMessageDialog(null, cToF(tempEscolhida), "Celsius para Fahrenheit", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, cToK(tempEscolhida), "Celsius para Kelvin", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " ºF para qual escala termométrica?\n 1 - Celsius\n 2 - Kelvin"));
                while (escalaTerm2 < 1 || escalaTerm2 > 2) {
                    JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida, tente novamente!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " ºF para qual escala termométrica?\n 1 - Celsius\n 2 - Kelvin"));
                }

                if (escalaTerm2 == 1){
                    JOptionPane.showMessageDialog(null, fToC(tempEscolhida), "Fahrenheit para Celsius", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, fToK(tempEscolhida), "Fahrenheit para Kelvin", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 3:
                escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " K para qual escala termométrica?\n 1 - Celsius\n 2 - Fahrenheit"));
                while (escalaTerm2 < 1 || escalaTerm2 > 2) {
                    JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida, tente novamente!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    escalaTerm2 = Integer.parseInt(JOptionPane.showInputDialog("Você deseja converter " + tempEscolhida + " K para qual escala termométrica?\n 1 - Celsius\n 2 - Fahrenheit"));
                }

                if (escalaTerm2 == 1){
                    JOptionPane.showMessageDialog(null, kToC(tempEscolhida), "Kelvin para Celsius", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, kToF(tempEscolhida), "Kelvin para Fahrenheit", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
        }
    }
}
