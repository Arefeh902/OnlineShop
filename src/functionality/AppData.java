package functionality;

import javafx.scene.Scene;
import javafx.stage.Stage;
import shop.Cart;
import shop.Product;
import shop.Purchase;
import users.Admin;
import users.Hash;
import users.Seller;
import users.User;

import java.security.NoSuchAlgorithmException;
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

    public void createUser(String username, String password){
        User user = new User(username, password);
        users.add(user);
    }

    public void createSeller(String username, String password){
        Seller seller = new Seller(username, password);
        sellers.add(seller);
    }

    public void createAdmin(String username, String password){
        try{
            password = Hash.bytesToHex(Hash.getSHA(password));
        }catch (NoSuchAlgorithmException e){
            System.out.println("there seems to be a problem");
            System.out.println("" + e);
        }
        Admin admin = new Admin(username, password);
        admins.add(admin);
    }

    public Cart createCart(User user){
        Cart cart = new Cart(user);
        carts.add(cart);
        return cart;
    }

    public Product createProduct(String name, Long price, Long inventory){
        Product product = new Product(name, price, inventory);
        product.seller = currentSeller;
        products.add(product);
        return product;
    }

    public Purchase createPurchase(Cart cart){
        Purchase purchase = new Purchase(cart);
        purchases.add(purchase);
        return purchase;
    }
}
