package engine.things.misc;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class ThrustTrail extends AnimatedSprite {

	// values to track the ships position
	// these values allow us to rotate around the ship's axis and not the thrust sprite's
	private float shipRotateX;
	private float shipRotateY;

	public ThrustTrail(float size, float positionX, float positionY, float velocity,
			float heading, float rotation, float shipX, float shipY, ArrayList thrustImageList, int animationSpeed) {
		
		super(thrustImageList, size, positionX, positionY, velocity, heading, rotation, animationSpeed);
		// TODO Auto-generated constructor stub
		shipRotateX = shipX;
		shipRotateY = shipY;
	}

	/** Methods **/
	// the method below was override to use the shipRotateX and Y for the rotate function call
	@Override
	public void render(GraphicsContext gc, float focusX, float focusY)
	// Renders the sprite onto the screen
	{
		// calculate screen position
		float screenX = this.getPositionX() - focusX;
		float screenY = this.getPositionY() - focusY;


		gc.save(); // saves the current state on stack, including the current transform
		rotate(gc, this.getRotation(), shipRotateX, shipRotateY);
		gc.drawImage(this.getImage(), screenX - getImgOffsetX(), screenY - getImgOffsetY());
		gc.restore(); // back to original state (before rotation)
	}

	// created to call render and change private variables for rotate
	public void rRender(GraphicsContext gc, float focusX, float focusY, float shipx, float shipy)
	{
		// this is how we render the thrust trail in relation to the ship
		// these two values store the ships coordinates for use in the rotate function
		shipRotateX = shipx;
		shipRotateY = shipy;

		// we call the render method normally
		render(gc, focusX, focusY);
	}



	// these methods are added from the super class so they can be used on the object
	public void setVelocity(float velocity) { super.setVelocity(velocity); }
	public void setHeading(float heading) { super.setHeading(heading); }
	public void setRotation(float rotation) { super.setRotation(rotation); }

}
