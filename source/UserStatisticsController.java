import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Calendar;
import java.util.Date;

/**
 * Controller class for the user statistics window.
 * Handles what happens when the user interacts with the UI.
 *
 * @author Sian Pike, Ivan Andreev
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

    /**
     * The x axis of the chart. (the day)
     */
    // we are using String and not Number, because Numbers are automatically sorted
    // so a problem occurs when we want to display days that go back to previous month
    // e.g. we can't get 29, 30, 31, 1, 2, 3
    @FXML
    private CategoryAxis xAxis;

    /**
     * The y axis of the chart. (number of resources borrowed)
     */
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
     * Label to show the total resources borrowed.
     */
    @FXML
    private Label totalBorrowedLabel;

    /**
     * Closes the statistics window.
     *
     * @param event When the user clicks the ok button.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event,
                "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Handles clicking on the daily button - sets labels and fills chart with per day.
     *
     * @param event Clicking on the daily button.
     */
    @FXML
    public void dailyButtonClicked(ActionEvent event) {
        int totalResourcesBorrowed = 0;
        statisticsLineChart.getData().clear();

        // we will show 7 days
        xAxis.setLabel("Day of the month");
        yAxis.setLabel("Number of resources");

        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");

        for (int days = 6; days >= 0; days--) {

            // remove days from the day of month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -days);

            // get the date in a Date object
            Date date = calendar.getTime();

            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesOn(
                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);
            totalResourcesBorrowed += resourcesBorrowed;

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(dayOfMonth), resourcesBorrowed));

        }
        totalBorrowedLabel.setText(Integer.toString(totalResourcesBorrowed));
        statisticsLineChart.getData().add(chartSeries);

    }

    /**
     * Handles clicking on the weekly button - sets labels and fills chart with data per week.
     *
     * @param event Clicking on the weekly button.
     */
    @FXML
    public void weeklyButtonClicked(ActionEvent event) {
        int totalResourcesBorrowed = 0;
        statisticsLineChart.getData().clear();

        // we will show 7 weeks back
        xAxis.setLabel("Week of the year");
        yAxis.setLabel("Number of resources");


        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");


        for (int weeks = 6; weeks >= 0; weeks--) {

            // remove weeks from the week in a year
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, -weeks);

            // get the time as a date
            Date date = calendar.getTime();

            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesForTheWeek(
                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);
            totalResourcesBorrowed += resourcesBorrowed;

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(weekOfYear), resourcesBorrowed));

        }
        totalBorrowedLabel.setText(Integer.toString(totalResourcesBorrowed));
        statisticsLineChart.getData().add(chartSeries);
    }

    /**
     * Handles clicking on the monthly button - sets labels and fills chart with data per month.
     *
     * @param event Clicking on the monthly button.
     */
    @FXML
    public void monthlyButtonClicked(ActionEvent event) {
        int totalResourcesBorrowed = 0;
        statisticsLineChart.getData().clear();

        // we will show 7 months back
        xAxis.setLabel("Month of the year");
        yAxis.setLabel("Number of resources");


        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of resources borrowed");

        for (int months = 6; months >= 0; months--) {


            // remove 1 month from the month of the year
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -months);

            Date date = calendar.getTime();

            // months are represented 0-11 so we need to add +1 to show the real one
            int monthOfYear = calendar.get(Calendar.MONTH) + 1;

            int resourcesBorrowed = getLibrary().getResourceManager().getNumberOfBorrowedResourcesForTheMonth(
                    (NormalUser) getLibrary().getCurrentUserLoggedIn(), date);
            totalResourcesBorrowed += resourcesBorrowed;

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(monthOfYear), resourcesBorrowed));

        }
        totalBorrowedLabel.setText(Integer.toString(totalResourcesBorrowed));
        statisticsLineChart.getData().add(chartSeries);
    }


}
