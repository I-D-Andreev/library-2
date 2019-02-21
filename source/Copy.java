import java.util.Date;
import java.io.*;

/**
 * Class to model a copy of a resource.
 *
 * @author Ivan Andreev
 */
public class Copy implements Serializable {

    /**
     * The unique ID of a copy.
     */
    private String uniqueCopyID;

    /**
     * What resource a copy is a copy of.
     */
    private Resource copyOf;

    /**
     * The copy manager of a copy.
     */
    private CopyManager copysManager;

    /**
     * The user who has borrowed this copy.
     */
    private User borrowedBy;

    /**
     * The user a copy is reserved for.
     */
    private User reservedFor;

    /**
     * The loan duration in days of a copy.
     */
    private int loanDurationInDays;

    /**
     * The date the copy was borrowed on.
     */
    private Date borrowedOn;

    /**
     * The date the copy is due to return.
     */
    private Date dueDate;

    /**
     * The history of loaning of the copy.
     */
    private History loanHistory;

    /**
     * The next unique ID of a copy.
     */
    private static int nextId;

    /**
     * The constructor of a Copy.
     *
     * @param loanDurationInDays The duration in days the copy was loaned.
     * @param copysManager       The copy manager of the copy.
     */
    public Copy(int loanDurationInDays, CopyManager copysManager) {

        this.copysManager = copysManager;
        this.copyOf = copysManager.getCopyManagerOf();
        this.loanDurationInDays = loanDurationInDays;
        this.loanHistory = new History();
        this.setUniqueCopyID();
        this.nullifyValues();
    }

    /**
     * Gets the unique ID of a copy.
     *
     * @return The unique copy ID of a copy.
     */
    public String getUniqueCopyID() {
        return uniqueCopyID;
    }

    /**
     * Gets the user the copy is borrowed by.
     *
     * @return The user that borrowed this copy.
     */
    public User getBorrowedBy() {
        return borrowedBy;
    }

    /**
     * Sets the user that has borrowed a copy.
     *
     * @param borrowedBy The new user that borrowed a copy.
     */
    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
        this.reservedFor = null;
    }

    /**
     * Gets the loan duration in days of a copy.
     *
     * @return The loan duration in days.
     */
    public int getLoanDurationInDays() {
        return loanDurationInDays;
    }

    /**
     * Sets the loan duration in days that a copy is loaned for.
     *
     * @param loanDurationInDays The duration in days of the loan.
     */
    public void setLoanDurationInDays(int loanDurationInDays) {
        this.loanDurationInDays = loanDurationInDays;
    }


    /**
     * Gets the due date of the copy.
     *
     * @return The due date of the copy.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the copy.
     */
    public void setDueDate() {
        // 3600 seconds in an hour, 24 hours a day, multiplied by 1000 to convert to milliseconds
        //final long oneDayInMilliseconds = 3600 * 24 * 1000;

        // one day changed to 15 seconds for testing and showcase purposes
        final long oneDayInMilliseconds = 15 * 1000;

        // get the current date and add one day, to get the date tomorrow
        Date tomorrowDate = new Date();
        tomorrowDate.setTime(tomorrowDate.getTime() + oneDayInMilliseconds);

        //add the loan duration to the date the copy was borrowed
        // to get the other possible due date
        Date dueDateLoan = new Date(borrowedOn.getTime());
        dueDateLoan.setTime(dueDateLoan.getTime() + loanDurationInDays * oneDayInMilliseconds);

        // The due date should be the bigger of the two
        this.dueDate = (tomorrowDate.compareTo(dueDateLoan) == 1) ? tomorrowDate : dueDateLoan;

    }

    /**
     * Gets the loan history of the copy.
     *
     * @return The loan history of the copy.
     */
    public History getLoanHistory() {
        return loanHistory;
    }

    /**
     * Gets the date the copy was borrowed on.
     *
     * @return The date the copy was borrowed on.
     */
    public Date getBorrowedOn() {
        return this.borrowedOn;
    }

    /**
     * Sets the date a copy was borrowed on.
     *
     * @param borrowedOn The new date the copy was borrowed on.
     */
    public void setBorrowedOn(Date borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    /**
     * Gets the user a copy is reserved for.
     *
     * @return The user the copy is reserved for.
     */
    public User getReservedFor() {
        return this.reservedFor;
    }

    /**
     * Sets the user this copy is reserved for.
     *
     * @param user The new user a copy is due for.
     */
    public void setReservedFor(User user) {
        this.reservedFor = user;
    }

    /**
     * Checks if the copy is available.
     *
     * @return true if the copy is not borrowed or reserved,false otherwise.
     */
    public boolean isAvailable() {
        return this.borrowedBy == null && this.reservedFor == null;
    }

    /**
     * Checks when the copy should be returned.
     *
     * @return The date the object is due,null otherwise..
     */
    public boolean shouldBeReturned() {
        return this.dueDate != null;
    }

    /**
     * Resets the attributes of the copy relating to being borrowed
     * and by who.
     */
    private void nullifyValues() {
        this.borrowedBy = null;
        this.reservedFor = null;
        this.borrowedOn = null;
        this.dueDate = null;
    }

    /**
     * Gets what resource copy this is.
     *
     * @return The resource this copy is a copy of.
     */
    public Resource getCopyOf() {
        return copyOf;
    }

    /**
     * What happens when a copy has just been returned.
     * @return The fine the user will get.
     */
    public double returnCopy() {
        NormalUser byUser = (NormalUser) this.borrowedBy;

        this.loanHistory.addEntry(new HistoryEntryItemTransaction(new Date(), false, byUser));
        byUser.getBorrowedCopies().remove(this);


        double fineAmount = 0;
        if (this.isOverdue()) {
            fineAmount = giveFineToUser(byUser);
        }

        // nullify the copy's values
        nullifyValues();

        // Notify the copy's copy manager that a new copy is available.
        this.copysManager.newAvailableCopyEvent();


        return fineAmount;
    }

    /**
     * Checks if the copy is overdue.
     *
     * @return true if the due date has been passed, false otherwise.
     */
    public boolean isOverdue() {
        Date today = new Date();
        // If today > due date, the item is overdue
        return ((this.dueDate != null) && (today.compareTo(this.dueDate) == 1));
    }

    /**
     * Sets a fine to a user.
     *
     * @param user The user to be fined.
     * @return How much a user was fined.
     */
    private double giveFineToUser(NormalUser user) {
        Date today = new Date();
        // 3600 seconds in an hour, 24 hours a day, multiplied by 1000 to convert to milliseconds
        //final long oneDayInMilliseconds = 3600 * 24 * 1000;

        // one day changed to 15 seconds for testing and showcase purposes
        final long oneDayInMilliseconds = 15 * 1000;

        long numberOfDaysOverdue = (today.getTime() - this.dueDate.getTime()) / oneDayInMilliseconds;

        double fineAmount = Math.min(this.copyOf.getMaxFineAmount(),
                this.copyOf.getLateReturnFinePerDay() * numberOfDaysOverdue);

        user.giveFine(fineAmount);
        user.getTransactionHistory().addEntry(new HistoryEntryFine(today, fineAmount,
                (int) numberOfDaysOverdue, this));

        return fineAmount;
    }

    /**
     * When the copy is loaned sets the date is was borrowed and by who.
     *
     * @param toUser The user that has borrowed the copy.
     * @return The loaned copy.
     */
    public Copy loanCopyTo(NormalUser toUser) {
        this.borrowedOn = new Date();
        this.setBorrowedBy(toUser);
        this.loanHistory.addEntry(new HistoryEntryItemTransaction(borrowedOn, true, toUser));
        toUser.getBorrowedCopies().add(this);
        return this;
    }

    /**
     * Sets each copy its unique ID.
     */
    private void setUniqueCopyID() {
        this.uniqueCopyID = this.copyOf.getUniqueID() + "-" + nextId;
        nextId++;
    }

    /**
     * Get the Copy's next id.
     *
     * @return The copy's next id.
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Set the copy's next id.
     *
     * @param nextId The copy's next id.
     */
    public static void setNextId(int nextId) {
        Copy.nextId = nextId;
    }

    /**
     * Checks if the copy is equal to the object passed.
     *
     * @param obj The object we are checking for.
     * @return true if the object passed is equal to the copies,
     * false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Copy)) return false;
        Copy o = (Copy) obj;
        return o.getUniqueCopyID().equals(this.getUniqueCopyID());
    }
}
