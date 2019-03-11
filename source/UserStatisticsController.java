import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LineChart<String, Number> statisticsLineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

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
        xAxis.setLabel("Day of the month");
        yAxis.setLabel("Number of resources");


        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");
        Date today = new Date();


        for (int days = 6; days >= 0; days--) {

            // remove days from today
            Date date = new Date(today.getTime() - days * (24 * 3600 * 1000));

            // remove days from the day of month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -days);

            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesOn(
                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(dayOfMonth), resourcesBorrowed));

            System.out.println(dayOfMonth + " - " + resourcesBorrowed);

        }
        statisticsLineChart.getData().add(chartSeries);

    }

    @FXML
    void weeklyButtonClicked(ActionEvent event) {

    }

    @FXML
    void monthlyButtonClicked(ActionEvent event) {

    }


}
