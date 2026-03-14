package org.danycli.flappyBird;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

public class Styling {
    public static Button buttonStyling(String text, int y){
        Button b = new Button(text);
        b.setTranslateY(y);
        b.setBorder(Border.EMPTY);
        b.setFocusTraversable(false);
        b.setTextFill(Color.WHITE);
        b.setStyle("""
                -fx-background-color: #00000080;
                -fx-background-radius:20;
                """);
        b.setOnMouseExited((MouseEvent t) ->{
            b.setStyle("""
                -fx-background-color: #00000080;
                -fx-background-radius:20;
                """);
            b.setTextFill(Color.WHITE);
        });
        b.setOnMouseEntered((MouseEvent t) -> {
            b.setStyle("""
                    -fx-background-color: #ffffff79;
                    -fx-background-radius:20;
                    """);
            b.setTextFill(Color.BLACK);
        });
        return b;
    }
}
