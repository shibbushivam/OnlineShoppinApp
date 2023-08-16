package shop;
import java.util.*;

public class User {
    String username;
    String password;
    ShoppingCart cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ShoppingCart();
    }

    public void viewCart() {
        List<Product> cartItems = cart.items;
        if (!cartItems.isEmpty()) {
            System.out.println("\nItems in Cart:");
            for (int i = 0; i < cartItems.size(); i++) {
                Product item = cartItems.get(i);
                System.out.println((i + 1) + ". " + item.name + " - $" + item.price);
            }
        } else {
            System.out.println("Your cart is empty.");
        }
    }

    public void checkout() {
        if (!cart.items.isEmpty()) {
            double total = cart.getTotalPrice();
            System.out.println("\nItems in Cart:");
            viewCart();
            System.out.println("Total Price: $" + total);
            cart.items.clear(); // Empty the cart after checkout
            System.out.println("Checkout completed.");
        } else {
            System.out.println("Your cart is empty.");
        }
    }
}
