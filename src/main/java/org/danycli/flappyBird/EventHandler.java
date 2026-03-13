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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EventHandler extends Application {
    private boolean escapePressed;
    private boolean playerBumped;

    @Override
    public void start(Stage stage) throws Exception {
        escapePressed = false;
        playerBumped = false;

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
        stage.fullScreenExitHintProperty().set("Press Esc to pause the game");
        stage.show();

        bg.setFitHeight(stage.getHeight() + 20);
        bg.setFitWidth(stage.getWidth() + 20);
        bk.setFitHeight(stage.getHeight() + 20);
        bk.setFitWidth(stage.getWidth() + 20);
        bk.setTranslateX(stage.getWidth());

        // Obstacles(Pipes)
        ArrayList<Rectangle> allRecs = new ArrayList<>();
        ArrayList<ImageView> obstacles = new ArrayList<>();
        ArrayList<ImageView> obstaclesRemove = new ArrayList<>();
        ArrayList<Rectangle> recRemove = new ArrayList<>();

        Rectangle pipe1 = new Rectangle(210,395);
        pipe1.setVisible(false);
        allRecs.add(pipe1);
        Image downwards = new Image(getClass().getResourceAsStream("/Downwards.png"));
        ImageView down = new ImageView(downwards);
        // down.set
        down.setFitHeight(400);
        down.setTranslateY(0);
        down.setTranslateX(stage.getWidth());
        pipe1.setTranslateY(0);
        pipe1.setTranslateX(stage.getWidth() + 55);
        // down.setPickOnBounds(false);
        obstacles.add(down);

        Rectangle pipe2 = new Rectangle(210,395);
        pipe2.setVisible(false);
        allRecs.add(pipe2);
        Image upwards = new Image(getClass().getResourceAsStream("/Upwards.png"));
        ImageView up = new ImageView(upwards);
        up.setFitHeight(400);
        up.setTranslateY(stage.getHeight() - up.getFitHeight());
        up.setTranslateX(stage.getWidth() - 4);
        pipe2.setTranslateY(stage.getHeight() - pipe2.getHeight());
        pipe2.setTranslateX(stage.getWidth() - 51);
        // up.setPickOnBounds(false);
        obstacles.add(up);

        root.getChildren().add(pipe1);
        root.getChildren().add(pipe2);

        Circle forPlayer = new Circle(30);
        forPlayer.setVisible(false);
        forPlayer.setTranslateX(stage.getWidth() / 2 + 50);
        forPlayer.setTranslateY(stage.getHeight() / 2 +50);
        Image bird = new Image(getClass().getResourceAsStream("/ghost1.png"));
        ImageView player = new ImageView(bird);
        player.setTranslateX(stage.getWidth() / 2);
        player.setTranslateY(stage.getHeight() / 2);

        root.getChildren().add(player);
        root.getChildren().add(forPlayer);
        root.getChildren().add(down);
        root.getChildren().add(up);

        PauseMenu menu = new PauseMenu();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE && !escapePressed) {
                stage.setHeight(stage.getHeight() - 10);
                stage.setWidth(stage.getWidth() - 10);
                stage.setX(0);
                stage.setY(0);
                escapePressed = true;
                menu.pauseMenu();
            } else if (e.getCode() == KeyCode.SPACE && !playerBumped) {
                player.setTranslateY(player.getTranslateY() - 70);
                forPlayer.setTranslateY(forPlayer.getTranslateY() - 70);
            }
        });

        // Loop
        AnimationTimer gameloop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (!playerBumped) {
                    player.setTranslateY(player.getTranslateY() + 3);
                    forPlayer.setTranslateY(forPlayer.getTranslateY() + 3);
                    bg.setTranslateX(bg.getTranslateX() - 3);
                    bk.setTranslateX(bk.getTranslateX() - 3);
                    if (bk.getTranslateX() == 0 && !playerBumped) {
                        bg.setTranslateX(stage.getWidth());
                    } else if (bg.getTranslateX() == 0 && !playerBumped) {
                        bk.setTranslateX(stage.getWidth());
                    }
                    for (ImageView img : obstacles) {
                        img.setTranslateX(img.getTranslateX() - 5);
                        if (img.getTranslateX() < -300) {
                            obstaclesRemove.add(img);
                            root.getChildren().remove(img);
                        }
                    }
                }
                obstacles.removeAll(obstaclesRemove);
                obstaclesRemove.clear();
                for(Rectangle rec : allRecs){
                    rec.setTranslateX(rec.getTranslateX() - 5);
                    if (rec.getTranslateX() < -300) {
                        recRemove.add(rec);
                        root.getChildren().remove(rec);
                    }
                    //Bumping of player in to obstacle
                        if (forPlayer.getBoundsInParent().intersects(rec.getBoundsInParent())) {
                        playerBumped = true;
                        break;
                    }
                }
                allRecs.removeAll(recRemove);
                recRemove.clear();

                Random random = new Random();
                int randomNum = random.nextInt(10, 300);

                if (obstacles.get(obstacles.size() - 1).getTranslateX() <= stage.getWidth() / 2 + randomNum && !playerBumped) {
                    int yAxisOfObstacle = random.nextInt(100,400);
                    yAxisOfObstacle = -yAxisOfObstacle;

                    ImageView addDown = new ImageView(downwards);
                    addDown.setTranslateY(yAxisOfObstacle);
                    addDown.setTranslateX(stage.getWidth());
                    Rectangle pipeDown = new Rectangle(210,650);
                    pipeDown.setVisible(false);
                    pipeDown.setTranslateY(yAxisOfObstacle);
                    pipeDown.setTranslateX(stage.getWidth() + 55);
                    obstacles.add(addDown);
                    allRecs.add(pipeDown);

                    ImageView addUp = new ImageView(upwards);
                    addUp.setTranslateY(stage.getHeight() - addUp.getLayoutBounds().getHeight() + (450 + yAxisOfObstacle));
                    addUp.setTranslateX(stage.getWidth());
                    Rectangle pipeUp = new Rectangle(210,650);
                    pipeUp.setVisible(false);
                    pipeUp.setTranslateY(stage.getHeight() - addUp.getLayoutBounds().getHeight() + (500 + yAxisOfObstacle));
                    pipeUp.setTranslateX(stage.getWidth() + 55);
                    allRecs.add(pipeUp);
                    obstacles.add(addUp);

                    root.getChildren().add(addDown);
                    root.getChildren().add(addUp);
                    root.getChildren().add(pipeDown);
                    root.getChildren().add(pipeUp);
                }
                if (player.getTranslateY() + 70 >= stage.getHeight() && !playerBumped) {
                    playerBumped = true;
                }

                if (playerBumped) {
                    player.setTranslateY(player.getTranslateY() + 5);
                    player.setScaleY(-1);
                }
            }

        };
        gameloop.start();

    }

}
