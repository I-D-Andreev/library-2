/**
 * Class that models the representation of a copy in a table.
 *
 * @author Ivan Andreev.
 */
public class TableRepresentationCopyInformation {

    /**
     * The unique copy ID.
     */
    private String copyID;

    /**
     * The name of the user that borrowed this copy.
     */
    private String borrowedBy;

    /**
     * The name of the copy.
     */
    private String resourceName;

    /**
     * The type of resource this copy is a copy of.
     */
    private String resourceType;

    /**
     * The date the copy was borrowed.
     */
    private String borrowDate;

    /**
     * The date the copy is due.
     */
    private String dueDate;

    /**
     * The days overdue the copy is.
     */
    private Integer daysOverdue;

    /**
     * Constructor for a copy representation in a table, containing its ID,name and type of copy it is.
     *
     * @param copyID       The unique copy ID.
     * @param resourceName The resource name this copy is a copy of.
     * @param resourceType The type of resource this copy is a copy of.
     */
    public TableRepresentationCopyInformation(String copyID, String resourceName, String resourceType) {
        this.copyID = copyID;
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    /**
     * Constructor for the table for a copy containing its ID,who borrowed it,its name and borrow date.
     *
     * @param copyID       The unique copy ID.
     * @param borrowedBy   The name of the user that borrowed this copy.
     * @param resourceName The name of the copy.
     * @param borrowDate   The date the copy was borrowed.
     */
    public TableRepresentationCopyInformation(String copyID, String borrowedBy, String resourceName, String borrowDate) {
        this.copyID = copyID;
        this.borrowedBy = borrowedBy;
        this.resourceName = resourceName;
        this.borrowDate = borrowDate;
    }

    /**
     * Constructor for a copy representation in a table,containing its ID, borrow date, due date and days overdue.
     *
     * @param copyID      The unique copy ID.
     * @param borrowDate  The date the copy was borrowed.
     * @param dueDate     The date the copy is due.
     * @param daysOverdue The days overdue the copy is.
     */
    public TableRepresentationCopyInformation(String copyID, String borrowDate, String dueDate, Integer daysOverdue) {
        this.copyID = copyID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.daysOverdue = daysOverdue;
    }

    /**
     * Constructor for a copy representation in a table containing, its ID, name, type, borrow date and due date.
     *
     * @param copyID       The unique copy ID.
     * @param resourceName The resource name this copy is a copy of.
     * @param resourceType The type of resource this copy is a copy of.
     * @param borrowDate   The date the copy was borrowed.
     * @param dueDate      The date the copy is due.
     */
    public TableRepresentationCopyInformation(String copyID, String resourceName,
                                              String resourceType, String borrowDate, String dueDate) {
        this(copyID, resourceName, resourceType);
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    /**
     * Constructor for a copy representation in a table containing, its ID, borrow date, due date, days overdue and
     * by whom it was borrowed.
     *
     * @param copyID      The unique copy ID.
     * @param borrowDate  The date the copy was borrowed.
     * @param dueDate     The date the copy is due.
     * @param daysOverdue The days overdue the copy is.
     * @param borrowedBy  The user that borrowed the copy.
     */
    public TableRepresentationCopyInformation(String copyID, String borrowDate,
                                              String dueDate, Integer daysOverdue, String borrowedBy) {
        this.copyID = copyID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.daysOverdue = daysOverdue;
        this.borrowedBy = borrowedBy;
    }

    /**
     * Gets the copy unique ID.
     *
     * @return The unique copy ID.
     */
    public String getCopyID() {
        return copyID;
    }

    /**
     * Gets the resource name this copy is a copy of.
     *
     * @return The resource name this copy is a copy of.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Gets the resource type this copy is a copy of.
     *
     * @return The resource type this copy is a copy of.
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Gets the date this copy was borrowed.
     *
     * @return The date this copy was borrowed.
     */
    public String getBorrowDate() {
        return borrowDate;
    }

    /**
     * Gets the date this copy is due.
     *
     * @return The date this copy is due.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Gets how many days overdue the copy is.
     *
     * @return The days the copy is overdue.
     */
    public Integer getDaysOverdue() {
        return daysOverdue;
    }

    /**
     * Gets the user that borrowed the copy.
     *
     * @return The user that borrowed the copy.
     */
    public String getBorrowedBy() {
        return borrowedBy;
    }
}
