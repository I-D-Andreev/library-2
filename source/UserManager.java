import java.io.*;
import java.util.ArrayList;

/**
 * UserManager class.
 * This class manages all the users in the system.
 *
 * @author Ivan Andreev, Arran Taylor
 */
public class UserManager implements Serializable {
    /**
     * An arrayList with all the users.
     */
    private ArrayList<User> users;

    /**
     * Constructor for a user manager taking an arrayList of users.
     */
    public UserManager() {
        users = new ArrayList<>();
        this.selfPopulate();

        // assign next ID static variable to the user class
        User.setNextID(new SaveStaticVariables().getUserNextID());
    }

    /**
     * Saves the user manager data.
     */
    public void save() {
        File file = fileToReadWrite();

        try {
            // file writer
            // every time overwrite the file instead of just appending
            FileOutputStream fileOut = new FileOutputStream(file, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // save the object to the file
            out.writeObject(users);

            // close the file writer
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Problem creating a file writer.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Problem writing to file.");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Successfully written user in file.");
    }

    /**
     * Fills the user manager with data.
     */
    private void selfPopulate() {
        // Get a file to read from or create it if it doesn't exist.
        File file = fileToReadWrite();

        try {
            // file reader
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // read the object in the file
            ArrayList<User> readUsers = (ArrayList<User>) in.readObject();

            // close the reader
            in.close();
            fileIn.close();

            // assign the variable we just read
            this.users = readUsers;

        } catch (IOException e) {
            System.out.println("Couldn't access file to read from.");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Problem reading from file.");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Successfully read user from file.");
    }


    /**
     * Get a file to read/write or create it if it doesn't exist.
     *
     * @return The file to read/write.
     */
    private File fileToReadWrite() {
        File file = null;
        try {
            file = new File("source/resources/users.ser");
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Problem accessing file.");
            e.printStackTrace();
            System.exit(0);
        }
        return file;
    }

    /**
     * Adds a user into the user manager.
     *
     * @param user The user to be added.
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * Edit a user from the user manager.
     *
     * @param user        The user to be edited
     * @param firstName   The new first name of the user.
     * @param lastName    The new last name of the user.
     * @param phoneNumber The new phone number of the user.
     * @param imagePath   The new thumbnail image path of the user.
     * @param address     The new address of the user.
     */
    public void editUser(User user, String firstName, String lastName, String phoneNumber,
                         String imagePath, Address address) {

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setProfileImagePath(imagePath);
        user.setAddress(address);
    }

    /**
     * Removes a user from the user manager.
     *
     * @param user The user to be removed.
     */
    public void removeUser(User user) {
        this.users.remove(user);
    }

    /**
     * Removes a user from the user manager, using his unique ID.
     *
     * @param userId The unique ID of the user to be removed.
     */
    public void removeUser(String userId) {
        this.users.remove(this.getUserById(userId));
    }

    /**
     * Gets a user by his unique ID.
     *
     * @param userId The ID of the user to be returned.
     * @return The user if found, null otherwise.
     */
    public User getUserById(String userId) {
        User returnUser = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                returnUser = user;
            }
        }
        return returnUser;
    }

    /**
     * Gets a user by the username they use.
     *
     * @param username The username of the user to be found.
     * @return The user if he is found, null otherwise.
     */
    public User getUserByUsername(String username) {
        User returnUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                returnUser = user;
            }
        }

        return returnUser;
    }

    /**
     * An ArrayList that includes all the users.
     *
     * @return An ArrayList with all the users.
     */
    public ArrayList<User> getAllUsers() {
        return this.users;
    }

    /**
     * Allows a user to pay a fine.
     *
     * @param amount The amount to pay.
     * @param user   The user paying.
     */
    public void payFine(double amount, User user) {
        if (!user.hasAdminAccess()) {
            ((NormalUser) user).payFines(amount);
        }
    }


    /**
     * Fills the user manager with dummy data.
     */
    // Might be needed to rebuild the program if it is crashing.
    // More in the README file.
    public void selfPopulate1() {
        this.addUser(new NormalUser("Sian", "Pike", "sianspike",
                "07813931066", "", new Address("14 Bryn Y Mor Crescent",
                "Swansea", "Wales", "SA14QT")));

        this.addUser(new NormalUser("Sian", "Pike", "ivan",
                "07813931066", "", new Address("14 Bryn Y Mor Crescent",
                "Swansea", "Wales", "SA14QT")));

        this.addUser(new Librarian("Joe", "Bloggs", "librarian1",
                "12345678", "", new Address("Somewhere", "Cardiff", "Wales",
                "AB12CD")));
    }
}
