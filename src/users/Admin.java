package users;

import functionality.Main;
import shop.Purchase;
import shop.PurchaseStatus;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Admin {

    static Long helpId = 1L;
    Long id;

    public String username;
    private String password;


    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println(username);
        System.out.println(password);
        id = helpId;
        helpId += 1;
    }

    public static Admin getByUsername(String username){
        for(Admin admin: Main.appData.admins){
            if(admin.username.equals(username)){
                return admin;
            }
        }
        return null;
    }

    public static Boolean login(String username, String password) {
        Admin tmpAdmin = Admin.getByUsername(username);
        if (tmpAdmin != null) {
            System.out.println(tmpAdmin.password);
            System.out.println(password);
            if (tmpAdmin.password.equals(password)) {
                Main.appData.currentUser = null;
                Main.appData.currentSeller = null;
                Main.appData.currentAdmin = tmpAdmin;
                System.out.println("Logged in successfully!");
                return true;
            }
        }
        return false;
    }

    private static ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();
        for(User user: Main.appData.users){
            users.add(user);
        }
        for(Seller seller: Main.appData.sellers){
            users.add(seller);
        }
        return users;
    }

    public void deleteUser(User user){
        user.delete();
        Main.appData.users.remove(user);
    }

    public void deleteSeller(Seller seller){
        seller.delete();
        Main.appData.sellers.remove(seller);
    }

    public void purchaseVerify(Purchase purchase){
        purchase.status = PurchaseStatus.VERIFIED;
    }

    public void purchaseDelivering(Purchase purchase){
        purchase.status = PurchaseStatus.DELIVERING;
    }

    public void purchaseRecieved(Purchase purchase){
        purchase.status = PurchaseStatus.RECEIVED;
    }

}
