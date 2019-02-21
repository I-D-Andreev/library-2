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
 * Controller class for the Overdue Copies window.
 * Handles user interaction with the UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class OverdueCopiesController extends Controller {

    /**
     * Table that shows the overdue copies.
     */
    @FXML
    private TableView<TableRepresentationCopyInformation> overdueCopiesTable;

    /**
     * Column that shows the copy ID.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> copyIDColumn;

    /**
     * Column that shows the user that borrowed the copies.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> borrowedByColumn;

    /**
     * Column that shows the date the copy was borrowed on.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> borrowedOnColumn;

    /**
     * Column that shows the due date of the resource.
     */
    @FXML
    private TableColumn<TableRepresentationCopyInformation, String> dueDateColumn;

    /**
     * Column that shows the how many days the copy is overdue.
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

        borrowedByColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("borrowedBy"));


        borrowedOnColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("borrowDate"));

        dueDateColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, String>("dueDate"));


        daysOverdueColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationCopyInformation, Integer>("daysOverdue"));

        this.fillInData();
        overdueCopiesTable.getItems().addAll(data);
    }

    /**
     * Fills the tables data.
     */
    private void fillInData() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        //final long oneDayInMilliseconds = 3600 * 24 * 1000;
        final long oneDayInMilliseconds = 15 * 1000;

        // current date
        Date today = new Date();

        for (Copy copy :  getLibrary().getResourceManager().getOverdueCopies()) {
            if (copy.isOverdue()) {
                String copyID = copy.getUniqueCopyID();
                String borrowedBy = copy.getBorrowedBy().getUsername();
                String borrowedOn = dateFormat.format(copy.getBorrowedOn());
                String dueDate = dateFormat.format(copy.getDueDate());
                Integer daysOverdue = Math.toIntExact((today.getTime() - copy.getDueDate().getTime()) / oneDayInMilliseconds);

                data.add(new TableRepresentationCopyInformation(copyID, borrowedOn, dueDate, daysOverdue, borrowedBy));
            }
        }
    }
}
