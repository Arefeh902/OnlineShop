package scenes;

import functionality.Main;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shop.Cart;
import shop.CartProduct;
import shop.Product;
import users.Seller;
import users.User;

import java.util.ArrayList;

public class UserDashboard {

    public static void userDashboard(){
        VBox userDashboardLayout = new VBox(Main.space);
        userDashboardLayout.setAlignment(Pos.CENTER);
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
//            displayProducts(Main.appData.currentUser.favouriteProducts);
        });
        Button purchasesButton = new Button("purchases");
        purchasesButton.setOnAction(e -> {
            //TODO
        });
        Button allProducts = new Button("see all products");
        allProducts.setOnAction(e -> {
//            displayProducts(Main.appData.products);
        });
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> {
            User.logout();
        });
        userDashboardLayout.getChildren().addAll(title, currentCartButton, nextCartButton, purchasesButton, allProducts, logoutButton);
        Main.userDashboardScene = new Scene(userDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.userDashboardScene);
    }


    public static void setDashboard(){
        if(Main.appData.currentUser != null){
            userDashboard();
        }else if(Main.appData.currentSeller != null){
            SellerDashBoard.sellerDashboard();
        }else if(Main.appData.currentAdmin != null){
//            adminDashboard();
        }
    }

    public static void showAllProducts(Scene prev){
        VBox showAllProductsLayout = new VBox(Main.space);
        showAllProductsLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        for(Product product: Main.appData.products){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label name = new Label(product.name.toString());
            Label price = new Label(product.price.toString());
            hbox.getChildren().addAll(name, price);
            if(Main.appData.currentUser != null){
                Button addToCart = new Button("add to cart");
                addToCart.setOnAction(e -> {
                    System.out.println("will implement soon!");
                });
                hbox.getChildren().add(addToCart);
            }
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showAllProductsLayout.getChildren().addAll(listView, backButton);
        Scene showAllProductsScene = new Scene(showAllProductsLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(showAllProductsScene);
    }





    public static void displayCart(Cart cart){
        VBox displayCartLayout = new VBox(Main.space);
        displayCartLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Cart");
        ObservableList<Button> products = FXCollections.observableArrayList();
        for(CartProduct cartP: cart.cartProducts) {
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

    public static void displayProducts(int back){
        VBox displayProductsLayout = new VBox(Main.space);
        displayProductsLayout.setAlignment(Pos.CENTER);
        ObservableList<Button> productsList = FXCollections.observableArrayList();
//        for(Product product: products) {
//            Button tmp = new Button();
//            tmp.setText(product.toString());
//            tmp.setOnAction(e -> {
//                displayProduct(product);
//            });
//            products.add(tmp);
//        }
//        ListView<Button> listView = new ListView<Button>(products);
//        listView.setMaxSize(200, 160);

    }

    public static void displayCartProduct(CartProduct cartP){
        VBox displayProductLayout = new VBox(Main.space);
        displayProductLayout.setAlignment(Pos.CENTER);
        Label name = new Label(cartP.product.name);
        Label price = new Label(cartP.product.price.toString());
        Label count = new Label(cartP.count.toString());

        TextField newCount = new TextField();

        Button editButton = new Button("edit");
        editButton.setOnAction(e -> {
            cartP.cart.editProduct(cartP.product, Long.parseLong(newCount.getText()));
        });

        Button removeButton = new Button("remove");
        removeButton.setOnAction(e -> {
            cartP.cart.removeProduct(cartP.product);

        });


    }
}
