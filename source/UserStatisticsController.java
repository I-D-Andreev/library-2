import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

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
    public void dailyButtonClicked(ActionEvent event) {
        statisticsLineChart.getData().clear();

        // we will show 7 days
        xAxis.setLabel("Day of the month");
        yAxis.setLabel("Number of resources");
        final int MILLISECONDS_IN_A_DAY = (24 * 3600 * 1000);


        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");
        Date today = new Date();


        for (int days = 6; days >= 0; days--) {

            // remove days from today
            Date date = new Date(today.getTime() - days * MILLISECONDS_IN_A_DAY);

            // remove days from the day of month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -days);

            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesOn(
                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(dayOfMonth), resourcesBorrowed));


        }
        statisticsLineChart.getData().add(chartSeries);
    }

    @FXML
    public void weeklyButtonClicked(ActionEvent event) {
        statisticsLineChart.getData().clear();

        // we will show 7 weeks back
        xAxis.setLabel("Week of year");
        yAxis.setLabel("Number of resources");
        final int MILLISECONDS_IN_A_WEEK = (7 * 24 * 3600 * 1000);


        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");
        Date today = new Date();


        for (int weeks = 6; weeks >= 0; weeks--) {

            // remove days from today
            Date date = new Date(today.getTime() - weeks * MILLISECONDS_IN_A_WEEK);

            // remove days from the day of month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, -weeks);

            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int resourcesBorrowed = 5;
//            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesOn(
//                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(weekOfYear), resourcesBorrowed));

            System.out.println(weekOfYear + " - " + resourcesBorrowed);

        }
        statisticsLineChart.getData().add(chartSeries);
    }

    @FXML
    public void monthlyButtonClicked(ActionEvent event) {

    }


}
