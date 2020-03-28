package engine.things.characters.spaceship;

import engine.managers.foreground.ForegroundSprite;
import engine.things.misc.AnimatedSprite;
import engine.things.misc.ThrustTrail;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.Arrays;


public class Spaceship extends ForegroundSprite {

    /** Variables **/
    private float mass = 10;           // for driving physics and collision
    private int dragForce = 10;
    private int thrustForce = 10000;        // n pixels per second
    private int boostForce = 1000;         //
    private int brakeForce = 1000;         // n pixels per second
    private int turnSpeed = 4;       // n degrees per second
    

    // two ThrustTrail animations for the boost and thrust animations
    private ThrustTrail thrustTrail;
    private ThrustTrail boostTrail;

    // animated sprite object for lasers
    private AnimatedSprite laserShoot;

    // this forces the user to release the space bar before they can shoot again
    private boolean prevState = false;

    // int thrustCapacity = 100;
    private float maxVelocity = 200.0f;
    // private int maxBoostForce = 10000;

    private boolean thrusting = false;
    private boolean boosting = false;
    private boolean braking = false;
    private boolean turningRight = false;
    private boolean turningLeft = false;

    private boolean shooting = false;


    // Thrust List
    private static ArrayList<Image> thrustImages = new ArrayList<>(Arrays.asList(
            new Image("art/spaceships/fire1.png"),
            new Image("art/spaceships/fire2.png"),
            new Image("art/spaceships/fire3.png")
    ));

    // Boost List
    private static ArrayList<Image> boostImages = new ArrayList<>(Arrays.asList(
            new Image("art/spaceships/fire4.png"),
            new Image("art/spaceships/fire5.png"),
            new Image("art/spaceships/fire6.png")
    ));

    // Bullet List
    private static ArrayList<Image> bulletImages = new ArrayList<>(Arrays.asList(
            new Image("art/spaceships/laserBlue1.png"),
            new Image("art/spaceships/laserBlue3.png"),
            new Image("art/spaceships/laserBlue4.png")
    ));

    // Arraylist of animated sprites which gets a sprite object when the user shoots
    private static ArrayList<AnimatedSprite> laserObjects = new ArrayList<>();



    /** Constructor **/
    public Spaceship(Image image, float size,
                     float positionX, float positionY) {

        super(image, size, positionX, positionY, 0, 0,  0, 0);
        // this.mass = this.mass * size;

        // initialize the ThrustTrail objects for the boost and thrust sprite lists
        thrustTrail = new ThrustTrail(size, positionX, positionY, 0, 0, 0, this.getScreenX(), this.getScreenY(), thrustImages,4);
        boostTrail = new ThrustTrail(size, positionX, positionY, 0,0,0, this.getScreenX(), this.getScreenY(), boostImages, 4);
    }
    

    /** Methods **/    
    @Override
    public void update(float deltaTime) {


        // check if turning (left/right)
        if (turningLeft || turningRight) {
            // handle turning

            // do turning
            if (turningLeft && turningRight) {
                // do nothing
            } else if (turningLeft) {
                // do turn left
                setRotation( getRotation() - turnSpeed );

                // turn trails left
                thrustTrail.setRotation( getRotation() );
                boostTrail.setRotation( getRotation() );

            } else {
                // do turn right
                setRotation( getRotation() + turnSpeed );

                // turn trails right
                thrustTrail.setRotation( getRotation() );
                boostTrail.setRotation( getRotation() );
            }

        }
        float rX = (float) Math.cos(Math.toRadians(getRotation()));
        float rY = (float) Math.sin(Math.toRadians(getRotation()));

        float hX = (float) Math.cos(Math.toRadians(getHeading()));
        float hY = (float) Math.sin(Math.toRadians(getHeading()));


        // check if thrusting
        float thrustX = 0.0f;
        float thrustY = 0.0f;
        if (thrusting) {
            // handle thrusting

            // do thrusting
            thrustX = thrustForce * rX;
            thrustY = thrustForce * rY;

        }

        // check if boosting
        float boostX = 0.0f;
        float boostY = 0.0f;
        if (boosting) {
            // handle boosting

            // do boosting
            boostX = boostForce * rX;
            boostY = boostForce * rY;

        }

        // check if braking
        float brakeX = 0.0f;
        float brakeY = 0.0f;
        if (braking) {
            // handle braking

            // do braking
            brakeX = -brakeForce * hX;
            brakeY = -brakeForce * hY;

        }

        // check if shooting and previous state
        if (shooting & !prevState) {
            laserShoot = new AnimatedSprite(bulletImages,
                    0,
                    super.getPositionX(),// - super.getScreenX(),
                    super.getPositionY(),// - super.getScreenY(),
                    1000,
                    this.getRotation(),
                    this.getRotation(),
                    5);

            laserObjects.add(laserShoot);
            if (laserObjects.size() > 10) {
                laserObjects.remove(0);
            }
        }
        // update prev state
        prevState = shooting;

        // calculate velocity vector
        float dragX = -dragForce * getVelocity() * hX;
        float dragY = -dragForce * getVelocity() * hY;

        float netForceX = thrustX + boostX + brakeX + dragX;
        float netForceY = thrustY + boostY + brakeY + dragY;

        float aX = (netForceX / mass);
        float aY = (netForceY / mass);

        float vX = aX * deltaTime + getVelocity() * hX;
        float vY = aY * deltaTime + getVelocity() * hY;

        float newVelocity = (float) Math.sqrt( Math.pow(vX, 2) + Math.pow(vY, 2) );

        // control terminal velocity
        if (newVelocity > maxVelocity && !boosting) {
            vX = (vX / newVelocity) * maxVelocity;
            vY = (vY / newVelocity) * maxVelocity;
            newVelocity = maxVelocity;
        } else if (boosting && newVelocity > maxVelocity * 2) {
            vX = (vX / newVelocity) * maxVelocity * 2;
            vY = (vY / newVelocity) * maxVelocity * 2;
            newVelocity = maxVelocity * 2;
        }

        // calculate new heading
        float newHeading = 0.0f;
        if (newVelocity == 0) {
            newHeading = getRotation();

        } else if (vX == 0) {
            newHeading = (float) Math.toDegrees( Math.asin(vY / newVelocity) );

        } else if (vY == 0) {
            newHeading = (float) Math.toDegrees( Math.acos(vX / newVelocity) );

        } else {
            float rawHeading = (float) Math.toDegrees( Math.atan(vY / vX) );
            if (vY < 0 && vX > 0) { // quadrant 1 : (0, -90)
                rawHeading += 0;

            } else if (vY < 0 && vX < 0) { // quadrant 2 (90, 180)
                rawHeading -= 180;

            } else if (vY > 0 && vX < 0) { // quadrant 3 (90, 180)
                rawHeading += 180;

            } else if (vY > 0 && vX > 0) { // quadrant 4 (0, 90)
                rawHeading += 0;

            }

            newHeading = rawHeading;
        }

        // update velocity & heading
        setVelocity( newVelocity );
        setHeading( newHeading );

        // super update
        super.update(deltaTime);

        // update thrust trail in relation to the ship
        thrustTrail.setVelocity( newVelocity );
        thrustTrail.setHeading( newHeading );
        thrustTrail.update(deltaTime);

        // update boost trail in relation to the ship
        boostTrail.setVelocity( newVelocity );
        boostTrail.setHeading( newHeading );
        boostTrail.update(deltaTime);


        // Update the animation step for each laser
        for(AnimatedSprite object: laserObjects)
        {
            object.update(deltaTime);
        }
    }
    
    @Override
    public void render(GraphicsContext gc, float focusX, float focusY)
    {
        for(AnimatedSprite object: laserObjects)
        {
            object.render(gc, focusX, focusY);
        }

    	// draw ship
    	super.render(gc, focusX, focusY);

    	// if thrusting then draw the thrust trail
    	if(thrusting)
    	    thrustTrail.rRender(gc, focusX, focusY-55, this.getScreenX(), this.getScreenY());

    	// if boosting then draw boost trail
    	if(boosting)
    	    boostTrail.rRender(gc, focusX, focusY-55, this.getScreenX(), this.getScreenY());



    }

    protected void thrust(boolean onOff) {
        // power ship thrusters
        thrusting = onOff;
    }
    protected void boost(boolean onOff) {
        // power ship boosters
        boosting = onOff;
    }
    protected void turnLeft(boolean onOff) {
        turningLeft = onOff;
    }
    protected void turnRight(boolean onOff) {
        turningRight = onOff;
    }
    protected void brake(boolean onOff) {
        // apply drag to ship
        braking = onOff;
    }
    protected void shoot(boolean onOff) {
        // fire projectile
        shooting = onOff;
    }

    /** Getters **/
    public ArrayList<AnimatedSprite> getLaserObjects() {
        return laserObjects;
    }


    /** Setters **/
    public void removeLaserObject(int index) {
        laserObjects.remove(index);
    }
}
