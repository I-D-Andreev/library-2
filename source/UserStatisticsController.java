import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
     * LineChart to graphically show statistics.
     */
    @FXML
    private LineChart<?, ?> statisticsLineChart;

    /**
     * Checkbox to filter statistics by day.
     */
    @FXML
    private CheckBox perDayCheckBox;

    /**
     * Checkbox to filter statistics by week.
     */
    @FXML
    private CheckBox perWeekCheckBox;

    /**
     * Checkbox to filter statistics by month.
     */
    @FXML
    private CheckBox perMonthCheckBox;

    /**
     * Closes the statistics window.
     *
     * @param event When the user clicks the ok button.
     */
    @FXML
    void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event,
                "Dashboard - TaweLib", getLibrary());
    }

}
