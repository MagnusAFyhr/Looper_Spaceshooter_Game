package engine.things.misc;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class AnimatedSprite extends Sprite{
	
	private Image currImage;
	private ArrayList<Image> images;
	private int currStep;
	private int animationSpeed;
	private int animationStep;

	/** Constructor **/
	public AnimatedSprite(ArrayList<Image> images, float size, float positionX, float positionY, float velocity, float heading,
			float rotation, int animationSpeed) {
		super(images.get(0), size, positionX, positionY, velocity, heading, rotation);
		// TODO Auto-generated constructor stub
		
		this.images = images;
		currStep = 0;
		currImage = images.get(0);

		// initalize animation speed variables
		this.animationSpeed = animationSpeed;
		animationStep = 0;
	}
	
	/** Methods **/
	@Override
	public void update(float deltaTime)
	{
		animationStep++;

		if(animationStep>=animationSpeed)
		{
			animationStep = 0;

			// update list pos
			currStep++;
			currStep = currStep % (images.size());
		}
		
		// get new image
		currImage = images.get(currStep);
		
		// set new image
		super.setImage(currImage);
		
		// super.update
		super.update(deltaTime);
	}

	protected void setVelocity(float velocity) { super.setVelocity(velocity); }
	protected void setHeading(float heading) { super.setHeading(heading); }
	protected void setRotation(float rotation) { super.setRotation(rotation); }
}
