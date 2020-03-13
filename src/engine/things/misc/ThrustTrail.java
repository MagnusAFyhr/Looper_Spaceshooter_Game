package engine.things.misc;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class ThrustTrail extends AnimatedSprite {

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

	public void rRender(GraphicsContext gc, float focusX, float focusY, float shipx, float shipy)
	{
		shipRotateX = shipx;
		shipRotateY = shipy;
		render(gc, focusX, focusY);
	}



	public void setVelocity(float velocity) { super.setVelocity(velocity); }
	public void setHeading(float heading) { super.setHeading(heading); }
	public void setRotation(float rotation) { super.setRotation(rotation); }

}
