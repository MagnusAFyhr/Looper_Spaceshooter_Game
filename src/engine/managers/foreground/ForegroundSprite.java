package engine.managers.foreground;

import engine.things.misc.CollidableSprite;
import javafx.scene.image.Image;

public class ForegroundSprite extends CollidableSprite {

    /** Variables **/

    /** Constructor **/
    public ForegroundSprite(Image image, float size,
                            float positionX, float positionY,
                            float velocity, float heading,
                            float rotation) {

        super(image, size, positionX, positionY, velocity, heading, rotation);
    }


    /** Methods **/

    /** Getters **/

    /** Setters **/
}
