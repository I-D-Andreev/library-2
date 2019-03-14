import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Controller class for the Librarian Statistics Window.
 * Handles the events when the user interacts with the UI.
 *
 * @author Sian Pike
 */
public class LibrarianStatisticsController extends Controller {

    @FXML
    private TableView<TableRepresentationResourceInformation> mostPopularResourceTable;

    @FXML
    private TableColumn<TableRepresentationResourceInformation, Integer> borrowTimesColumn;

    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> resourceTypeColumn;

    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> titleColumn;

    @FXML
    private TableColumn<TableRepresentationResourceInformation, String> idColumn;

    @FXML
    private RadioButton pastDayRadioButton;

    @FXML
    private RadioButton pastWeekRadioButton;

    @FXML
    private RadioButton allTimeRadioButton;

    @FXML
    private TableView<?> finesTable;


    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationResourceInformation> data;

    /**
     * Button to close the window.
     */
    @FXML
    private Button okButton;


    /**
     * Closes the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    @Override
    public void onStart() {
        data = FXCollections.observableArrayList();

        resourceTypeColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceType"));

        idColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceID"));

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, String>("resourceName"));

        borrowTimesColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationResourceInformation, Integer>("numberOfTimesBorrowed"));

    }


    @FXML
    public void pastDayRadioButtonClicked(ActionEvent event) {
        fillTable();
    }

    @FXML
    public void pastWeekRadioButtonClicked(ActionEvent event) {
        fillTable();
    }


    @FXML
    public void allTimeRadioButtonClicked(ActionEvent event) {
        fillTable();

    }

    private void fillTable() {
        mostPopularResourceTable.getItems().clear();
        data.clear();

        // Set the values to min for our borrowed resources (1 of each type)
        int mostBorrowedBookCount = Integer.MIN_VALUE;
        int mostBorrowedDVDCount = Integer.MIN_VALUE;
        int mostBorrowedLaptopCount = Integer.MIN_VALUE;
        int mostBorrowedVideoGameCount = Integer.MIN_VALUE;

        // Our references for the most borrowed resources (1 of each type)
        Book mostBorrowedBook = null;
        DVD mostBorrowedDVD = null;
        Laptop mostBorrowedLaptop = null;
        VideoGame mostBorrowedVideoGame = null;

        for (Resource resource : getLibrary().getResourceManager().getAllResources()) {
            int borrowedCount = 0;

            // !!!!!!!!
            // Depending on which radio button is clicked, we shall call
            // different time interval to calculate the number of times borrowed
            if (pastDayRadioButton.isSelected()) {
                // remove 1 day from the today to get the past day
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -1);

                // get the date in a Date object
                Date pastDay = calendar.getTime();

                // call the function which returns how many times a resource has been borrowed on a given
                // day with the past day as argument (i.e. how many times a resource was borrowed the past day)
                borrowedCount = getLibrary().getResourceManager().
                        getNumberOfTimesResourceWasBorrowedOn(resource, pastDay);
            } else if (pastWeekRadioButton.isSelected()) {
                // note: for past week it is just 6 days behind from today (7 days in total including today)
                // and not monday to sunday type of past week like in borrowed resources

                // call the function which returns how many times a resource was borrowed the past week
                // i.e. from today (incl.) six days back
                // we pass it the day today
                borrowedCount = getLibrary().getResourceManager().
                        getNumberOfTimesResourceWasBorrowedPastWeek(resource, new Date());
            } else if (allTimeRadioButton.isSelected()) {
                borrowedCount = getLibrary().getResourceManager()
                        .getNumberOfTimesResourceWasBorrowedForAllTime(resource);
            }


            // check which resource we have and whether it is the most borrowed
            if ((resource instanceof Book) && borrowedCount > mostBorrowedBookCount) {
                mostBorrowedBookCount = borrowedCount;
                mostBorrowedBook = (Book) resource;
            }

            if ((resource instanceof DVD) && borrowedCount > mostBorrowedDVDCount) {
                mostBorrowedDVDCount = borrowedCount;
                mostBorrowedDVD = (DVD) resource;
            }

            if ((resource instanceof Laptop) && borrowedCount > mostBorrowedLaptopCount) {
                mostBorrowedLaptopCount = borrowedCount;
                mostBorrowedLaptop = (Laptop) resource;
            }

            if ((resource instanceof VideoGame) && borrowedCount > mostBorrowedVideoGameCount) {
                mostBorrowedVideoGameCount = borrowedCount;
                mostBorrowedVideoGame = (VideoGame) resource;
            }
        }

        // fill in the resources (if they exist)
        if (mostBorrowedBook != null) {
            data.add(new TableRepresentationResourceInformation(mostBorrowedBook.getUniqueID(),
                    mostBorrowedBook.getTitle(), mostBorrowedBook.getType(), mostBorrowedBookCount));
        }

        if (mostBorrowedDVD != null) {
            data.add(new TableRepresentationResourceInformation(mostBorrowedDVD.getUniqueID(),
                    mostBorrowedDVD.getTitle(), mostBorrowedDVD.getType(), mostBorrowedDVDCount));
        }

        if (mostBorrowedLaptop != null) {
            data.add(new TableRepresentationResourceInformation(mostBorrowedLaptop.getUniqueID(),
                    mostBorrowedLaptop.getTitle(), mostBorrowedLaptop.getType(), mostBorrowedLaptopCount));
        }

        if (mostBorrowedVideoGame != null) {
            data.add(new TableRepresentationResourceInformation(mostBorrowedVideoGame.getUniqueID(),
                    mostBorrowedVideoGame.getTitle(), mostBorrowedVideoGame.getType(), mostBorrowedVideoGameCount));
        }


        // add the data to the table
        mostPopularResourceTable.getItems().addAll(data);

    }
}
