package menu;

import engine.GameEngine;
import engine.managers.GameManager;
import engine.managers.scoreboard.ScoreboardManager;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.nio.file.Paths;

public class HelpScreen {

    /** Private Variables **/
    public static float WIDTH, HEIGHT;
    private Stage theStage;
    private Stage oldStage;

    /** Constructors **/
    public HelpScreen(Stage theStage) {
        WIDTH = (float) theStage.getWidth();
        HEIGHT = (float) theStage.getHeight();

        this.theStage = theStage;
        this.oldStage = theStage;

        launch();

        this.theStage.show();

    }

    private void launch() {
        // "back" button
        javafx.scene.control.Button exitButton = new Button();
        exitButton.setText("Back");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                back();

            }
        });

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
    // Terminate the program
    public void back()
    {
        //TODO: GO BACK TO MAIN PAGE

    }


}
