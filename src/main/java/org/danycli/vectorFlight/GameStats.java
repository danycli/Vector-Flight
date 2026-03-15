package org.danycli.vectorFlight;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameStats {
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),40);
    private long score;
    private long highScore;

    public void gameStats(long scoree, long highScoree){
        
        this.score = scoree;
        this.highScore = highScoree;

        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,450,650);

        Text Score = new Text("CURRENT SCORE = "+score);
        Score.setFont(font);
        Score.setFill(Color.ALICEBLUE);
        Score.setTranslateY(-50);

        Text HighScore = new Text("HIGH SCORE = "+highScore);
        HighScore.setFont(font);
        HighScore.setFill(Color.ALICEBLUE);
        HighScore.setTranslateY(50);

        Button exit = Styling.buttonStyling("CLOSE",150);    
        exit.setFont(font);    

        exit.setOnAction(e ->{
            stage.close();
        });

        root.getChildren().add(Score);
        root.getChildren().add(HighScore);
        root.getChildren().add(exit);

        Image icon = new Image(getClass().getResourceAsStream("/VectorFlight.png"));

        stage.getIcons().add(icon);
        stage.setTitle("Game Stats");
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
