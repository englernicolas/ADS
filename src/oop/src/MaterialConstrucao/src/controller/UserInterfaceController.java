package oop.src.MaterialConstrucao.src.controller;

import view.UserInterface;

public class UserInterfaceController {
    UserInterface userInterface = new UserInterface();
    public void menuOptionsController(int selectedIndex){
        switch(selectedIndex){
            case 0:
                userInterface.askProductRegisterInfo();
                break;
            case 1:
                userInterface.askProductsQuantity();
                break;
            case 2:
                userInterface.askProductToSell();
                break;
            case 3:
                userInterface.showRegisteredProducts();
                break;
            case 4:
                userInterface.showCoupons();
                break;
            case 5:
                System.exit(0);
                break;

        }
    }
}
