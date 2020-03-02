package engine.things.characters.spaceship;

import engine.managers.foreground.ForegroundSprite;
import javafx.scene.image.Image;


public class Spaceship extends ForegroundSprite {

    /** Variables **/
    private float mass = 10;           // for driving physics and collision
    private int dragForce = 10;
    private int thrustForce = 10000;        // n pixels per second
    private int boostForce = 1000;         //
    private int brakeForce = 1000;         // n pixels per second
    private int turnSpeed = 4;       // n degrees per second

    // int thrustCapacity = 100;
    private float maxVelocity = 200.0f;
    // private int maxBoostForce = 10000;

    private boolean thrusting = false;
    private boolean boosting = false;
    private boolean braking = false;
    private boolean turningRight = false;
    private boolean turningLeft = false;
    private boolean shooting = false;


    /** Constructor **/
    public Spaceship(Image image, float size,
                     float positionX, float positionY) {

        super(image, size, positionX, positionY, 0, 0,  0);
        // this.mass = this.mass * size;
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
            } else {
                // do turn right
                setRotation( getRotation() + turnSpeed );
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

        // check if shooting
        if (shooting) {
            // handle shooting

        }

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


    /** Setters **/
}
