package engine.things.characters;

import engine.things.characters.spaceship.Spaceship;
import javafx.scene.image.Image;

public class Player extends Spaceship { //, Controllable


    /** Variables **/
    int lives = 3;
    int score = 0;

    /** Constructor **/
    public Player(Image image, float positionX, float positionY) {
        super(image, 1.0f, positionX, positionY);


    }

    /** Methods **/
    public boolean handleKeyPressed(String key) {
        switch (key) {

            case "W":
                thrust(true);
                return true;
            case "A":
                turnLeft(true);
                return true;
            case "S":
                brake(true);
                return true;
            case "D":
                turnRight(true);
                return true;
            case "SHIFT":
                boost(true);
                return true;
            default:
                break;
        }

        return false;
    }
    public boolean handleKeyReleased(String key) {
        switch (key) {

            case "W":
                thrust(false);
                return true;
            case "A":
                turnLeft(false);
                return true;
            case "S":
                brake(false);
                return true;
            case "D":
                turnRight(false);
                return true;
            case "SHIFT":
                boost(false);
                return true;
            default:
                break;
        }

        return false;
    }

    /** Getters **/
    public float getMapPosX() { return getPositionX(); }
    public float getMapPosY() { return getPositionY(); }


}
