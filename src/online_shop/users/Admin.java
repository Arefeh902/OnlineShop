package online_shop.users;

import online_shop.functionality.Main;
import online_shop.shop.CartProduct;
import online_shop.shop.CartProductStatus;
import online_shop.shop.Purchase;
import online_shop.shop.PurchaseStatus;

public class Admin {

    static Long helpId = 1L;
    Long id;

    public String username;
    private final String password;


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
        for(CartProduct cartP: purchase.cart.cartProducts){
            if(cartP.status == CartProductStatus.PENDING){
                cartP.status = CartProductStatus.DELETED;
                System.out.println(cartP.toString() + " was deleted due to seller's delay");
            }
        }
    }

    public void purchaseDelivering(Purchase purchase){
        purchase.status = PurchaseStatus.DELIVERING;
    }

    public void purchaseReceive(Purchase purchase){
        purchase.status = PurchaseStatus.RECEIVED;
    }

}
