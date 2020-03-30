package engine.managers.scoreboard;

import engine.managers.foreground.ForegroundSprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import engine.things.characters.Player;
import javafx.scene.text.Font;

import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * This class will display real-time game stats of the player
 *  - lives
 *  - boost
 *  - health
 *  - shield
 *  - credits
 *  - score
 *  - etc.
 *
 * Should be displayed on the top and bottom edges of the screen using game icons; positioned appropriately
 *
 */

public class ScoreboardManager {

    private double WIDTH, HEIGHT;
    public static int score;
    public static int highScore;


    public ScoreboardManager(Canvas screen) {
        WIDTH = screen.getWidth();
        HEIGHT = screen.getHeight();
        score = 0;
        highScore = 0;
    }

    /** Methods **/


    public void renderScoreboard(GraphicsContext gc, Player a) {
        // Render foreground objects
        gc.setFill(Color.WHITESMOKE);
        gc.fillText("Score: " + score, a.getScreenX()-(WIDTH/2)+5, a.getScreenY()-(HEIGHT/2)+15);
        gc.fillText("High Score: " + highScore, a.getScreenX() + (WIDTH/2)-150, a.getScreenY()-(HEIGHT/2)+15);
    }

}
