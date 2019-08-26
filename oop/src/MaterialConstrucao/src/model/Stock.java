package model;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Stock {
    private List<Product> productList = new ArrayList<Product>();
    private List<Coupon> couponList = new ArrayList<Coupon>();

    public List<Product> getProductList() {
        return productList;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setProductInformations(Product product, String productCode, String productDescription, float productPrice){
        product.setProductAtributes(productCode, productDescription, productPrice);
        getProductList().add(product);
    }

    public void addProductsToStock(int productQuantity, JComboBox field1) {
        getProductList().get(field1.getSelectedIndex()).setQuantity(productQuantity);
    }

    public void sellProducts(int productQuantity, JComboBox field1, float productValue, String productDescription) {
        getProductList().get(field1.getSelectedIndex()).setQuantity(-productQuantity);

        Coupon coupon = new Coupon(productValue, productDescription, productQuantity);
        couponList.add(coupon);
    }
}
