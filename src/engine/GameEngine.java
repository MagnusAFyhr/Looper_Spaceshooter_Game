package engine;

import com.sun.javaws.Main;
import engine.managers.GameManager;
import engine.managers.scoreboard.ScoreboardManager;
import engine.things.characters.spaceship.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


// when player passes over x = 0, they should then be on x = n

public class GameEngine {

    /** Private Variables **/
    public static float WIDTH, HEIGHT;
    private Stage theStage;
    private GameManager gameManager;

    public static boolean dead = false;


    /** Constructors **/
    public GameEngine(Stage theStage) {
        WIDTH = (float) theStage.getWidth();
        HEIGHT = (float) theStage.getHeight();

        this.theStage = theStage;

        launch();


        theStage.show();
    }

    /** Game Loop **/
    private void launch() {

        // Initialize game manager; background, foreground, scoreboard; and all pertinent objects
        gameManager = new GameManager(theStage);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Update game
                gameManager.update(currentNanoTime);

                // Render game
                gameManager.render(currentNanoTime);

                while(dead)
                {

                    if(ScoreboardManager.score > ScoreboardManager.highScore)
                    {
                        ScoreboardManager.highScore = ScoreboardManager.score;
                    }
                    ScoreboardManager.score = 0;
                    Spaceship.laserObjects.clear();

                    gameManager.playAgain(theStage);
                    dead = false;
                    break;
                }

            }
        }.start();

    }

    /** Methods **/


    /** Getters & Setters
     * **/
    public double getScreenWidth() { return WIDTH; }
    public double getScreenHeight() { return HEIGHT; }
    // public Group getScreenObjects() { return screen.}

}
