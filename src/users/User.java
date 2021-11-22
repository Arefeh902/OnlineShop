package users;

import functionality.Main;
import shop.Cart;
import shop.CartStatus;
import shop.Product;
import shop.Purchase;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    static Scanner scanner = new Scanner(System.in);
    static Long helpId = 1L;
    Long id;

    public String username;
    private String password;

    public Cart currentCart;
    public Cart nextCart;

    public ArrayList<Product> favouriteProducts;
    ArrayList<Purchase> purchases;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.currentCart = new Cart(this);
        this.nextCart = new Cart(this);

        this.favouriteProducts = new ArrayList<>();
        this.purchases = new ArrayList<>();

        this.id = helpId;
        helpId += 1;
    }

    public User(String username) {
        this.username = username;

        this.currentCart = Main.appData.createCart(this);
        this.nextCart = Main.appData.createCart(this);

        this.favouriteProducts = new ArrayList<>();
        this.purchases = new ArrayList<>();

        this.id = helpId;
        helpId += 1;

    }

    public static User getByUsername(String username){
        for(User user: Main.appData.users){
            if(user.username.equals(username)){
                return user;
            }
        }
        return null;
    }

    public static Boolean isUsernameUnique(String username){
        for(User user: Main.appData.users){
            if(user.username.equals(username)){
                return Boolean.FALSE;
            }
        }
        for(Seller seller: Main.appData.sellers){
            if(seller.username.equals(username)){
                return Boolean.FALSE;
            }
        }
        for(Admin admin: Main.appData.admins){
            if(admin.username.equals(username)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public void purchase(){
        Main.appData.createPurchase(currentCart);
        currentCart = nextCart;
        nextCart = Main.appData.createCart(this);
    }

    public void addFavourite(Product product){
        favouriteProducts.add(product);
    }

    public void removeFavourite(Product product){
        favouriteProducts.remove(product);
    }

    public void addProductCurrentCart(Product product, Long count){
        currentCart.addProduct(product, count);
    }

    public void addProductCurrentCart(Product product){
        currentCart.addProduct(product, 1L);
    }

    public void editProductCurrentCart(Product product, Long count){

    }

    public void addProductNextCart(Product product, Long count){
        nextCart.addProduct(product, count);
    }

    public void addProductNextCart(Product product){
        nextCart.addProduct(product, 1L);
    }













    public static Boolean register(int userType, String username, String password, String verifyPassword){
//        for(User user: Main.appData.users){
//            System.out.println(user.toString());
//        }
        System.out.println("user type : " + userType);
        System.out.println(Main.appData.users.size());
        System.out.println(Main.appData.sellers.size());
        if(!User.isUsernameUnique(username)){
            System.out.println("Username must be unique");
            return Boolean.FALSE;
        }
        if(!password.equals(verifyPassword)){
            System.out.println("passwords didn't match, try again!");
            return Boolean.FALSE;
        }
        try{
            password = Hash.bytesToHex(Hash.getSHA(password));
        }catch (NoSuchAlgorithmException e){
            System.out.println("there seems to be a problem");
            System.out.println("" + e);
        }
        if(userType == 0){
            System.out.println("lanat!");
            Main.appData.createUser(username, password);
        }
        else{
            System.out.println("user is a seller!");
            System.out.println();
            Main.appData.createSeller(username, password);
        }
        System.out.println("created successfully");
        return Boolean.TRUE;
    }

    public static Boolean login(String username, String password){
        try{
            password = Hash.bytesToHex(Hash.getSHA(password));
        }catch (NoSuchAlgorithmException e){
            System.out.println("there seems to be a problem");
            System.out.println("" + e);
        }
        User tmpUser = User.getByUsername(username);
        if(tmpUser != null){
            if(tmpUser.password.equals(password)){
                Main.appData.currentUser = tmpUser;
                Main.appData.currentSeller = null;
                Main.appData.currentAdmin = null;
                System.out.println("Logged in successfully! in user.register");
                return true;
            }
        }
        if(Seller.login(username, password)){
            return true;
        }
        if(Admin.login(username, password)){
            return true;
        }
        System.out.println("username and password don't match, try again");
        return false;
    }

    public static void logout(){
        Main.appData.currentUser = null;
        Main.appData.currentSeller = null;
        Main.appData.currentAdmin = null;
        Main.window.setScene(Main.loginScene);
    }

    @Override
    public String toString() {
        return  id +
                " " + username;
    }
}

