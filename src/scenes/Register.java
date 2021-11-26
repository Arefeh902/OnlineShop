package scenes;

import functionality.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import users.Hash;
import users.User;

import java.util.concurrent.atomic.AtomicInteger;

public class Register {
    static int userType;
    public static void register(){
        VBox registerLayout = new VBox(Main.space);
        Label title = new Label("Register");
        RadioButton rb1 = new RadioButton("Client");
        RadioButton rb2 = new RadioButton("Seller");
        rb1.setSelected(true);
        final ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        HBox hbox = new HBox(Main.space, rb1, rb2);
        hbox.setAlignment(Pos.CENTER);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
            {
                RadioButton selectedButton = (RadioButton)group.getSelectedToggle();
                if(selectedButton == rb2){
                    userType = 1;
                }else{
                    userType = 0;
                }
            }
        });
        Label usernameLabel = new Label("Enter username");
        TextField username = new TextField();
        username.setMaxWidth((float)Main.screenWidth/4);
        Label passwordLabel = new Label("Enter password");
        TextField password = new TextField();
        password.setMaxWidth((float)Main.screenWidth/4);
        Label verifyPasswordLabel = new Label("Enter password again");
        TextField verifyPassword = new TextField();
        verifyPassword.setMaxWidth((float)Main.screenWidth/4);
        Button registerButton = new Button();
        registerButton.setText("register");
        registerButton.setOnAction(e -> {
            if(User.register(userType, username.getText(), password.getText(), verifyPassword.getText())){
                Main.window.setScene(Main.loginScene);
            }
            username.clear();
            password.clear();
            verifyPassword.clear();
        });
        Button loginButton = new Button();
        loginButton.setText("login");
        loginButton.setOnAction(e -> {
            Main.window.setScene(Main.loginScene);
        });
        registerLayout.getChildren().addAll(title, hbox, usernameLabel, username, passwordLabel, password);
        registerLayout.getChildren().addAll(verifyPasswordLabel, verifyPassword, registerButton, loginButton);
        registerLayout.setAlignment(Pos.CENTER);
        Main.registerScene = new Scene(registerLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.registerScene);
    }

    public static void login(){
        VBox loginLayout = new VBox(Main.space);
        Label title = new Label("Login");
        Label usernameLabel = new Label("Enter username");
        TextField username = new TextField();
        username.setMaxWidth((float)Main.screenWidth/4);
        Label passwordLabel = new Label("Enter password");
        TextField password = new TextField();
        password.setMaxWidth((float)Main.screenWidth/4);
        Button loginButton = new Button();
        loginButton.setText("login");
        loginButton.setOnAction(e -> {
            if(User.login(username.getText(), password.getText())){
                UserDashboard.setDashboard();
            }
            username.clear();
            password.clear();
        });
        Button registerButton = new Button();
        registerButton.setText("register");
        registerButton.setOnAction(e -> {
            Main.window.setScene(Main.registerScene);
        });
        loginLayout.getChildren().addAll(title, usernameLabel, username, passwordLabel, password, loginButton, registerButton);
        loginLayout.setAlignment(Pos.CENTER);
        Main.loginScene = new Scene(loginLayout, Main.screenWidth, Main.screenHeight);
        Main.window.setScene(Main.loginScene);
    }


}
