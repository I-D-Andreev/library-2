/**
 * Controller class for the fine statistics.
 *
 * @author Ivan Andreev
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FineStatisticsController extends Controller {

    /**
     * Button to go back to the dashboard.
     */
    @FXML
    private Button backButton;

    /**
     * A label presenting the number of fines.
     */
    @FXML
    private Label numberOfFinesLabel;

    /**
     * A label presenting the amount of the biggest fine.
     */
    @FXML
    private Label biggestFineLabel;

    /**
     * A label presenting the total amount of fines.
     */
    @FXML
    private Label amountOfFinesLabel;

    /**
     * A label representing the total amount of money paid for fines.
     */
    @FXML
    private Label amountPaidFinesLabel;

    /**
     * The chart to represent fines.
     */
    @FXML
    private LineChart<String, Number> finesChart;

    /**
     * The x axis of the chart. (the day number)
     */
    // we are using String and not Number, because Numbers are automatically sorted
    // so a problem occurs when we want to display days that go back to previous month
    // e.g. we can't get 29, 30, 31, 1, 2, 3 (they will be sorted)
    @FXML
    private CategoryAxis xAxis;

    /**
     * The y axis of the chart. (The number of fines.)
     */
    @FXML
    private NumberAxis yAxis;

    /**
     * List of all fines.
     */
    private ArrayList<HistoryEntryFine> listOfAllFines;

    /**
     * List of all fine payments.
     */
    private ArrayList<HistoryEntryMoneyTransaction> finePayments;

    /**
     * Handles clicking on the back button - gets us back to dashboard.
     * @param event Clicking on the button.
     */
    @FXML
    public void backButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Actions to be executed on the window loading.
     * Calculates the data and fills in the labels and the chart.
     */
    @Override
    public void onStart() {
        // get all the fines and the fine payments
        calculateData();

        // fill in the labels
        fillInData();

        // fill in the chart
        fillChart();
    }

    /**
     * Calculates the list of fine payments and the list of all fines.
     */
    private void calculateData() {
        listOfAllFines = new ArrayList<>();
        finePayments = new ArrayList<>();

        for (User user : getLibrary().getUserManager().getAllUsers()) {
            if (user instanceof NormalUser) {
                for (HistoryEntry historyEntry : ((NormalUser) user).getTransactionHistory().getHistory()) {
                    if (historyEntry instanceof HistoryEntryFine) {
                        listOfAllFines.add((HistoryEntryFine) historyEntry);
                    }

                    if (historyEntry instanceof HistoryEntryMoneyTransaction
                            && !(historyEntry instanceof HistoryEntryFine)) {
                        finePayments.add((HistoryEntryMoneyTransaction) historyEntry);
                    }
                }
            }
        }

    }

    /**
     * Fills in the labels.
     */
    private void fillInData() {
        int numberOfFines = listOfAllFines.size();

        double biggestFine = 0;
        int totalAmountOfFines = 0;

        for (HistoryEntryFine fine : listOfAllFines) {
            biggestFine = Math.max(fine.getAmount(), biggestFine);
            totalAmountOfFines += fine.getAmount();
        }

        int totalAmountOfFinesPaid = 0;
        for (HistoryEntryMoneyTransaction finePayment : finePayments) {
            totalAmountOfFinesPaid += finePayment.getAmount();
        }

        // set the labels
        numberOfFinesLabel.setText(Integer.toString(numberOfFines));
        amountOfFinesLabel.setText(Integer.toString(totalAmountOfFines) + "£");
        biggestFineLabel.setText(Double.toString(biggestFine) + "£");
        amountPaidFinesLabel.setText(Integer.toString(totalAmountOfFinesPaid) + "£");

    }


    /**
     * Fills in the chart.
     */
    private void fillChart() {
        finesChart.getData().clear();

        // we will show 7 days
        xAxis.setLabel("Day of the month");
        yAxis.setLabel("Number of fines");

        XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();
        chartSeries.setName("Number of fines incurred");

        for (int days = 6; days >= 0; days--) {

            // remove days from the day of month
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -days);

            // get the date in a Date object
            Date date = calendar.getTime();

            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int numberOfFinesIncurred = getLibrary().getResourceManager().getNumberOfFinesOn(listOfAllFines, date);

            chartSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(dayOfMonth), numberOfFinesIncurred));
        }

        finesChart.getData().add(chartSeries);

    }

}