package oop.src.FabricaDeCarros.src.controller;

import view.UserInterface;

public class UserInterfaceController {

    UserInterface userInterface = new UserInterface();
    public void menuOptionsController(int selectedMenuOption){
        switch (selectedMenuOption){
            case 0:
                userInterface.askQntOfCars();
                break;
            case 1:
                userInterface.selectCarToSell();
                break;
            case 2:
                userInterface.showCarsInfo();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
}
