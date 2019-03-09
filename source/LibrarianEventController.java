import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalTime;

/**
 * Controller class for the librarian event window.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike, James Hodder
 */
public class LibrarianEventController extends Controller{

    /**
     * Table containing events.
     */
    @FXML
    private TableView<Event> eventTable;

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
     * The data inside the table.
     */
    private ObservableList<Event> data;

    public void initialize() {
        data = FXCollections.observableArrayList();
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        maxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxAttendees"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    /**
     * Refreshes the table on startup.
     */
    @Override
    public void onStart() {
        this.updateTable();
    }

    /**
     * Adds the new event to the events table.
     *
     * @param event When the create button is clicked.
     */
    @FXML
    void createButtonClicked(ActionEvent event) {
        if(titleTextField.getText().isEmpty() || datePicker.getValue().equals(null) || timeTextField.getText().isEmpty()
            || maxAttendeesTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the fields.",
                    ButtonType.OK);
            alert.show();
        } else {
            String[] timeTextSplit = timeTextField.getText().split(":");
            LocalTime time = LocalTime.of(Integer.parseInt(timeTextSplit[0]), Integer.parseInt(timeTextSplit[1]));
            getLibrary().getEventManager().addEvent(new Event(titleTextField.getText(), datePicker.getValue(), time,
                    Integer.parseInt(maxAttendeesTextField.getText()), descriptionTextArea.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event created successfully.", ButtonType.OK);
            alert.show();

            this.updateTable();
            this.clearAllCreateEventFields();
        }
    }

    /**
     * Closes the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Clears all text fields on the window. Cannot reset DatePicker.
     */
    private void clearAllCreateEventFields() {
        this.titleTextField.setText("");
        this.timeTextField.setText("");
        this.maxAttendeesTextField.setText("");
        this.descriptionTextArea.setText("");
    }

    /**
     * Updates the data shown on the table.
     */
    @FXML
    public void updateTable() {
        // clear previous data
        data.clear();
        eventTable.getItems().clear();

        for (Event event: getLibrary().getEventManager().getAllEvents()) {
            data.add(event);
        }

        eventTable.getItems().addAll(data);
    }
}
