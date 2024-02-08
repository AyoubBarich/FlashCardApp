package com.example.flashcardapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashcardApp extends Application {
    private String[] questions = {"Question 1", "Question 2"};
    private String[] answers = {"Answer 1", "Answer 2"};
    private int currentIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flashcard App");

        Label questionLabel = new Label();
        questionLabel.setText(questions[currentIndex]);

        Label answerLabel = new Label();

        Button nextButton = new Button();
        nextButton.setText("Next Card");
        nextButton.setOnAction(e -> {
            currentIndex = (currentIndex + 1) % questions.length;
            questionLabel.setText(questions[currentIndex]);
            answerLabel.setText("");
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(questionLabel, answerLabel, nextButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}