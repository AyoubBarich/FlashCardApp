package com.example.flashcardapp;

import eu.hansolo.tilesfx.colors.Bright;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Berger extends Application {
    @Override
    public void start(Stage stage) {
        Text text = new Text();
        Label popupLabel = new Label("WORNG");
        Popup wrong = new Popup();
        wrong.getContent().add(popupLabel);
        wrong.setAutoHide(true);
        //Setting font to the text
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        text.setY(20);

        text.setText("Vous devez amener les trois loups et les trois poussins sur l'autre rive. Attention à bien respecter les règles suivantes :\n" +
                "\n" +
                "Le radeau ne peut accueillir plus de deux animaux.\n" +
                "Vous ne pouvez déplacer le radeau s'il est vide.\n" +
                "S'il y a plus de loups que de poussins sur une des rives, les loups mangeront les pauvres volatiles sans défense et il vous faudra recommencer depuis le début.\n" +
                "Vous n'avez pas de limite de déplacement, mais il est possible de résoudre cette énigme en 11 déplacements.");

        Text left = new Text();
        Text right = new Text();
        left.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        right.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        left.setText("Left");
        right.setText("Right");
        left.setY(300);
        right.setY(300);
        left.setX(20);
        right.setX(580);
        double place = 350;
        ToggleButton mouton = new ToggleButton("M");

        ToggleButton loup = new ToggleButton("L");
        loup.setLayoutY(mouton.getLayoutY()+50);
        ToggleButton chou = new ToggleButton("C");
        chou.setLayoutY(mouton.getLayoutY()+100);
        Group buttonsLeft = new Group(mouton,loup,chou);
        buttonsLeft.setLayoutX(20);
        buttonsLeft.setLayoutY(place);
        Group buttonsRight = new Group();
        buttonsRight.setLayoutX(580);
        buttonsRight.setLayoutY(place);
        EventHandler<MouseEvent> moutonLeftEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsRight.getChildren().add(mouton);
                buttonsLeft.getChildren().remove(mouton);
            }
        };
        EventHandler<MouseEvent> moutonRightEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsLeft.getChildren().add(mouton);
                buttonsRight.getChildren().remove(mouton);
            }
        };
        EventHandler<MouseEvent> loupLeftEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsRight.getChildren().add(loup);
                buttonsLeft.getChildren().remove(loup);
            }
        };
        EventHandler<MouseEvent> loupRightEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsLeft.getChildren().add(loup);
                buttonsRight.getChildren().remove(loup);
            }
        };
        EventHandler<MouseEvent> chouLeftEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsRight.getChildren().add(chou);
                buttonsLeft.getChildren().remove(chou);
            }
        };
        EventHandler<MouseEvent> chouRightEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buttonsLeft.getChildren().add(chou);
                buttonsRight.getChildren().remove(chou);
            }
        };
        // Use a selected property to check the state of the ToggleButton
        if (mouton.isSelected()) {
            mouton.addEventFilter(MouseEvent.MOUSE_CLICKED, moutonRightEventHandler);
        } else {
            mouton.addEventFilter(MouseEvent.MOUSE_CLICKED, moutonLeftEventHandler);
        }

        if (loup.isSelected()) {
            loup.addEventFilter(MouseEvent.MOUSE_CLICKED, loupRightEventHandler);
        } else {
            loup.addEventFilter(MouseEvent.MOUSE_CLICKED, loupLeftEventHandler);
        }

        if (chou.isSelected()) {
            chou.addEventFilter(MouseEvent.MOUSE_CLICKED, chouRightEventHandler);
        } else {
            chou.addEventFilter(MouseEvent.MOUSE_CLICKED, chouLeftEventHandler);
        }

// Add an event handler to handle the toggle state change
        mouton.setOnAction(event -> {
            if (mouton.isSelected()) {
                buttonsLeft.getChildren().remove(mouton);
                buttonsRight.getChildren().add(mouton);
            } else {
                buttonsRight.getChildren().remove(mouton);
                buttonsLeft.getChildren().add(mouton);
            }
            checkAndReset(buttonsLeft,buttonsRight,mouton,loup,chou,wrong,stage);
        });
        loup.setOnAction(event -> {
            if (loup.isSelected()) {
                buttonsLeft.getChildren().remove(loup);
                buttonsRight.getChildren().add(loup);
            } else {
                buttonsRight.getChildren().remove(loup);
                buttonsLeft.getChildren().add(loup);
            }
            checkAndReset(buttonsLeft,buttonsRight,mouton,loup,chou,wrong,stage);
        });
        chou.setOnAction(event -> {
            if (chou.isSelected()) {
                buttonsLeft.getChildren().remove(chou);
                buttonsRight.getChildren().add(chou);
            } else {
                buttonsRight.getChildren().remove(chou);
                buttonsLeft.getChildren().add(chou);
            }
            checkAndReset(buttonsLeft,buttonsRight,mouton,loup,chou,wrong,stage);
        });


/*
        if (buttonsLeft.getChildren().contains(mouton)) {
            mouton.addEventFilter(MouseEvent.MOUSE_CLICKED, moutonLeftEventHandler);
            mouton.cancelButtonProperty();
        }else {
            mouton.addEventFilter(MouseEvent.MOUSE_CLICKED, moutonRightEventHandler);
        }*/
/*
        loup.addEventFilter(MouseEvent.MOUSE_CLICKED,loupLeftEventHandler);
        loup.addEventFilter(MouseEvent.MOUSE_CLICKED,loupRightEventHandler);
        chou.addEventFilter(MouseEvent.MOUSE_CLICKED,chouLeftEventHandler);
        chou.addEventFilter(MouseEvent.MOUSE_CLICKED,chouRightEventHandler);
*/




        Group root = new Group(text,right,left,buttonsLeft,buttonsRight);
        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("La traversée du LAC");
        stage.setScene(scene);

        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
    private void checkAndReset(Group buttonsLeft,Group buttonsRight,ToggleButton mouton,ToggleButton loup,ToggleButton chou,Popup popup,Stage stage) {
        if ((buttonsLeft.getChildren().contains(mouton) && buttonsLeft.getChildren().contains(loup)) ||
                (buttonsRight.getChildren().contains(mouton) && buttonsRight.getChildren().contains(loup))) {
            // Reset the positions of all buttons
            buttonsLeft.getChildren().clear();
            buttonsRight.getChildren().clear();
            buttonsLeft.getChildren().addAll(mouton, loup, chou);
            /*popup.show(stage);*/
        }
        if ((buttonsLeft.getChildren().contains(chou) && buttonsLeft.getChildren().contains(mouton)) ||
                (buttonsRight.getChildren().contains(chou) && buttonsRight.getChildren().contains(mouton))) {
            // Reset the positions of all buttons
            buttonsLeft.getChildren().clear();
            buttonsRight.getChildren().clear();
            buttonsLeft.getChildren().addAll(mouton, loup, chou);

            /*popup.show(stage);*/
        }


    }




}   