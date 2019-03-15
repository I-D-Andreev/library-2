import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Optional;

/**
 * Controller class used for the window used when a librarian views an event.
 *
 * @author James Hodder
 */
public class ViewEventController extends Controller {

    /**
     * Column containing the usernames of all the attending users.
     */
    @FXML
    private TableColumn<?, ?> usernameColumn;

    /**
     * Column containing the first names of all the attending users.
     */
    @FXML
    private TableColumn<?, ?> firstNameColumn;

    /**
     * Column containing the surnames of all the attending users.
     */
    @FXML
    private TableColumn<?, ?> surnameColumn;

    /**
     * Text field for the new title of the event.
     */
    @FXML
    private TextField titleTextField;

    /**
     * Date picker to choose a new event date.
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Text field for the new time of the event.
     */
    @FXML
    private TextField timeTextField;

    /**
     * Text field for the new capacity of the event.
     */
    @FXML
    private TextField maxAttendeesTextField;

    /**
     * Text area for the new description of the event.
     */
    @FXML
    private TextArea descriptionTextArea;

    /**
     * Table containing all the attending users for the event.
     */
    @FXML
    private TableView<User> userTable;

    /**
     * Button used to save the new details of the event.
     */
    @FXML
    private Button saveButton;

    /**
     * Button used to both add and search for new users to attend the event.
     */
    @FXML
    private Button addButton;

    /**
     * Label used to show the user the result of a search result.
     */
    @FXML
    private Label searchResultLabel;

    /**
     * Text field used for the username of a new attendant.
     */
    @FXML
    private TextField usernameSearchBox;

    /**
     * Button used to return to the previous window.
     */
    @FXML
    private Button backButton;

    /**
     * The event clicked in the previous window.
     */
    private Event clickedEvent;

    /**
     * The data inside the table.
     */
    private ObservableList<NormalUser> data;

    public void initialize(){
        clickedEvent = LibrarianEventController.getViewEventFocus();

        titleTextField.setText(clickedEvent.getTitle());
        datePicker.setValue(clickedEvent.getStartDate());
        timeTextField.setText(clickedEvent.getStartTime().toString());
        maxAttendeesTextField.setText(""+clickedEvent.getMaxAttendees());
        descriptionTextArea.setText(clickedEvent.getDescription());

        data = FXCollections.observableArrayList();
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        
        userTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                NormalUser clickedUser = (NormalUser) userTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, clickedEvent.getTitle()+" remove user: " + clickedUser.getUsername());
                alert.setHeaderText("Would you like to remove this user?");
                alert.setTitle("Removal Confirmation");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    if(clickedEvent.removeAttendent(clickedUser)) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "The user has been successfully removed from the event.");
                        alert.show();
                        updateTable();
                    }
                }
            }
        });

        usernameSearchBox.setOnKeyTyped(event -> {
            addButton.setText("Search");
            searchResultLabel.setText("");
        });

        maxAttendeesTextField.addEventFilter(EventType.ROOT, event -> {
            if(event.getEventType().toString().equals("KEY_TYPED")) {
                if(((KeyEvent) event).getCharacter().matches("\\D")) {
                    event.consume();
                }
            }
        });

        timeTextField.addEventFilter(EventType.ROOT, event -> {
            if(event.getEventType().toString().equals("KEY_TYPED")) {
                if(timeTextField.getText().length() >=5) {
                    event.consume();
                    return;
                }

                if(((KeyEvent) event).getCharacter().matches("\\D")) {
                    event.consume();
                } else {
                    if(timeTextField.getText().matches("^\\d")) {
                        timeTextField.setText(timeTextField.getText() + ((KeyEvent) event).getCharacter());
                        timeTextField.setText(timeTextField.getText() + ":");
                        timeTextField.positionCaret(3);
                        event.consume();
                    }
                }
            }
        });
    }

    /**
     * Refreshes the table on startup.
     */
    @Override
    public void onStart() {
        this.updateTable();
    }

    /**
     * Updates the data shown on the table.
     */
    @FXML
    public void updateTable() {
        // clear previous data
        data.clear();
        userTable.getItems().clear();

        for (NormalUser user: clickedEvent.getAttendees()) {
            data.add(user);
        }

        userTable.getItems().addAll(data);
    }

    /**
     * Search for a new user to add to the event. If found press again to add user to the attendants list.
     *
     * @param event The action event of clicking the button.
     */
    @FXML
    void addButtonClicked(ActionEvent event) {
        if(usernameSearchBox.getText().isEmpty()) {
            searchResultLabel.setText("Field empty");
        } else {
            User searchUser = getLibrary().getUserManager().getUserByUsername(usernameSearchBox.getText());
            if(addButton.getText() != "Add") {
                if ((searchUser != null) && (searchUser instanceof NormalUser)) {
                    addButton.setText("Add");
                    searchResultLabel.setText("User found");
                } else {
                    searchResultLabel.setText("Invalid User");
                }
            } else {
                if(!getLibrary().getEventManager().attendEvent(clickedEvent, searchUser)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "User is either already booked into this event," +
                            "or the event has been fully booked.");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event booked successfully.", ButtonType.OK);
                    alert.show();
                }
                usernameSearchBox.setText("");
                addButton.setText("Search");
                updateTable();
            }
        }
    }

    /**
     * Save the current field details as the new event details, if they are valid.
     *
     * @param event The action event of clicking the button.
     */
    @FXML
    void saveButtonClicked(ActionEvent event) {
        if(titleTextField.getText().isEmpty() || datePicker.getValue().equals(null) || timeTextField.getText().isEmpty()
                || maxAttendeesTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all the fields.",
                    ButtonType.OK);
            alert.show();
        } else if(Integer.parseInt(maxAttendeesTextField.getText()) < clickedEvent.attendeeCount()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Max capacity cannot be lower than current attendee count.",
                    ButtonType.OK);
            alert.show();
        } else {
            String[] timeTextSplit = timeTextField.getText().split(":");

            if((Integer.parseInt(timeTextSplit[0]) > 23) || (Integer.parseInt(timeTextSplit[1]) > 59)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid time.",
                        ButtonType.OK);
                alert.show();
                return;
            }

            LocalTime time = LocalTime.of(Integer.parseInt(timeTextSplit[0]), Integer.parseInt(timeTextSplit[1]));

            clickedEvent.setTitle(titleTextField.getText());
            clickedEvent.setStartDate(datePicker.getValue());
            clickedEvent.setStartTime(time);
            clickedEvent.setMaxAttendees(Integer.parseInt(maxAttendeesTextField.getText()));
            clickedEvent.setDescription(descriptionTextArea.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event edited successfully.", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Return to the previous window.
     *
     * @param event The action event of clicking the button.
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianEvent.fxml", event, "Events - TaweLib", getLibrary());
    }
}
