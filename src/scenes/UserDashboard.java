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

    public static void sellerDashboard(){
        VBox sellerDashboardLayout = new VBox(Main.space);
        sellerDashboardLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Dashboard");
        Button myProductsButton = new Button("my products");
        myProductsButton.setOnAction(e -> {
            showSellerProductsView(Main.sellerDashboardScene);
        });
        Button unverifiedProducts = new Button("unverified products");
        unverifiedProducts.setOnAction(e -> {
            showUnverifiedProducts();
        });
        Button allProductsButton = new Button("all products");
        allProductsButton.setOnAction(e -> {
//            showAllProducts(1);
        });
        Button addProductButton = new Button("add product");
        addProductButton.setOnAction(e -> {
            createProductView(Main.sellerDashboardScene);
        });
        Button logoutButton = new Button("logout");
        logoutButton.setOnAction(e -> {
            User.logout();
        });
        sellerDashboardLayout.getChildren().addAll(title, myProductsButton, unverifiedProducts, allProductsButton);
        sellerDashboardLayout.getChildren().addAll(addProductButton, logoutButton);
        Main.sellerDashboardScene = new Scene(sellerDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.sellerDashboardScene);
    }

    public static void setDashboard(){
        if(Main.appData.currentUser != null){
            userDashboard();
        }else if(Main.appData.currentSeller != null){
            sellerDashboard();
        }else if(Main.appData.currentAdmin != null){
//            adminDashboard();
        }
    }

    public static void showSellerProductsView(Scene prev){
        System.out.println("these are my products");
        System.out.println(Main.appData.currentSeller);
        StackPane showSellerProductLayout = new StackPane();
        showSellerProductLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        HBox labels = new HBox(Main.space);
        labels.setAlignment(Pos.CENTER);
        String nameSpace = "        ";
        String priceSpace = "       ";
        String inventorySpace = "   ";
        labels.getChildren().addAll(new Label("name" + nameSpace),new Label("price" + priceSpace), new Label("inventory" + inventorySpace), new Label("edit"));
        productsList.add(labels);
        for(Product product: Main.appData.currentSeller.getProducts()){
            HBox hbox = new HBox(Main.space);
            hbox.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(product.name);
            Label priceLabel = new Label(product.price.toString());
            Label inventoryLabel = new Label(product.inventory.toString());
            Button editButton = new Button("edit");
            editButton.setOnAction(e -> {
                editProductView(product);
            });
            hbox.getChildren().addAll(nameLabel, priceLabel, inventoryLabel, editButton);
            productsList.add(hbox);
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showSellerProductLayout.getChildren().addAll(listView, backButton);
        Scene showSellerProductScene = new Scene(showSellerProductLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(showSellerProductScene);
    }

    public static void editProductView(Product product){
        VBox editProductLayout = new VBox(Main.space);
        editProductLayout.setAlignment(Pos.CENTER);
        Label emptyLabel = new Label("");
        HBox name = new HBox(Main.space);
        name.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("name: " + product.name);
        TextField nameText = new TextField();
        name.getChildren().addAll(nameLabel, nameText);
        HBox price = new HBox(Main.space);
        price.setAlignment(Pos.CENTER);
        Label priceLabel = new Label("price " + product.price);
        TextField priceText = new TextField();
        price.getChildren().addAll(priceLabel, priceText);
        HBox inventory = new HBox(Main.space);
        inventory.setAlignment(Pos.CENTER);
        Label inventoryLabel = new Label("inventory: " + product.inventory);
        TextField inventoryText = new TextField();
        inventory.getChildren().addAll(inventoryLabel, inventoryText);
        Button editButton = new Button("edit");
        editButton.setOnAction(e -> {
            emptyLabel.setText("");
            if(nameText.getText() != null)
                product.editName(nameText.getText());
            if(priceText.getText() != null){
                try{
                    Long newPrice = Long.parseLong(priceText.getText());
                    product.editPrice(newPrice);
                }catch (NumberFormatException exception){
                    emptyLabel.setText("invalid input!");
                }
            }
            if(inventoryText.getText() != null){
                try{
                    Long newInventory = Long.parseLong(inventoryText.getText());
                    product.editInventory(newInventory);
                }catch (NumberFormatException exception){
                    emptyLabel.setText("invalid input!");
                }
            }
            nameText.clear();
            priceText.clear();
            inventoryText.clear();
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            if(Main.appData.currentSeller != null)
                Main.window.setScene(Main.sellerDashboardScene);
            else if(Main.appData.currentAdmin != null)
                System.out.println("should implement");
        });
        editProductLayout.getChildren().addAll(name, price, inventory, editButton, emptyLabel, backButton);
        Scene editProductScene = new Scene(editProductLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(editProductScene);
    }

    public static void createProductView(Scene prev){
        VBox createProductLayout = new VBox(Main.space);
        createProductLayout.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Name");
        TextField name = new TextField();
        name.setMaxWidth((float)Main.screenWidth/4);
        Label priceLabel = new Label("Price");
        TextField price = new TextField();
        price.setMaxWidth((float)Main.screenWidth/4);
        Label inventoryLabel = new Label("Inventory");
        TextField inventory = new TextField();
        inventory.setMaxWidth((float)Main.screenWidth/4);
        Button addButton = new Button("add");
        Label emptyLabel = new Label("");
        addButton.setOnAction(e -> {
            for(Product p: Main.appData.products){
                System.out.println(p.toString());
            }
            emptyLabel.setText("");
            try{
                Long priceVar = Long.parseLong(price.getText());
                Long inventoryVar = Long.parseLong(inventory.getText());
                Main.appData.createProduct(name.getText(), priceVar, inventoryVar);
                name.clear();
                price.clear();
                inventory.clear();
                emptyLabel.setText("");
            }catch (NumberFormatException exception){
                emptyLabel.setText("invalid input!");
            }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        createProductLayout.getChildren().addAll(nameLabel, name, priceLabel, price, inventoryLabel, inventory);
        createProductLayout.getChildren().addAll(addButton, backButton, emptyLabel);
        Scene productView = new Scene(createProductLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(productView);
    }



    public static void showUnverifiedProducts(){

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
