package engine.managers.background;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class BackgroundManager {

    /** Class Variables **/
    private double WIDTH, HEIGHT;

    // user Rectangle2D instead for 'updateArea'

    private final int managementRadius = 1000;   // radius, in pixels, around player that the
                                                    // background manager needs to actively load
    private final int maxObjectCount = 20;      // maximum number of objects in active background
    private ArrayList<BackgroundSprite> activeObjects;   // array of active background objects

    private int minDepth = 1;
    private int maxDepth = 100;
    private float maxVelocity = 100;
    private String[] images = {
            "art/background/star1.png",
            "art/background/star2.png",
            "art/background/star3.png"
    };


    /** Constructor **/
    public BackgroundManager(Canvas screen) { // double focusX, double focusY

        // define WIDTH & HEIGHT
        this.WIDTH = screen.getWidth();
        this.HEIGHT = screen.getHeight();


        // Create update region

        // Create render region

        // Initialize array
        activeObjects = new ArrayList<>();

    }

    /** Methods **/
    public void updateBackground(float elapsed, float focusX, float focusY) {

        // Check if any background objects should be removed (object outside of management radius)
        for (int i = 0; i < activeObjects.size(); i++) {
            BackgroundSprite object = activeObjects.get(i);

            // calculate objects distance to focus point
            float distance = (float) Math.sqrt(Math.pow(focusX - object.getPositionX(), 2) +
                    Math.pow(focusY - object.getPositionY(), 2));

            // if its outside management radius; remove it
            if (distance > managementRadius) {
                activeObjects.remove(object);
            }
        }

        // Check if any background objects should be added (new objects in management radius)
        while (activeObjects.size() < maxObjectCount) {
            // create a new background object and add it to active objects
            BackgroundSprite newObject = makeBackgroundObject(focusX, focusY);
            activeObjects.add(newObject);
        }

        // Update current background objects
        for (BackgroundSprite object : activeObjects) {
            object.update(elapsed);
        }
    }

    public void renderBackground(GraphicsContext gc, float focusX, float focusY) {
        // Check if background objects should be rendered (object inside view area)
        for (BackgroundSprite object: activeObjects) {

            // if its outside view area; do not render it

            // if its inside view area; render it
            object.render(gc, (float) (focusX - WIDTH / 2), (float)(focusY - HEIGHT / 2));
        }

    }

    private BackgroundSprite makeBackgroundObject(float focusX, float focusY) {
        /**
         Image image,
         float size, int depth,
         float positionX, float positionY,
         float velocity, float heading,
         float rotation
         **/

        // choose random background image
        int rnd = new Random().nextInt(images.length);
        Image rand_image = new Image(images[rnd]);

        // generate random size
        float size = 1.0f;

        // generate random depth
        int rand_depth = (int) (Math.random() * (maxDepth - minDepth + 1)) + minDepth;

        // generate random position
        float rand_posX = (float) ((Math.random() * (managementRadius + 1)) - managementRadius / 2) + focusX;
        float rand_posY = (float) ((Math.random() * (managementRadius + 1)) - managementRadius / 2) + focusY;

        // generate random velocity
        float rand_vel = (float) Math.random() * maxVelocity;

        // generate random heading
        float rand_heading = (float) Math.random() * 360;

        // return random object
        return new BackgroundSprite(rand_image, size, rand_depth,
                rand_posX, rand_posY,
                rand_vel, rand_heading,
                0);

    }

}
