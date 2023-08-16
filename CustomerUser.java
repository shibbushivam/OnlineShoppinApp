package shop;
import java.util.*;


import java.util.List;

public class CustomerUser extends User {
    public CustomerUser(String username, String password) {
        super(username, password);
    }

    public void addProductToCart(List<Product> products, int choice) {
        if (choice >= 1 && choice <= products.size()) {
            Product selectedProduct = products.get(choice - 1);
            cart.addItem(selectedProduct);
            System.out.println(selectedProduct.name + " added to cart.");
        } else {
            System.out.println("Invalid product number.");
        }
    }
}
