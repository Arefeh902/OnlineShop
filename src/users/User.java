package users;

import functionality.Main;
import shop.Cart;
import shop.Product;
import shop.Purchase;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    static Scanner scanner = new Scanner(System.in);
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

    public User(String username) {
        this.username = username;

        this.currentCart = new Cart();
        this.nextCart = new Cart();

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

    public static Boolean isUsernameUnique(String inputUsername){
        for(User user: Main.appData.users){
            if(user.username.equals(inputUsername)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static void register(){
        System.out.println("Enter 0 for Client and 1 for Seller");
        int userType = scanner.nextInt();
        scanner.nextLine();
        while(userType != 0 && userType != 1){
            System.out.println("Invalid input!");
            System.out.println("Enter 0 for Client and 1 for Seller");
            userType = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.println("Enter username");
        String username = scanner.nextLine();
        while(!User.isUsernameUnique(username)){
            System.out.println("Username must be unique");
            System.out.println("Enter another username");
            username = scanner.nextLine();
        }
        System.out.println("Enter password");
        String password = scanner.nextLine();
        System.out.println("Enter password Again");
        String passwordVerify = scanner.nextLine();
        while(!password.equals(passwordVerify)){
            System.out.println("passwords didn't match, try again!");
            System.out.println("Enter password");
            password = scanner.nextLine();
            System.out.println("Enter password Again");
            passwordVerify = scanner.nextLine();
        }
        User newUser;
        if(userType == 0){
            newUser = new User(username, password);
        }
        else{
            newUser = new Seller(username, password);
        }
        Main.appData.users.add(newUser);
        System.out.println("user created successfully!");
    }

    public static Boolean login(){
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
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
        if(Admin.login(username, password))
        System.out.println("username and password don't match, try again");
        return login();
    }

}

