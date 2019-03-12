import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Controller class for the transaction history window.
 * Handles the interaction between the user and the UI.
 *
 * @author Sian Pike, Ivan Andreev
 */
public class TransactionHistoryController extends Controller {

    /**
     * Button to return to the dashboard.
     */
    @FXML
    private Button okButton;

    /**
     * Table that shows the transaction history.
     */
    @FXML
    private TableView<TableRepresentationMoneyTransaction> transactionHistoryTable;

    /**
     * Column showing the type of the resource.
     */
    @FXML
    private TableColumn<TableRepresentationMoneyTransaction, String> typeColumn;

    /**
     * Column showing the date of the transaction.
     */
    @FXML
    private TableColumn<TableRepresentationMoneyTransaction, String> dateColumn;

    /**
     * Table showing the amount of the transaction.
     */
    @FXML
    private TableColumn<TableRepresentationMoneyTransaction, Double> amountColumn;

    /**
     * Column showing the item ID.
     */
    @FXML
    private TableColumn<TableRepresentationMoneyTransaction, String> itemID;

    /**
     * Column showing the days overdue the item was.
     */
    @FXML
    private TableColumn<TableRepresentationMoneyTransaction, Integer> daysOverdue;

    /**
     * The data inside the table.
     */
    @FXML
    private ObservableList<TableRepresentationMoneyTransaction> data;


    /**
     * Returns the user back to the dashboard when the button is clicked.
     *
     * @param event The button is clicked.
     */
    @FXML
    public void okButtonClicked(ActionEvent event) {
        new NewWindow("resources/UserDashboard.fxml", event, "Dashboard - TaweLib", getLibrary());
    }

    /**
     * Initializes the table, fills it with data.
     */
    @Override
    public void onStart() {
        data = FXCollections.observableArrayList();
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationMoneyTransaction, String>("type"));

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationMoneyTransaction, String>("date"));

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationMoneyTransaction, Double>("amount"));

        itemID.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationMoneyTransaction, String>("itemID"));

        daysOverdue.setCellValueFactory(
                new PropertyValueFactory<TableRepresentationMoneyTransaction, Integer>("daysOverdue"));


        this.fillInData();
        transactionHistoryTable.getItems().addAll(data);
    }

    /**
     * Fills the data.
     */
    private void fillInData() {

        NormalUser currentlyLoggedIn = (NormalUser) getLibrary().getCurrentUserLoggedIn();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (HistoryEntry entry : currentlyLoggedIn.getTransactionHistory().getHistory()) {
            if (entry instanceof HistoryEntryFine) {
                String type = "fine";
                String date = dateFormat.format(entry.getDate());
                Double amount = ((HistoryEntryFine) entry).getAmount();
                String itemID = ((HistoryEntryFine) entry).getItem().getUniqueCopyID();
                Integer daysOverdue = ((HistoryEntryFine) entry).getDaysOverdue();

                data.add(new TableRepresentationMoneyTransaction(type, date, amount, itemID, daysOverdue));
            } else if (entry instanceof HistoryEntryMoneyTransaction) {
                data.add(new TableRepresentationMoneyTransaction("payment",
                        dateFormat.format(entry.getDate()),
                        ((HistoryEntryMoneyTransaction) entry).getAmount()));
            }
        }
    }

}