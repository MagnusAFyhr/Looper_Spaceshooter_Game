import engine.GameEngine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    double WIDTH = 1200.0;
    double HEIGHT = 800;

    private Stage theStage;

    // Main Function
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage initStage)
    {
        // Define visual properties
        initStage.setTitle("Final Computer Games Project: Looper!");
        initStage.setWidth(WIDTH);
        initStage.setHeight(HEIGHT);
        initStage.setResizable(false);

        // Launch the game
        theStage = initStage;
        mainMenu();

        // Done! Show the stage
        theStage.show();
    }


    private Scene mainMenu()
    {
        // Create Game Logo


        // Create various buttons for screen
        // "Play" button
        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                play();
            }
        });

        // "Help" button
        Button helpButton = new Button();
        helpButton.setText("Help");
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                help();
            }
        });

        // "About" button
        Button aboutButton = new Button();
        aboutButton.setText("About");
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                about();
            }
        });

        // "Exit" button
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit();
            }
        });

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(WIDTH, HEIGHT);

        // Setting the padding
        gridPane.setPadding(new Insets(20, 10, 20, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Add elements to grid pane
        gridPane.add(playButton, 1,0 );
        gridPane.add(helpButton, 1,1 );
        gridPane.add(aboutButton, 1,2 );
        gridPane.add(exitButton, 1,3 );

        // Create a scene object
        Scene menu = new Scene(gridPane);
        theStage.setScene(menu);

        // Done! Return the scene
        return menu;
    }

    // Launch A New Game
    public void play()
    {
        System.out.println("Play!");

        new GameEngine(theStage);
    }

    // Launch Instructions Screen
    public Scene help()
    {
        System.out.println("Help!");
        return null;
    }

    // Launch About Screen
    public Scene about()
    {
        System.out.println("About!");
        return null;
    }

    // Terminate the program
    public void exit()
    {
        System.out.println("Exit!");

        // ask "are you sure?" with pop up

        // show like a retro tv turning off animation before exiting

        System.exit(0);
    }

}
