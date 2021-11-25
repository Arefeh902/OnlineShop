package users;

import functionality.Main;
import shop.CartProduct;
import shop.Product;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Seller extends User {

    public ArrayList<Product> products;
    public ArrayList<CartProduct> unverifiedPurchases;
    private String password;

    public Seller(String username, String password) {
        super(username);
        this.password = password;
        this.products = new ArrayList<>();
        this.unverifiedPurchases = new ArrayList<>();
    }

    public void addProduct(String name, Long price, Long inventory){
        Product product = new Product(name, price, inventory);
        products.add(product);
        Main.appData.products.add(product);
    }

    public static Seller getByUsername(String username){
        for(Seller seller: Main.appData.sellers){
            if(seller.username.equals(username)){
                return seller;
            }
        }
        return null;
    }

    public static Boolean login(String username, String password){
        Seller tmpSeller = Seller.getByUsername(username);
        if(tmpSeller != null){
            if(tmpSeller.password.equals(password)){
                Main.appData.currentUser = null;
                Main.appData.currentSeller = tmpSeller;
                Main.appData.currentAdmin = null;
                System.out.println("Logged in successfully!");
                return true;
            }
        }
        return false;
    }

//    public static ArrayList<Product> getUnverifiedProducts(){
//
//    }

}
