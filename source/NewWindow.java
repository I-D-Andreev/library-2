import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class to create a new window.
 *
 * @author Sian Pike, Ivan Andreev
 */

public class NewWindow {

    /**
     * Creates a new window when called.
     *
     * @param fxmlResource The location of the fxml document for the new window.
     * @param event        The current event being executed.
     * @param title        The title of the window.
     * @param library      The library.
     */
    public NewWindow(String fxmlResource, Event event, String title, Library library) {
        final int windowHeight = 600;
        final int windowWidth = 700;

        Parent root;

        //Attempt to load the fxml file and set the scene.
        try {

            //root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlResource));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlResource));
            root = fxmlLoader.load();
            fxmlLoader.<Controller>getController().setLibrary(library);

            Stage stage = new Stage();

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setMinHeight(windowHeight);
            stage.setMaxHeight(windowHeight);
            stage.setMinWidth(windowWidth);
            stage.setMaxWidth(windowWidth);

            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

            // save data on stage close
            stage.setOnCloseRequest(eventHandler -> {
                library.save();
            });

        } catch (IOException e) {

            e.printStackTrace();
            System.exit(0);

        } catch (Exception e) {

            e.printStackTrace();
            System.exit(1);
        }
    }
}
