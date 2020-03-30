package engine.managers;

import engine.GameEngine;
import engine.managers.background.BackgroundManager;
import engine.managers.foreground.ForegroundManager;
import engine.managers.performance.PerformanceManager;
import engine.managers.player.PlayerManager;
import engine.managers.scoreboard.ScoreboardManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class GameManager {

    /** Class Variables **/
    static Scene theScene;
    private Canvas screen;

    private PlayerManager playerManager;
    private BackgroundManager backgroundManager;
    private ForegroundManager foregroundManager;
    private ScoreboardManager scoreboardManager;
    private PerformanceManager performanceManager;


    private Media bgS;
    private MediaPlayer bgSPlayer;
    MediaView mediaView;


    /** Constructor **/
    public GameManager(Stage theStage) {

        // Initialize mediastuff
        bgS = new Media(Paths.get("src/SFX/TG.mp3").toUri().toString());
        bgSPlayer = new MediaPlayer(bgS);
        mediaView = new MediaView(bgSPlayer);


        // Initialize Screen
        Group root = new Group(mediaView);
        theScene = new Scene( root );
        theStage.setScene( theScene );

        // create keyboard input handler
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) ->
            handleKeyPressed(key.getCode().toString())
        );
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, (key) ->
            handleKeyReleased(key.getCode().toString())
        );

        screen = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add( screen );


        // Initialize player manager
        playerManager = new PlayerManager(screen);

        // Initialize background manager
        backgroundManager = new BackgroundManager(screen);

        // Initialize foreground manager
        foregroundManager = new ForegroundManager(screen);

        // Initialize scoreboard manager
        scoreboardManager = new ScoreboardManager(screen);

        // Initialize performance manager
        performanceManager = new PerformanceManager();

        bgSPlayer.play();

    }

    /** Methods **/
    public void update(long currentNanoTime) {
            // Ask performance manager permission to update
            if (performanceManager.canUpdate()) {
                float elapsed = performanceManager.startUpdate(currentNanoTime);

                // Update player
                playerManager.updatePlayer(elapsed);

                // Update background
                backgroundManager.updateBackground(elapsed,
                        playerManager.player.getPositionX(), playerManager.player.getPositionY());

                // Update foreground
                foregroundManager.updateForeground(playerManager.player, elapsed,
                        playerManager.player.getPositionX(), playerManager.player.getPositionY());

                performanceManager.stopUpdate();
            }
    }

    public void render(long currentNanoTime) {

        GraphicsContext gc = screen.getGraphicsContext2D();

        // Ask performance manager permission to render
        if (performanceManager.canRender()) {
            performanceManager.startRender(currentNanoTime);

            // Clear the canvas
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, theScene.getWidth(), theScene.getHeight());

            // Render background objects
            backgroundManager.renderBackground(gc,
                    playerManager.player.getPositionX(), playerManager.player.getPositionY());

            // Render foreground objects
            // Pass the player into the foreground render method for asteroid collision
            foregroundManager.renderForeground(gc,
                    playerManager.player.getPositionX(), playerManager.player.getPositionY());

            // Render player
            playerManager.renderPlayer(gc);

            // Render scoreboard objects
            scoreboardManager.renderScoreboard(gc, playerManager.player);

            // Optional : Render performance objects (Calculate FPS, ticks, etc.)

            performanceManager.stopRender();
        }

    }

    public void handleKeyPressed(String code) {

        // determine type of event

        // determine if this is player input
        boolean playerInput = playerManager.handleKeyPressed(code);


        System.out.println("Key Pressed : " + code);

        if (code.equals("I")) {
            playerManager.printStatus();
        }

    }
    public void handleKeyReleased(String code) {

        // determine type of event

        // determine if this is player input
        boolean playerInput = playerManager.handleKeyReleased(code);

        System.out.println("Key Released : " + code);

        if (code.equals("I")) {
            playerManager.printStatus();
        }
        if (code.equals("SPACE"))
        {
            playerManager.player.shootbullet();
        }

    }


    public void playAgain(Stage theStage) {

        // Initialize Screen
        Group root = new Group();
        theScene = new Scene( root );
        theStage.setScene( theScene );

        screen = new Canvas( theStage.getWidth(), theStage.getHeight() );
        root.getChildren().add( screen );


        // create keyboard input handler
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) ->
                handleKeyPressed(key.getCode().toString())
        );
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, (key) ->
                handleKeyReleased(key.getCode().toString())
        );

        // Initialize player manager
        playerManager = new PlayerManager(screen);

        // Initialize background manager
        backgroundManager = new BackgroundManager(screen);

        // Initialize foreground manager
        foregroundManager = new ForegroundManager(screen);

        // Don't Initialize scoreboard manager
        // scoreboardManager = new ScoreboardManager();

        // Initialize performance manager
        performanceManager = new PerformanceManager();

    }

}
