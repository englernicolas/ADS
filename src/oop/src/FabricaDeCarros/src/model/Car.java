package model;

public class Car {
    private String model;
    private String color;

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public void buildCar(String model, String color){
        this.model = model;
        this.color = color;
    }
}
