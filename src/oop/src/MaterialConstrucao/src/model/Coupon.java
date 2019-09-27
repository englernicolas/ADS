package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Coupon {
    private String data;
    private float value;
    private String productDescription;
    private int productQuantity;

    Coupon(float productValue, String productDescription, int productQuantity){
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss  MMM dd, yyyy");
        this.data = sdf.format(data.getTime());
        this.value = productValue * productQuantity;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
    }

    public String getData() {
        return data;
    }

    public float getValue() {
        return value;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
