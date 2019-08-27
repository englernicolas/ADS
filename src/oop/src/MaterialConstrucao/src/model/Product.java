package oop.src.MaterialConstrucao.src.model;

public class Product {
    String code;
    String description;
    float price;
    int quantity;

    public void setProductAtributes(String code, String description, float price){
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
