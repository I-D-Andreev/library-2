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
     * Table containing list of upcoming events.
     */
    @FXML
    private TableView<Event> upcomingEventsTable;

    /**
     * Column containing the title of upcoming events.
     */
    @FXML
    private TableColumn<?, ?> upcomingTitleColumn;

    /**
     * Column containing the date of upcoming events.
     */
    @FXML
    private TableColumn<?, ?> upcomingDateColumn;

    /**
     * Column containing the time of upcoming events.
     */
    @FXML
    private TableColumn<?, ?> upcomingTimeColumn;

    /**
     * Column containing the maximum number of attendees for the upcoming events.
     */
    @FXML
    private TableColumn<?, ?> upcomingMaxAttendeesColumn;

    /**
     * Column containing the description of upcoming events.
     */
    @FXML
    private TableColumn<?, ?> upcomingDescriptionColumn;

    /**
     *
     */
    @FXML
    private Label registerLabel;

    /**
     * Button that registers user for an event.
     */
    @FXML
    private Button registerButton;

    /**
     * Button to close the window.
     */
    @FXML
    private Button backButton;

    /**
     * Table containing list of past events.
     */
    @FXML
    private TableView<Event> pastEventsTable;

    /**
     * Column containing the title of past events.
     */
    @FXML
    private TableColumn<?, ?> pastTitleColumn;

    /**
     * Column containing the date of past events.
     */
    @FXML
    private TableColumn<?, ?> pastDateColumn;

    /**
     * Column containing the time of past events.
     */
    @FXML
    private TableColumn<?, ?> pastTimeColumn;

    /**
     * Column containing the maximum number of attendees for the past events.
     */
    @FXML
    private TableColumn<?, ?> pastMaxAttendeesColumn;

    /**
     * Column containing the description of past events.
     */
    @FXML
    private TableColumn<?, ?> pastDescriptionColumn;

    /**
     * The data inside the upcoming event table.
     */
    private ObservableList<Event> upcomingEventData;

    /**
     * The data inside the past event table.
     */
    private ObservableList<Event> pastEventData;

    public void initialize() {
        upcomingEventData = FXCollections.observableArrayList();
        upcomingTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        upcomingDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        upcomingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        upcomingMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxAttendees"));
        upcomingDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        upcomingEventsTable.setOnMouseClicked(event -> {
            Event clickedEvent = upcomingEventsTable.getSelectionModel().getSelectedItem();
            updateLabel(clickedEvent);
            if (event.getClickCount() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, clickedEvent.getDescription(), ButtonType.OK);
                alert.setHeaderText("Event: " + clickedEvent.getTitle());
                alert.show();
            }
        });

        pastEventData = FXCollections.observableArrayList();
        pastTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        pastDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        pastTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        pastMaxAttendeesColumn.setCellValueFactory(new PropertyValueFactory<>("maxAttendees"));
        pastDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        pastEventsTable.setOnMouseClicked(event -> {
            Event clickedEvent = pastEventsTable.getSelectionModel().getSelectedItem();
            if (event.getClickCount() == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, clickedEvent.getDescription(), ButtonType.OK);
                alert.setHeaderText("Event: " + clickedEvent.getTitle());
                alert.show();
            }
        });
    }

    /**
     * Refreshes the table on startup.
     */
    @Override
    public void onStart() {
        this.updateRegisterTable();
        this.updateAttendedTable();
    }

    /**
     * Button to close the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    public void backButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Button to register a user for an event.
     *
     * @param event When the register button is clicked.
     */
    @FXML
    public void registerButtonClicked(ActionEvent event) {
        Event clickedEvent = upcomingEventsTable.getSelectionModel().getSelectedItem();
        if (clickedEvent != null) {
            if (getLibrary().getEventManager().attendEvent(clickedEvent, getLibrary().getCurrentUserLoggedIn())) {
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
     * Updates the data shown on the upcoming events table.
     */
    @FXML
    public void updateRegisterTable() {
        // clear previous upcomingEventData
        upcomingEventData.clear();
        upcomingEventsTable.getItems().clear();

        for (Event event : getLibrary().getEventManager().getUpcomingEvents()) {
            upcomingEventData.add(event);
        }

        upcomingEventsTable.getItems().addAll(upcomingEventData);
    }

    /**
     * Updates the data shown on the past events table, for the logged in user.
     */
    @FXML
    public void updateAttendedTable() {
        // clear previous pastEventData
        pastEventData.clear();
        pastEventsTable.getItems().clear();

        for (Event event : getLibrary().getEventManager().getAttendedEventsFor(getLibrary().getCurrentUserLoggedIn())) {
            pastEventData.add(event);
        }

        pastEventsTable.getItems().addAll(pastEventData);
    }

    /**
     * Updates a status label to notify a user if an event is unable to be booked in advance.
     *
     * @param event The clicked event.
     */
    public void updateLabel(Event event) {
        String labelText = "";
        registerButton.setDisable(false);
        if (event.getMaxAttendees() <= event.attendeeCount()) {
            labelText += "Event has been fully booked.\n";
            registerButton.setDisable(true);
        }
        if (event.containsUser((NormalUser) getLibrary().getCurrentUserLoggedIn())) {
            labelText += "You are already booked\n for this event.";
            registerButton.setDisable(true);
        }
        registerLabel.setText(labelText);
    }
}
