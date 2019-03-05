import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class for the User Events window.
 * Handles the interaction between the user and the UI.
 *
 * @author Sian Pike
 */
public class UserEventController {

    /**
     * Table containing list of events.
     */
    @FXML
    private TableView<?> eventsTable;

    /**
     * Column containing the title of events.
     */
    @FXML
    private TableColumn<?, ?> titleColumn;

    /**
     * Column containing the date of events.
     */
    @FXML
    private TableColumn<?, ?> dateColumn;

    /**
     * Column containing the time of events.
     */
    @FXML
    private TableColumn<?, ?> timeColumn;

    /**
     * Column containing the maximum number of attendees for the events.
     */
    @FXML
    private TableColumn<?, ?> maxAttendeesColumn;

    /**
     * Column containing the description of events.
     */
    @FXML
    private TableColumn<?, ?> descriptionColumn;

    /**
     * Button that registers user for an event.
     */
    @FXML
    private Button registerButton;

    /**
     * Button to close the window.
     */
    @FXML
    private Button okButton;

    /**
     * Button to close the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {

    }

    /**
     * Button to register a user for an event.
     *
     * @param event When the register button is clicked.
     */
    @FXML
    void registerButtonClicked(ActionEvent event) {

    }

}
