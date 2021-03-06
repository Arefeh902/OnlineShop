package online_shop.functionality;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import online_shop.scenes.Register;
import online_shop.users.User;


public class Main extends Application{

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
        window = primaryStage;
        createScenes();
        window.setTitle("Online Shop");
        window.setScene(registerScene);
        window.show();
        createTestData();
    }

    public void createScenes(){
        Register.register();
        Register.login();
    }

    public void createTestData(){
        User.register(0, "1", "1");
        appData.createAdmin("admin", "admin");
        User.register(0, "client", "client");
        User.register(1, "seller", "seller");
        User.login("seller", "seller");
        appData.createProduct("book", 50L, 30L);
        appData.createProduct("notepad", 30L, 25L);
        appData.createProduct("pen", 8L, 130L);
        User.logout();
    }

}
