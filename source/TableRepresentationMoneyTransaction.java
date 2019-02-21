/**
 * Class to model the table representation of a money transactions.
 * @author Ivan Andreev
 */
public class TableRepresentationMoneyTransaction {

    /**
     * The type of the item the transaction happened on.
     */
    private String type;
    /**
     * The date the transaction happened on.
     */
    private String date;
    /**
     * The amount of the transaction.
     */
    private Double amount;
    /**
     * The ID of the item the transaction happened on.
     */
    private String itemID;
    /**
     * How many days overdue the item was.
     */
    private Integer daysOverdue;

    /**
     * Class to construct a table representing a money transaction using type,date and amount.
     *
     * @param type   The type of the item the transaction happened on.
     * @param date   The date the transaction happened on.
     * @param amount The amount of the transaction.
     */
    public TableRepresentationMoneyTransaction(String type, String date, Double amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    /**
     * Class to construct a table representing a money transaction using type, date, item ID and days overdue.
     *
     * @param type        The type of the item the transaction happened on.
     * @param date        The date the transaction happened on.
     * @param amount      The amount of the transaction.
     * @param itemID      The ID of the item the transaction happened on.
     * @param daysOverdue How many days overdue the item was.
     */
    public TableRepresentationMoneyTransaction(String type, String date, Double amount, String itemID, Integer daysOverdue) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.itemID = itemID;
        this.daysOverdue = daysOverdue;
    }

    /**
     * Gets the type of the item.
     *
     * @return The type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return The date of the transaction.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Gets the item ID of the item the transaction happened on.
     *
     * @return The item ID of the item the transaction happened on.
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Gets the days overdue the item was.
     *
     * @return The days overdue the item was.
     */
    public Integer getDaysOverdue() {
        return daysOverdue;
    }
}
