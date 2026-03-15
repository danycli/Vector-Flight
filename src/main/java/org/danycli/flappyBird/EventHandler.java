package org.danycli.flappyBird;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventHandler extends Application {
    private boolean playerBumped;
    private boolean pauseTheGame;
    private final PauseMenu menu = new PauseMenu();
    private long score;
    private final Font font = Font.loadFont(getClass().getResourceAsStream("/Font/Linkara.otf"),50);
    private AnimationTimer gameloop;
    private String gameMod;
    private long highScore;
    private double PlayerSpeedDownwards;
    private double PlayerSpeedUpwards;
    private boolean goUp;

    @Override
    public void start(Stage args0) throws Exception{
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMENU(EventHandler.this);
    }
    @SuppressWarnings("ConvertToTryWithResources")
    public void startGame(){
        playerBumped = false;
        pauseTheGame = false;
        goUp = false;
        PlayerSpeedUpwards = 1;
        score = 0;
        PlayerSpeedDownwards = 0;
        highScore = 0;
        if (gameMod == null) {
            gameMod = "Heaven";
        }

        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);

        //Getting HighScore
        try {
            File file = SaveManager.getStatsFile();
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("Score = "+score);
                writer.close();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = read.readLine();
            if (line != null) {
                String[] sp = line.split(" ");
                highScore = Integer.parseInt(sp[sp.length-1]);
                read.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong wile fetching high score");
        }


        // Background
        Image back = new Image(getClass().getResourceAsStream("/"+gameMod+"/"+gameMod+"1.png"));
        ImageView bg = new ImageView(back);
        Image back2 = new Image(getClass().getResourceAsStream("/"+gameMod+"/"+gameMod+"2.png"));
        ImageView bk = new ImageView(back2);
        
        Text scoreBoard = new Text("SCORE = "+score);
        scoreBoard.setTranslateX(10);
        scoreBoard.setTranslateY(50);
        scoreBoard.setFont(font);
        scoreBoard.setFill(Color.ALICEBLUE);

        Rectangle scoreRectangle = new Rectangle();
        scoreRectangle.setStyle(
            "-fx-fill: #00d8fe7b;" +
            "-fx-arc-width: 30;" +
            "-fx-arc-height: 30;"
        );
        scoreRectangle.setWidth(scoreBoard.getLayoutBounds().getWidth() + 15);
        scoreRectangle.setHeight(scoreBoard.getLayoutBounds().getHeight() + 10);
        scoreRectangle.setTranslateX(0);
        scoreRectangle.setTranslateY(0);

        Text HighScoreBoard = new Text("HIGH SCORE = "+highScore);
        HighScoreBoard.setTranslateY(50);
        HighScoreBoard.setFont(font);
        HighScoreBoard.setFill(Color.ALICEBLUE);

        Rectangle HighScoreRectangle = new Rectangle();
        HighScoreRectangle.setStyle(
            "-fx-fill: #00d8fe7b;" +
            "-fx-arc-width: 30;" +
            "-fx-arc-height: 30;"
        );
        HighScoreRectangle.setWidth(HighScoreBoard.getLayoutBounds().getWidth() + 15);
        HighScoreRectangle.setHeight(HighScoreBoard.getLayoutBounds().getHeight() + 10);
        HighScoreRectangle.setTranslateY(0);

        root.getChildren().add(bg);
        root.getChildren().add(bk);
        root.getChildren().add(scoreRectangle);
        root.getChildren().add(scoreBoard);
        root.getChildren().add(HighScoreBoard);
        root.getChildren().add(HighScoreRectangle);

        stage.setTitle("Vector Flight");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.fullScreenExitHintProperty().set("Press Esc to pause the game");
        stage.setMinHeight(stage.getHeight() - 10);
        stage.setMinWidth(stage.getWidth() - 10);
        stage.show();

        HighScoreBoard.setTranslateX(stage.getWidth() - (HighScoreBoard.getLayoutBounds().getWidth() + 15));
        HighScoreRectangle.setTranslateX(stage.getWidth() - (HighScoreRectangle.getLayoutBounds().getWidth() + 15));

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
        Image downwards = new Image(getClass().getResourceAsStream("/"+gameMod+"/"+gameMod+"Downwards.png"));
        ImageView down = new ImageView(downwards);
        // down.set
        down.setFitHeight(400);
        down.setTranslateY(0);
        down.setTranslateX(stage.getWidth());
        pipe1.setTranslateY(0);
        pipe1.setTranslateX(stage.getWidth() + 55);
        obstacles.add(down);

        Rectangle pipe2 = new Rectangle(210,395);
        pipe2.setVisible(false);
        allRecs.add(pipe2);
        Image upwards = new Image(getClass().getResourceAsStream("/"+gameMod+"/"+gameMod+"Upwards.png"));
        ImageView up = new ImageView(upwards);
        up.setFitHeight(400);
        up.setTranslateY(stage.getHeight() - up.getFitHeight());
        up.setTranslateX(stage.getWidth());
        pipe2.setTranslateY(stage.getHeight() - pipe2.getHeight());
        pipe2.setTranslateX(stage.getWidth() + 55);
        up.setPickOnBounds(false);
        obstacles.add(up);

        root.getChildren().add(pipe1);
        root.getChildren().add(pipe2);

        Circle forPlayer = new Circle(40);
        forPlayer.setVisible(false);
        forPlayer.setTranslateX(stage.getWidth() / 2 + 50);
        forPlayer.setTranslateY(stage.getHeight() / 2 +50);
        Image bird = new Image(getClass().getResourceAsStream("/"+gameMod+"/"+gameMod+"Ghost.png"));
        ImageView player = new ImageView(bird);
        player.setTranslateX(stage.getWidth() / 2);
        player.setTranslateY(stage.getHeight() / 2);

        root.getChildren().add(player);
        root.getChildren().add(forPlayer);
        root.getChildren().add(down);
        root.getChildren().add(up);

        // PauseMenu menu = new PauseMenu();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.setX(0);
                stage.setY(0);
                pauseTheGame = true;
                menu.pauseMenu();
                bg.setFitHeight(stage.getHeight() + 20);
                bg.setFitWidth(stage.getWidth() + 20);
                bk.setFitHeight(stage.getHeight() + 20);
                bk.setFitWidth(stage.getWidth() + 20);
            } else if (e.getCode() == KeyCode.SPACE && !playerBumped) {
                PlayerSpeedUpwards = 0;
                PlayerSpeedDownwards = 0;
                goUp = true;
            } 
        });

        // Loop
        gameloop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (!pauseTheGame) {
                stage.setFullScreen(true);
            }

                if (!playerBumped && !pauseTheGame) {
                    //player speed moving downwards
                    if (PlayerSpeedDownwards <= 3 && !goUp) {
                        PlayerSpeedDownwards += 0.1;
                        player.setTranslateY(player.getTranslateY() + PlayerSpeedDownwards);
                        forPlayer.setTranslateY(forPlayer.getTranslateY() + PlayerSpeedDownwards);
                    }else if(goUp){
                        PlayerSpeedDownwards += 1;
                        player.setTranslateY(player.getTranslateY() - PlayerSpeedDownwards);
                        forPlayer.setTranslateY(forPlayer.getTranslateY() - PlayerSpeedDownwards);
                        PlayerSpeedUpwards ++;
                        if (PlayerSpeedUpwards >= 12) {
                            goUp = false;
                        }
                    }
                    else{
                        player.setTranslateY(player.getTranslateY() + 3);
                        forPlayer.setTranslateY(forPlayer.getTranslateY() + 3);
                    }
                    bg.setTranslateX(bg.getTranslateX() - 3);
                    bk.setTranslateX(bk.getTranslateX() - 3);
                    if (bk.getTranslateX() == 0 && !playerBumped) {
                        bg.setTranslateX(stage.getWidth());
                    } else if (bg.getTranslateX() == 0 && !playerBumped) {
                        bk.setTranslateX(stage.getWidth());
                    }
                    for (ImageView img : obstacles) {
                        img.setTranslateX(img.getTranslateX() - 5);
                        scoreRectangle.toFront();
                        scoreBoard.toFront();
                        HighScoreRectangle.toFront();
                        HighScoreBoard.toFront();
                        if (img.getTranslateX() < -300) {
                            obstaclesRemove.add(img);
                            root.getChildren().remove(img);
                        }
                    }
                    for(Rectangle rec : allRecs){
                    rec.setTranslateX(rec.getTranslateX() - 5);
                    //Incrementing Score
                if (rec.getTranslateX() == stage.getWidth()/2 - 50) {
                    score++;
                    scoreBoard.setText("SCORE = "+score/2);
                    scoreRectangle.setWidth(scoreBoard.getLayoutBounds().getWidth() + 15);
                    scoreRectangle.setHeight(scoreBoard.getLayoutBounds().getHeight() + 10);
                }
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
                }
                obstacles.removeAll(obstaclesRemove);
                obstaclesRemove.clear();
                allRecs.removeAll(recRemove);
                recRemove.clear();

                Random random = new Random();
                int randomNum = random.nextInt(10, 300);

                if (obstacles.get(obstacles.size() - 1).getTranslateX() <= stage.getWidth() / 2 + randomNum && !playerBumped && !pauseTheGame) {
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
                if (player.getTranslateY() + 70 >= stage.getHeight() && !playerBumped && !pauseTheGame) {
                    playerBumped = true;
                }

                if (playerBumped) {
                    player.setTranslateY(player.getTranslateY() + 5);
                    player.setScaleY(-1);
                }

                if (menu.closeGame()) {
                stage.close();
            }
            pauseTheGame = menu.resumeGame();
            if (playerBumped && player.getTranslateY() >= stage.getHeight()) {
                score /= 2;
                CheckHighScore();
                GameOver over = new GameOver();
                over.gameOver(score,highScore);
                stage.close();
                gameloop.stop();
            }
            }
        };
        gameloop.start();
    }
    public void setMod(String mod){
        gameMod = mod;
    }
    @SuppressWarnings("ConvertToTryWithResources")
    private void CheckHighScore(){
        try {
            File file = SaveManager.getStatsFile();
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("Score = "+score);
                writer.close();
            }
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = read.readLine();
            if (line != null) {
                if (highScore < score) {
                    highScore = score;
                    System.out.println("High Score = "+highScore);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write("Score = "+highScore);
                    writer.close();
                }
                read.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong wile setting high score");
        }
    }
}
