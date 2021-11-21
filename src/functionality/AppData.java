package functionality;

import shop.Cart;
import shop.Product;
import shop.Purchase;
import users.Admin;
import users.Seller;
import users.User;

import java.util.ArrayList;

public class AppData {

    public ArrayList<User> users;
    public ArrayList<Seller> sellers;
    public ArrayList<Admin> admins;
    public ArrayList<Cart> carts;
    public ArrayList<Product> products;
    public ArrayList<Purchase> purchases;
    public User currentUser;
    public Seller currentSeller;
    public Admin currentAdmin;

    public AppData() {
        users = new ArrayList<>();
        sellers = new ArrayList<>();
        admins = new ArrayList<>();
        carts = new ArrayList<>();
        products = new ArrayList<>();
        purchases = new ArrayList<>();

        currentUser = null;
        currentSeller = null;
        currentAdmin = null;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addSeller(Seller seller){
        sellers.add(seller);
    }

    public void addAdmin(Admin admin){
        admins.add(admin);
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
