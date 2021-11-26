package users;

import functionality.Main;
import shop.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Seller extends User {

//    public ArrayList<Product> products;
    private String password;

    public Seller(String username, String password) {
        super(username);
        this.password = password;
//        this.products = new ArrayList<>();
    }
//
//    public void addProduct(String name, Long price, Long inventory){
//        Product product = new Product(name, price, inventory);
//        products.add(product);
//        Main.appData.products.add(product);
//    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        for(Product product: Main.appData.products){
            if(product.seller.equals(this)){
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<CartProduct> getUnverifiedProducts(){
        ArrayList<CartProduct> unverified = new ArrayList<>();
        for(Purchase purchase: Main.appData.purchases){
            for(CartProduct cartP: purchase.cart.cartProducts){
                if(cartP.product.seller.equals(Main.appData.currentSeller) && cartP.status == CartProductStatus.PENDING){
                    unverified.add(cartP);
                }
            }
        }
        return unverified;
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

}
