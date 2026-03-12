package org.danycli.flappyBird;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EventHandler extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        Image back = new Image(getClass().getResourceAsStream("/Day1.png"));
        ImageView bg = new ImageView(back);
        Image back2 = new Image(getClass().getResourceAsStream("/Day2.png"));
        ImageView bk = new ImageView(back2);
        
        root.getChildren().add(bg);
        root.getChildren().add(bk);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

        stage.setMinHeight(stage.getHeight()-10);
        stage.setMinWidth(stage.getWidth()-10);
        
        bg.setFitHeight(stage.getHeight()+20);
        bg.setFitWidth(stage.getWidth()+20);
        bk.setFitHeight(stage.getHeight()+20);
        bk.setFitWidth(stage.getWidth()+20);
        bk.setTranslateX(stage.getWidth());

        AnimationTimer gameloop = new AnimationTimer(){
            @Override
            public void handle(long now){
                // if (bgs.get(0).getTranslateX() < 0 && bgs.size() < 3) {
                //     ImageView bk = new ImageView(back);
                //     bk.setFitHeight(stage.getHeight()+20);
                //     bk.setFitWidth(stage.getWidth()+20);
                //     ImageView last = bgs.get(bgs.size()-1);
                //     bk.setTranslateX(last.getTranslateX() + last.getFitWidth());
                //     root.getChildren().add(bk);
                //     bgs.add(bk);
                // }
                // for(ImageView im : bgs){
                //     im.setTranslateX(im.getTranslateX() - 5);
                // }
                // if (bgs.get(0).getTranslateX() < -bgs.get(0).getFitWidth()) {
                //     ImageView rem = bgs.get(0);
                //     root.getChildren().remove(rem);
                //     bgs.remove(rem);
                // }
                
                bg.setTranslateX(bg.getTranslateX() - 5);
                bk.setTranslateX(bk.getTranslateX() - 5);
                if (bk.getTranslateX() == 0) {
                    bg.setTranslateX(stage.getWidth());
                }else if (bg.getTranslateX() == 0) {
                    bk.setTranslateX(stage.getWidth());
                }
            }
        };
        gameloop.start();

    }
    
}
