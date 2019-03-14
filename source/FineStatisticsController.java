/**
 * Controller class for the fine statistics.
 *
 * @author Ivan Andreev
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class FineStatisticsController extends Controller {

    @FXML
    private Button backButton;

    @FXML
    private Label numberOfFinesLabel;

    @FXML
    private Label biggestFineLabel;

    @FXML
    private Label amountOfFinesLabel;

    @FXML
    private Label amountPaidFinesLabel;

    private ArrayList<HistoryEntryFine> allFines;
    private ArrayList<HistoryEntryMoneyTransaction> finePayments;

    @FXML
    public void backButtonClicked(ActionEvent event) {
        new NewWindow("resources/LibrarianDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    @Override
    public void onStart() {
        calculateData();
    }

    private void calculateData() {
        allFines = new ArrayList<>();
        finePayments = new ArrayList<>();

        for (User user : getLibrary().getUserManager().getAllUsers()) {
            if (user instanceof NormalUser) {
                for (HistoryEntry historyEntry : ((NormalUser) user).getTransactionHistory().getHistory()) {
                    if (historyEntry instanceof HistoryEntryFine) {
                        allFines.add((HistoryEntryFine) historyEntry);
                    }

                    if (historyEntry instanceof HistoryEntryMoneyTransaction
                            && !(historyEntry instanceof HistoryEntryFine)) {
                        finePayments.add((HistoryEntryMoneyTransaction) historyEntry);
                    }
                }
            }
        }

        int numberOfFines = allFines.size();

        double biggestFine = 0;
        int totalAmountOfFines = 0;

        for (HistoryEntryFine fine : allFines) {
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


}