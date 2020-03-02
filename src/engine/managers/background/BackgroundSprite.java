package engine.managers.background;

import engine.things.misc.Sprite;
import javafx.scene.image.Image;

public class BackgroundSprite extends Sprite {


    /** Variables **/
    private int depth;

    /** Constructor **/
    public BackgroundSprite(Image image, float size, int depth,
                            float positionX, float positionY,
                            float velocity, float heading,
                            float rotation) {

        super(image, (float)(size / Math.sqrt(depth)), positionX, positionY, velocity / depth, heading, rotation);
        this.depth = depth;
    }

    /** Methods **/

    /** Getters **/

}
