package engine.things.misc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CollidableSprite extends Sprite {


    /** Constructor **/
    public CollidableSprite(Image image, float size,
                            float positionX, float positionY,
                            float velocity, float heading,
                            float rotation) {

        super(image, size, positionX, positionY, velocity, heading, rotation);
    }

    /** Methods **/
    // public HitBox initHitBox() { }
    public boolean collidesWith(CollidableSprite sprite) {

        // calculate the bounding box for the secondary sprite
        float negX = sprite.getScreenX() - sprite.getImgOffsetX();
        float posX = sprite.getScreenX() + sprite.getImgOffsetX();
        float negY = sprite.getScreenY() - sprite.getImgOffsetY();
        float posY = sprite.getScreenY() + sprite.getImgOffsetY();

        // calculate the bounding box for the main sprite
        float pNegX = this.getScreenX() - this.getImgOffsetX();
        float pPosX = this.getScreenX() + this.getImgOffsetX();
        float pNegY = this.getScreenY() - this.getImgOffsetY();
        float pPosY = this.getScreenY() + this.getImgOffsetY();

        // the code below checks if any of the corners of the secondary sprite enter the bounding box for the main sprite

        // if the bottom right corner of the secondary sprite enters the main sprite box
        if(posX <= pPosX && posX >= pNegX && posY <= pPosY && posY >= pNegY)
        {
            return true;
        }
        // if the top left corner of the secondary sprite enters the main sprite box
        if(negX <= pPosX && negX >= pNegX && negY <= pPosY && negY >= pNegY)
        {
            return true;
        }
        // if the top right corner of the secondary sprite enters the main sprite box
        if(posX <= pPosX && posX >= pNegX && negY <= pPosY && negY >= pNegY)
        {
            return true;
        }
        // if the bottom left corner of the secondary sprite enters the main sprite box
        if(negX <= pPosX && negX >= pNegX && posY <= pPosY && posY >= pNegY)
        {
            return true;
        }


        return false;
    }

    public void applyForce() {

        // get current velocity

        // end by applying velocities
    }

    /** Getters **/
    // get HitBox


    /** Setters **/
    protected void setRotation(float rotation) { super.setRotation(rotation);}
}
