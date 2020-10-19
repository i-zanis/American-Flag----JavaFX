import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Name:        Ioannis Lafazanis
 * Student ID:  21425229
 * Course Code: CP4CS61E
 * Date:        08/10/2020
 * Project Name: PracticalActivity1
 *
 * Experimental attempt to create an American flag design with any type of character.
 * The program is intended to be improved in the future. With that in mind I decided
 * to store the values to reduce redundant work.
 *
 * Last year I was overwhelmed by TKinter but this year I will try to make up.
 *
 * Made on OpenJDK14.
 * Please download "javafx-sdk-11.0" and add all the jar-files in
 * Javafx-sdk-11.0.2\lib\ to the global library.
 * VM options --module-path [%java path%] --add-modules javafx.controls,javafx.fxml
 * Thank you for your patience.
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {                   // Primary Container(window) of the app
        Button flagButton = new Button(createFlag("*", "=")); // Create a button object
        //flagButton.setStyle("-fx-text-fill: #0000ff");        // Text color blue
        StackPane pane = new StackPane();                     // Container class that lays children like a stack
        pane.getChildren().add(flagButton);                   // add button to container class
        Scene scene = new Scene(pane, 500, 200);        // Place the button in the scene container
        primaryStage.setTitle("PracticalActivity1_FX");       // Set the title of the window
        primaryStage.setScene(scene);                         // Place the scene in the stage
        primaryStage.show();                                  // Display the stage(window)
    }

    /**
     * Creates a flag with two characters of your choice
     * The row size of the flag is 46.
     * The column size is 11.
     */
    private static String createFlag(String firstString, String secondString) throws IllegalArgumentException {
        if (firstString.length() >= 2 || secondString.length() >= 2)
            throw new IllegalArgumentException("For a correct display use only single-character parameters.");
        String space = " ";

        //Create an array to save values for future operations
        String[] arrayAsteriskFirst = new String[12];
        arrayAsteriskFirst[arrayAsteriskFirst.length - 1] = space; //space at the beginning of the secondString
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) arrayAsteriskFirst[i] = firstString;
            else arrayAsteriskFirst[i] = space;
        }

        //Create an array to save values for future operations
        String[] arraySpaceFirst = new String[12];
        arraySpaceFirst[arrayAsteriskFirst.length - 1] = space; //the space at the beginning of the secondString
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) arraySpaceFirst[i] = space;
            else arraySpaceFirst[i] = firstString;
        }
        //casts the Array into a String object, joins the array and removes the comma between every character
        String evenLine = String.join("", arrayAsteriskFirst).replace(",", "")
                + String.format("%40s", "").replace(" ", secondString);

        //casts the Array into a String object, joins the array and removes the comma between every character
        String oddLine = String.join("", arraySpaceFirst)
                //converts empty space into the secondString
                + String.format("%40s", "").replace(" ", secondString);
        String fullSecondStringLine = String.format("%46s", "").replace(" ", secondString);
        StringBuilder buffer = new StringBuilder(); //create a buffer to display the text
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0 & i < 9) buffer.append(evenLine).append("\n");

            if (i % 2 != 0 & i < 9) buffer.append(oddLine).append("\n");
            else if (i > 9) buffer.append(fullSecondStringLine).append("\n");
        }
        return buffer.toString(); // Casts the buffer to a String
    }
    public static void main(String[] args) {

        launch(args);
    }
}
