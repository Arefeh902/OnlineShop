package users;

import functionality.Main;
import shop.Product;

import java.util.ArrayList;

public class Seller extends User {

    ArrayList<Product> products;
    private String password;

    public Seller(String username, String password) {
        super(username);
        this.password = password;
        this.products = new ArrayList<>();
    }

    public void addProduct(String name, Long price, Long inventory){
        Product product = new Product(name, price, inventory);
        products.add(product);
        Main.appData.products.add(product);
    }

    public static Seller getByUsername(String username){
        for(Seller seller: Main.appData.sellers){
            if(seller.username.equals(username) && seller.isActive){
                return seller;
            }
        }
        return null;
    }

    public static Boolean login(String username, String password){
//        System.out.println();
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
