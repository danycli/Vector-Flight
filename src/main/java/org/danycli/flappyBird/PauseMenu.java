package org.danycli.flappyBird;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PauseMenu {
    

    public void pauseMenu(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,400,550);

        Button resume = Styling.buttonStyling("RESUME", -30);
        resume.setFont(Font.font("Amasis MT Pro Black",30));

        Button exit = Styling.buttonStyling("Quit Game", 30);
        exit.setFont(Font.font("Amasis MT Pro Black",30));

        root.getChildren().add(resume);
        root.getChildren().add(exit);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #00d8fe;
            -fx-background-radius: 20;
        """);
        stage.show();
    }
}
