package view;

import controller.UserInterfaceController;
import model.Car;
import model.Factory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    Factory factory = new Factory();

    public  void showMenuOptions() {
        UserInterfaceController userInterfaceController = new UserInterfaceController();
        while (true) {
            String[] menuOptionsList = {"Fabricar Carro(s)", "Vender Carro(s)", "Informação do(s) carro(s)", "Sair"};
            JComboBox menuOptions = new JComboBox(menuOptionsList);
            JOptionPane.showMessageDialog(null, menuOptions, "Fábrica de Carros", JOptionPane.QUESTION_MESSAGE);
            userInterfaceController.menuOptionsController(menuOptions.getSelectedIndex());
        }
    }

    public void askQntOfCars(){
        int numbOfCars = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de carros a ser fabricada"));
        while (numbOfCars < 1){
            JOptionPane.showMessageDialog(null, "O número de carro(s) não pode ser menor que um", "Erro", JOptionPane.ERROR_MESSAGE);
            numbOfCars = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de carros a ser fabricada"));
        }
        factory.addCarToList(numbOfCars);
    }

    public Car askCarInformations(){
        Car car = new Car();
        String carModel = JOptionPane.showInputDialog("Informe o modelo do carro:");
        String carColor = JOptionPane.showInputDialog("Informe a cor do carro:");
        car.buildCar(carModel, carColor);
        return car;
    }

    public void showCarsInfo() {
        if (factory.getCarList() == null || factory.getCarList().size() == 0) {
            JOptionPane.showMessageDialog(null, "A fábrica não possuí carro(s)","Informações", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String msg = "";
            for (Car car : factory.getCarList()) {
                msg += "Modelo: " + car.getModel() + "\n";
                msg += "Cor: " + car.getColor() + "\n";
                msg += "-----\n";
            }
            JOptionPane.showMessageDialog(null, msg, "Informações", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void selectCarToSell() {
        if (factory.getCarList() == null || factory.getCarList().size() == 0) {
            JOptionPane.showMessageDialog(null, "A fábrica ainda não possuí carro(s)","Vender Carro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            List list = new ArrayList();
            for (Car car : factory.getCarList()) {
                list.add(car.getModel() + ", " + car.getColor());
            }
            JComboBox field1 = new JComboBox(list.toArray());

            Object[] message = {"Selecione o carro que você deseja vender: ", field1};
            int option = JOptionPane.showConfirmDialog(null, message, "Vender carro", JOptionPane.OK_CANCEL_OPTION);

            factory.sellCar(option, list, field1);
        }
    }
}
