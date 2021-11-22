package users;

import functionality.Main;

import java.security.NoSuchAlgorithmException;

public class Admin {

    static Long helpId = 1L;
    Long id;

    public String username;
    private String password;

    Boolean isActive;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;

        this.isActive = true;

        id = helpId;
        helpId += 1;

        Main.appData.admins.add(this);
    }

    public static Admin getByUsername(String username){
        for(Admin admin: Main.appData.admins){
            if(admin.username.equals(username) && admin.isActive){
                return admin;
            }
        }
        return null;
    }

    public static Boolean login(String username, String password) {
        try{
            password = Hash.bytesToHex(Hash.getSHA(password));
        }catch (NoSuchAlgorithmException e){
            System.out.println("there seems to be a problem");
            System.out.println("" + e);
        }
        Admin tmpAdmin = Admin.getByUsername(username);
        if (tmpAdmin != null) {
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


}
