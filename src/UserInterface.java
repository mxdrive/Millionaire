import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends Application {
    private List<Button> buttonsList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        buttonsList.add(createButton("answer 1", 100, 200));
        buttonsList.add(createButton("answer 2", 200, 200));
        buttonsList.add(createButton("answer 3", 300, 200));
        buttonsList.add(createButton("answer 4", 400, 200));

        //TODO wrap with if-else if hints
        buttonsList.add(createButton("hint", 150, 250));

        buttonsList.get(0).setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Hello World");
            }
        }));

        buttonsList.get(1).setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Hello World");
            }
        }));
        //Creating a Group object
        Group root = new Group();

        for (Button button : buttonsList) {
            root.getChildren().add(button);
        }

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.LAVENDER);

        //Setting title to the Stage
        primaryStage.setTitle("Convenience Methods Example");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    private Button createButton(String buttonText, int layoutX, int layoutY) {
        Button button = new Button(buttonText);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        return button;
    }
}
