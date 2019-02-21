import java.util.Date;

/**
 * Models a fine type of history entry.
 *
 * @author Ivan Andreev
 */
public class HistoryEntryFine extends HistoryEntryMoneyTransaction {

    /**
     * The item that is overdue and caused the fine.
     */
    private Copy item;

    /**
     * The number of days the item was overdue.
     */
    private int daysOverdue;

    /**
     * Creates a fine type of history entry.
     *
     * @param date        The date of the fine.
     * @param amount      The amount due.
     * @param daysOverdue The number of days the item is overdue.
     * @param item        The item that was not returned on time.
     */
    public HistoryEntryFine(Date date, double amount, int daysOverdue, Copy item) {
        super(date, amount);
        this.daysOverdue = daysOverdue;
        this.item = item;
    }

    /**
     * Get the number of days an item is overdue.
     *
     * @return The number of days the item is overdue.
     */
    public int getDaysOverdue() {
        return daysOverdue;
    }

    /**
     * Gets the item not returned on time.
     *
     * @return The item that was not return on time.
     */
    public Copy getItem() {
        return item;
    }
}
