package engine.things.misc;

import javafx.scene.image.Image;

public class CollidableSprite extends Sprite {


    /** Variables **/
    // HitBox hitBox

    /** Constructor **/
    public CollidableSprite(Image image, float size,
                            float positionX, float positionY,
                            float velocity, float heading,
                            float rotation) {

        super(image, size, positionX, positionY, velocity, heading, rotation);
    }

    /** Methods **/
    // public HitBox initHitBox() { }
    public void collidesWith(CollidableSprite sprite) {
        // Will need to add a "HitBox" maybe based on image??? use image processing
        // maybe find average radius from center and just make hit box a circle
    }
    public void applyForce() {

        // get current velocity

        // end by applying velocities
    }

    /** Getters **/
    // get HitBox

    /** Setters **/
}
