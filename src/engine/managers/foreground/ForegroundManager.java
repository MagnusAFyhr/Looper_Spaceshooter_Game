package engine.managers.foreground;

import engine.managers.background.BackgroundSprite;
import engine.things.characters.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class ForegroundManager {

    // map?
    // meteors
    // npc(s)
    // items

    private double WIDTH, HEIGHT;

    // user Rectangle2D instead for 'updateArea'
    private final int spawnRadius, updateRadius, renderRadius;

    private final int maxObjectCount = 15;      // maximum number of objects in foreground
    private ArrayList<ForegroundSprite> activeObjects;   // array of active foreground objects

    private final int maxItemCount = 300;      // maximum number of objects in foreground

    private float innacuracy = 50;
    private float maxVelocity = 500;
    private float minVelocity = 200;
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
        WIDTH = screen.getWidth();
        HEIGHT = screen.getHeight();

        spawnRadius = (int) WIDTH;
        updateRadius = (int) (WIDTH * 1.2);
        renderRadius = (int) WIDTH;

        activeObjects = new ArrayList<>();

    }


    /** Methods **/
    public void updateForeground(Player p, float elapsed, float focusX, float focusY) {
        // Update foreground objects
        // Check if any foreground objects should be removed (object outside of management radius)
        for (int i = 0; i < activeObjects.size(); i++) {
            ForegroundSprite object = activeObjects.get(i);

            // calculate objects distance to focus point
            float distance = (float) Math.sqrt(Math.pow(focusX - object.getPositionX(), 2) +
                    Math.pow(focusY - object.getPositionY(), 2));

            // if its outside management radius; remove it
            if (distance > updateRadius) {
                activeObjects.set(i, makeForegroundObject(focusX, focusY));

            } else {

                for (int j = i; j < activeObjects.size(); j++) {

                    object.setRotation(object.getRotation() + object.getRotateVel());

                    // check if player hits asteroid
                    if (p.collidesWith(object)) {
                        activeObjects.set(i, makeForegroundObject(focusX, focusY));
                        // TODO: ADD HEALTH HERE
                    }

                    // check if player bullet hits asteroid
                    // TODO: LASER COLLISIONS
                }
            }

        }

        // Check if any foreground objects should be added (new objects in management radius)
        if (activeObjects.size() < maxObjectCount) {
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
            float distance = (float) Math.sqrt(Math.pow(focusX - object.getPositionX(), 2) +
                    Math.pow(focusY - object.getPositionY(), 2));

            if (distance < renderRadius)
                object.render(gc, focusX, focusY);

        }

    }

    private ForegroundSprite makeForegroundObject(float focusX, float focusY) {

        // choose random foreground image
        int rnd = new Random().nextInt(images.length);
        Image rand_image = new Image(images[rnd]);

        // generate random size
        float size = (float) rand_image.getHeight(); // use image value

        // generate random heading
        float rand_heading = (float) Math.random() * 360;

        // generate random position
        float rand_posX = (float) (focusX + WIDTH/2 + (spawnRadius * Math.cos(Math.toRadians(rand_heading))));
        float rand_posY = (float) (focusY + HEIGHT/2 + (spawnRadius * Math.sin(Math.toRadians(rand_heading))));

        rand_heading += 180 + (float) (-innacuracy + Math.random() * (innacuracy * 2));

        // generate random velocity
        float rand_vel = (float) (minVelocity + Math.random() * (maxVelocity - minVelocity));

        // generate random rotation
        float rand_rotate = (float) Math.random() * 50;

        float rotate_vel = (float) Math.random() * 4;

        // return random object
        return new ForegroundSprite(rand_image, size, rand_posX, rand_posY, rand_vel, rand_heading, rand_rotate, rotate_vel);
    }
}
