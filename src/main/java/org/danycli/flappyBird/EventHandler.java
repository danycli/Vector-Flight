package org.danycli.flappyBird;

import java.util.ArrayList;
import java.util.Random;

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

        //Background
        Image back = new Image(getClass().getResourceAsStream("/Day1.png"));
        ImageView bg = new ImageView(back);
        Image back2 = new Image(getClass().getResourceAsStream("/Day2.png"));
        ImageView bk = new ImageView(back2);
        
        root.getChildren().add(bg);
        root.getChildren().add(bk);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
        stage.setMinHeight(stage.getHeight()-20);
        stage.setMinWidth(stage.getWidth()-20);
        
        bg.setFitHeight(stage.getHeight()+20);
        bg.setFitWidth(stage.getWidth()+20);
        bk.setFitHeight(stage.getHeight()+20);
        bk.setFitWidth(stage.getWidth()+20);
        bk.setTranslateX(stage.getWidth());

        //Obstacles(Pipes)
        ArrayList<ImageView> obstacles = new ArrayList<>();
        ArrayList<ImageView> toRemove = new ArrayList<>();

        Image downwards = new Image(getClass().getResourceAsStream("/Downwards.png"));
        ImageView down = new ImageView(downwards);
        // down.set
        down.setFitHeight(400);
        down.setTranslateY(0);
        down.setTranslateX(stage.getWidth());
        obstacles.add(down);

        Image upwards = new Image(getClass().getResourceAsStream("/Upwards.png"));
        ImageView up = new ImageView(upwards);
        up.setFitHeight(400);
        up.setTranslateY(stage.getHeight() - up.getFitHeight());
        up.setTranslateX(stage.getWidth() - 4);
        obstacles.add(up);

        root.getChildren().add(down);
        root.getChildren().add(up);

        //Loop
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

                bg.setTranslateX(bg.getTranslateX() - 3);
                bk.setTranslateX(bk.getTranslateX() - 3);
                if (bk.getTranslateX() == 0) {
                    bg.setTranslateX(stage.getWidth());
                }else if (bg.getTranslateX() == 0) {
                    bk.setTranslateX(stage.getWidth());
                }

                // root.getChildren().add(down);
                // root.getChildren().add(up);
                for(ImageView img : obstacles){
                    img.setTranslateX(img.getTranslateX() - 3);
                    if (img.getTranslateX() < -300) {
                        toRemove.add(img);
                        // obstacles.remove(img);
                        root.getChildren().remove(img);
                    }
                }
                obstacles.removeAll(toRemove);
                toRemove.clear();

                Random random = new Random();
                int randomNum = random.nextInt(25,101);

                if (randomNum % 50 == 0 && obstacles.size() <= 6) {
                    int randomInt = random.nextInt(0,500);
                    ImageView addDown = new ImageView(downwards);
                    addDown.setFitHeight(400);
                    addDown.setTranslateY(0);
                    addDown.setTranslateX(stage.getWidth() + randomInt);
                    obstacles.add(addDown);

                    ImageView addUp = new ImageView(upwards);
                    addUp.setFitHeight(400);
                    addUp.setTranslateY(stage.getHeight() - addUp.getFitHeight());
                    addUp.setTranslateX(stage.getWidth() - 4 + randomInt);
                    obstacles.add(addUp);

                    root.getChildren().add(addDown);
                    root.getChildren().add(addUp);
                }

            }

        };
        gameloop.start();

    }
    
}
