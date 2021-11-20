package users;

import shop.Product;

import java.util.ArrayList;

public class Seller extends User {

    ArrayList<Product> products;

    public Seller(String username, String password) {
        super(username, password);
        this.products = new ArrayList<>();
    }
}
