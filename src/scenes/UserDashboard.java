package scenes;

import functionality.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shop.Cart;
import shop.CartProduct;
import shop.Product;
import users.User;


public class UserDashboard {

    public static void userDashboard(){
        VBox userDashboardLayout = new VBox(Main.space);
        userDashboardLayout.setAlignment(Pos.CENTER);
        Label title = new Label("DashBoard");
        Button currentCartButton = new Button("current cart");
        currentCartButton.setOnAction(e -> {
            Main.appData.currentUser.currentCart.verifyCart();
            displayCart(Main.appData.currentUser.currentCart, Main.userDashboardScene);
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
            showAllProducts(Main.userDashboardScene);
        });
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> {
            User.logout();
        });
        userDashboardLayout.getChildren().addAll(title, currentCartButton, purchasesButton, allProducts, logoutButton);
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


    public static void displayCart(Cart cart, Scene prev){
        VBox displayCartLayout = new VBox(Main.space);
        displayCartLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Cart");
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        for(CartProduct cartP: cart.cartProducts){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label productLabel = new Label(cartP.product.toString());
            Label priceLabel = new Label(cartP.product.price.toString());
            Label countLabel = new Label(cartP.count.toString());
            Button incProduct = new Button("+");
            incProduct.setOnAction(e -> {
                cartP.incCount();
                countLabel.setText(cartP.count.toString());
            });
            Button decProduct = new Button("-");
            decProduct.setOnAction(e -> {
                Product p = cartP.product;
                cartP.decCount();
                countLabel.setText(cartP.count.toString());
                if(!cart.hasProduct(p)){
                    displayCart(cart, prev);
                }
            });
            hbox.getChildren().addAll(productLabel, priceLabel, decProduct, countLabel, incProduct);
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button purchaseButton = new Button("purchase");
        purchaseButton.setOnAction(e -> {
            //
            //
            //
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        displayCartLayout.getChildren().addAll(title, listView, purchaseButton, backButton);
        Scene displayCartScene = new Scene(displayCartLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(displayCartScene);
    }


    public static void displayCartProduct(CartProduct cartP){
        VBox displayProductLayout = new VBox(Main.space);
        displayProductLayout.setAlignment(Pos.CENTER);
        Label name = new Label(cartP.product.name);
        Label price = new Label(cartP.product.price.toString());
        Label count = new Label(cartP.count.toString());
        Button addButton = new Button("+");
        addButton.setOnAction(e -> {
            cartP.incCount();
        });
        Button removeOneButton = new Button("-");
        removeOneButton.setOnAction(e -> {
            cartP.decCount();
        });
        Button removeButton = new Button("remove");
        removeButton.setOnAction(e -> {
            cartP.cart.removeProduct(cartP.product);

        });
    }


    public static void showAllProducts(Scene prev){
        VBox showAllProductsLayout = new VBox(Main.space);
        showAllProductsLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        for(Product product: Main.appData.products){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label name = new Label(product.name);
            Label price = new Label(product.price.toString());
            hbox.getChildren().addAll(name, price);
            if(Main.appData.currentUser != null){
                Cart cart = Main.appData.currentUser.currentCart;
                if(cart.getCartProduct(product) == null){
                    Button addToCart = new Button("add to cart");
                    addToCart.setOnAction(e -> {
                        Main.appData.currentUser.currentCart.addProduct(product);
                        showAllProducts(prev);
                    });
                    hbox.getChildren().add(addToCart);
                }else{
                    Label count = new Label(cart.getCartProduct(product).count.toString());
                    Button incProduct = new Button("+");
                    incProduct.setOnAction(e -> {
                        cart.addProduct(product);
                        count.setText(cart.getCartProduct(product).count.toString());
                    });
                    Button decProduct = new Button("-");
                    decProduct.setOnAction(e -> {
                        cart.removeOne(product);
                        if(!cart.hasProduct(product)){
                            showAllProducts(prev);
                            return;
                        }
                        count.setText(cart.getCartProduct(product).count.toString());
                    });
                    hbox.getChildren().addAll(decProduct, count, incProduct);
                }

            }
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showAllProductsLayout.getChildren().addAll(listView, backButton);
        Scene showAllProductsScene = new Scene(showAllProductsLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(showAllProductsScene);
    }

}
