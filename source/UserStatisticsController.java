import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class for the user statisitics window.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike
 */
public class UserStatisticsController extends Controller {

    /**
     * Button to close the window.
     */
    @FXML
    private Button okButton;

    /**
     * Table containing statistics data.
     */
    @FXML
    private TableView<?> statsTable;

    /**
     * Column containing 'per day' statistics data.
     */
    @FXML
    private TableColumn<?, ?> perDayColumn;

    /**
     * Column containing 'per week' statistics data.
     */
    @FXML
    private TableColumn<?, ?> perWeekColumn;

    /**
     * Column containing 'per month' statistics data.
     */
    @FXML
    private TableColumn<?, ?> perMonthColumn;

    /**
     * Closes the statistics window.
     *
     * @param event When the user clicks the ok button.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {

    }

}
