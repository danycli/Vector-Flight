package org.danycli.flappyBird;

import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Scene scene = new Scene(root,350,400);

        Button Heaven = Styling.buttonStyling("Heaven", -50);
        Heaven.setFont(font);

        Button Hell = Styling.buttonStyling("Hell",50);
        Hell.setFont(font);

        root.getChildren().add(Heaven);
        root.getChildren().add(Hell);

        Heaven.setOnAction(e ->{
            gameMod = "Heaven";
            menu.setMod(gameMod);
            over.setMod(gameMod);
            stage.close();
        });
        Hell.setOnAction(e ->{
            gameMod = "Hell";
            menu.setMod(gameMod);
            over.setMod(gameMod);
            stage.close();
        });

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
