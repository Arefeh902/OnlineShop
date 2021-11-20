package users;

import shop.Cart;
import shop.Product;
import shop.Purchase;

import java.util.ArrayList;

public class User {

    static Long helpId = 1L;
    Long id;

    public String username;
    private String password;

    Cart currentCart;
    Cart nextCart;

    ArrayList<Product> favouriteProducts;
    ArrayList<Purchase> purchases;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.currentCart = new Cart();
        this.nextCart = new Cart();

        this.favouriteProducts = new ArrayList<>();
        this.purchases = new ArrayList<>();

        this.id = helpId;
        helpId += 1;
    }
}
