/**
 * Class that models the representation of an available copy in a table.
 *
 * @author Ivaan Andreev.
 */
public class TableRepresentationCopyAvailable {

    /**
     *  The unique copy ID.
     */
    private String uniqueCopyID;

    /**
     * If the copy is available.
     */
    private String isAvailable;

    /**
     * Who borrowed the copy.
     */
    private String borrowedBy;

    /**
     * ho it is reserved for.
     */
    private String reservedFor;

    /**
     * Constructor for the table using unique copy ID and if it is available.
     *
     * @param uniqueCopyID The unique copy ID.
     * @param isAvailable  true if copy is available, false otherwise.
     */
    public TableRepresentationCopyAvailable(String uniqueCopyID, String isAvailable) {
        this.uniqueCopyID = uniqueCopyID;
        this.isAvailable = isAvailable;
    }

    /**
     * Constructor for the table representation of a copy using unique copy ID, if it is available, who borrowed it
     * and if it is reserved.
     *
     * @param uniqueCopyID The unique copy ID.
     * @param isAvailable  If the copy is available.
     * @param borrowedBy   Who borrowed the copy.
     * @param reservedFor  Who it is reserved for.
     */
    public TableRepresentationCopyAvailable(String uniqueCopyID, String isAvailable, String borrowedBy, String reservedFor) {
        this(uniqueCopyID, isAvailable);
        this.borrowedBy = borrowedBy;
        this.reservedFor = reservedFor;
    }

    /**
     * Gets the unique copy ID.
     *
     * @return The unique copy ID.
     */
    public String getUniqueCopyID() {
        return uniqueCopyID;
    }

    /**
     * Gets if a copy is available as a string.
     *
     * @return true if it is available, false otherwise.
     */
    public String getIsAvailable() {
        return isAvailable;
    }

    /**
     * Gets who the copy is borrowed by.
     *
     * @return The user that borrowed the copy.
     */
    public String getBorrowedBy() {
        return borrowedBy;
    }

    /**
     * Gets who the copy is reserved for.
     *
     * @return The user the copy is reserved for.
     */
    public String getReservedFor() {
        return reservedFor;
    }
}
