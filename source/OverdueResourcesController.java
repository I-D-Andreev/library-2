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
import java.util.Date;

/**
 * Controller class for the overdue resources window.
 * Handles the interaction between the user and UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class OverdueResourcesController extends Controller {

    /**
     * The button to return to the dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * Table that shows the overdue resources.
     */
    @FXML
    private TableView<TableRepresentationCopyInformation> overdueResourcesTable;

    /**
     * The column showing the copy ID.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> copyIDColumn;

    /**
     * The column showing the date the resource was borrowed on.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> borrowedOnColumn;

    /**
     * The column showing the due date of the resource.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> dueDateColumn;

    /**
     * The column showing the days overdue the resource is.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, Integer> daysOverdueColumn;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationCopyInformation> data;

    /**
     * When the button is clicked the user is returned to the main dashboard.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table, fills it with data.
     */
    @Override
    public void onStart() {
        NormalUser currentlyLoggedIn = (NormalUser) getLibrary().getCurrentUserLoggedIn();
        data = FXCollections.observableArrayList();
        copyIDColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("copyID"));


        borrowedOnColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("borrowDate"));

        dueDateColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("dueDate"));


        daysOverdueColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, Integer>("daysOverdue"));

        this.fillInData();
        overdueResourcesTable.getItems().addAll(data);
    }

    /**
     * Fills the tables data.
     */
    private void fillInData() {
        // The current user
        NormalUser user = (NormalUser) getLibrary().getCurrentUserLoggedIn();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        //final long oneDayInMilliseconds = 3600 * 24 * 1000;
        final long oneDayInMilliseconds = 15 * 1000;

        // current date
        Date today = new Date();

        for (Copy copy : user.getBorrowedCopies()) {
            if (copy.isOverdue()) {
                String copyID = copy.getUniqueCopyID();
                String borrowedOn = dateFormat.format(copy.getBorrowedOn());
                String dueDate = dateFormat.format(copy.getDueDate());
                Integer daysOverdue = Math.toIntExact((today.getTime() - copy.getDueDate().getTime()) / oneDayInMilliseconds);

                data.add(new TableRepresentationCopyInformation(copyID, borrowedOn, dueDate, daysOverdue));
            }
        }
    }
}
