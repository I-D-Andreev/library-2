import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Optional;

public class ViewEventController extends Controller {

    @FXML
    private TableColumn<?, ?> usernameColumn;

    @FXML
    private TextField titleTextField;

    @FXML
    private TableColumn<?, ?> firstNameColumn;

    @FXML
    private TextField maxAttendeesTextField;

    @FXML
    private TableColumn<?, ?> registerColumn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TableView<User> userTable;

    @FXML
    private Button saveButton;

    @FXML
    private TextField timeTextField;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private Button addButton;

    @FXML
    private Label searchResultLabel;

    @FXML
    private TextField usernameSearchBox;

    @FXML
    private Button backButton;

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

    @FXML
    void addButtonClicked(ActionEvent event) {
        if(usernameSearchBox.getText().isEmpty()) {
            System.out.println("empty box!");
        } else {
            User searchUser = getLibrary().getUserManager().getUserByUsername(usernameSearchBox.getText());
            if(addButton.getText() != "Add") {
                if ((searchUser != null) && (searchUser instanceof NormalUser)) {
                    addButton.setText("Add");
                } else {
                    searchResultLabel.setText("Invalid User");
                }
            } else {
                if(getLibrary().getEventManager().attendEvent(clickedEvent, searchUser)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Event booked successfully.", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "User is either already booked into this event," +
                            "or the event has been fully booked.");
                    alert.show();
                }
                usernameSearchBox.setText("");
                addButton.setText("Search");
                updateTable();
            }
        }
    }

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

    @FXML
    void backButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianEvent.fxml", event, "Events - TaweLib", getLibrary());
    }
}
