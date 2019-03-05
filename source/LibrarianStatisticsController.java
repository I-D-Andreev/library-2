import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class for the Librarian Statistics Window.
 * Handles the events when the user interacts with the UI.
 *
 * @author Sian Pike
 */
public class LibrarianStatisticsController {

    /**
     * Table containing the most popular resources.
     */
    @FXML
    private TableView<?> mostPopularResourceTable;

    /**
     * Column displaying the type of the most popular resources.
     */
    @FXML
    private TableColumn<?, ?> resourceTypeColumn;

    /**
     * Column displaying the title of the most popular resources.
     */
    @FXML
    private TableColumn<?, ?> titleColumn;

    /**
     * Checkbox to filter most popular resources from the past day.
     */
    @FXML
    private CheckBox pastDayCheckBox;

    /**
     * Checkbox to filter the most popular resources from the past week.
     */
    @FXML
    private CheckBox pastWeekCheckBox;

    /**
     * Checkbox to filter the most popular resources from all time.
     */
    @FXML
    private CheckBox allTimeCheckBox;

    /**
     * Table containing statistics about fines.
     */
    @FXML
    private TableView<?> finesTable;

    /**
     * Button to close the window.
     */
    @FXML
    private Button okButton;

    /**
     * Filters the items in the table to display resources from all time.
     *
     * @param event When the all time checkbox is clicked.
     */
    @FXML
    void allTimeCheckBoxClicked(ActionEvent event) {

    }

    /**
     * Closes the window.
     *
     * @param event When the ok button is clicked.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {

    }

    /**
     * Filters the items in the table to display resources from the past day.
     *
     * @param event When the past day checkbox is clicked.
     */
    @FXML
    void pastDayCheckBoxClicked(ActionEvent event) {

    }

    /**
     * Filters the items in the table to display resources from the past week.
     *
     * @param event When the past week checkbox is clicked.
     */
    @FXML
    void pastWeekCheckBoxClicked(ActionEvent event) {

    }

}
