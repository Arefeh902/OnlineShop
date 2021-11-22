package scenes;

import functionality.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import users.User;


public class Register {
    public static void register(){
        VBox registerLayout = new VBox(Main.space);
        Label title = new Label("Register");
        MenuButton userTypeButton = new MenuButton("user type");
        userTypeButton.getItems().addAll(new MenuItem("Client"), new MenuItem("Seller"));
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
            int userType = 0;
            if(userTypeButton.getItems().equals("Seller")){
                userType = 1;
            }
            if(User.register(userType, username.getText(), password.getText(), verifyPassword.getText())){
                Main.window.setScene(Main.loginScene);
            }
            else{
                System.out.println(username.getText() + password.getText() + verifyPassword.getText());
                register();
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
        registerLayout.getChildren().addAll(title, userTypeButton, usernameLabel, username, passwordLabel, password);
        registerLayout.getChildren().addAll(verifyPasswordLabel, verifyPassword, registerButton, loginButton);
        registerLayout.setAlignment(Pos.CENTER);
        Main.registerScene = new Scene(registerLayout, Main.screenWidth, Main.screenHeight);
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
//                Main.window.setScene();
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
    }

//    play scene
//    VBox playLayout = new VBox(space);
//    Label instruction = new Label("Guess the number!");
//    Label furtherInstruction = new Label("From 1 to 100");
//    TextField guessedNum = new TextField();
//        guessedNum.setMaxWidth((int)screenWidth/4);
//    Button guessButton = new Button();
//        guessButton.setText("Guess");
//        guessButton.setOnAction(e -> {
//        int guess = Integer.parseInt(guessedNum.getText());
//        if (guess < randomInt)
//            furtherInstruction.setText("higher!");
//        else if (guess > randomInt)
//            furtherInstruction.setText("lower!");
//        else{
//
//            window.setScene(end);
//            furtherInstruction.setText("From 1 to 100");
//        }
//    });
//        playLayout.getChildren().addAll(instruction, furtherInstruction, guessedNum, guessButton);
//        playLayout.setAlignment(Pos.CENTER);
//    play = new Scene(playLayout, screenWidth, screenHeight);

}
