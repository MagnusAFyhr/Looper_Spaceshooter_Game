package engine.managers.foreground;

import engine.managers.background.BackgroundSprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class ForegroundManager {

    // map?
    // meteors
    // npc(s)
    // items

    private double WIDTH, HEIGHT;

    // user Rectangle2D instead for 'updateArea'
    private final int updateRadius = 1800;      // radius, in pixels, around player that need to be updated
    private final int renderRadius = 600;       // radius, in pixels, around player that need to be rendered

    private final int maxObjectCount = 15;      // maximum number of objects in foreground
    private ArrayList<ForegroundSprite> activeObjects;   // array of active foreground objects

    private final int spawnDistance = 700;
    private final int maxItemCount = 50;      // maximum number of objects in foreground
    private final int maxNpcCount = 50;      // maximum number of objects in foreground

    private float maxVelocity = 200;
    private final int managementRadius = 3000;  // radius, in pixels, around player that the
                                                // background manager needs to actively load

    private String[] images = {
            "art/foreground/meteorBrown_big1.png",
            "art/foreground/meteorBrown_big2.png",
            "art/foreground/meteorBrown_big3.png",
            "art/foreground/meteorBrown_big4.png",
            "art/foreground/meteorBrown_med1.png",
            "art/foreground/meteorBrown_med3.png",
            "art/foreground/meteorBrown_small1.png",
            "art/foreground/meteorBrown_small2.png"
    };

    private double focusX, focusY;              // map position of center of player


    /** Constructor **/
    public ForegroundManager(Canvas screen) {

        // define WIDTH & HEIGHT
        this.WIDTH = screen.getWidth();
        this.HEIGHT = screen.getHeight();

        activeObjects = new ArrayList<>();

    }


    /** Methods **/
    public void updateForeground(float elapsed, float focusX, float focusY) {
        // Update foreground objects
        // Check if any foreground objects should be removed (object outside of management radius)
        for (int i = 0; i < activeObjects.size(); i++) {
            ForegroundSprite object = activeObjects.get(i);

            // calculate objects distance to focus point
            float distance = (float) Math.sqrt(Math.pow(focusX - object.getPositionX(), 2) +
                    Math.pow(focusY - object.getPositionY(), 2));

            // if its outside management radius; remove it
            if (distance > managementRadius) {
                activeObjects.remove(object);
            }
        }

        // Check if any foreground objects should be added (new objects in management radius)
        while (activeObjects.size() < maxObjectCount) {
            // create a new foreground object and add it to active objects
            ForegroundSprite newObject = makeForegroundObject(focusX, focusY);
            activeObjects.add(newObject);
        }

        // Update current foreground objects
        for (ForegroundSprite object : activeObjects) {
            object.update(elapsed);
        }
    }

    public void renderForeground(GraphicsContext gc, float focusX, float focusY) {
        // Render foreground objects
        for(ForegroundSprite object: activeObjects)
        {
            object.setRotation(object.getRotation() + object.getRotateVel());
            object.render(gc, (float) (focusX - WIDTH / 2), (float)(focusY - HEIGHT / 2));
        }

    }

    private ForegroundSprite makeForegroundObject(float focusX, float focusY) {

        // choose random foreground image
        int rnd = new Random().nextInt(images.length);
        Image rand_image = new Image(images[rnd]);

        // generate random size
        float size = (float)rand_image.getHeight(); // use image value

        // generate random position
        float rand_posX = (float) ((Math.random() * (managementRadius + 1)) - managementRadius / 2) + focusX;
        float rand_posY = (float) ((Math.random() * (managementRadius + 1)) - managementRadius / 2) + focusY;

        // generate random velocity
        float rand_vel = (float) Math.random() * maxVelocity;
        rand_vel += 30;

        // generate random heading
        float rand_heading = (float) Math.random() * 360;

        // generate random rotation
        float rand_rotate = (float) Math.random() * 50;

        float rotate_vel = (float) Math.random() * 5;

        // return random object
        return new ForegroundSprite(rand_image, size, rand_posX, rand_posY, rand_vel, rand_heading, rand_rotate, rotate_vel);
    }
}
