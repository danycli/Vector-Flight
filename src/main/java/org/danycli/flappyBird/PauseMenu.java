package org.danycli.flappyBird;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseMenu {
    private boolean quitGame;
    private boolean resumeGamee;
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),50);

    public void pauseMenu(){
        quitGame = false;
        resumeGamee = true;

        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,400,550);

        Button resume = Styling.buttonStyling("RESUME", -70);
        resume.setFont(font);

        Button exit = Styling.buttonStyling("QUIT GAME", 70);
        exit.setFont(font);

        root.getChildren().add(resume);
        root.getChildren().add(exit);

        resume.setOnAction(e ->{
            stage.close();
            resumeGamee = false;
        });
        exit.setOnAction(e ->{
            stage.close();
            quitGame = true;
        });

        stage.setTitle("Pause Menu");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #0f3737e0;
            -fx-background-radius: 20;
        """);
        stage.show();
    }
    public boolean closeGame(){
        return quitGame;
    }
    public boolean resumeGame(){
        return resumeGamee;
    }
}
