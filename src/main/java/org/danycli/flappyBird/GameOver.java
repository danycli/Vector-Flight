package org.danycli.flappyBird;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameOver {
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),50);
    
    public void gameOver(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,450,650);

        Button playAgain = Styling.buttonStyling("PLAY AGAIN", -240);
        playAgain.setFont(font);

        Button gameMod = Styling.buttonStyling("GAME MOD",-100);
        gameMod.setFont(font);

        Button summary = Styling.buttonStyling("Game Summary",40);
        summary.setFont(font);

        Button exit = Styling.buttonStyling("QUIT GAME", 180);
        exit.setFont(font);

        root.getChildren().add(playAgain);
        root.getChildren().add(gameMod);
        root.getChildren().add(summary);
        root.getChildren().add(exit);


        playAgain.setOnAction(e ->{
            stage.close();
            EventHandler event = new EventHandler();
            event.startGame();
        });
        exit.setOnAction(e ->{
            stage.close();
        });

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
}
