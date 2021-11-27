package online_shop.scenes;

import javafx.scene.control.TextField;
import online_shop.functionality.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import online_shop.users.Seller;
import online_shop.users.User;

public class AdminDashboard {

    public static void adminDashboard(){
        VBox adminDashboardLayout = new VBox(Main.space);
        adminDashboardLayout.setAlignment(Pos.CENTER);
        Label title = new Label("DashBoard");
        Button allUsersButton = new Button("Users");
        allUsersButton.setOnAction(e -> showAllUsers(Main.adminDashboardScene));
        Button allSellersButton = new Button("Sellers");
        allSellersButton.setOnAction(e -> showAllSellers(Main.adminDashboardScene));
        Button allPurchasesButton = new Button("all Purchases");
        allPurchasesButton.setOnAction(e -> UserDashboard.showPurchases(Main.appData.purchases, Main.adminDashboardScene));
        Button allProductsButton = new Button("all products");
        allProductsButton.setOnAction(e -> UserDashboard.showAllProducts(Main.adminDashboardScene));
        Button addAdminButton = new Button("add admin");
        addAdminButton.setOnAction(e -> {
            addAdminView();
        });
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> User.logout());
        adminDashboardLayout.getChildren().addAll(title, allUsersButton, allSellersButton, allPurchasesButton, allProductsButton, addAdminButton, logoutButton);
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
                Main.appData.currentUser = user;
                UserDashboard.showPurchases(user.getPurchases(), Main.allUsersScene);
                Main.appData.currentUser = null;
            });
            Button deleteButton = new Button("delete");
            deleteButton.setOnAction(e -> {
                Main.appData.currentAdmin.deleteUser(user);
                showAllUsers(prev);
            });
            hbox.getChildren().addAll(nameLabel, seePurchases, deleteButton);
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> Main.window.setScene(prev));
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
        final ListView<HBox> listView = new ListView<>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> Main.window.setScene(prev));
        showAllSellersLayout.getChildren().addAll(listView, backButton);
        Main.allSellersScene = new Scene(showAllSellersLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.allSellersScene);
    }

    public static void addAdminView(){
        VBox addAdminLayout = new VBox(Main.space);
        addAdminLayout.setAlignment(Pos.CENTER);
        Label usernameLabel = new Label("Enter username");
        TextField username = new TextField();
        username.setMaxWidth((float)Main.screenWidth/4);
        Label passwordLabel = new Label("Enter password");
        TextField password = new TextField();
        password.setMaxWidth((float)Main.screenWidth/4);
        Label verifyPasswordLabel = new Label("Enter password again");
        TextField verifyPassword = new TextField();
        verifyPassword.setMaxWidth((float)Main.screenWidth/4);
        Label emptyLabel = new Label("");
        Button createButton = new Button("create");
        createButton.setOnAction(e -> {
            if(!User.isUsernameUnique(username.getText())) {
                emptyLabel.setText("Username must be unique");
                username.clear();
                password.clear();
                verifyPassword.clear();
            }else if(password.getText().equals("")){
                emptyLabel.setText("please enter a password");
            }else if(!password.getText().equals(verifyPassword.getText())){
                emptyLabel.setText("passwords didn't match, try again!");
                password.clear();
                verifyPassword.clear();
            }else{
                Main.appData.currentAdmin.createAdmin(username.getText(), password.getText());
                Main.window.setScene(Main.adminDashboardScene);
                System.out.println("created admin successfully");
            }
        });
        addAdminLayout.getChildren().addAll(usernameLabel, username, passwordLabel, password, verifyPasswordLabel);
        addAdminLayout.getChildren().addAll(verifyPassword, emptyLabel, createButton);
        Scene addAdminScene = new Scene(addAdminLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(addAdminScene);
    }

}
