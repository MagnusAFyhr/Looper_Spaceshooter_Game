package engine.managers.foreground;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ForegroundManager {

    // map?
    // meteors
    // npc(s)
    // items

    private double WIDTH, HEIGHT;

    // user Rectangle2D instead for 'updateArea'
    private final int updateRadius = 1800;      // radius, in pixels, around player that need to be updated
    private final int renderRadius = 600;       // radius, in pixels, around player that need to be rendered

    private final int maxObjectCount = 50;      // maximum number of objects in foreground
    private ForegroundSprite[] activeObjects;   // array of active foreground objects

    private final int spawnDistance = 700;
    private final int maxItemCount = 50;      // maximum number of objects in foreground
    private final int maxNpcCount = 50;      // maximum number of objects in foreground


    private double focusX, focusY;              // map position of center of player


    /** Constructor **/
    public ForegroundManager(Canvas screen) {

        // define WIDTH & HEIGHT
        this.WIDTH = screen.getWidth();
        this.HEIGHT = screen.getHeight();

    }


    /** Methods **/
    public void updateForeground(long currentNanoTime) {
        // Update foreground objects

    }

    public void renderForeground(GraphicsContext gc) {
        // Render foreground objects

    }
}
