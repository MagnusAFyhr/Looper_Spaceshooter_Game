package menu;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class HelpScreen {

    /** Private Variables **/
    public static float WIDTH, HEIGHT;
    private Stage theStage;

    /** Constructors **/
    public HelpScreen(Stage theStage, Button exitButton) {
        WIDTH = (float) theStage.getWidth();
        HEIGHT = (float) theStage.getHeight();

        this.theStage = theStage;

        launch(exitButton);

        this.theStage.show();

    }

    private void launch(Button exitButton) {

        Group root = new Group();
        Scene theScene = new Scene (root);
        theStage.setScene(theScene);

        Canvas screen = new Canvas( theStage.getWidth(), theStage.getHeight() );
        GraphicsContext gc = screen.getGraphicsContext2D();
        Image helpscreen = new Image(Paths.get("src/art/background/HelpPage.png").toUri().toString());
        gc.fillRect(0,0,WIDTH,HEIGHT);
        gc.drawImage(helpscreen,0,0);


        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(screen);
        pane.getChildren().add(exitButton);

        root.getChildren().add( pane );


        exitButton.setAlignment(Pos.CENTER);
        root.getChildren().add(exitButton);


    }

}
