package online_shop.users;

import online_shop.functionality.Main;
import online_shop.shop.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    static Long helpId = 1L;
    Long id;

    public String username;
    private String password;

    public Cart currentCart;
    public Boolean isActive;
//    public Cart nextCart;

    public ArrayList<Product> favouriteProducts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.currentCart = new Cart(this);

        this.isActive = Boolean.TRUE;
        this.favouriteProducts = new ArrayList<>();

        this.id = helpId;
        helpId += 1;
    }

    public User(String username) {
        this.username = username;

        this.currentCart = Main.appData.createCart(this);

        this.favouriteProducts = new ArrayList<>();

        this.id = helpId;
        helpId += 1;

    }

    public static User getByUsername(String username){
        for(User user: Main.appData.users){
            if(user.username.equals(username) && user.isActive){
                return user;
            }
        }
        return null;
    }

    public static Boolean isUsernameUnique(String username){
        for(User user: Main.appData.users){
            if(user.username.equals(username) && user.isActive){
                return Boolean.FALSE;
            }
        }
        for(Seller seller: Main.appData.sellers){
            if(seller.username.equals(username) && seller.isActive){
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

    public void addFavourite(Product product){
        favouriteProducts.add(product);
    }

    public void removeFavourite(Product product){
        favouriteProducts.remove(product);
    }

    public ArrayList<Purchase> getPurchases(){
        ArrayList<Purchase> purchases = new ArrayList<>();
        for(Purchase purchase: Main.appData.purchases){
            if(purchase.cart.user.equals(this)){
                purchases.add(purchase);
            }
        }
        return purchases;
    }

    public void delete(){
        for(Cart cart: Main.appData.carts){
            if(cart.user.equals(this)){
                cart.status = CartStatus.DELETED;
            }
        }
    }

    public static Boolean register(int userType, String username, String password, String verifyPassword){
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
            Main.appData.createUser(username, password);
        }
        else{
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
                System.out.println("Logged in successfully!");
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

