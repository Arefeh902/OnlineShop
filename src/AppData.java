import shop.Cart;
import shop.Product;
import shop.Purchase;
import users.User;

import java.util.ArrayList;

public class AppData {

    public ArrayList<User> users;
    public ArrayList<Cart> carts;
    public ArrayList<Product> products;
    public ArrayList<Purchase> purchases;

    public AppData() {
        users = new ArrayList<>();
        carts = new ArrayList<>();
        products = new ArrayList<>();
        purchases = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addCart(Cart cart){
        carts.add(cart);
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void addPurchase(Purchase purchase){
        purchases.add(purchase);
    }
}
