package scenes;

import functionality.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import shop.Cart;
import shop.CartProduct;
import shop.Product;
import users.User;

import java.util.ArrayList;

public class UserDashboard {

    public static void userDashboard(){
        VBox userDashboardLayout = new VBox(Main.space);
        Label title = new Label("DashBoard");
        Button currentCartButton = new Button("current cart");
        currentCartButton.setOnAction(e -> {
            displayCart(Main.appData.currentUser.currentCart);
        });
        Button nextCartButton = new Button("next cart");
        nextCartButton.setOnAction(e -> {
            displayCart(Main.appData.currentUser.nextCart);
        });
        Button favouritesButton = new Button("favourites");
        favouritesButton.setOnAction(e -> {
            displayProducts(Main.appData.currentUser.favouriteProducts);
        });
        Button purchasesButton = new Button("purchases");
        purchasesButton.setOnAction(e -> {
            //TODO
        });
        Button allProducts = new Button("see all products");
        allProducts.setOnAction(e -> {
            displayProducts(Main.appData.products);
        });
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> {
            User.logout();
        });
        userDashboardLayout.getChildren().addAll(title, currentCartButton, nextCartButton, purchasesButton, allProducts, logoutButton);
        Main.userDashboardScene = new Scene(userDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.userDashboardScene);
    }

    public static void sellerDashboard(){
        VBox sellerDashboardLayout = new VBox(Main.space);
        Label title = new Label("Dashboard");
        Button unverifiedProducts = new Button("unverified products");
        Button allProductsButton = new Button("all products");
        Button myProductsButton = new Button("my products");
        Button addProductButton = new Button("add product");
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> {
            User.logout();
        });
        sellerDashboardLayout.getChildren().addAll(title, unverifiedProducts, allProductsButton, myProductsButton, addProductButton, logoutButton);
        Main.sellerDashboardScene = new Scene(sellerDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.sellerDashboardScene);
    }

    public static void setDashboard(){
        if(Main.appData.currentUser != null){
            userDashboard();
        }
        else if(Main.appData.currentSeller != null){
            sellerDashboard();
        }else if(Main.appData.currentAdmin != null){
//            adminDashboard();
        }
    }

    public static void displayCart(Cart cart){
        VBox displayCartLayout = new VBox(Main.space);
        Label title = new Label("Cart");
        ObservableList<Button> products = FXCollections.observableArrayList();
        for(CartProduct cartP: cart.products) {
            Button tmp = new Button();
            tmp.setText(cartP.toString());
            tmp.setOnAction(e -> {
                displayCartProduct(cartP);
            });
            products.add(tmp);
        }
        ListView<Button> listView = new ListView<Button>(products);
        listView.setMaxSize(200, 160);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(Main.userDashboardScene);
        });
        displayCartLayout.getChildren().addAll(title, listView, backButton);
        Scene displayCart = new Scene(displayCartLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(displayCart);
    }

    public static void displayCartProduct(CartProduct cartP){

    }

    public static void displayProducts(ArrayList<Product> products){

    }
}
