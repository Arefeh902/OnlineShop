package functionality;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.Register;
import shop.Product;
import users.User;


import java.util.Scanner;

public class Main extends Application{

    static Scanner scanner = new Scanner(System.in);
    public static AppData appData = new AppData();

    public static int screenWidth = 600;
    public static int screenHeight = 500;
    public static int space = 20;

    public static Stage window;
    public static Scene registerScene, loginScene;
    public static Scene userDashboardScene, sellerDashboardScene, adminDashboardScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Online Shop");
        createScenes();
        window.setScene(registerScene);
        window.show();
    }

    public void createScenes(){
        Register.register();
        Register.login();
    }

}
