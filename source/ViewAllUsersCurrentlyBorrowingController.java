import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Controller class for the view all users window.
 * Handles the interaction between the user and UI.
 *
 * @author Sian Pike, Ivan Andreev, Christopher McAuley
 */
public class ViewAllUsersCurrentlyBorrowingController extends Controller {

    /**
     * The table showing the users.
     */
    @FXML
    private TableView<TableRepresentationCopyInformation> userTable;

    /**
     * Column showing the copy name.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> copyIDColumn;

    /**
     * Column showing the copy name.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> copyNameColumn;

    /**
     * Column showing the username.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> usernameColumn;

    /**
     * The column showing the date the copy was borrowed on.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> borrowedOnColumn;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationCopyInformation> data;

    /**
     * The button to return to the dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * The user is returned to the dashboard when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table and fills it with data.
     */
    @Override
    public void onStart() {
        data = FXCollections.observableArrayList();
        copyIDColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("copyID"));

        copyNameColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("resourceName"));

        usernameColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("borrowedBy"));

        borrowedOnColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("borrowDate"));


        this.fillInData();
        userTable.getItems().addAll(data);
    }

    /**
     * Fills in data.
     */
    private void fillInData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Copy copy : getLibrary().getResourceManager().getAllCopies()) {
            if (copy.getBorrowedBy() != null) {
                String copyID = copy.getUniqueCopyID();
                String copyName = copy.getCopyOf().getTitle();
                String username = copy.getBorrowedBy().getUsername();
                String borrowedOn = dateFormat.format(copy.getBorrowedOn());

                data.add(new TableRepresentationCopyInformation(copyID, username, copyName, borrowedOn));
            }
        }
    }
}