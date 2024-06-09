package com.example.finalprojectcompiler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {


    private TextArea textArea;
    private Label resultLabel;
    private TextArea parsingTableTextArea;


        @Override
        public void start(Stage primaryStage) {

                // First screen: Choose File and Load Contents
                BorderPane root = new BorderPane();

                textArea = new TextArea();
                textArea.setWrapText(true);
                Button chooseFileButton = new Button("Choose File");
                chooseFileButton.setOnAction(e -> chooseFile(primaryStage));

                Button parseButton = new Button("Parse");
                parseButton.setOnAction(e -> parse());

                VBox vBox = new VBox(10, chooseFileButton, textArea, parseButton);
                vBox.setPadding(new Insets(10));
                root.setCenter(vBox);

                resultLabel = new Label();
                root.setBottom(resultLabel);

                Scene scene1 = new Scene(root, 400, 300);

                // Second screen: Parsing Table
                BorderPane root2 = new BorderPane();
                parsingTableTextArea = new TextArea();
                parsingTableTextArea.setEditable(false);

                Button backButton = new Button("Back to Main Screen");
                backButton.setOnAction(e -> primaryStage.setScene(scene1));

                root2.setCenter(parsingTableTextArea);
                root2.setBottom(backButton);

                Scene scene2 = new Scene(root2, 600, 400);

                primaryStage.setScene(scene1);
                primaryStage.setTitle("Parsing Table App");
                primaryStage.show();
            }

            private void chooseFile(Stage stage) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile != null) {
                    try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        textArea.setText(content.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

    private void parse() {
        String inputText = textArea.getText();

        // Create a CompilerScanner using the input text
        CompilerScanner scanner = new CompilerScanner(inputText);

        // Create an LL1Parser using the CompilerScanner
        LL1Parser parser = new LL1Parser(scanner);

        // Get the parsing result
        String parsingResult = parser.getParsingResult();

        // Display the parsing result in the resultLabel
        resultLabel.setText("Parsing result: " + parsingResult);
    }
    private void openParseTableScreen(Stage primaryStage, String parsingTableContent) {
        // Create a new TextArea to display the parsing table content
        parsingTableTextArea = new TextArea();
        parsingTableTextArea.setEditable(false);
        parsingTableTextArea.setText(parsingTableContent);

        // Create a new BorderPane for the parsing table screen
        BorderPane parsingTablePane = new BorderPane();
        parsingTablePane.setCenter(parsingTableTextArea);

        // Create a new Scene for the parsing table screen
        Scene parsingTableScene = new Scene(parsingTablePane, 600, 400);

        // Set the parsing table screen as the current scene
        primaryStage.setScene(parsingTableScene);
    }
            public static void main(String[] args) {
                launch(args);
            }
        }