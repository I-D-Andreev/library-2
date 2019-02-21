/**
 * Class to model the table representation of an item transaction.
 *
 * @author Ivan Andreev.
 */
public class TableRepresentationItemTransaction {

    /**
     * Shows if the item is borrowed by a user or returned to the library.
     */
    private String borrowedOrReturned;
    /**
     * The username of the user that borrowed or returned the item.
     */
    private String username;
    /**
     * The date the user borrowed or returned the item.
     */
    private String date;

    /**
     * Constructor for the table of an item transaction.
     *
     * @param borrowedOrReturned Shows if the item is borrowed or returned to the library.
     * @param username           The username of the user that borrowed or returned the item.
     * @param date               The date the item was borrowed or returned.
     */
    public TableRepresentationItemTransaction(String borrowedOrReturned, String username, String date) {
        this.borrowedOrReturned = borrowedOrReturned;
        this.username = username;
        this.date = date;
    }

    /**
     * Gets the string that shows us whether the item was borrowed or returned by a user.
     *
     * @return The string that shows us whether the item was borrowed or returned by a user.
     */
    public String getBorrowedOrReturned() {
        return borrowedOrReturned;
    }

    /**
     * Gets the username of the user that borrowed or returned the item.
     *
     * @return The username of the user that borrowed or returned the item.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the date the item was borrowed or returned.
     *
     * @return The date the item was borrowed or returned.
     */
    public String getDate() {
        return date;
    }
}
