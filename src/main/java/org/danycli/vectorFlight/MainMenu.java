package org.danycli.vectorFlight;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenu {
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),50);
    private boolean playGame;
    private String mod;

    public void mainMENU(EventHandler event){

        mod = null;
        playGame = false;
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,450,650);

        Button play = Styling.buttonStyling("PLAY", -140);
        play.setFont(font);

        Button gameMod = Styling.buttonStyling("GAME MOD",0);
        gameMod.setFont(font);

        Button exit = Styling.buttonStyling("QUIT GAME", 140);
        exit.setFont(font);

        root.getChildren().add(play);
        root.getChildren().add(gameMod);
        root.getChildren().add(exit);

        play.setOnAction(e ->{
            stage.close();
            event.setMod(mod);
            event.startGame();
        });

        exit.setOnAction(e ->{
            stage.close();
        });

        GameOver over = new GameOver();
        gameMod.setOnAction(e ->{
            GameMod Mod = new GameMod();
            Mod.mod(MainMenu.this,over);
        });

        Image icon = new Image(getClass().getResourceAsStream("/VectorFlight.png"));

        stage.getIcons().add(icon);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #0f3737e0;
            -fx-background-radius: 20;
        """);
        stage.show();
        stage.setAlwaysOnTop(true);
    }

    public boolean startGame(){
        return playGame;
    }
    
    public void setMod(String Mod){
        mod = Mod;
    }
}
