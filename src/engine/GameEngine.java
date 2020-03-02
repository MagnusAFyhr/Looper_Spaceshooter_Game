package engine;

import engine.managers.GameManager;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;


// when player passes over x = 0, they should then be on x = n

public class GameEngine {

    /** Private Variables **/
    public static float WIDTH, HEIGHT;
    private Stage theStage;
    private GameManager gameManager;


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
            }
        }.start();

    }

    /** Methods **/


    /** Getters & Setters **/
    public double getScreenWidth() { return WIDTH; }
    public double getScreenHeight() { return HEIGHT; }
    // public Group getScreenObjects() { return screen.}

}
