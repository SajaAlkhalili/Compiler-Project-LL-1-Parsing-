//package com.example.finalprojectcompiler;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class HelloApplication extends Application {
//
//
//    private TextArea textArea;
//    private Label resultLabel;
//    private TextArea parsingTableTextArea;
//
//    public Button parsebtn;
//
//
//        @Override
//        public void start(Stage primaryStage)throws IOException {
//
//                // First screen: Choose File and Load Contents
//                BorderPane root = new BorderPane();
//
//                textArea = new TextArea();
//                textArea.setWrapText(true);
//                Button openButton = new Button("CHOOSE FILE");
//                openButton.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #6a9485");
//            Button tablebtn = new Button("Parsing table→");
//            tablebtn.setStyle("-fx-background-color: #b33951; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #80283b");
//            tablebtn.setOnAction(e ->{
//                    try {
//                        parsingtable(primaryStage);
//                    } catch (Exception e1) {
//                        System.out.println(e1.getMessage());
//                    }
//                });
//                openButton.setOnAction(e -> chooseFile(primaryStage));
//            resultLabel = new Label();
//
//                 parsebtn = new Button("parse →");
//            parsebtn.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: white; -fx-border-width: 2; -fx-border-color: #6a9485");
//                parsebtn.setOnAction(e -> parse());
//            HBox h2=new HBox(10,parsebtn,resultLabel );
//            HBox h1=new HBox(10,openButton,tablebtn );
//                VBox vBox = new VBox(10, h1, textArea, h2);
//                vBox.setPadding(new Insets(10));
//                root.setCenter(vBox);
//
//
//
//                Scene scene1 = new Scene(root, 400, 300);
//
//                // Second screen: Parsing Table
//                BorderPane root2 = new BorderPane();
//                parsingTableTextArea = new TextArea();
//                parsingTableTextArea.setEditable(false);
//
//                Button backButton = new Button("Back to Main Screen");
//                backButton.setOnAction(e -> primaryStage.setScene(scene1));
//
//                root2.setCenter(parsingTableTextArea);
//                root2.setBottom(backButton);
//
//                Scene scene2 = new Scene(root2, 600, 400);
//            primaryStage.setFullScreen(true);
//                primaryStage.setScene(scene1);
//                primaryStage.setTitle("Parsing Table App");
//                primaryStage.show();
//            }
//
//            private void chooseFile(Stage stage) {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Open Resource File");
//                File selectedFile = fileChooser.showOpenDialog(stage);
//                if (selectedFile != null) {
//                    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
//                        StringBuilder content = new StringBuilder();
//                        String line;
//                        while ((line = br.readLine()) != null) {
//                            content.append(line).append("\n");
//                        }
//                        textArea.setText(content.toString());
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//
//    private void parse() {
//        String inputText = textArea.getText();
//
//        // Create a CompilerScanner using the input text
//        CompilerScanner scanner = new CompilerScanner(inputText);
//
//        // Create an LL1Parser using the CompilerScanner
//        LL1Parser parser = new LL1Parser(scanner);
//
//        // Get the parsing result
//        String parsingResult = parser.getParsingResult();
//
//        // Display the parsing result in the resultLabel
//        resultLabel.setText("Parsing result: " + parsingResult);
//    }
//
//    public static void parsingtable(Stage stage) {
//
//        BorderPane root = new BorderPane();
//        TextArea valuesTable = new TextArea();
//        Button back = new Button("← back");
//        back.setStyle("-fx-background-color: #b33951; -fx-text-fill: White; -fx-border-width: 2; -fx-border-color: #80283b");
//        back.setOnAction(e -> {
//            try {
//                start(stage);
//            } catch (Exception e1) {
//                System.out.println(e1.getMessage());
//            }
//        });
//        valuesTable.appendText(Parsing_Table.printParsingTable());
//
//        // Add the TextArea to the center of the BorderPane
//        BorderPane r1 = new BorderPane();
//        r1.setCenter(valuesTable);
//root.setCenter(r1);
//root.setBottom(back);
//        Scene scene = new Scene(root, 800, 600); // Adjust dimensions as needed
//        stage.setScene(scene);
//        stage.setFullScreen(true);
//        stage.setMaximized(false);
//        stage.setTitle("Parsing Table");
//        stage.show();
//
//
//    }
//
//            public static void main(String[] args) {
//                launch(args);
//            }
//        }
package com.example.finalprojectcompiler;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {

    private TextArea txtarea;
    private Label resulparsetLabel;
    public Button parsebtn;
    Button tablebtn;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Parsing Table App");
        primaryStage.show();
    }

    private Scene createMainScene(Stage primaryStage) {
        // First screen: Choose File and Load Contents
        BorderPane root = new BorderPane();

        txtarea = new TextArea();
        txtarea.setWrapText(true);
        Button openButton = new Button("CHOOSE FILE");
        openButton.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #6a9485");

         tablebtn = new Button("PARSING TABLE →");
        tablebtn.setStyle("-fx-background-color: #b33951; -fx-text-fill: White;  -fx-border-width: 2; -fx-border-color: #80283b");
        tablebtn.setDisable(true);
        tablebtn.setOnAction(e -> {
            parsingtable(primaryStage);
        });

        openButton.setOnAction(e -> chooseFile(primaryStage));
        resulparsetLabel = new Label();

        parsebtn = new Button("PARSE →");
        parsebtn.setStyle("-fx-background-color: #91c7b1; -fx-text-fill: white; -fx-border-width: 2; -fx-border-color: #6a9485");
        parsebtn.setOnAction(e -> parse());

        HBox h2 = new HBox(10, parsebtn, resulparsetLabel);
        HBox h1 = new HBox(10, openButton, tablebtn);
        VBox vBox = new VBox(10, h1, txtarea, h2);
        vBox.setPadding(new Insets(10));
        root.setCenter(vBox);

        return new Scene(root, 400, 300);
    }

    private void chooseFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                tablebtn.setDisable(false);
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
                txtarea.setText(content.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void parse() {
        String inputText = txtarea.getText();

        // Create a Scanneere using the input text
        Scanneere scanner = new Scanneere(inputText);

        // Create an LL1Parser using the CompilerScanner
        LL1Parser parser = new LL1Parser(scanner);

        // Get the parsing result
        String parsingResult = parser.getParsingResult();

        resulparsetLabel.setText("Parsing result: " + parsingResult);
    }

    public static void parsingtable(Stage stage) {
        BorderPane root = new BorderPane();
        TextArea valuesTable = new TextArea();
        Button back = new Button("← back");
        back.setStyle("-fx-background-color: #b33951; -fx-text-fill: White; -fx-border-width: 2; -fx-border-color: #80283b");

        back.setOnAction(e -> stage.setScene(new HelloApplication().createMainScene(stage)));

        valuesTable.appendText(Parsing_Table.printParsingTable());

        BorderPane r1 = new BorderPane();
        r1.setCenter(valuesTable);
        root.setCenter(r1);
        root.setBottom(back);

        Scene scene = new Scene(root, 800, 600); // Adjust dimensions as needed
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setMaximized(false);
        stage.setTitle("Parsing Table");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

