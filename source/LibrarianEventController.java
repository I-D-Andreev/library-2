import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for the librarian event window.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike
 */
public class LibrarianEventController {

    /**
     * Table containing events.
     */
    @FXML
    private TableView<?> eventTable;

    /**
     * Column containing the event titles.
     */
    @FXML
    private TableColumn<?, ?> titleColumn;

    /**
     * Column containing the event dates.
     */
    @FXML
    private TableColumn<?, ?> dateColumn;

    /**
     * Column containing the event times.
     */
    @FXML
    private TableColumn<?, ?> timeColumn;

    /**
     * Column containing the maximum amount of attendees allowed at the events.
     */
    @FXML
    private TableColumn<?, ?> maxAttendeesColumn;

    /**
     * Column containing the event descriptions.
     */
    @FXML
    private TableColumn<?, ?> descriptionColumn;

    /**
     * Button to close the window.
     */
    @FXML
    private Button okButton;

    /**
     * Text field to type in a new event title.
     */
    @FXML
    private TextField titleTextField;

    /**
     * Date picker to choose a new event date.
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Text field to type in a new event time.
     */
    @FXML
    private TextField timeTextField;

    /**
     * Text field to type in a maximum amount of attendees for a new event.
     */
    @FXML
    private TextField maxAttendeesTextField;

    /**
     * Text area to type in a new event description.
     */
    @FXML
    private TextArea descriptionTextArea;

    /**
     * Button to add new event to events table.
     */
    @FXML
    private Button createButton;

    /**
     * Adds the new event to the events table.
     *
     * @param event When the create button is clicked.
     */
    @FXML
    void createButtonClicked(ActionEvent event) {

    }

    /**
     * Closes the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {

    }

}
