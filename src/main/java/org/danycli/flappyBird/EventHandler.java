package org.danycli.flappyBird;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class EventHandler extends Application {
    private boolean escapePressed;
    private boolean playerBumped = false;

    @Override
    public void start(Stage stage) throws Exception {
        escapePressed = false;
        // playerBumped = false;

        Group root = new Group();
        Scene scene = new Scene(root);

        // Background
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

        bg.setFitHeight(stage.getHeight() + 20);
        bg.setFitWidth(stage.getWidth() + 20);
        bk.setFitHeight(stage.getHeight() + 20);
        bk.setFitWidth(stage.getWidth() + 20);
        bk.setTranslateX(stage.getWidth());

        // Obstacles(Pipes)
        ArrayList<ImageView> obstacles = new ArrayList<>();
        ArrayList<ImageView> toRemove = new ArrayList<>();

        Image downwards = new Image(getClass().getResourceAsStream("/Downwards.png"));
        ImageView down = new ImageView(downwards);
        // down.set
        down.setFitHeight(400);
        down.setTranslateY(0);
        down.setTranslateX(stage.getWidth());
        // down.setPickOnBounds(false);
        obstacles.add(down);

        Image upwards = new Image(getClass().getResourceAsStream("/Upwards.png"));
        ImageView up = new ImageView(upwards);
        up.setFitHeight(400);
        up.setTranslateY(stage.getHeight() - up.getFitHeight());
        up.setTranslateX(stage.getWidth() - 4);
        // up.setPickOnBounds(false);
        obstacles.add(up);

        root.getChildren().add(down);
        root.getChildren().add(up);

        Image bird = new Image(getClass().getResourceAsStream("/ghost1.png"));
        ImageView player = new ImageView(bird);
        player.setTranslateX(stage.getWidth() / 2);
        player.setTranslateY(stage.getHeight() / 2);
        // player.setPickOnBounds(false);
        // player.setStyle("""
        //         -fx-background-image: url("/ghost1.png");
        //         -fx-background-repeat: no-repeat;
        //         -fx-shape: "M 0 0 L 100 0 L 100 100 L 0 100 Z";
        //         -fx-scale-shape: true;
        //         """);

        root.getChildren().add(player);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE && !escapePressed) {
                stage.setHeight(stage.getHeight() - 10);
                stage.setWidth(stage.getWidth() - 10);
                stage.setX(0);
                stage.setY(0);
                escapePressed = true;
            } else if (e.getCode() == KeyCode.SPACE && !playerBumped) {
                player.setTranslateY(player.getTranslateY() - 70);
            }
        });

        // Loop
        AnimationTimer gameloop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (!playerBumped) {
                    player.setTranslateY(player.getTranslateY() + 2);
                    bg.setTranslateX(bg.getTranslateX() - 3);
                    bk.setTranslateX(bk.getTranslateX() - 3);
                    if (bk.getTranslateX() == 0 && !playerBumped) {
                        bg.setTranslateX(stage.getWidth());
                    } else if (bg.getTranslateX() == 0 && !playerBumped) {
                        bk.setTranslateX(stage.getWidth());
                    }
                    for (ImageView img : obstacles) {
                        img.setTranslateX(img.getTranslateX() - 3);
                        if (img.getTranslateX() < -300) {
                            toRemove.add(img);
                            root.getChildren().remove(img);
                        }
                        //Bumping of player in to obstacle
                        if (player.getBoundsInParent().intersects(img.getBoundsInParent())) {
                        playerBumped = true;
                        break;
                    }
                    }
                }
                obstacles.removeAll(toRemove);
                toRemove.clear();

                Random random = new Random();
                int randomNum = random.nextInt(10, 300);

                if (obstacles.get(obstacles.size() - 1).getTranslateX() <= stage.getWidth() / 2 + randomNum && !playerBumped) {
                    // int randomInt = random.nextInt(0,500);
                    ImageView addDown = new ImageView(downwards);
                    addDown.setFitHeight(400);
                    addDown.setTranslateY(0);
                    addDown.setTranslateX(stage.getWidth());
                    obstacles.add(addDown);

                    ImageView addUp = new ImageView(upwards);
                    addUp.setFitHeight(400);
                    addUp.setTranslateY(stage.getHeight() - addUp.getFitHeight());
                    addUp.setTranslateX(stage.getWidth() - 4);
                    obstacles.add(addUp);

                    root.getChildren().add(addDown);
                    root.getChildren().add(addUp);
                }
                if (player.getTranslateY() + 70 >= stage.getHeight() && !playerBumped) {
                    playerBumped = true;
                }
            }

        };
        gameloop.start();

    }

}
