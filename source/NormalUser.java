import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * This is used to make an account for a normal user
 *
 * @author Christina Meggs, Ivan Andreev
 */
public class NormalUser extends User implements Serializable {
    private double balance;
    private ArrayList<Copy> borrowedCopies;
    private ArrayList<Resource> newAdditions;
    private History transactionHistory;

    /**
     * Creates a normal users account with the inputted information.
     *
     * @param firstName        The users first name.
     * @param lastName         The users last name.
     * @param username         The users account username.
     * @param phoneNumber      The users phone number.
     * @param profileImagePath The users profile image.
     * @param address          The users home address.
     */
    public NormalUser(String firstName, String lastName, String username,
                      String phoneNumber, String profileImagePath, Address address) {
        super(firstName, lastName, username, phoneNumber, profileImagePath, address);
        this.balance = 0;
        this.transactionHistory = new History();
        this.borrowedCopies = new ArrayList<>();
        this.newAdditions = new ArrayList<>();
    }


    /**
     * Gets the user's account balance.
     *
     * @return The user's account balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gives the user a fine.
     * Also adjusts their account balance.
     *
     * @param amount The amount of the fine.
     */
    public void giveFine(double amount) {
        this.balance += amount;
    }

    /**
     * Pays off fine(s).
     * Also adjusts the account balance accordingly.
     *
     * @param amount The amount the user is paying off.
     */
    public void payFines(double amount) {

        this.balance -= amount;
        this.balance = Math.round(this.balance * 100.0) / 100.0;

        transactionHistory.addEntry(new HistoryEntryMoneyTransaction(new Date(), amount));
    }

    /**
     * Checks if the user has admin access.
     *
     * @return false Normal users don't have admin access.
     */
    public boolean hasAdminAccess() {
        return false;
    }

    /**
     * Gets the transaction history of the user.
     *
     * @return Transaction history of the user.
     */
    public History getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Gets an arrayList of all the borrowed copies.
     *
     * @return An arrayList of all the borrowed copies.
     */
    public ArrayList<Copy> getBorrowedCopies() {
        return borrowedCopies;
    }

    /**
     * Says if a user can borrow copies.
     *
     * @return true if a user can borrow a copy, otherwise - false.
     */
    public boolean canBorrowCopy() {
        return !(balance > 0 || hasOverdueCopies());
    }

    /**
     * Checks the arrayList of all borrowed copies for overdue copies.
     *
     * @return True if there are overdue copies,false otherwise.
     */
    private boolean hasOverdueCopies() {
        boolean hasOverdueCopies = false;
        Date today = new Date();
        for (Copy copy : borrowedCopies) {
            if (copy.getDueDate() != null && copy.getDueDate().compareTo(today) == -1) {
                hasOverdueCopies = true;
            }
        }
        return hasOverdueCopies;
    }

    public ArrayList<Resource> getNewAdditions() {
        return newAdditions;
    }

    /**
     * Gets the number of books borrowed by the users in between dates.
     *
     * @param fromDate The start date.
     * @param toDate   The end date.
     * @return Number of books borrowed between the start and the end date.
     */
    public int getNumberOfBooksBorrowedBetween(Date fromDate, Date toDate) {
        return 0;
    }
}