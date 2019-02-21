import java.util.Date;

/**
 * Models an item transaction type of history entry.
 *
 * @author Ivan Andreev
 */
public class HistoryEntryItemTransaction extends HistoryEntry {
    /**
     * Whether the item was borrowed.
     * True if it was borrowed, false if it was returned.
     */
    private boolean isBorrowed;

    /**
     * The person who borrowed the item.
     */
    private User borrowedBy;

    /**
     * Creates an item transaction type of history entry.
     *
     * @param date           The date of the item transaction.
     * @param isBorrowed     True if the item was borrowed, false if it was returned.
     * @param borrowedByUser The user who borrowed the item.
     */
    public HistoryEntryItemTransaction(Date date, boolean isBorrowed, User borrowedByUser) {
        super(date);
        this.isBorrowed = isBorrowed;
        this.borrowedBy = borrowedByUser;
    }

    /**
     * Get whether the item was borrowed.
     *
     * @return True if the item was borrowed, false if it was returned.
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * Gets the user that borrowed this item.
     *
     * @return The user that borrowed this item.
     */
    public User getBorrowedBy() {
        return borrowedBy;
    }
}
