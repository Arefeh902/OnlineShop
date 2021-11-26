package functionality;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.Register;
import users.Admin;
import users.User;


import java.util.Scanner;

public class Main extends Application{

//    static Scanner scanner = new Scanner(System.in);
    public static AppData appData = new AppData();

    public static int screenWidth = 600;
    public static int screenHeight = 500;
    public static int space = 20;

    public static Stage window;
    public static Scene registerScene, loginScene;
    public static Scene userDashboardScene, sellerDashboardScene, adminDashboardScene;
    public static Scene allUsersScene, allSellersScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        User.register(1, "1", "1", "1");
        User.login("1", "1");
        System.out.println(appData.currentSeller);
        appData.createProduct("product1", 123L, 4L);
        appData.createProduct("product2", 13476L, 9L);
        appData.createProduct("product3", 5365L, 13L);
        window = primaryStage;
        User.logout();
        User.register(0, "4", "4", "4");
        User.register(0, "test user", "4", "4");
        User.register(0, "hello", "4", "4");
//        User.login("4", "4");
        createScenes();
        appData.createAdmin("5", "5");
        for(Admin a: appData.admins)
            System.out.println(a.username.toString());
        window.setTitle("Online Shop");
//        window.setScene(registerScene);
        window.show();
//        UserDashboard.userDashboard();
    }

    public void createScenes(){
        Register.register();
        Register.login();
    }

}
