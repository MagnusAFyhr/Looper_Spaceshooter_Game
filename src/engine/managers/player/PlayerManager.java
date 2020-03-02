package engine.managers.player;

import engine.things.characters.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerManager {

    public Player player;
    private final String imagePath = "art/spaceships/playerShip1_blue.png";
    private float WIDTH, HEIGHT;

    /** Constructor **/
    public PlayerManager(Canvas screen) {

        // define WIDTH & HEIGHT
        this.WIDTH = (float) screen.getWidth();
        this.HEIGHT = (float) screen.getHeight();

        // Create a player
        player = new Player(new Image(imagePath), 0, 0);

        // set up keyboard interrupts

        // set up mouse interrupts

    }

    /** Methods **/
    public void spawnPlayer() {
        // give the player some attributes, like a blaster
    }

    public void updatePlayer(float deltaTime) {
        // Update player and player objects (projectiles, thruster, shield, damage)
        player.update(deltaTime);

    }
    public void renderPlayer(GraphicsContext gc) {
        // calculate screen position
        float screenX = player.getPositionX() - WIDTH / 2;
        float screenY = player.getPositionY() - HEIGHT / 2;


        // Render player and player objects
        player.render(gc, screenX, screenY);
    }

    public boolean handleKeyPressed(String key) {

        // Determine if it is a game play action
        boolean playerAction = player.handleKeyPressed(key);
        if (playerAction)
            return true;

        // Or it could be a pause action; do we want a pause screen?

        return false;
    }
    public boolean handleKeyReleased(String key) {

        // Determine if it is a game play action
        boolean playerAction = player.handleKeyReleased(key);
        if (playerAction)
            return true;

        // Or it could be a pause action; do we want a pause screen?

        return false;
    }

    /** Getters **/
    public void getPlayerDeltaPos() { }
    public void getPlayerScores() { }

    public void printStatus() {
        System.out.println("Player (x,y) : ("+player.getPositionX()+", "+player.getPositionY()+")");
    }

}
