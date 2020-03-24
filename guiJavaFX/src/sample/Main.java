package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main extends Application {

    Stage window;
    Scene scene2;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("OOP Project");

        GridPane gridPane = createRegistrationFormPane();

        addUIControls(gridPane);
        Scene scene1 = new Scene(gridPane, 800, 500);


        FileInputStream input = new FileInputStream("Jagged.png");

        // create a image
        Image image = new Image(input);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);




        //BackgroundFill background_fill = new BackgroundFill(Color.POWDERBLUE,
                //CornerRadii.EMPTY, Insets.EMPTY);
        //Background background = new Background(background_fill);
        gridPane.setBackground(background);



        GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setPadding(new Insets(40, 40, 40, 40));
        gridPane2.setHgap(10);
        gridPane2.setVgap(10);
        gridPane2.setBackground(background);

        //ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        //columnOneConstraints.setHalignment(HPos.RIGHT);
        //ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        //columnTwoConstrains.setHgrow(Priority.ALWAYS);

        //ridPane2.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        Label headerLabel = new Label("Automated Attendance ");
        headerLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 30));
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        window = primaryStage;


        Button recB = new Button("Recognize Images");
        TextArea outA = new TextArea();
        outA.setDisable(true);
        Button regB = new Button("Register New Student");
        Button trainB = new Button("Train Images");
        Button backB = new Button("Back");


        recB.setOnAction(e ->{
            try{
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd \"..\\OOP Project\" && python recognize.py");
                builder.redirectErrorStream(true);
                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                outA.setText("");
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                    Character c ='[';
                    if ( !c.equals(line.charAt(0)) ){
                        outA.appendText("\n"+line);
                    }


                }
            }
            catch (Exception z){
                System.out.println(z);
            }
        });
        trainB.setOnAction(e ->{
            try{
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd \"..\\OOP Project\" && python trainimg.py");
                builder.redirectErrorStream(true);
                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                outA.setText("");
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                    outA.appendText("\n"+line);
                }
            }
            catch (Exception z){
                System.out.println(z);
            }
        });
        regB.setOnAction(e ->{
            primaryStage.setIconified(true);
            try{
                ProcessBuilder builder = new ProcessBuilder(
                        "cmd.exe", "/c", "cd \"..\\OOP Project\" && python train.py");
                builder.redirectErrorStream(true);
                Process p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
            }
            catch (Exception z){
                System.out.println(z);
            }
            primaryStage.setIconified(false);
        });
        backB.setOnAction(e ->    {window.setScene(scene1);});


        gridPane2.add(headerLabel, 0,0,4,1);

        regB.setPrefHeight(40);
        regB.setDefaultButton(true);
        regB.setPrefWidth(200);
        regB.setFont(Font.font("Monospaced", FontWeight.BOLD, 11));
        recB.setFont(Font.font("Monospaced", FontWeight.BOLD, 11));
        trainB.setFont(Font.font("Monospaced", FontWeight.BOLD, 11));
        backB.setFont(Font.font("Monospaced", FontWeight.BOLD, 11));

        gridPane2.add(regB, 0, 1);

        trainB.setPrefHeight(40);
        trainB.setDefaultButton(true);
        trainB.setPrefWidth(200);
        gridPane2.add(trainB, 1, 1);

        recB.setPrefHeight(40);
        recB.setDefaultButton(true);
        recB.setPrefWidth(200);
        gridPane2.add(recB, 2, 1);

        backB.setPrefHeight(40);
        backB.setDefaultButton(true);
        backB.setPrefWidth(200);
        gridPane2.add(backB, 3, 1);



        gridPane2.add(outA,0,2,4,2);


        scene2 = new Scene(gridPane2, 800, 500);
        window.setScene(scene1);
        window.show();
    }


    private GridPane createRegistrationFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        //ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        //columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        //ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        //columnTwoConstrains.setHgrow(Priority.ALWAYS);

        //gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {

        Label headerLabel = new Label("Automated Attendance Login");
        headerLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 30));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label nameLabel = new Label("\t    User ID : ");
        nameLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        nameLabel.setPrefWidth(400);
        gridPane.add(nameLabel, 0,1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        nameField.setPrefWidth(600);
        gridPane.add(nameField, 1,1);

        Label passwordLabel = new Label("\t    Password : ");
        passwordLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        passwordLabel.setPrefWidth(500);
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        Button submitButton = new Button("Submit");
        submitButton.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(passwordField.getText().equals("rvce") && nameField.getText().equals("OOPis18") ){
                    window.setScene(scene2);
                    showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Login Successful!", "Welcome " + nameField.getText());
                }
                else{
                    if(!nameField.getText().equals("OOPis18")) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Incorrect ID!", "Please enter the correct ID!");
                        return;
                    }
                    if(!passwordField.getText().equals("rvce")) {
                        showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Incorrect Password!", "Please enter the correct password!");
                        return;
                    }
                }
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}