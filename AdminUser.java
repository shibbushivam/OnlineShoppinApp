package shop;

import java.util.*;


import java.util.List;

public class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
    }

    public void addProduct(List<Product> products, String name, double price) {
        products.add(new Product(name, price));
        System.out.println("Product added.");
    }

    public void removeProduct(List<Product> products, int choice) {
        if (choice >= 1 && choice <= products.size()) {
            Product removedProduct = products.remove(choice - 1);
            System.out.println(removedProduct.name + " removed from the list.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    public void viewProducts(List<Product> productList) {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.println((i + 1) + ". " + product.name + " - $" + product.price);
        }
    }
}
