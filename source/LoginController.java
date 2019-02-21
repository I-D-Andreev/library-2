import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Chris McAuley, Sian Pike.
 *
 * Controller class for the Login Window.  Handles user interaction with the UI.
 */
public class LoginController extends Controller {

    /**
     * The field showing the username.
     */
    @FXML
    private TextField usernameTextField;

    /**
     * The button for logging in.
     */
    @FXML
    private Button loginButton;


    /**
     * When the login button is clicked, this method retrieves the text from the usernameTextField and checks,
     * whether the user exists.
     */
    @FXML
    private void loginButtonClicked(ActionEvent event) {
        String username = usernameTextField.getText();
        User existingUser = getLibrary().getUserManager().getUserByUsername(username);

        //Checks whether the user exists.
        if (existingUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username " + username + " doesn't exist.",
                    ButtonType.OK);
            alert.show();
        } else {
            // set the currently logged in user
            getLibrary().setCurrentUserLoggedIn(existingUser);
            //Checks whether the user is a librarian.
            if (existingUser.hasAdminAccess()) {

                new NewWindow("resources/LibrarianDashboard.fxml", event,
                        "Dashboard - TaweLib", getLibrary());

            } else {

                new NewWindow("resources/UserDashboard.fxml", event,
                        "Dashboard - TaweLib", getLibrary());
            }
        }
    }
}
