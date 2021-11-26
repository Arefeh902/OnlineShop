package online_shop.scenes;

import online_shop.functionality.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import online_shop.shop.CartProduct;
import online_shop.shop.CartProductStatus;
import online_shop.shop.Product;
import online_shop.users.Admin;
import online_shop.users.User;

public class SellerDashboard {

    public static void sellerDashboard(){
        Admin admin = Main.appData.currentAdmin;
        VBox sellerDashboardLayout = new VBox(Main.space);
        sellerDashboardLayout.setAlignment(Pos.CENTER);
        Label title = new Label("Dashboard");
        Button myProductsButton = new Button("my products");
        myProductsButton.setOnAction(e -> {
            showSellerProductsView(Main.sellerDashboardScene);
        });
        sellerDashboardLayout.getChildren().add(myProductsButton);
        Button unverifiedProducts = new Button("unverified products");
        unverifiedProducts.setOnAction(e -> {
            showUnverifiedProducts(Main.sellerDashboardScene);
        });
        sellerDashboardLayout.getChildren().add(unverifiedProducts);
        Button allProductsButton = new Button("all products");
        allProductsButton.setOnAction(e -> {
            UserDashboard.showAllProducts(Main.sellerDashboardScene);
        });
        sellerDashboardLayout.getChildren().add(allProductsButton);
        if(admin == null) {
            Button addProductButton = new Button("add product");
            addProductButton.setOnAction(e -> {
                createProductView(Main.sellerDashboardScene);
            });
            sellerDashboardLayout.getChildren().add(addProductButton);
        }
        if(admin == null) {
            Button logoutButton = new Button("logout");
            logoutButton.setOnAction(e -> {
                User.logout();
            });
            sellerDashboardLayout.getChildren().add(logoutButton);
        }
        if(admin != null){
            Button backButton = new Button("back");
            backButton.setOnAction(e -> {
                AdminDashboard.adminDashboard();
            });
            sellerDashboardLayout.getChildren().add(backButton);
        }
        Main.sellerDashboardScene = new Scene(sellerDashboardLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.sellerDashboardScene);
    }

    public static void showSellerProductsView(Scene prev){
        VBox showSellerProductLayout = new VBox(Main.space);
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


    public static void showUnverifiedProducts(Scene prev){
        VBox showUnverifiedLayout = new VBox(Main.space);
        showUnverifiedLayout.setAlignment(Pos.CENTER);
        ObservableList<HBox> productsList = FXCollections.observableArrayList();
        System.out.println(Main.appData.currentSeller.toString());
        if(Main.appData.currentSeller.getUnverifiedProducts() != null) {
            for (CartProduct cartP : Main.appData.currentSeller.getUnverifiedProducts()) {
                HBox hbox = new HBox(Main.space);
                hbox.setAlignment(Pos.CENTER);
                Label productLabel = new Label(cartP.product.toString());
                Label priceLabel = new Label(cartP.price.toString());
                Label countLabel = new Label(cartP.count.toString());
                Button verifyButton = new Button("verify");
                verifyButton.setOnAction(e -> {
                    cartP.status = CartProductStatus.VERIFIED;
                    showUnverifiedProducts(prev);
                });
                hbox.getChildren().addAll(productLabel, priceLabel, countLabel, verifyButton);
                productsList.add(hbox);
            }
        }
        final ListView<HBox> listView = new ListView<HBox>(productsList);
        listView.setMaxSize(350, 250);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> {
            Main.window.setScene(prev);
        });
        showUnverifiedLayout.getChildren().addAll(listView, backButton);
        Scene showUnverifiedScene = new Scene(showUnverifiedLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(showUnverifiedScene);
    }

}
