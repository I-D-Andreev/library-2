import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
    private LineChart<Number, Number> statisticsLineChart;

    /**
     * Button to filter statistics by day.
     */
    @FXML
    private Button dailyButton;

    /**
     * Button to filter statistics by week.
     */
    @FXML
    private Button weeklyButton;

    /**
     * Button to filter statistics by month.
     */
    @FXML
    private Button monthlyButton;

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


    @FXML
    void dailyButtonClicked(ActionEvent event) {
        // we will show 7 days
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Day of the month");
        yAxis.setLabel("Number of resources borrowed");

        statisticsLineChart = new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series<Integer, Integer> chartSeries = new XYChart.Series<>();
//        chartSeries.getData().add(new XYChart.Data<Integer, Integer>());
        Date today = new Date();


    }

    @FXML
    void weeklyButtonClicked(ActionEvent event) {

    }

    @FXML
    void monthlyButtonClicked(ActionEvent event) {

    }


}
