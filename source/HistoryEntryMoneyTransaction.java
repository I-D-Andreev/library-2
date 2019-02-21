import java.util.Date;

/**
 * Models a money transaction type of history entry.
 *
 * @author Ivan Andreev
 */
public class HistoryEntryMoneyTransaction extends HistoryEntry {
    /**
     * The amount of money in the transaction.
     */
    private double amount;

    /**
     * Creates a money transaction type of history entry.
     *
     * @param date   The date of the money transaction.
     * @param amount The amount of the transaction.
     */
    public HistoryEntryMoneyTransaction(Date date, double amount) {
        super(date);
        this.amount = amount;
    }

    /**
     * Get the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }
}
