package engine.things.misc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Sprite {

    /** Variables **/
    private Image image;
    private float size;
    private float imgOffsetX, imgOffsetY;

    private float px, py;                  // map position
    private float screenX, screenY;

    private float velocity;
    private float heading;
    private float rotation;

    /** Constructor **/
    public Sprite(Image image, float size,
                  float positionX, float positionY,
                  float velocity, float heading,
                  float rotation) {

        this.image = image;
        this.size = size;
        this.imgOffsetX = (float) image.getWidth() / 2;
        this.imgOffsetY = (float) image.getHeight() / 2;

        this.px = positionX;
        this.py = positionY;
        this.velocity = velocity;
        this.heading = heading;
        this.rotation = rotation;

    }


    /** Methods **/
    public void update(float deltaTime)
    // Updates the position of the sprite
    {
        px += velocity * Math.cos(Math.toRadians(heading)) * deltaTime;
        py += velocity * Math.sin(Math.toRadians(heading)) * deltaTime;
    }

    public void render(GraphicsContext gc, float focusX, float focusY)
    // Renders the sprite onto the screen
    {
        // calculate screen position
        screenX = px - focusX;
        screenY = py - focusY;

        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, rotation, screenX, screenY);
        gc.drawImage(image, screenX - imgOffsetX, screenY - imgOffsetY);
        gc.restore(); // back to original state (before rotation)
    }

    protected void rotate(GraphicsContext gc, float angle, float px, float py) {
        Rotate r = new Rotate(angle + 90, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /** Getters **/
    public Image getImage() { return image; }
    public float getPositionX() { return px; }
    public float getPositionY() { return py; }
    public float getImgOffsetX() { return imgOffsetX; }
    public float getImgOffsetY() { return imgOffsetY; }
    public float getScreenX() { return screenX; }
    public float getScreenY() { return screenY; }
    public float getVelocity() { return velocity; }
    public float getHeading() { return heading; }
    public float getRotation() { return rotation; }


    /** Setters **/
    protected void setImage(Image image) { this.image = image; }
    protected void setPositionX(float positionX) { px = positionX; }
    protected void setPositionY(float positionY) { py = positionY; }
    protected void setVelocity(float velocity) { this.velocity = velocity; }
    protected void setHeading(float heading) { this.heading = heading; }
    protected void setRotation(float rotation) { this.rotation = rotation; }

}
