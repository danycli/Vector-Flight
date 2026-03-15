package org.danycli.flappyBird;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameMod {
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),50);
    private String gameMod;

    public void mod(MainMenu menu, GameOver over){

        gameMod = null;
        
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,350,450);

        Button Heaven = Styling.buttonStyling("Heaven", -70);
        Heaven.setFont(font);

        Button Hell = Styling.buttonStyling("Hell",70);
        Hell.setFont(font);

        root.getChildren().add(Heaven);
        root.getChildren().add(Hell);
        EquippedPopUp equip = new EquippedPopUp();
        Heaven.setOnAction(e ->{
            gameMod = "Heaven";
            menu.setMod(gameMod);
            over.setMod(gameMod);
            stage.close();
            equip.cautionPopUp("  HEAVEN\nSELECTED");
        });
        Hell.setOnAction(e ->{
            gameMod = "Hell";
            menu.setMod(gameMod);
            over.setMod(gameMod);
            stage.close();
            equip.cautionPopUp("     HELL\nSELECTED");
        });

        Image icon = new Image(getClass().getResourceAsStream("/VectorFlight.png"));

        stage.getIcons().add(icon);
        stage.setTitle("Game Mod");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #1a5c5c;
            -fx-background-radius: 20;
        """);
        stage.show();
        stage.setAlwaysOnTop(true);
    }
}
