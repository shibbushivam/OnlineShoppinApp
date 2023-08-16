package shop;


import java.io.IOException;
import java.util.*;

public class OnlineShoppingApp {
    private static Map<String, User> users = new HashMap<>();
    private static List<Product> products = new ArrayList<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Register as Admin");
                System.out.println("3. Register as Customer");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = readIntInput();

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        registerAdmin();
                        break;
                    case 3:
                        registerCustomer();
                        break;
                    case 4:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                if (currentUser instanceof AdminUser) {
                    adminMenu();
                } else if (currentUser instanceof CustomerUser) {
                    customerMenu();
                }
            }
        }
    }

    private static void initializeData() {
        users.put("admin", new AdminUser("admin", "admin"));

        products.add(new Product("Product 1", 10.99));
        products.add(new Product("Product 2", 25.99));
        products.add(new Product("Product 3", 5.99));
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void registerAdmin() {
        registerUser(true);
    }

    private static void registerCustomer() {
        registerUser(false);
    }

    private static void registerUser(boolean isAdmin) {
        System.out.print("Enter new username: ");
        String username = scanner.next();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        System.out.print("Enter new password: ");
        String password = scanner.next();
        users.put(username, isAdmin ? new AdminUser(username, password) : new CustomerUser(username, password));
        System.out.println("Registration successful!");
    }

    private static void adminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. View Products");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = readIntInput();

        switch (choice) {
            case 1:
                viewProducts(products);
                break;
            case 2:
                addProduct();
                break;
            case 3:
                removeProduct();
                break;
            case 4:
                currentUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void customerMenu() {
        System.out.println("\nCustomer Menu:");
        System.out.println("1. View Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
        int choice = readIntInput();

        switch (choice) {
            case 1:
                viewProducts(products);
                break;
            case 2:
                addProductToCart();
                break;
            case 3:
                viewCart();
                break;
            case 4:
                checkout();
                break;
            case 5:
                currentUser = null;
                System.out.println("Logged out.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewProducts(List<Product> productList) {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.println((i + 1) + ". " + product.name + " - $" + product.price);
        }
    }

    private static void addProduct() {
        System.out.print("Enter the name of the new product: ");
        String name = scanner.next();
        System.out.print("Enter the price of the new product: ");
        double price = scanner.nextDouble();
        products.add(new Product(name, price));
        System.out.println("Product added.");
    }

    private static void removeProduct() {
        System.out.print("Enter the number of the product you want to remove: ");
        int choice = readIntInput();
        if (choice >= 1 && choice <= products.size()) {
            Product removedProduct = products.remove(choice - 1);
            System.out.println(removedProduct.name + " removed from the list.");
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static void addProductToCart() {
        System.out.print("Enter the number of the product you want to add to cart: ");
        int choice = readIntInput();
        if (choice >= 1 && choice <= products.size()) {
            Product selectedProduct = products.get(choice - 1);
            if (currentUser != null && currentUser instanceof CustomerUser) {
                ((CustomerUser) currentUser).addProductToCart(products, choice);
            } else {
                System.out.println("You are not authorized to perform this action.");
            }
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static void viewCart() {
        if (currentUser != null && currentUser instanceof CustomerUser) {
            ((CustomerUser) currentUser).viewCart();
        } else {
            System.out.println("You are not authorized to perform this action.");
        }
    }

    private static void checkout() {
        if (currentUser != null && currentUser instanceof CustomerUser) {
            ((CustomerUser) currentUser).checkout();
        } else {
            System.out.println("You are not authorized to perform this action.");
        }
    }

    private static int readIntInput() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return readIntInput();
        }
    }
}
