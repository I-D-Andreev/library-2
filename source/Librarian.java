import java.util.Date;

/**
 * This is used to make an account for a librarian
 *
 * @author Christina Meggs
 */
public class Librarian extends User {
    private String staffNumber;
    private Date employmentDate;

    /**
     * Creates a librarian account with the inputted information.
     *
     * @param firstName   The librarians first name.
     * @param lastName    The librarians last name.
     * @param username    The librarians account username.
     * @param phoneNumber The librarians phone number.
     * @param imagePath   The librarians profile image.
     * @param address     The librarians home address.
     */
    public Librarian(String firstName, String lastName,
                     String username, String phoneNumber, String imagePath, Address address) {
        super(firstName, lastName, username, phoneNumber, imagePath, address);
        this.setStaffNumber();

        // Sets the date to the current date.
        this.employmentDate = new Date();
    }

    /**
     * Gets staff number of the librarian.
     *
     * @return The librarians staff number.
     */
    public String getStaffNumber() {
        return this.staffNumber;
    }

    /**
     * Sets the staff number of the librarian.
     */
    private void setStaffNumber() {
        this.staffNumber = this.getId() + "-" + this.getUsername().charAt(0) + this.getFirstName().charAt(0);
    }

    /**
     * Checks whether librarian has admin access.
     *
     * @return True as librarians always have admin access.
     */
    public boolean hasAdminAccess() {
        return true;
    }

    /**
     * Gets the employment date of the librarian.
     *
     * @return Librarian's employment date.
     */
    public Date getEmploymentDate() {
        return this.employmentDate;
    }

}