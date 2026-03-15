package org.danycli.flappyBird;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class EquippedPopUp {
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),45);
    
    public void cautionPopUp(String Text){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,300,200);

        Text text = new Text(Text);
        text.setFont(font);
        text.setFill(Color.WHITE);
        root.getChildren().add(text);


        stage.setTitle("Pop Up");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        root.setStyle("""
            -fx-background-color: #1a5c5ce6;
            -fx-background-radius: 30;
        """);

        stage.show();
        
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> stage.close());
        delay.play();

    }
}
