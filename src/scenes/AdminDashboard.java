package scenes;

import functionality.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shop.Product;
import shop.Purchase;
import users.Seller;
import users.User;

import java.util.ArrayList;

public class AdminDashboard {

    public static void adminDashboard(){
        VBox adminDashboardLayout = new VBox(Main.space);
        adminDashboardLayout.setAlignment(Pos.CENTER);
        Label title = new Label("DashBoard");
        Button allUsersButton = new Button("Users");
        allUsersButton.setOnAction(e -> {
            showAllUsers(Main.adminDashboardScene);
        });
        Button allSellersButton = new Button("Sellers");
        allSellersButton.setOnAction(e -> {
            showAllSellers(Main.adminDashboardScene);
        });
        Button allPurchasesButton = new Button("all Purchases");
        allPurchasesButton.setOnAction(e -> {
            UserDashboard.showPurchases(Main.appData.purchases, Main.adminDashboardScene);
        });

        adminDashboardLayout.getChildren().addAll(title, allUsersButton, allSellersButton, allPurchasesButton);
        Main.adminDashboardScene = new Scene(adminDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.adminDashboardScene);
    }

    public static void showAllUsers(Scene prev){
        VBox showAllUsersLayout = new VBox(Main.space);
        showAllUsersLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        for(User user: Main.appData.users){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(user.username);
            Button seePurchases = new Button("purchases");
            seePurchases.setOnAction(e -> {
                UserDashboard.showPurchases(user.getPurchases(), Main.userDashboardScene);
            });
            Button deleteButton = new Button("delete");
            deleteButton.setOnAction(e -> {
                Main.appData.currentAdmin.deleteUser(user);
                showAllUsers(prev);
            });
            hbox.getChildren().addAll(nameLabel, seePurchases, deleteButton);
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showAllUsersLayout.getChildren().addAll(listView, backButton);
        Main.allUsersScene = new Scene(showAllUsersLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.allUsersScene);
    }

    public static void showAllSellers(Scene prev){
        VBox showAllSellersLayout = new VBox(Main.space);
        showAllSellersLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        for(Seller seller: Main.appData.sellers){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(seller.username);
            Button deleteButton = new Button("delete");
            deleteButton.setOnAction(e -> {
                Main.appData.currentAdmin.deleteSeller(seller);
                showAllSellers(prev);
            });
            hbox.getChildren().addAll(nameLabel, deleteButton);
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showAllSellersLayout.getChildren().addAll(listView, backButton);
        Main.allSellersScene = new Scene(showAllSellersLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.allSellersScene);
    }
}
