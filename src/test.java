import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

/**
 * Name:        Ioannis Lafazanis
 * Student ID:  21425229
 * Course Code: CP4CS61E
 * Date:        08/10/2020
 * Project Name: Practical Activity 1
 *
 * Last year I was overwhelmed by TKinter, this year I looked back to the slides and I smiled.
 * This year I will try to make up for what I did not do.
 *
 * Made on OpenJDK14.
 * Please download "javafx-sdk-11.0" and add all the jar-files in
 * Javafx-sdk-11.0.2\lib\ to the global library.
 * VM options --module-path [%java path%] --add-modules javafx.controls,javafx.fxml
 * Thank you for your patience.
 */

public class test extends Application {
    public static void main(String[] args) {
        launch(args); // launches the Application
    }

    @Override // start() is abstract and has to be overridden
    public void start(Stage primaryStage) {
        Group flagItems = new Group();
        int flagWidth = 800;
        int flagHeight = 480;

        // red rectangle height = flagItems height/13.0
        double rectangleHeight = flagHeight / 13.0;
        // starting point in relation to the window.
        // 0,0 in the upper-left corner.
        double rectangleX = 0, rectangleY = 0;

        //create 7 red rectangles (stripes)
        for (int i = 0; i < 7; i++) {
            Rectangle rectangle = new Rectangle(rectangleX, rectangleY, flagWidth, rectangleHeight);
            rectangle.setFill(Color.RED);           //stripe color red
            flagItems.getChildren().add(rectangle); //add to flagItems
            rectangleY += rectangleHeight * 2;      //changes y coordinate to next stripe height
        }

        // Create the blue rectangle of the flag
        // Width = about half flagWidth
        // Height of rectangleHeight*7
        Rectangle blueRectangle = new Rectangle(0, 0, flagWidth / 2.1, rectangleHeight * 7);
        blueRectangle.setFill(Color.BLUE);          // set color to blue
        flagItems.getChildren().add(blueRectangle); // add blueRectangle to flagItems group

        //star width in proportion to blueRectangle
        double starSize = blueRectangle.getWidth() / 11;
        rectangleY = 20; //Y coordinate starting point - for first star to appear

        for (int i = 0; i < 9; i++) {
            int column;
            rectangleX = 20; //X coordinate starting point

            //adds 6 stars when even in the row, 5 when not
            if (i % 2 == 0) {
                column = 6;
            } else {
                column = 5;
                rectangleX += starSize; // change X coordinate
            }

            //star creation using the Polygon class
            for (int j = 0; j < column; j++) {
                Polygon star = createStar(rectangleX, rectangleY, starSize / 3);
                flagItems.getChildren().add(star);
                rectangleX += starSize * 1.8;// distance between each star in row    - X coordinate
            }
            rectangleY += starSize / 1.3;    // distance between each star in column - Y coordinate
        }
        Image flagIcon = new Image("americanflag.png");// Create an image object


        Path path = new Path();
        path.getElements().add(new MoveTo(680, 240));//first value how far on the right of the screen
        path.getElements().add(new LineTo(400, 240));// probably how far on the left it starts
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setNode(flagItems);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);


//need pause
        flagItems.setOnMouseClicked(me -> pathTransition.play());
        flagItems.setOnMouseDragged(x -> pathTransition.pause());


        Scene scene = new Scene(flagItems,1000, 500);              // Add the flagItems to a scene container
        primaryStage.setScene(scene);                    // Place the scene in the stage
        primaryStage.setTitle("Practical Activity 1");   // Set the title of the window
        primaryStage.getIcons().add(flagIcon);           // Add the icon to the stage(window)
        primaryStage.show();                             // Display the stage(window)
    }

    /**
     * Method that creates a star from the Polygon class.
     */
    private Polygon createStar(double centerX, double centerY, double size) {
        Polygon star = new Polygon();
        //end points lie on a Circle
        int divisions = 360 / 5; // divide circle into 5 parts
        int theta = -90;         // initial value - direction from the center

        // creates 10 points to form the star shape
        while (star.getPoints().size() < 10) {
            double starX = centerX + size * Math.cos(Math.toRadians(theta));
            double starY = centerY + size * Math.sin(Math.toRadians(theta));
            star.getPoints().addAll(starX, starY);
            theta += 2 * divisions;
        }
        star.setFill(Color.WHITE); // star Color
        return star;
    }
}
