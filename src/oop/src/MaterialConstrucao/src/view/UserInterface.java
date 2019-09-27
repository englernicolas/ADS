package view;

import javax.swing.*;

import controller.UserInterfaceController;
import model.Coupon;
import model.Product;
import model.Stock;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    Stock stock = new Stock();
    public void showMenu() {
        UserInterfaceController userInterfaceController = new UserInterfaceController();
        while (true) {
            String[] optionsList = {"Cadastrar produto", "Adicionar ao estoque", "Vender Produto", "Produtos cadastrados", "Cupom de vendas",  "Sair"};
            JComboBox menuOptions = new JComboBox(optionsList);
            Object[] optionMessage = {"Selecione uma opção:", menuOptions};
            JOptionPane.showMessageDialog(null, optionMessage, "Material de Construção", JOptionPane.QUESTION_MESSAGE);
            userInterfaceController.menuOptionsController(menuOptions.getSelectedIndex());
        }
    }

    public void askProductRegisterInfo() {
        Product product = new Product();
        String productCode = JOptionPane.showInputDialog("Insira o código do produto");
        String productDescription = JOptionPane.showInputDialog("Insira a descrição do produto");
        float productPrice = Float.parseFloat(JOptionPane.showInputDialog("Insira o preço a ser cobrado por unidade do produto"));
        while (productPrice < 0.01){
            JOptionPane.showMessageDialog(null, "O valor do produto não pode ser menor que R$ 0,01", "Erro", JOptionPane.ERROR_MESSAGE);
            productPrice = Float.parseFloat(JOptionPane.showInputDialog("Insira o preço a ser cobrado por unidade do produto"));
        }

        stock.setProductInformations(product, productCode, productDescription, productPrice);
    }

    public void showRegisteredProducts() {
        String msg = "";
        if (stock.getProductList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O estoque de produtos está vazio","Estoque", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Product product : stock.getProductList()) {
                msg += "Código: " + product.getCode() + "\n";
                msg += "Descrição: " + product.getDescription() + "\n";
                msg += "Preço: R$ " + product.getPrice() + "\n";
                msg += "Unidade(s): " + product.getQuantity() + "\n";
                msg += "-----\n\n";
            }
            JOptionPane.showMessageDialog(null, msg,"Estoque", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void askProductsQuantity() {
        if (stock.getProductList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O estoque de produtos está vazio","Estoque", JOptionPane.INFORMATION_MESSAGE);
        } else {
            List list = new ArrayList();
            for (Product product: stock.getProductList()) {
                list.add(product.getCode() + ", " + product.getDescription() + ", R$ " + product.getPrice() + ", " + product.getQuantity() + " unidades");
            }
            JComboBox field1 = new JComboBox(list.toArray());

            Object[] message = {"Selecione o produto que você deseja adicionar ao estoque: ", field1, "Insira quantas unidades desse produtos você quer adicionar:"};

            int productQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Estoque", JOptionPane.QUESTION_MESSAGE));

            stock.addProductsToStock(productQuantity, field1);
        }
    }

    public void askProductToSell() {
        if (stock.getProductList().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "O estoque de produtos está vazio","Estoque", JOptionPane.INFORMATION_MESSAGE);
        } else {
            List list = new ArrayList();
            for (Product product : stock.getProductList()) {
                if (product.getQuantity() > 1) {
                    list.add(product.getCode() + ", " + product.getDescription() + ", R$ " + product.getPrice() + ", " + product.getQuantity() + " unidades");
                }
            }

            if (!list.isEmpty()) {
                JComboBox field1 = new JComboBox(list.toArray());

                Object[] message = {"Selecione o produto que você deseja vender: ", field1, "Insira quantas unidades desse produtos você quer vender:"};

                int productQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Estoque", JOptionPane.QUESTION_MESSAGE));
                while (productQuantity < 1 || stock.getProductList().get(field1.getSelectedIndex()).getQuantity() - productQuantity < 0) {
                    JOptionPane.showMessageDialog(null, "Não foi possível efetuar a venda", "Erro", JOptionPane.ERROR_MESSAGE);
                    productQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, message, "Estoque", JOptionPane.QUESTION_MESSAGE));
                }

                float productValue = stock.getProductList().get(field1.getSelectedIndex()).getPrice();
                String productDescription = stock.getProductList().get(field1.getSelectedIndex()).getDescription();

                stock.sellProducts(productQuantity, field1, productValue, productDescription);
            } else{
                JOptionPane.showMessageDialog(null, "O estoque de produtos está vazio","Estoque", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void showCoupons() {
        if (stock.getCouponList().isEmpty()){
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum cupom","Cupons", JOptionPane.INFORMATION_MESSAGE);
        } else{
            List list = new ArrayList();
            float totalSales = 0;
            for (Coupon coupon: stock.getCouponList()) {
                list.add(coupon.getData() + " - R$ " + coupon.getValue());
                totalSales += coupon.getValue();
            }

            JComboBox field1 = new JComboBox(list.toArray());

            Object[] message = {"Vendas totais: R$ " + totalSales,"Selecione o cupom a ser visualizado: ", field1};

            JOptionPane.showConfirmDialog(null, message, "Cupons", JOptionPane.OK_CANCEL_OPTION);

            String selectCouponContent = "";
            selectCouponContent += "Descrição: " + stock.getCouponList().get(field1.getSelectedIndex()).getProductDescription() + "\n";
            selectCouponContent += "Quantidade vendida: " + stock.getCouponList().get(field1.getSelectedIndex()).getProductQuantity() + "\n";
            selectCouponContent += "Data: " + stock.getCouponList().get(field1.getSelectedIndex()).getData() + "\n";
            selectCouponContent += "Valor: R$ " + stock.getCouponList().get(field1.getSelectedIndex()).getValue() + "\n";

            JOptionPane.showMessageDialog(null, selectCouponContent, "Cupom", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
