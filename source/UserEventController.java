import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class for the User Events window.
 * Handles the interaction between the user and the UI.
 *
 * @author Sian Pike, James Hodder
 */
public class UserEventController extends Controller {

    /**
     * Table containing list of events.
     */
    @FXML
    private TableView<Event> eventsTable;

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
     * Button to close the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Button to register a user for an event.
     *
     * @param event When the register button is clicked.
     */
    @FXML
    void registerButtonClicked(ActionEvent event) {
        Event clickedEvent = eventsTable.getSelectionModel().getSelectedItem();
        if(clickedEvent != null) {
            if(getLibrary().getEventManager().attendEvent(clickedEvent, getLibrary().getCurrentUserLoggedIn())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event booked successfully.", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You are either already booked into this event," +
                        "or the event has been fully booked.",
                        ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please click an event to attend.",
                    ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Updates the data shown on the table.
     */
    @FXML
    public void updateTable() {
        // clear previous data
        data.clear();
        eventsTable.getItems().clear();

        for (Event event: getLibrary().getEventManager().getAllEvents()) {
            data.add(event);
        }

        eventsTable.getItems().addAll(data);
    }

}
