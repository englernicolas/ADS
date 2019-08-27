package oop.src.FabricaDeCarros.src.model;

import view.UserInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Factory {
    private List<Car> carList = new ArrayList<Car>();

    public void addCarToList(int numbOfCars){
        for (int i = 0; i < numbOfCars; i++){
            UserInterface userInterface = new UserInterface();
            this.carList.add(userInterface.askCarInformations());
        }
    }

    public void sellCar(int option, List list, JComboBox field1){
        for (int i = 0; i < getCarList().size(); i++) {
            if (option == 0) {
                if (field1.getSelectedIndex() == i) {
                    list.remove(i);
                    getCarList().remove(i);
                }
            } else if (option == 1){
                break;
            }
        }
    }

    public List<Car> getCarList() {
        return carList;
    }
}
