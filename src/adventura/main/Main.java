/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventura.main;

import logika.*;
import uiText.TextoveRozhrani;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author mikm11
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        IHra hra = new Hra();

        BorderPane borderPane = new BorderPane();

        TextArea centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        borderPane.setCenter(centerText);

        Label zadejPrikazLabel = new Label("Zadej Prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        TextField zadejPrikazTextField = new TextField("Sem zadej Prikaz");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);

                centerText.appendText("\n\n" + zadanyPrikaz + "\n\n");
                centerText.appendText("\n\n" + odpoved + "\n\n");

                zadejPrikazTextField.setText("");
                if (hra.konecHry()) {
                    zadejPrikazTextField.setEditable(false);

                }
            }
        });

        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);

        borderPane.setBottom(dolniPanel);

        Scene scene = new Scene(borderPane, 300, 250); //app, web.. 

        primaryStage.setTitle("Moje adventura");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            launch(args);

        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }

        }

    }

}
