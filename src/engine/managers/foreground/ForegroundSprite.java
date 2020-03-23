package engine.managers.foreground;

import engine.things.misc.CollidableSprite;
import javafx.scene.image.Image;

public class ForegroundSprite extends CollidableSprite {

    /** Variables **/
    // this is for a constant rotation and is not used by the class itself
    private float rotateVel;

    /** Constructor **/
    public ForegroundSprite(Image image, float size,
                            float positionX, float positionY,
                            float velocity, float heading,
                            float rotation, float rotateVel) {

        super(image, size, positionX, positionY, velocity, heading, rotation);

        this.rotateVel = rotateVel;
    }


    /** Methods **/

    /** Getters **/
    protected float getRotateVel() { return rotateVel; }

    /** Setters **/
    protected void setRotation(float rotation) { super.setRotation(rotation);}
}
