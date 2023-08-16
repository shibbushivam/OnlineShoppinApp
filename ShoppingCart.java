package shop;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(product -> product.price).sum();
    }
}
